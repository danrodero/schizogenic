# Development environment

## Source of truth

`flake.nix` declares the development tools and supported systems. `flake.lock`
pins the exact nixpkgs revision. Both files must be reviewed and committed
together when the input changes.

The declared systems are:

- Linux on x86-64.
- Linux on AArch64.
- macOS on Apple Silicon.

The pinned nixpkgs revision no longer supports Intel macOS, so the flake does
not claim that target.

## Current toolchain

| Tool | Pinned version | Purpose |
| --- | --- | --- |
| OpenJDK | 25.0.4 | Backend language and runtime |
| Gradle | 9.5.1 | Bootstrap tool until the project wrapper exists |
| Node.js | 24.18.0 LTS | Web tooling runtime |
| pnpm | 11.17.0 | JavaScript package manager |
| PostgreSQL | 18.4 | Local database server and client |

Spring Boot's exact 4.x version will be chosen in the backend initialization
assignment. It must be compatible with Java 25 and the selected Gradle wrapper.

Compatibility references:

- [Spring Boot system requirements](https://docs.spring.io/spring-boot/system-requirements.html)
- [Gradle Java compatibility](https://docs.gradle.org/current/userguide/compatibility.html)
- [Node.js release status](https://nodejs.org/en/about/previous-releases)
- [PostgreSQL versioning policy](https://www.postgresql.org/support/versioning/)

## Commands

Enter and verify the environment:

```bash
nix develop
just doctor
just check
```

Format the flake and lint documentation:

```bash
just format-nix
just lint-docs
```

Use the database recipes documented in the root README for local PostgreSQL.
They place data in an ignored `.local` directory and configure trust
authentication. Never reuse that configuration outside local development.

## Upgrade policy

Upgrade tools through a focused maintenance issue and pull request:

1. Read official runtime, framework, and build-tool compatibility notes.
2. Update the flake input or explicit package selection.
3. Refresh `flake.lock` intentionally.
4. Run `nix flake check --all-systems` for all declared outputs.
5. Run `just doctor`, documentation checks, and every application build and
   test once those projects exist.
6. Record breaking choices or migration consequences in an ADR when durable.

Do not update the lock file incidentally during unrelated feature work.
