---
name: review-learning-pr
description: Review or re-review a Schizogenic pull request as a production lead developer and teacher, leave durable GitHub feedback, decide whether to request changes or approve, merge approved work, and record demonstrated learning. Use when the user provides a PR, says a PR is ready, asks for code review, requests re-review after fixes, or asks whether a change is production-ready.
---

# Review Learning PR

Review the developer's work rigorously without taking over its implementation.

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

Ask for the developer's reasoning when a choice is not self-explanatory. Do not
assume unfamiliarity merely because a choice differs from your preference.

## Write feedback

Leave durable, actionable findings on GitHub. For each finding:

- Label it `Blocker`, `Major`, `Minor`, `Nit`, or `Question`.
- Point to the smallest useful location.
- Describe the observed behavior or risk.
- Explain why it matters in production.
- State the required outcome without supplying the implementation.

Avoid flooding the PR with duplicate symptoms of one root cause. Separate
required changes from optional coaching.

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

Do not edit feature code or write replacement tests. Adversarial-test work must
meet the exception in `AGENTS.md`.

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
