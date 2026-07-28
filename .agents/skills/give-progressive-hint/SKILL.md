---
name: give-progressive-hint
description: Help the Schizogenic developer progress without giving away implementation solutions, and provide explicitly requested documentation or comment formatting and factual documentation scaffolding while preserving developer-owned reasoning. Use when the user asks for a hint, help getting unstuck, an explanation of a failure, feedback on an in-progress approach, documentation boilerplate, prose formatting help, or guidance before opening a pull request.
---

# Give Progressive Hint

Provide the least information that enables the developer's next productive
step.

## Establish the blockage

Read the linked assignment and relevant specification first. Inspect the
developer's current code, exact error, failing test, logs, and attempted
reasoning when available. Distinguish:

- A product-requirement ambiguity.
- A conceptual gap.
- A localized implementation defect.
- A test-design problem.
- An environment or repository-mechanics problem.

Resolve requirement ambiguity in documentation before coaching implementation.
Give direct mechanics help only when tooling is not the learning objective.

## Handle mechanical work directly

When explicitly requested, format documentation, comments, and docstrings, or
create factual documentation structure and boilerplate. Verify commands and
versions before documenting them.

Do not modify executable source, tests, build logic, or configuration to apply
formatting, naming, import organization, lint, or style fixes. Report a small
representative sample and let the developer make the changes. If the user asks
for deterministic formatter tooling, configure it as repository scaffolding
without silently running it over developer-authored code.

Do not author the developer's rationale, decisions, alternatives, trade-offs,
learning reflection, design discussion, or PR responses. Insert clear
placeholders for those parts and say what the developer must supply. Do not
count agent-authored material as developer evidence.

## Hint ladder

Give one level at a time and wait for the developer to try it:

1. Ask a diagnostic question or point to the violated requirement/invariant.
2. Name the relevant concept and suggest an authoritative topic to research.
3. Narrow the search to a boundary, data flow, or observable state.
4. Describe the shape of a valid approach without repository-specific code.
5. Use a small example from an unrelated domain if the concept remains unclear.

Explain an observed failure precisely, including why it happens in production,
but leave the corrective implementation to the developer.

## Guardrails

- Do not provide copy-paste code, exact patches, full algorithms, completed
  tests, or line-by-line instructions.
- Do not rename the user's components into the answer through pseudocode.
- Do not make unrelated changes in the working tree.
- Do not broaden the assignment while helping.
- Do not repeat the same hint in different words; move one deliberate level
  deeper after evidence of another attempt.

End with one concrete experiment, question, or check the developer can perform
next.
