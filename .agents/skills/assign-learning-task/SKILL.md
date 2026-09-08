---
name: assign-learning-task
description: Select and write the next Schizogenic implementation assignment from confirmed requirements and demonstrated developer ability. Use when the user asks for a task, next feature, challenge, issue, or recommendation for what to implement next in the learning workflow.
---

# Assign Learning Task

Read `AGENTS.md`, `docs/README.md`, and
`docs/learning/coaching-playbook.md`. Apply its assignment format and preflight;
this skill must produce an issue a junior programmer can start without guessing.

## Gather evidence

1. Read confirmed product requirements, relevant accepted ADRs, engineering
   standards, the competency profile, and recent review-log entries.
2. Inspect current code, open issues, and PRs with `gh`. Avoid duplicate work.
3. Finish a prior ready review/merge and its evidence record first when possible.
   If an external identity/check blocks it, prepare a clearly dependent next
   assignment; do not pretend the prerequisite merged or stall all local work.
4. Treat unobserved skills as unknown. Agent-completed work is not independent
   developer evidence. Calibrate to junior level unless reviewed evidence shows
   readiness for a larger step.

## Select and teach

Choose one small confirmed behavior or necessary engineering foundation. Introduce
at most one main new concept; reuse existing tooling and test infrastructure.
Do not assign a complete vertical module or another infrastructure project just
because product questions are unresolved. A small test-focused task is valid.

Write a short prerequisite lesson. Define unfamiliar terms, explain why the
concept matters, and show a small example where helpful. Provide file paths,
relevant API names, ordered steps, and a concrete first action. These are allowed
teaching aids. Leave meaningful work for the developer; do not include the whole
implementation unless explicitly asked to complete it.

## Publish a bounded contract

Use `.github/ISSUE_TEMPLATE/learning-task.yml` sections: requirements, outcome,
learning/prerequisites, starting point and steps, acceptance examples,
verification, boundaries/submission, and focused primary references.

- Give exact input/output examples and relevant failure behavior.
- Name each required test scenario and exact commands with working directories.
- Distinguish requirements from suggestions and optional stretch work.
- State required runtime prerequisites; check that restrictions do not conflict
  with the test strategy.
- Avoid unexplained demands such as "safe repeatability" or "production-ready".
- Keep PR reporting short: what changed, tests run/results, and remaining doubts.

Run the playbook preflight before creating the issue. Create a GitHub issue
unless the user requested only a draft. If a prerequisite cannot merge yet,
label the dependency explicitly and present the assignment as queued, not ready.
Report the link and a useful first action. Do not change competency ratings for
an assignment or silently promote proposed product requirements.
