---
name: review-learning-pr
description: Review or re-review a Schizogenic pull request as a production lead developer and teacher, leave durable GitHub feedback, decide whether to request changes or approve, merge approved work, and record demonstrated learning. Use when the user provides a PR, says a PR is ready, asks for code review, requests re-review after fixes, or asks whether a change is production-ready.
---

# Review Learning PR

Read `docs/learning/coaching-playbook.md` and follow its review contract.
Review correctness while teaching a junior how to complete the agreed slice.
If the user explicitly requests completion, implement and verify that scope;
mark agent contributions honestly and preserve author/approver separation.

## Collect context

1. Read `AGENTS.md`, the linked assignment, relevant confirmed requirements,
   accepted ADRs, engineering standards, competency profile, and recent review
   log.
2. Use `gh pr view`, `gh pr diff`, changed-file context, commit history, and
   check results. Confirm the target and scope before reviewing.
3. Detect stacked-PR membership. When the pull request is stacked, use
   `gh stack view --json` (checking out the stack when necessary) to establish
   its trunk, order, immediate parent, dependent layers, and merge prefix.
   Review the pull request's layer-relative diff and the cumulative diff from
   trunk through its exact head.
4. Compare the authenticated GitHub identity with the PR author, CODEOWNERS,
   and applicable repository rules. Detect a self-approval or last-push
   deadlock for every pull request in a proposed stack merge prefix before
   committing, pushing, or opening another PR.
5. Run relevant format, static-analysis, build, and test commands in the pinned
   environment. Record commands and distinguish existing failures from
   introduced failures.
6. Never expose secrets or overwrite unrelated local changes.

## Review stacked pull requests

- Attribute findings to the lowest layer that introduces them. Do not report a
  parent-layer defect as if the current layer introduced it, but do block the
  merge prefix when the cumulative result is unsafe.
- Treat the ordinary pull request diff as the incremental layer only. Also
  inspect and verify the cumulative repository state through the selected
  layer against the stack trunk.
- Check every pull request from the bottom of the stack through the selected
  layer: scope, author and approval identity, required review threads, draft
  state, and required checks must all satisfy the normal production bar.
- Do not submit final approval for a layer while any pull request in its merge
  prefix is not production-ready. Once the prefix is ready, approve and merge
  it atomically with `gh stack merge <pr-number>`; do not use `gh pr merge` on
  a stacked pull request.
- After the atomic merge, verify the merged state of every included pull
  request, the linked issue state, the landed commits, and any dependent layers
  that remain open. Do not push or rewrite the developer's remaining branches;
  request a developer-run `gh stack sync` if their local stack needs repair.

## Review order

Review in this priority:

1. Correctness, domain invariants, edge cases, and scope.
2. Security, authorization, data integrity, transactions, and concurrency.
3. Architecture boundaries and dependency direction.
4. Test quality and missing behavioral evidence.
5. Failure behavior, accessibility, observability, and operations.
6. Maintainability, documentation, naming, and style.

Ask about reasoning only when it affects a concrete review decision. Explain
unfamiliar concepts directly; a missing explanation is not itself a merge blocker.
Do not assume unfamiliarity merely because a choice differs from your preference.

## Write feedback

Leave durable, actionable findings on GitHub. For each finding:

- Label it `Blocker`, `Major`, `Minor`, `Nit`, or `Question`.
- Point to the smallest useful location.
- Describe the observed behavior or risk.
- Explain why it matters in production.
- Give the expected result and an actionable next step, teaching the concept
  and showing a small example if needed.

Start with specific working behavior. Group all known required findings by root
cause in one review. Only Blocker/Major findings with a demonstrated consequence
or missing agreed behavioral evidence are required. Minor/Nit suggestions and
ordinary Questions are non-blocking. No surprise standards: correct ambiguous
issue wording, accept reasonable interpretations, and explain newly discovered
risks. Do not block for prose, preferences, or unfamiliar vocabulary.

## Decide the review

- Request changes when any blocker or required major finding remains.
- Approve only when the agreed scope is production-ready and required checks
  pass.
- Never approve based only on green CI.
- On re-review, verify the actual fix, affected neighboring behavior, and every
  prior required thread.
- Treat approval as authorization to merge. Immediately merge an approved PR
  without waiting for a separate request, unless the user explicitly requested
  a hold or GitHub reports a blocking repository condition.
- For a stacked pull request, approval and immediate merge apply to the fully
  verified prefix through that pull request and must use `gh stack merge`.
- If an open PR was already approved before the current session, verify that
  the approval still applies to its exact head and merge it immediately.

Use GitHub's review action so the decision is visible on the PR. After merging,
verify the merged commit and linked issue state.

## Record growth

Once the feature changes meet the approval bar:

1. Append one entry to `docs/learning/review-log.md` based only on observed
   code, tests, reasoning, and review iterations.
2. Update the competency profile only where the entry provides sufficient
   evidence.
3. Prepare these changes immediately; never defer them until the developer asks
   for feedback or requests another assignment.
4. Prefer including the records at the end of the reviewed PR. If the agent is
   the required CODEOWNER or last-push approver, leave its record changes
   uncommitted for the developer to commit and push so self-approval rules are
   not deadlocked.
5. Re-run required checks against the exact head, submit the final approval,
   merge immediately, and deliver the review feedback.
6. If an already-approved PR must be merged before missing records can be
   added, merge it first, then immediately prepare and complete an
   identity-safe fast-track PR for those records.

By default leave implementation and initial tests to the developer while giving
concrete instruction. An explicit request to take over permits scoped completion
and tests; record that contribution as assisted work. Otherwise adversarial-test
work must meet the exception in `AGENTS.md`.

## Own the agent's changes

Apply `AGENTS.md`'s clean-handoff exception before the uncommitted guidance below.
When asked to commit agent-owned records or clear the workspace, commit locally
with the agent identity on a separate documentation branch from `origin/main`.
Leave a clean implementation branch for the developer. GitHub publication and
approval constraints still apply; do not open a self-approval deadlock.

Do not block a learning PR because agent-owned documentation remains unmerged,
agent edits are present, or inherited commits make the commit list look broad.
Inspect the actual base-relative diff and provenance. Help separate agent work;
do not turn your housekeeping into a required developer fix. Require changes
only for a demonstrated defect or missing agreed behavioral evidence, not commit
organization or optional cleanup.

## Fast-track agent-authored policy changes

For a PR limited to repository policy, agent workflows, or learning-process
documentation:

1. Confirm that the developer, not the required CODEOWNER identity, authored
   the PR.
2. Inspect the complete diff and verify the exact head with every relevant
   documentation and skill validation.
3. Request changes for any defect; fast-track does not waive the production
   bar.
4. If the change is correct, submit the CODEOWNER approval and merge
   immediately in the same session.

When preparing this kind of change before a PR exists, edit and validate it but
leave it uncommitted for the developer if the current GitHub identity must
approve the eventual PR.

### Give a complete publication handoff

The developer uses Nushell. Follow the shell section in `AGENTS.md`: direct
Nushell commands, `with-env`/`hide-env` for scoped authentication, and no Bash
scripts, `export`, `unset`, inline assignments, or backslash continuations.
The agent execution shell is not evidence of the user's shell.

Follow `AGENTS.md`'s explicit publication handoff. Before asking the developer to
act, list the working directory, branch, exact files, and committed/uncommitted
state. Supply direct, filled-in commands for checking the `danrodero` login,
staging only the named files and committing if needed, pushing, and creating the
PR with the agent-prepared title/body. Existing local commits need publication,
not another commit. A single helper is acceptable if its actions are explained.
Prevent inherited reviewer tokens from silently selecting the wrong account.

Tell the developer: "Run these publication commands with your developer account;
I will verify the resulting PR, submit the reviewer approval, and merge it."
When they say "done", discover the PR without demanding a URL. Review its exact
head and submit approval yourself from the required reviewer account when the
agreed scope is ready, then merge immediately. Do not send the developer away to
find an approver or make them request approval/merge separately.

### Identity availability

Before proposing fresh authentication, read the "Previously used developer login"
section in `docs/engineering/development-workflow.md`. PR #10 documents
`~/.config/gh-danrodero` with reviewer token overrides removed. A missing login
in the harness account does not prove it is missing in the developer's account.
Reuse that existing developer-terminal command when agent access is unavailable.

Use only available, authorized GitHub identities. Historical account paths or
keys in old instructions are not evidence that a developer login exists here.
Inspect `gh api user`, CODEOWNERS, and active rules before publishing. If the
available account is the sole required approver, prepare and validate the changes
but leave them uncommitted for developer publication. Explain the exact GitHub
constraint once, with a short ready-to-run handoff. Never change protections,
forge authorship, or search for alternate credentials to force a merge.

After developer publication, verify the exact remote head, author, diff, rules,
review threads and checks. Query the pull request API's `stack` field. For an
ordinary PR, approve then `gh pr merge <number> --squash`; for a stack, verify the
entire prefix and use `gh stack merge <number> --yes --squash`. Verify merged
state, landed commit, linked issues, and dependent layers afterward.
