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

## Clean handoffs

Before the developer starts another assignment, agent-owned edits must not remain
as unexplained working-tree changes. When asked to commit them, use a separate
local documentation branch based on current `origin/main` and the agent's commit
identity; leave the developer on a clean task branch. This overrides the default
uncommitted-record procedure. Publishing still requires valid GitHub identities.

The documentation branch is not a prerequisite for the next learning PR. The
agent owns separating or accounting for its changes and inherited commits. Review
the actual diff against the correct base; do not reject developer work for the
agent's leftovers, commit grouping, or optional cleanup.

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

## Direct commands and ownership at publication

If the developer must publish prepared changes, the agent supplies a concrete
handoff in the conversation. It states:

- The developer GitHub login (`danrodero`) and reviewer login
  (`clawstopher-moltosanti`), with the verified reason they must be separate.
- The working directory, branch, exact file list, and whether a commit already
  exists. Already-committed work needs no duplicate commit.
- Ready-to-run commands for identity verification, explicit-file staging and
  commit if needed, push, and PR creation. The agent fills in the actual branch,
  paths, commit message, PR title, and prepared body file. No `git add .` and no
  unexplained placeholders. A helper is acceptable with a description of what
  it does and the exact command to run it.
- Protection against an inherited `GH_TOKEN` or `GITHUB_TOKEN` overriding the
  developer login. The push and PR creation must use the same verified account.
- The agent's next action: discover the PR when the developer says "done",
  verify its exact head and relevant checks, submit the required reviewer
  approval, immediately merge, and confirm the landed commit and issue state.

Do not merely say "commit the files" or "create a PR". Do not ask the developer
to approve the PR, locate another reviewer, or request the merge again. Approval
still requires the agreed scope to be correct; explain a real blocker concretely.
Publication of agent housekeeping is not a new learning assignment and must not
contaminate the developer's implementation branch.

## Previously used developer login

PR #10 documented a successful existing setup: the developer's `gh` login is
under `$HOME/.config/gh-danrodero`, and Git publication used the existing SSH key
`$HOME/.ssh/danrodero`. The default environment supplies the reviewer token.
Reuse this documented setup before proposing a new login or configuration directory.

Run this in the developer's own terminal to verify the existing login:

```bash
env -u GH_TOKEN -u GITHUB_TOKEN \
  GH_CONFIG_DIR="$HOME/.config/gh-danrodero" \
  gh auth status
```

The account must be `danrodero`. Use the same environment prefix for `gh pr
create`; Git pushes can use the existing SSH setup independently. Give the
actual branch and prepared PR body in each publication handoff.

`$HOME` belongs to the process account. An agent running as `harness` checks
`/home/harness`, not the human's home directory. Missing configuration there does
not establish that the developer needs to authenticate again. If the agent
cannot access the developer's configuration, say so and provide the existing
command for their terminal. Do not read token files or search for alternate keys.

Historical evidence: [PR #10](https://github.com/danrodero/schizogenic/pull/10).

## Tooling failures

Give direct procedural help for environment and repository problems. Explain
commands and expected results, including when tooling is the lesson. Use the
pinned Nix environment and committed Gradle wrapper. A machine ownership issue
with `nix develop` can be avoided using `nix develop path:.`; this does not require
changing repository ownership or weakening checks.
