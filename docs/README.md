# Documentation index

The documentation is organized by the kind of decision it records.

## Product

- [Vision](product/vision.md): purpose, audience, and product principles.
- [Main specification](product/SPEC.md): requirements and release scope.
- [User stories](product/user-stories.md): prioritized behavior slices and
  acceptance questions.

## Engineering

- [Architecture](engineering/architecture.md): current system boundaries and
  constraints.
- [Development environment](engineering/environment.md): pinned toolchain,
  compatibility, and upgrade policy.
- [Engineering standards](engineering/standards.md): the production quality
  bar.
- [Development workflow](engineering/development-workflow.md): issue-to-merge
  and teaching lifecycle.
- [Architecture decision template](engineering/decisions/0000-template.md):
  structure for durable technical decisions.
- [ADR 0001: Backend build DSL and initial project layout](engineering/decisions/0001-backend-build-and-layout.md):
  proposed decision for the backend bootstrap.
- [ADR 0002: PostgreSQL schema migrations with Flyway](engineering/decisions/0002-postgresql-migrations-with-flyway.md):
  proposed migration mechanism, integrity policy, and test strategy.

## Learning

- [Coaching playbook](learning/coaching-playbook.md): junior-level instruction,
  concrete assignments, useful help, and actionable review.

- [Competency profile](learning/competency-profile.md): current, evidence-based
  capability summary.
- [Review log](learning/review-log.md): append-only record of reviewed evidence
  and coaching.

## Document states

Product statements use three states:

- **Confirmed**: explicitly agreed and safe to implement.
- **Proposed**: plausible direction that still needs agreement.
- **Open**: a question or unresolved decision.

Agents must preserve these distinctions. An architecture decision becomes
binding only when its ADR status is `Accepted`.
