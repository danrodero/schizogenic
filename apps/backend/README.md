# Schizogenic backend

This directory contains the Spring Boot backend for Schizogenic, an
opinionated personal organization system.

The current foundation establishes a minimal application, its build, and its
PostgreSQL migration boundary. Product endpoints, domain persistence,
authentication, and other application behavior remain outside this scope.

## Toolchain

| Component | Version |
| --- | --- |
| Java | 25 |
| Spring Boot | 4.1.0 |
| Gradle wrapper | 9.6.1 |
| PostgreSQL | 18 |
| Schema migrations | Flyway |

Enter the repository's Nix development environment before using the commands
below:

```bash
nix develop
```

## Build

From this directory, clean previous outputs, compile the code, run the tests,
and package the application:

```bash
./gradlew clean build
```

## Database configuration

The backend requires PostgreSQL and runs Flyway migrations during application
startup. Configuration is externalized through these environment variables:

| Variable | Development default | Purpose |
| --- | --- | --- |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:${PGPORT}/${PGDATABASE}` | Complete JDBC connection URL |
| `SPRING_DATASOURCE_USERNAME` | `${PGUSER}` | Database role |
| `SPRING_DATASOURCE_PASSWORD` | Empty | Database password |
| `PGPORT` | `5432` | Port used to construct the default URL |
| `PGDATABASE` | `schizogenic_dev` | Database used to construct the default URL |
| `PGUSER` | `schizogenic` | Role used as the default username |

The Nix development shell supplies the documented `PG*` defaults. Override the
Spring datasource variables in environments that need a different connection.
Do not commit passwords or `.env` files.

## Run

From the repository root, prepare the local PostgreSQL cluster and database:

```bash
just db-init
just db-start
just db-create
```

Then start the backend:

```bash
cd apps/backend
./gradlew bootRun
```

Stop the application with `Ctrl+C`. From the repository root, stop PostgreSQL
with `just db-stop` when it is no longer needed.

Flyway records successful migrations in `flyway_schema_history`. Restarting
the backend against the same database validates that history and skips versions
that are already applied. Never edit a successfully applied migration; add a
new versioned migration for the correction.

If PostgreSQL is unavailable, connection settings are invalid, a migration
fails, or an applied migration's checksum no longer matches, startup must fail.
Correct the unavailable connection or failing unapplied script and retry. Do
not use Flyway `repair` merely to bypass an unexplained integrity error.

## Test

Run the backend tests from a clean state:

```bash
./gradlew clean test
```

The migration integration tests use `initdb` and `pg_ctl` from the Nix shell
to start a temporary PostgreSQL 18 cluster on loopback with a temporary port.
Each test gets a fresh database. The suite stops its cluster and removes its
temporary files after success or failure. It never uses the local database
created by `just db-*`; Docker and a running development database are not needed.
Run tests as a regular user, because PostgreSQL refuses to run as root.

The tests prove first application-context startup creates `init(id INT)` and
one successful V1 history entry; a second startup preserves history and data.
They also prove altered migration content, an unavailable database, and an
invalid role prevent startup without logging the test password. These tests
exercise application-context startup, not HTTP requests.

`init` is a deliberately empty, non-product migration marker. Application code
must not store product data in it. A future migration may remove it when a
confirmed product schema replaces this foundation.

The HTML test report is written to `build/reports/tests/test/index.html`.

From the repository root, run the complete repository verification:

```bash
just check
```

## Architecture decisions

The build DSL and initial project layout must be justified in
[ADR 0001](../../docs/engineering/decisions/0001-backend-build-and-layout.md).
The PostgreSQL migration policy and test strategy are documented in
[ADR 0002](../../docs/engineering/decisions/0002-postgresql-migrations-with-flyway.md).
