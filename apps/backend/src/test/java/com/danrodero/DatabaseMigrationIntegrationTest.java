package com.danrodero;

import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Uses the flake's PostgreSQL binaries, never the developer's database. */
@ExtendWith(OutputCaptureExtension.class)
class DatabaseMigrationIntegrationTest {
    @TempDir
    static Path temporary;
    static Path cluster;
    static int port;
    String url;

    @BeforeAll
    static void startPostgres() throws Exception {
        cluster = temporary.resolve("data");
        run("initdb", "-D", cluster.toString(), "-U", "postgres", "--auth=trust", "--no-locale", "-E", "UTF8");
        try (var socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
        }
        // Empty unix_socket_directories avoids machine-specific socket paths.
        try {
            run("pg_ctl", "-D", cluster.toString(), "-l", temporary.resolve("postgres.log").toString(),
                "-o", "-h 127.0.0.1 -p " + port + " -k ''", "-w", "-t", "30", "start");
        } catch (Exception failure) {
            stopPostgres();
            throw failure;
        }
    }

    @AfterAll
    static void stopPostgres() throws Exception {
        if (cluster != null && Files.exists(cluster.resolve("postmaster.pid"))) {
            run("pg_ctl", "-D", cluster.toString(), "-m", "immediate", "-w", "-t", "30", "stop");
        }
    }

    @BeforeEach
    void freshDatabase() throws Exception {
        String database = "test_" + UUID.randomUUID().toString().replace("-", "");
        try (var connection = DriverManager.getConnection(jdbcUrl("postgres"), "postgres", "");
             var statement = connection.createStatement()) {
            statement.execute("CREATE DATABASE " + database);
        }
        url = jdbcUrl(database);
    }

    @Test
    void startupMigratesCleanDatabaseAndRepeatStartupPreservesHistoryAndData() throws Exception {
        try (var context = start(url, "postgres")) {
            assertThat(context.isActive()).isTrue();
            assertThat(query("SELECT version FROM flyway_schema_history WHERE success")).isEqualTo(List.of("1"));
            assertThat(query("SELECT id FROM init")).isEmpty();
        }
        execute("INSERT INTO init (id) VALUES (42)");
        var history = query("SELECT installed_rank || ':' || version || ':' || checksum || ':' || installed_on FROM flyway_schema_history ORDER BY installed_rank");
        try (var context = start(url, "postgres")) {
            assertThat(context.isActive()).isTrue();
            assertThat(query("SELECT id FROM init")).isEqualTo(List.of("42"));
            assertThat(query("SELECT installed_rank || ':' || version || ':' || checksum || ':' || installed_on FROM flyway_schema_history ORDER BY installed_rank"))
                .isEqualTo(history);
        }
    }

    @Test
    void changedAppliedMigrationPreventsStartup() throws Exception {
        try (var context = start(url, "postgres")) {
            assertThat(context.isActive()).isTrue();
        }
        Path altered = Files.createDirectory(temporary.resolve("altered"));
        // Change a temporary copy; never modify a migration in the checkout.
        Files.writeString(altered.resolve("V1__init.sql"), "CREATE TABLE init (id BIGINT);\n");
        assertThatThrownBy(() -> {
            try (var ignored = start(url, "postgres", "--spring.flyway.locations=filesystem:" + altered)) {
                // Close the context even if a regression unexpectedly allows startup.
            }
        }).hasStackTraceContaining("checksum mismatch");
        assertThat(query("SELECT data_type FROM information_schema.columns WHERE table_name = 'init' AND column_name = 'id'"))
            .isEqualTo(List.of("integer"));
    }

    @Test
    void unavailableDatabasePreventsStartupWithoutLoggingPassword(CapturedOutput output) throws Exception {
        // Reserve a port without accepting PostgreSQL connections for the entire attempt.
        try (var socket = new ServerSocket(0)) {
            String unavailable = "jdbc:postgresql://127.0.0.1:" + socket.getLocalPort() + "/missing?connectTimeout=1&socketTimeout=1";
            assertThatThrownBy(() -> {
                try (var ignored = start(unavailable, "postgres")) { }
            }).hasStackTraceContaining("org.postgresql");
        }
        assertThat(output.getAll()).doesNotContain("test-only-password-marker");
    }

    @Test
    void invalidDatabaseRolePreventsStartupWithoutLoggingPassword(CapturedOutput output) {
        assertThatThrownBy(() -> {
            try (var ignored = start(url, "missing_role")) { }
        }).hasStackTraceContaining("role \"missing_role\" does not exist");
        assertThat(output.getAll()).doesNotContain("test-only-password-marker");
    }

    private ConfigurableApplicationContext start(String databaseUrl, String username, String... extra) {
        var args = new ArrayList<>(List.of(
            "--spring.datasource.url=" + databaseUrl,
            "--spring.datasource.username=" + username,
            "--spring.datasource.password=test-only-password-marker",
            "--spring.datasource.hikari.connection-timeout=1000",
            "--spring.datasource.hikari.initialization-fail-timeout=1",
            "--spring.flyway.connect-retries=0",
            "--spring.flyway.locations=classpath:db/migration/postgresql",
            "--spring.main.banner-mode=off"));
        for (String argument : extra) {
            String option = argument.substring(0, argument.indexOf('=') + 1);
            args.removeIf(existing -> existing.startsWith(option));
            args.add(argument);
        }
        return new SpringApplicationBuilder(SchizogenicBackendApplication.class)
            .web(WebApplicationType.NONE).run(args.toArray(String[]::new));
    }

    private static String jdbcUrl(String database) {
        return "jdbc:postgresql://127.0.0.1:" + port + "/" + database;
    }

    private List<String> query(String sql) throws Exception {
        try (var connection = DriverManager.getConnection(url, "postgres", "");
             var statement = connection.createStatement();
             var rows = statement.executeQuery(sql)) {
            var values = new ArrayList<String>();
            while (rows.next()) values.add(rows.getString(1));
            return values;
        }
    }

    private void execute(String sql) throws Exception {
        try (var connection = DriverManager.getConnection(url, "postgres", "");
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static void run(String... command) throws Exception {
        Path log = Files.createTempFile(temporary, "postgres-command-", ".log");
        var process = new ProcessBuilder(command).redirectErrorStream(true).redirectOutput(log.toFile()).start();
        if (!process.waitFor(40, TimeUnit.SECONDS)) {
            process.destroyForcibly().waitFor();
            throw new IllegalStateException(command[0] + " timed out; see " + log);
        }
        if (process.exitValue() != 0) {
            throw new IllegalStateException(command[0] + " failed: " + Files.readString(log));
        }
    }
}
