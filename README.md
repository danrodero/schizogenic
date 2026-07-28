# Schizogenic

Schizogenic is an opinionated personal organization system and a deliberate
software-engineering apprenticeship.

The first product module is time tracking, with a responsive web interface and
a CLI. The repository is also a learning environment: the developer implements
the product, while an AI agent acts as lead developer by refining requirements,
assigning appropriately sized work, giving bounded hints, and reviewing pull
requests against production standards.

No application has been generated yet. This repository currently contains only
the development environment, documentation system, and collaboration workflow.

## Working agreement

- The human developer owns all feature code and its initial tests.
- The lead agent may edit product and engineering documentation, create
  assignments, review pull requests, and maintain the learning records.
- The lead agent must not implement assigned features or disclose complete
  solutions.
- A pull request is approved only when it is production-ready for its agreed
  scope.
- Demonstrated skills and coaching outcomes are recorded from review evidence,
  not from self-assessment or assumptions.

See [AGENTS.md](AGENTS.md) for the complete collaboration contract.

## Planned stack

- Java 25
- Spring Boot 4.x
- TypeScript and React
- PostgreSQL
- Nix flakes for the development environment

Framework dependencies will be added by the developer as part of explicit
learning assignments. The flake supplies language runtimes, build bootstrap
tools, database tooling, and developer utilities.

## Repository layout

```text
apps/
  backend/              Java and Spring Boot API (reserved)
  web/                  TypeScript and React client (reserved)
  cli/                  Command-line client (reserved)
docs/
  product/              Vision, specification, and user stories
  engineering/          Architecture, standards, workflow, and ADRs
  learning/             Evidence-based competency profile and review log
.agents/skills/         Lead-agent workflows
.github/                Contribution and assignment templates
```

Start with the [documentation index](docs/README.md) and the
[product specification](docs/product/SPEC.md).

## Enter the development environment

Prerequisites:

1. Install Nix with flakes enabled.
2. Clone the repository.
3. From the repository root, run:

```bash
nix develop
just doctor
```

If `direnv` is installed, run `direnv allow` once and the flake will load when
you enter the directory.

The pinned shell provides Java 25, Gradle 9.5, Node.js 24 LTS, pnpm,
PostgreSQL 18, language servers, Git, GitHub CLI, and repository utilities.
Once a Java project exists, its committed Gradle wrapper (`./gradlew`) becomes
the canonical build command; the shell Gradle is only for bootstrapping it.

## Local PostgreSQL

Inside `nix develop`:

```bash
just db-init
just db-start
just db-create
just db-status
just db-stop
```

The cluster is stored under `.local/state/postgres` and uses trust
authentication only for local development. It is not a production database
configuration.

## Typical learning cycle

1. Discuss the product and refine the specification.
2. Ask the lead agent for one next task.
3. Implement the task and its tests yourself.
4. Open a pull request and request review.
5. Address review findings until the code is production-ready.
6. The lead agent approves the pull request and records demonstrated growth.

Use the pull request and issue templates so acceptance criteria, verification,
and learning evidence remain visible.

## Status

Bootstrap phase: environment and documentation only. Product implementation
has intentionally not started.

## License

[MIT](LICENSE)
