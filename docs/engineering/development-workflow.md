# Development and learning workflow

## 1. Refine the product

The developer and lead agent discuss outcomes, terms, constraints, and edge
cases. The agent updates product documentation while preserving `Confirmed`,
`Proposed`, and `Open` states. Technical decisions with lasting consequences
receive ADRs.

Product discussion does not automatically authorize implementation.

## 2. Select one learning task

When asked for a task, the lead agent reads:

- Confirmed product requirements and accepted ADRs.
- Open issues and pull requests.
- The competency profile and recent review-log entries.

The agent chooses a small vertical or enabling slice that practices a needed
skill and stretches slightly beyond demonstrated ability. It creates a GitHub
learning-task issue containing outcomes and acceptance criteria, never a
solution design.

## 3. Implement independently

The developer:

1. Creates a focused branch.
2. Researches primary documentation.
3. Implements the behavior and its initial tests.
4. Runs all relevant checks.
5. Updates documentation.
6. Opens a pull request linked to the assignment.

The developer may ask the agent for hints. Hints become progressively more
specific but stop short of the implementation.

## 4. Review as production code

The lead agent inspects the whole change and repository context, runs relevant
verification, and leaves durable PR feedback.

Findings are prioritized:

- **Blocker**: unsafe to merge; correctness, security, data-loss, or fundamental
  scope failure.
- **Major**: required production-quality change.
- **Minor**: worthwhile improvement that may be required depending on scope.
- **Nit**: optional polish.
- **Question**: reasoning or intent needs clarification.

The developer addresses findings and requests re-review. The cycle continues
until no required finding remains and all agreed checks pass.

### Stacked pull requests

A developer may use `gh stack` when a coherent assignment benefits from
separately reviewable, dependent layers. Each layer must remain focused and
must identify its immediate parent pull request. The lead agent reviews the
layer-relative diff for attribution and the cumulative diff through that layer
for integration, regression, and scope risk.

Before approving a layer, the agent verifies that every pull request from the
bottom of the stack through that layer meets the production bar, including
checks, identity rules, and resolved required findings. Approval is followed
by `gh stack merge` for that verified prefix; `gh pr merge` must not be used to
merge a member of a stack in isolation. Afterward, the agent verifies every
included pull request, linked issue, and the remaining stack state.

## 5. Record evidence, approve, and merge

Once the implementation meets the approval bar, the agent appends a concise
evidence record and updates the competency profile only where the reviewed code
supports a change. The agent then re-runs required checks against the exact
head, approves, immediately merges, verifies the result, and delivers the final
coaching feedback. Approval does not wait for a separate merge request.

For a stacked pull request, "the exact head" includes the verified cumulative
state through that layer, and immediate merge means an atomic stack merge of
the production-ready prefix.

Repository identity rules still apply. If the agent is the required CODEOWNER
or last-push approver, it leaves its learning-record edits uncommitted for the
developer to commit and push. The agent then verifies that exact head,
approves, and merges in the same review lifecycle. Learning records and
feedback are never deferred until the next assignment request.

An already-approved open PR is merged as soon as the agent confirms that the
approval still applies to its current head. Any missing learning record is then
completed immediately through the identity-safe fast track.

The next task is chosen from that updated evidence, creating a continuous loop
of deliberate practice.

## Pull request states

```text
Assignment -> Implementation -> PR -> Changes requested
                                  ^            |
                                  |____________|

PR -> Production-ready review -> Learning record -> Approval -> Immediate merge
```

## Policy-change fast track

For agent-authored changes limited to repository policy, agent workflows, or
learning-process documentation:

1. The agent edits, self-reviews the complete diff, and runs relevant checks.
2. If the agent identity is the required approver, it leaves the edits
   uncommitted.
3. The developer commits, pushes, and opens the PR.
4. The agent verifies the exact head, approves, immediately merges, and
   confirms the merged state.

This path removes redundant ceremony without relaxing correctness or
validation. The agent must inspect repository rules and identities before
creating a PR so it does not author work that only it is allowed to approve.

## Tooling problems

If a developer is blocked by Nix, Git, GitHub, or an unrelated environment
failure, the agent may give direct procedural help. If the tooling itself is
the assignment's learning objective, use the normal progressive-hint approach.
