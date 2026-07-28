# Schizogenic backend

This directory contains the Spring Boot backend for Schizogenic, an
opinionated personal organization system.

The current bootstrap establishes a minimal application and its build. Product
endpoints, persistence, authentication, and other application behavior are
outside this bootstrap's scope.

## Toolchain

| Component | Version |
| --- | --- |
| Java | 25 |
| Spring Boot | 4.1.0 |
| Gradle wrapper | 9.5.1 |

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

## Run

Start the application locally:

```bash
./gradlew bootRun
```

Stop it with `Ctrl+C`. The bootstrap application does not require PostgreSQL
or other external services.

## Test

Run the backend tests from a clean state:

```bash
./gradlew clean test
```

The HTML test report is written to `build/reports/tests/test/index.html`.

From the repository root, run the complete repository verification:

```bash
just check
```

## Architecture decision

The build DSL and initial project layout must be justified in
[ADR 0001](../../docs/engineering/decisions/0001-backend-build-and-layout.md).
