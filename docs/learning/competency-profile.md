# Engineering competency profile

Last evidence review: 2026-09-08 — [PR #12](https://github.com/danrodero/schizogenic/pull/12)
(technical review complete; developer merged as `3e3ed04`).
Ratings remain based on PR #4; the explicitly agent-completed migration tests
provide examples for guided practice and do not establish independent skill.

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
| Java language and standard library | Developing | [PR #4](https://github.com/danrodero/schizogenic/pull/4) | Practice Java through behavior with meaningful assertions. |
| Spring Boot and dependency injection | Developing | [PR #4](https://github.com/danrodero/schizogenic/pull/4) | Reinforce configuration and test boundaries with a real external dependency. |
| Domain and API design | Not observed | — | Practice translating confirmed behavior into contracts. |
| PostgreSQL and data integrity | Not observed | — | Start with schema and migration fundamentals when needed. |
| Automated testing | Developing | [PR #4](https://github.com/danrodero/schizogenic/pull/4) | Write tests whose claims precisely match their observable boundary. |
| TypeScript and React | Not observed | — | Establish baseline after an API capability exists or via an isolated setup task. |
| CLI design | Not observed | — | Decide language and interface through an ADR first. |
| Git and pull-request discipline | Developing | [PR #4](https://github.com/danrodero/schizogenic/pull/4) | Keep PR evidence complete and accurate as scopes become more complex. |
| Documentation and decision records | Developing | [PR #4](https://github.com/danrodero/schizogenic/pull/4) | State decisions and consequences precisely and keep PR documentation aligned. |
| Production operations and security | Not observed | — | Introduce proportionately with each boundary. |

## Calibration rules

- Cite review-log entries or PRs for every rating above `Not observed`.
- Require repeated evidence before `Independent` or `Consistent`.
- Record regressions neutrally; one mistake does not erase prior mastery.
- Select tasks that reinforce one developing skill and introduce at most one
  major new concept.
- Never infer developer ability from agent-authored repository scaffolding.
