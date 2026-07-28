---
name: refine-product-spec
description: Refine Schizogenic product ideas into durable, state-labeled requirements, user stories, open questions, and decision records. Use when the user wants to discuss, clarify, add, remove, or reprioritize product behavior or asks the agent to update the vision, specification, stories, or related product documentation.
---

# Refine Product Spec

Turn product conversation into precise documentation without beginning
implementation.

## Workflow

1. Read `AGENTS.md`, `docs/product/`, and any accepted ADRs relevant to the
   topic.
2. Identify what the user explicitly confirmed, what remains a proposal, and
   what is still open.
3. Surface conflicts with existing requirements and ask focused questions when
   a choice materially changes product behavior. Ask one conceptual group at a
   time.
4. Update the smallest relevant set of documents:
   - Maintain product purpose and principles in `vision.md`.
   - Maintain numbered requirements, scope, rules, and open questions in
     `SPEC.md`.
   - Maintain reviewable behavior slices in `user-stories.md`.
   - Create an ADR only for a lasting technical decision.
5. Preserve stable IDs. Mark superseded items rather than silently reusing
   their IDs.
6. Check links, terminology, status labels, and contradictions.
7. Summarize confirmed changes, unresolved questions, and affected existing
   stories.

## Guardrails

- Keep product requirements technology-neutral unless technology is itself a
  confirmed constraint.
- Never promote `Proposed` or `Open` content to `Confirmed` without the user's
  agreement.
- Do not create application code, tests, build files, migrations, or an
  implementation task.
- Do not invent acceptance criteria for an unresolved domain rule.
- Record assumptions explicitly and prefer examples over vague adjectives.
- Separate current scope from future possibilities.

## Quality test

Before finishing, verify that a different developer could tell:

- What user outcome is required.
- Which statements are binding.
- Which edge cases remain undecided.
- How success could be observed.
- What is explicitly out of scope.
