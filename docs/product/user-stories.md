# User stories

Status: Draft

This file turns confirmed specification items into reviewable behavior slices.
Stories marked `Proposed` are discovery material, not implementation
assignments.

## Story map

| Journey | Story | State | Requirement |
| --- | --- | --- | --- |
| Capture time | As a user, I want to start a timer so that I can record what I am doing now. | Proposed | TIME-001 |
| Observe time | As a user, I want to see the running timer so that I know tracking is active. | Proposed | TIME-002 |
| Complete time | As a user, I want to stop a timer so that the elapsed interval is saved. | Proposed | TIME-003 |
| Correct history | As a user, I want to add and correct entries so that my history reflects reality. | Proposed | TIME-004, TIME-006 |
| Review history | As a user, I want to browse recent entries so that I can understand where time went. | Proposed | TIME-005 |
| Use another interface | As a user, I want the same core workflow in a CLI so that I can track without leaving the terminal. | Confirmed at capability level | PROD-004 |

## Story readiness checklist

Before a story becomes an assignment, it needs:

- A linked confirmed requirement.
- Observable acceptance criteria without implementation instructions.
- Explicit domain rules and important edge cases.
- Named web, CLI, and API impacts where relevant.
- Test obligations and documentation impact.
- Clear out-of-scope behavior.
- A size suitable for one pull request.

## Story template

```markdown
### STORY-NNN: Outcome

State: Proposed | Ready | Implemented
Requirements: PROD-NNN

As a ...
I want ...
So that ...

Acceptance criteria:

- Given ..., when ..., then ...

Important edge cases:

- ...

Out of scope:

- ...

Open questions:

- ...
```
