# Review and coaching log

This file is append-only. The lead agent adds one concise entry after a
substantive pull request reaches approval. Corrections should be appended and
linked rather than rewriting historical evidence.

There is no implementation evidence yet. The bootstrap files are agent-authored
and must not be treated as evidence of developer competency.

## 2026-07-28 — PR #4: Initialize the Java 25 Spring Boot backend

- Assignment: [#3](https://github.com/danrodero/schizogenic/issues/3)
- Scope completed: initialized the Java 25 and Spring Boot 4.1 backend with a
  committed Gradle wrapper, a startup smoke test, developer documentation, and
  a build-and-layout decision record.
- Demonstrated independently: selected a focused single-project build,
  configured the required tool versions and dependency management, wrote the
  initial Spring context test, integrated it into repository verification, and
  kept product and database behavior outside the bootstrap.
- Demonstrated with guidance: calibrated the claim made by the context test and
  identified that the pull-request documentation section must reflect
  documentation actually changed.
- Production feedback taught: a context-startup test does not prove that a
  network listener accepts requests, and verification claims must name the
  boundary they establish.
- Tests and verification observed: the committed wrapper, `just check`, clean
  application startup, and the developer-authored Spring context test were
  verified at the approved commit.
- Continue practicing: precise behavioral assertions, complete pull-request
  evidence, and clearer decision language and consequences.
- Appropriate next stretch: connect the backend to real PostgreSQL through a
  migration lifecycle and test that external boundary deterministically.
- Evidence: [PR #4](https://github.com/danrodero/schizogenic/pull/4),
  [approved commit](https://github.com/danrodero/schizogenic/commit/79bf4639e34e15fb0243dfb3d7a2226ce460bc4c)

## Entry template

```markdown
## YYYY-MM-DD — PR #NNN: Title

- Assignment: #NNN
- Scope completed: ...
- Demonstrated independently: ...
- Demonstrated with guidance: ...
- Production feedback taught: ...
- Tests and verification observed: ...
- Continue practicing: ...
- Appropriate next stretch: ...
- Evidence: links to relevant PR files, commits, or review threads
```

## 2026-09-08 — PR #12: Migration foundation and coaching reset

- Assignment: [#7](https://github.com/danrodero/schizogenic/issues/7).
- Review disposition: technical review of
  `111fc946c498e5d3c58b3d88f1de5bb63152d3a5` found no required implementation
  findings. The agent could not formally approve its own PR. The developer
  subsequently merged PR #12; GitHub confirms landed commit
  `3e3ed04f857b6b3d21baed23493e9ae7250408dc` on `main` and closure of issue #7.
  This records the actual merge, not an agent-submitted approval.
- Scope completed: external PostgreSQL configuration, a Flyway V1 marker-table
  baseline, isolated native PostgreSQL tests, operational documentation, and a
  concrete junior-level coaching contract. ADR 0002 is accepted on the reviewed
  migration and verification evidence.
- Developer work present before agent completion: datasource configuration,
  migration dependencies, V1 SQL, an initial context test, wrapper update, and
  documentation draft. That snapshot alone does not prove independent authorship
  or mastery of every part.
- Agent-completed with explicit authorization: native temporary PostgreSQL
  lifecycle, four behavioral integration tests replacing context-only checks,
  startup verification, documentation alignment, and coaching-policy changes.
  These tests are learning examples, not independent developer testing evidence.
- Verification: `nix develop path:. -c just check` passes at the exact published
  head; the earlier clean build and fresh source-snapshot checks pass. Four tests
  prove clean/repeat startup, checksum rejection, and unavailable/invalid database
  failures without logging the test password. Two packaged starts preserve the
  baseline/history; startup fails after that temporary database is stopped.
  Skill validation, YAML parsing, Markdown lint, and diff whitespace checks pass.
  Verification is Linux x86-64; other declared platforms were not executed.
- Coaching given: explain the difference between context startup and asserted
  database behavior; correct the undefined baseline and contradictory Docker
  restriction as assignment defects. Provide lessons, examples, file paths,
  and concrete next steps instead of repeating abstract hints.
- Remaining practice: writing behavioral assertions using the existing helpers
  and understanding what a database transaction rolls back. Ratings remain
  unchanged because agent-completed work does not establish independent skill.
- Next challenge: [#11](https://github.com/danrodero/schizogenic/issues/11), one
  regression test for a failing transactional migration; its prerequisite is met.
- Evidence: [PR #12](https://github.com/danrodero/schizogenic/pull/12),
  [reviewed commit](https://github.com/danrodero/schizogenic/commit/111fc946c498e5d3c58b3d88f1de5bb63152d3a5).
