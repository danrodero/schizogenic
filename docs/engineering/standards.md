# Engineering standards

These are review expectations, not a feature implementation recipe. Apply the
parts relevant to each assignment.

## Definition of done

A change is done when:

- Its acceptance criteria and relevant edge cases are met.
- The design is understandable; unfamiliar trade-offs receive coaching.
- Automated tests cover the behavior at appropriate boundaries.
- Required format, static analysis, build, and test commands pass from a clean
  checkout.
- Errors are safe and useful, and logs do not leak sensitive data.
- Documentation and API contracts match the behavior.
- No known blocker or major review finding remains.
- The pull request is focused and reviewable.

## Design

- Prefer simple, cohesive components with explicit responsibilities.
- Make invalid states difficult to represent and validate at boundaries.
- Keep domain decisions separate from transport and persistence mechanics.
- Use names that express product language from the specification.
- Add abstractions after a real repeated need, not in anticipation of one.
- Explain significant trade-offs in an ADR.

## Java and Spring

- Use Java 25 deliberately; avoid preview features unless an ADR accepts them.
- Follow normal Java naming, immutability, exception, and resource-management
  conventions.
- Prefer constructor injection and explicit dependencies.
- Keep controllers thin and define transaction boundaries intentionally.
- Use Spring Boot dependency management rather than independently overriding
  managed versions without a documented reason.
- Treat compiler warnings and static-analysis findings as work to understand,
  not suppress by default.

## HTTP APIs

- Define resource semantics, validation, status codes, error bodies, and
  idempotency expectations.
- Do not expose persistence entities as public contracts.
- Make breaking changes explicit and reviewed.
- Test contracts from the caller's perspective.

## PostgreSQL

- Evolve schema only through ordered, immutable migrations after they are
  shared.
- Use database constraints for invariants the database can enforce.
- Make transaction and concurrency behavior explicit.
- Avoid destructive migrations without a safe rollout and recovery plan.
- Test real PostgreSQL behavior when an in-memory substitute would differ.

## TypeScript and React

- Enable strict TypeScript checking.
- Preserve semantic HTML, keyboard operation, visible focus, and meaningful
  accessible names.
- Model loading, empty, error, and success states deliberately.
- Keep server data and authoritative business rules outside presentation
  components.
- Test observable behavior rather than implementation details.
- Verify layouts at mobile and desktop widths.

## Testing

- The developer normally writes the initial tests for assigned behavior. An
  explicit request for agent completion permits assisted tests; record authorship.
- Prefer fast unit tests for isolated policy and fewer integration or
  end-to-end tests for important boundaries and journeys.
- Keep tests deterministic, independent, and readable.
- Test failures, validation, boundary values, and meaningful concurrency cases,
  not only the happy path.
- A test must be able to fail for the defect it claims to detect.
- Do not weaken assertions merely to make a suite pass.

## Security and operations

- Never commit secrets or use production credentials in development.
- Validate untrusted input and authorize protected operations.
- Pin and review dependencies; address known vulnerabilities proportionately.
- Use structured, useful logs without tokens or personal content.
- Provide health and diagnostic behavior only when its exposure is safe.
- Document backup, restore, migration, and deployment procedures before relying
  on them.

## Git and review

- One issue and one coherent outcome per pull request.
- Use descriptive commits; avoid unrelated formatting or refactors.
- Complete the PR template and include exact verification commands.
- Respond to every blocking review thread with a change or reasoned discussion.
- Do not resolve a review conversation before the concern is addressed.
