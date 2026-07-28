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

## 5. Record evidence and approve

Once the implementation meets the approval bar, the agent appends a concise
evidence record and updates the competency profile only where the reviewed code
supports a change. The agent then approves, but does not merge unless
explicitly asked.

The next task is chosen from that updated evidence, creating a continuous loop
of deliberate practice.

## Pull request states

```text
Assignment -> Implementation -> PR -> Changes requested
                                  ^            |
                                  |____________|

PR -> Production-ready review -> Learning record -> Approval -> Merge
```

## Tooling problems

If a developer is blocked by Nix, Git, GitHub, or an unrelated environment
failure, the agent may give direct procedural help. If the tooling itself is
the assignment's learning objective, use the normal progressive-hint approach.
