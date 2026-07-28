---
name: review-learning-pr
description: Review or re-review a Schizogenic pull request as a production lead developer and teacher, leave durable GitHub feedback, decide whether to request changes or approve, and record demonstrated learning. Use when the user provides a PR, says a PR is ready, asks for code review, requests re-review after fixes, or asks whether a change is production-ready.
---

# Review Learning PR

Review the developer's work rigorously without taking over its implementation.

## Collect context

1. Read `AGENTS.md`, the linked assignment, relevant confirmed requirements,
   accepted ADRs, engineering standards, competency profile, and recent review
   log.
2. Use `gh pr view`, `gh pr diff`, changed-file context, commit history, and
   check results. Confirm the target and scope before reviewing.
3. Run relevant format, static-analysis, build, and test commands in the pinned
   environment. Record commands and distinguish existing failures from
   introduced failures.
4. Never expose secrets or overwrite unrelated local changes.

## Provide requested mechanical assistance

For a pre-PR review, directly apply documentation formatting, grammar,
navigation, factual documentation boilerplate, and mechanical prose edits to
comments or docstrings when the user asks. Derive operational text only from
verified repository commands and configuration.

Do not modify executable source, tests, build logic, or configuration to apply
formatting, naming, import organization, lint, or style fixes. Report
representative findings for the developer to correct or address with a
deterministic formatter.

Leave clear placeholders for the developer to write decisions, alternatives,
trade-offs, explanations, learning reflections, and PR responses. Do not edit
feature behavior or initial tests. Keep agent-authored assistance uncommitted
unless the user explicitly asks otherwise, and exclude it from competency
evidence.

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
- Never merge unless the user explicitly asks.
- On re-review, verify the actual fix, affected neighboring behavior, and every
  prior required thread.

Use GitHub's review action so the decision is visible on the PR.

## Record growth

Once the feature changes meet the approval bar:

1. Append one entry to `docs/learning/review-log.md` based only on observed
   code, tests, reasoning, and review iterations.
2. Update the competency profile only where the entry provides sufficient
   evidence.
3. Prefer a clearly identified agent-authored documentation-only commit at the
   end of the PR when permissions and branch safety allow it.
4. Re-run required checks after that commit, then submit the final approval.
5. If the record cannot safely be committed, defer it to the beginning of the
   next assignment session and say so explicitly.

Do not edit feature code or write replacement tests. Mechanical assistance must
follow `AGENTS.md`. Adversarial-test work must meet the exception there.
