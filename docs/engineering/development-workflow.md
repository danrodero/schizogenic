# Development and learning workflow

Read [the coaching playbook](../learning/coaching-playbook.md) for the teaching
contract. This project teaches junior programming through small completed steps.

## 1. Refine the product

Discuss behavior and preserve `Confirmed`, `Proposed`, and `Open` requirements.
Record durable technical choices as ADRs. Product discussion does not itself
assign implementation work.

## 2. Assign one understandable task

Read current code, requirements, accepted ADRs, open issues/PRs, and learning
records. Choose one outcome and one main new concept. Publish an issue with a
short lesson, prerequisites, starting files, ordered steps, concrete acceptance
examples, exact verification commands, and explicit non-goals. Identify suggested
choices separately from requirements. Check that a junior can begin without
having to guess what the issue means.

Finish prior ready reviews first. If a real external prerequisite blocks a merge,
a requested next issue may be queued with that dependency stated clearly.

## 3. Implement with useful coaching

The developer normally implements the behavior and initial tests on a focused
branch. The agent explains concepts, demonstrates small examples, names APIs and
files, and gives concrete debugging steps. Research links supplement instruction.
There is no mandatory hint ladder or requirement to fail again to get more help.
An explicit request for agent completion authorizes scoped implementation and
tests; that work is recorded as assisted rather than independent evidence.

## 4. Review and teach

Inspect the full diff, changed-file context, linked issue, and verification.
Report specific working behavior, then all known required root causes together.
Every required finding names the observed and expected result, concrete risk,
and a practical next step. Explain unfamiliar concepts instead of testing whether
the developer already knows them.

- **Blocker/Major (required):** demonstrated correctness, safety, data integrity,
  material architectural defect, or missing agreed behavioral-test evidence.
- **Minor/Nit (optional):** preferences, polish, alternate valid designs, and
  robustness outside the agreed slice.
- **Question (clarification):** ask only when the answer affects a real decision.
  An unanswered vocabulary or design quiz is not a blocker.

Correct confusing assignment text and agent-owned documentation yourself. Accept
reasonable interpretations; do not add surprise criteria. Newly discovered real
risks still need resolution, with explanation and bounded scope. Re-review fixes
and affected behavior without introducing rounds of optional demands.

## 5. Record evidence, approve, and merge

When substantive work reaches the approval bar, append observed evidence to the
review log and update the profile only where supported. Distinguish independent,
coached, and agent-completed work. Prepare the record immediately, not at the next
assignment. Until a PR exists and is reviewed, keep completion evidence in a draft
handoff rather than inventing a PR review or awarding competence.

Inspect authenticated identity, PR author, CODEOWNERS, and active rules before
publishing or approving. If an agent push would deadlock required approval, leave
validated changes uncommitted for developer publication. Explain the actual
external constraint and provide a concrete handoff. Do not assume old credential
paths are available or change repository protections to force a merge.

After publication, verify the exact PR head and relevant checks. Approve when no
required defect remains, then immediately merge and verify the landed commit and
linked issue state. An already-approved PR whose approval still applies is merged
immediately; prepare missing learning records through the policy fast track.

## Stacked pull requests

Inspect stack membership using the pull request API and `gh stack view --json`.
Review both the layer-relative diff and cumulative result. Attribute findings to
the lowest responsible layer. Verify all PRs from trunk through the selected layer:
identities, exact heads, checks, review threads, and correctness must be ready.
Approve only a ready prefix and merge it atomically with `gh stack merge`, never
`gh pr merge` for a stack member. Verify every included PR/issue, landed commits,
and remaining dependencies. Do not rewrite the developer's remaining branches.

## Policy-change fast track

The agent may edit policy, workflow, and learning-process documents when asked.
Review the complete diff and run relevant validation. When the available identity
is the required approver, leave changes uncommitted for the developer to publish.
Once the developer opens the PR, verify its exact head, approve, and immediately
merge. This is a real identity constraint, not another learning exercise.

For an ordinary PR use `gh pr merge <number> --squash` after approval. For a
verified stack prefix use `gh stack merge <number> --yes --squash`. Confirm the
merged state and linked issue afterward. Never claim completion from an attempted
merge command alone.

## Tooling failures

Give direct procedural help for environment and repository problems. Explain
commands and expected results, including when tooling is the lesson. Use the
pinned Nix environment and committed Gradle wrapper. A machine ownership issue
with `nix develop` can be avoided using `nix develop path:.`; this does not require
changing repository ownership or weakening checks.
