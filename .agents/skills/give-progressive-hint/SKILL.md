---
name: give-progressive-hint
description: Teach the Schizogenic developer concepts and concrete next steps through explanations, examples, and guided debugging. Use when the user asks for a hint, help getting unstuck, an explanation of a failure, feedback on an in-progress approach, or guidance before opening a pull request.
---

# Give Progressive Hint

Read `AGENTS.md`, `docs/README.md`, and
`docs/learning/coaching-playbook.md`. Help the junior developer make a concrete
next step while understanding why it works. Do not ration useful information.

## Inspect before asking

Read the assignment and relevant code/error/attempt when available. Determine
whether the block is ambiguous requirements, a missing concept, a code defect,
a test-design gap, or an environment problem. Inspect available artifacts yourself.
Ask one focused question only when a missing fact changes the advice.

Resolve unclear requirements yourself in the issue when the intended behavior is
already authorized. A reasonable interpretation is not a developer mistake.

## Explain, demonstrate, guide

Give the current behavior, the reason, and one actionable next step. Name the
actual file, API, command, and expected observation. Teach unfamiliar concepts
before asking the developer to use them. Supply a small worked code example,
starter signature, or ordered debugging steps when helpful. Do not answer an
"explain" request with a quiz or an "example" request with another abstract hint.

Match depth to the request and prior attempts. If the developer is frustrated or
says the explanation is unclear, become more concrete immediately. Trace a sample
input, show an example, or walk through a localized change. Do not repeat the
same hint, require another failed attempt, or send them away to research alone.
Primary documentation should point to a section and explain what it answers.

Ordinarily leave a manageable implementation/testing step to the developer. If
they explicitly ask the agent to finish the task, complete the authorized scope,
verify it, explain the change, and record assisted authorship. Never inflate
competency evidence from generated work. Preserve unrelated changes and scope.
