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
| Gradle | 9.5.1 | Environment diagnostics and project bootstrapping |
| Node.js | 24.18.0 LTS | Web tooling runtime |
| pnpm | 11.17.0 | JavaScript package manager |
| PostgreSQL | 18.4 | Local database server and client |

The backend currently pins Spring Boot 4.1.0 and a Gradle 9.6.1 wrapper. Use
the committed wrapper for backend builds; the Nix-provided Gradle remains
available for diagnostics and project bootstrapping.

Compatibility references:

- [Spring Boot system requirements](https://docs.spring.io/spring-boot/system-requirements.html)
- [Gradle Java compatibility](https://docs.gradle.org/current/userguide/compatibility.html)
- [Node.js release status](https://nodejs.org/en/about/previous-releases)
- [PostgreSQL versioning policy](https://www.postgresql.org/support/versioning/)

## Commands

Enter and verify the environment:

```nu
nix develop path:. -c nu
just doctor
just check
```

Format the flake and lint documentation:

```nu
just format-nix
just lint-docs
```

Use the database recipes documented in the root README for local PostgreSQL.
They place data in an ignored `.local` directory and configure trust
authentication. Never reuse that configuration outside local development.

## Git and GitHub identities

The repository deliberately uses separate identities:

- Human development sessions run as `louie`. Entering the Nix environment
  removes inherited `GH_TOKEN` and `GITHUB_TOKEN` values, selects the existing
  `~/.config/gh-danrodero` login, configures commits as Dan Rodero, and sets the
  push URL to SSH.
- Agent sessions run as `harness`. They retain the injected
  `clawstopher-moltosanti` credentials for review and agent-owned maintenance.
  Agent commits override the repository-local Git author explicitly and use the
  configured GitHub ruleset bypass for their own pull requests.

This keeps ordinary developer `git commit`, `git push`, and `gh` commands on the
`danrodero` account inside the Nix environment. The agent never needs or uses
the developer's GitHub configuration or SSH key. The developer never commits or
publishes agent-authored files.

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
