---
name: assign-learning-task
description: Select and write the next Schizogenic implementation assignment from confirmed requirements and demonstrated developer ability. Use when the user asks for a task, next feature, challenge, issue, or recommendation for what to implement next in the learning workflow.
---

# Assign Learning Task

Choose one production-relevant task that is achievable with effort and teaches
the next appropriate skill without revealing its solution.

## Gather evidence

1. Read `AGENTS.md`, confirmed product requirements, accepted ADRs,
   `docs/engineering/standards.md`, the competency profile, and recent review
   log entries.
2. Use `gh` to inspect open issues and pull requests. Do not duplicate active
   work.
3. If an approved PR has not yet been recorded, update the learning records
   from its review evidence before selecting new work.
4. Treat unobserved skills as unknown. Do not assume mastery or weakness.

## Select the assignment

Prefer a task that:

- Advances a confirmed requirement or necessary engineering foundation.
- Produces one coherent, reviewable outcome.
- Reinforces one developing skill and introduces at most one major new concept.
- Requires the developer to write meaningful initial tests.
- Fits the current architecture and avoids speculative infrastructure.

For a new developer with no evidence, begin with a small setup or behavior
slice that reveals fundamentals. Do not assign a complete vertical product
module as the first task.

## Write the assignment

Include:

- Linked requirement IDs and relevant context.
- An observable outcome.
- One or two learning objectives and why they fit the evidence.
- Acceptance criteria, including important failure behavior.
- Test, documentation, and verification obligations.
- Constraints and explicit non-goals.
- Expected deliverables and PR instructions.
- Optional links to primary documentation for research.

State what quality must be demonstrated, not how to structure the
implementation.

Create a GitHub issue using the learning-task template unless the user asks for
a conversational draft only. Report the issue link and a concise starting
point.

## Guardrails

- Do not supply code, pseudocode, file-by-file steps, class names, schemas, or a
  hidden reference solution.
- Do not make an assignment from a merely proposed requirement.
- Do not use unexplained complexity as a difficulty mechanism.
- Do not bundle cleanup, product behavior, and infrastructure into one task.
- Do not test several new skill domains at once.
- Do not update competency ratings merely because a task was assigned.
