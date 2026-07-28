# Engineering competency profile

Last evidence review: No reviewed implementation yet.

This is an evidence-based planning aid, not a grade. `Not observed` means the
repository has not supplied evidence; it does not mean the developer lacks the
skill.

## Rating scale

- **Not observed**: no reviewed evidence.
- **Guided**: completed with substantial hints or corrections.
- **Developing**: completed with some independent reasoning and some required
  coaching.
- **Independent**: repeatedly completed to production standard with minor
  review input.
- **Consistent**: demonstrated across multiple contexts and can explain
  trade-offs and review others' work.

## Current profile

| Area | Rating | Evidence | Practice direction |
| --- | --- | --- | --- |
| Java language and standard library | Not observed | — | Establish current Java baseline. |
| Spring Boot and dependency injection | Not observed | — | Begin with explicit application scaffolding task. |
| Domain and API design | Not observed | — | Practice translating confirmed behavior into contracts. |
| PostgreSQL and data integrity | Not observed | — | Start with schema and migration fundamentals when needed. |
| Automated testing | Not observed | — | Require focused tests from the first code task. |
| TypeScript and React | Not observed | — | Establish baseline after an API capability exists or via an isolated setup task. |
| CLI design | Not observed | — | Decide language and interface through an ADR first. |
| Git and pull-request discipline | Not observed | — | Observe first implementation PR. |
| Documentation and decision records | Not observed | — | Require developer-authored docs for their decisions. |
| Production operations and security | Not observed | — | Introduce proportionately with each boundary. |

## Calibration rules

- Cite review-log entries or PRs for every rating above `Not observed`.
- Require repeated evidence before `Independent` or `Consistent`.
- Record regressions neutrally; one mistake does not erase prior mastery.
- Select tasks that reinforce one developing skill and introduce at most one
  major new concept.
- Never infer developer ability from agent-authored repository scaffolding.
