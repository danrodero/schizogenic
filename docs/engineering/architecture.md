# Architecture

Status: Provisional constraints; no application architecture ADR is accepted
yet.

## System context

The initial system has two user-facing clients:

```text
Responsive web client ─┐
                       ├─> HTTP API ─> PostgreSQL
CLI client ────────────┘
```

The API owns domain rules and persistence. The web and CLI are clients of the
same application contract; neither should duplicate authoritative business
rules.

## Initial direction

- Use a monorepo with reserved areas under `apps/`.
- Begin with one Spring Boot deployable organized as a modular monolith.
- Organize backend boundaries by business capability, not by framework layer
  across the entire codebase.
- Keep the time-tracking domain independent from future finance, habit, and
  goal domains.
- Use PostgreSQL as the system of record.
- Expose explicit HTTP contracts usable by both web and CLI clients.
- Keep infrastructure choices replaceable at domain boundaries.

This direction limits accidental complexity while leaving room for extraction
only when evidence justifies it.

## Reserved application areas

- `apps/backend`: Java 25 and Spring Boot 4.x API and domain code.
- `apps/web`: TypeScript and React responsive web client.
- `apps/cli`: CLI client; its implementation language is intentionally open.

The developer will initialize each area through a reviewed learning task.

## Decisions requiring ADRs

At minimum, record a decision before committing to:

- Backend build language and project layout.
- API description and compatibility strategy.
- Database migration tool and migration policy.
- Authentication and user model.
- CLI implementation language and distribution method.
- Frontend build tool, routing, state, and API-client strategy.
- Test categories and external dependency strategy.
- Deployment and observability platform.

Use [the ADR template](decisions/0000-template.md). Do not create an ADR for a
minor, easily reversible implementation detail.

## Dependency rules

- Domain policy must not depend on HTTP, UI, or database representation.
- Clients depend on published API behavior rather than database details.
- A future module may depend on shared platform capabilities but not another
  module's internal persistence or implementation.
- Cross-module behavior must be explicit in an application boundary.

These rules guide review but do not prescribe specific class names or patterns.
