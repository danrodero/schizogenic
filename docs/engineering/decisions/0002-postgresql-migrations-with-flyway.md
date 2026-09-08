# ADR 0002: PostgreSQL schema migrations with Flyway

Status: Proposed

Date: 2026-08-10

Owners: Human developer and lead agent

## Context

PostgreSQL is the system of record, so its schema needs a reproducible history
that can be applied from a clean database and validated on later application
starts. The project is still establishing its persistence boundary and has no
product schema. At this stage, a small SQL-first mechanism is preferable to a
broader database-change abstraction.

The migration boundary must preserve applied history, fail application startup
when the database or migration state is invalid, and exercise real PostgreSQL
behavior without coupling automated tests to a developer's local database.

## Decision

Use Flyway through Spring Boot to own PostgreSQL schema evolution. Versioned
SQL migrations live under `classpath:db/migration/postgresql` and follow
Flyway's `V<version>__<description>.sql` naming convention. Spring Boot runs
Flyway during application-context startup, before application code may rely on
the schema.

An applied migration is immutable. Flyway records its version and checksum in
`flyway_schema_history` and validates them on later runs. After a migration has
been shared or applied successfully, correct it with a new forward migration;
do not edit the applied file or use `repair` to conceal an unexpected mismatch.

For PostgreSQL migrations that Flyway executes transactionally, a failed
migration rolls back its transactional changes and prevents application
startup. A failing migration that has not been applied successfully may be
fixed and `migrate` retried. Operational recovery must inspect both the
database state and Flyway history rather than assume that every possible
database operation is transactional.

The baseline is the empty, non-product table `init(id INT)` plus Flyway's
successful V1 history entry. It establishes observable migration behavior and
is not a domain model.

Integration tests use a disposable PostgreSQL 18 cluster started with the
Nix-provided `initdb` and `pg_ctl` tools.
They must establish observable migration behavior, including clean-database
initialization and a safe repeat run, without reading or modifying a personal
development database. Each test uses a fresh database; suite teardown stops
the cluster and JUnit removes its temporary directory. Migration-integrity validation must also have automated
or explicitly recorded verification evidence.

## Alternatives considered

- **Liquibase:** supports SQL and structured changelogs plus a wider set of
  change-management features. That flexibility adds concepts and configuration
  the project does not currently need.
- **Application-managed or manually applied SQL:** minimizes dependencies, but
  would require the project to build or operate its own ordering, history,
  checksum validation, and startup integration. That is more risk and
  maintenance than adopting a focused migration tool.

Flyway was selected because its versioned, SQL-first workflow fits the current
team knowledge and project scope while retaining explicit PostgreSQL SQL.

## Consequences

- Schema history is ordered, reviewable SQL and is reproduced at application
  startup.
- Application startup fails when PostgreSQL is unavailable, connection
  configuration is invalid, a migration fails, or validation detects changed
  applied migration content.
- Developers must treat successfully applied migrations as append-only and
  use a later version for corrections.
- Automated integration tests require the Nix development shell and a non-root
  user, but no Docker runtime or separately configured test database. The
  temporary cluster accepts trusted connections only on loopback and is test
  infrastructure, never a deployment configuration.
- PostgreSQL-specific SQL is an intentional dependency. Supporting another
  database would require separate migrations and validation.
- Production rollout, backup, and restore procedures remain separate future
  decisions; introducing Flyway does not define them.

## Validation

The decision is validated when repository verification proves migration of a
clean PostgreSQL 18 database and repeat startup against the same migrated
state, while an altered applied migration is rejected by checksum validation.
Automated verification must also show credential-safe startup failure when
PostgreSQL is unavailable or connection configuration is invalid.

Revisit this decision if Flyway cannot express a required rollout safely, the
project needs coordinated changes across database technologies, or operational
evidence shows that a more capable change-management workflow is warranted.
