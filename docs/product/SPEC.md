# Schizogenic product specification

Status: Draft

Last updated: 2026-07-28

## 1. Purpose

Schizogenic is a personal organization system whose first usable module tracks
time. It must provide a responsive web UI and a CLI and leave clear boundaries
for future modules such as finances, habits, and goals.

This specification distinguishes confirmed requirements from proposed behavior.
Only confirmed requirements and explicitly accepted task criteria authorize
implementation.

## 2. Confirmed requirements

| ID | Requirement |
| --- | --- |
| PROD-001 | Time tracking is the first product module. |
| PROD-002 | A user can operate time-tracking capabilities from a web UI. |
| PROD-003 | The web UI is usable on mobile-size viewports. |
| PROD-004 | A user can operate time-tracking capabilities from a CLI. |
| PROD-005 | The design can add independently bounded tracking modules later. |
| PROD-006 | The backend uses Java 25, Spring Boot 4.x, and PostgreSQL. |
| PROD-007 | The web client uses TypeScript and React. |
| PROD-008 | The development environment is reproducible through a Nix flake. |

## 3. Proposed first-release behavior

These capabilities are a starting hypothesis based on the requested
Toggl-like workflow. They require confirmation before assignment.

| ID | Proposed capability |
| --- | --- |
| TIME-001 | Start a timer with an optional description. |
| TIME-002 | Show the currently running timer and its elapsed duration. |
| TIME-003 | Stop the running timer and preserve the completed time entry. |
| TIME-004 | Create a completed entry by providing start and end times. |
| TIME-005 | View time entries in reverse chronological order. |
| TIME-006 | Correct or delete an existing time entry. |
| TIME-007 | Associate an entry with a project and zero or more tags. |
| TIME-008 | Filter history by date range, project, and tag. |
| TIME-009 | Summarize tracked duration for a selected date range. |

## 4. Candidate domain rules

The following rules must be decided explicitly:

- Whether one user may have more than one running timer.
- Whether completed time entries may overlap.
- Whether elapsed duration is stored or derived from timestamps.
- Which timestamp is authoritative when a timer is stopped.
- How time zones and daylight-saving transitions are represented and displayed.
- Whether deletion is permanent or recoverable.
- Whether descriptions, projects, and tags have length or uniqueness rules.

Until these are confirmed, assignments should focus on scaffolding or on a
small behavior whose semantics have been agreed in its issue.

## 5. Quality attributes

### Confirmed

- The development workflow must be reproducible from a clean checkout.
- Each implemented behavior must have automated tests written by the developer.
- Web behavior must be keyboard-usable and responsive.
- Credentials and local data must not be committed.

### Proposed

- API changes should be backward-compatible within a released major version.
- Mutating operations should be safe against accidental duplicate requests.
- Stored timestamps should use UTC instants while clients preserve display-zone
  intent where the behavior requires it.
- Common operations should return promptly under personal-use workloads; exact
  service-level objectives remain open.

## 6. First usable release

The first release is reached when the confirmed, minimal timer workflow is
available through both web and CLI, persists in PostgreSQL, has production-grade
automated tests and documentation, and can be run from a clean checkout using
documented commands.

The exact timer workflow remains open until the proposed capabilities and domain
rules above are refined.

## 7. Non-goals for the first release

- Expenses, income, habits, goals, or analytics beyond time tracking.
- Native mobile applications.
- Microservices or independent deployment of modules.
- Team billing, invoicing, payroll, or enterprise administration.
- Scale optimizations unsupported by measured needs.

## 8. Open product questions

1. Is the first release strictly single-user? If so, is authentication omitted
   or still required?
2. Which proposed time-tracking capabilities are essential for the first
   usable release?
3. What are the exact rules for running and overlapping entries?
4. Are projects, tags, billable flags, notes, or favorites required?
5. What reports and export formats are needed?
6. Must either client work offline?
7. Which operating systems must the CLI support?
8. What data portability, backup, and retention guarantees are expected?

## 9. Change discipline

Refine this specification through product conversation before implementation.
Assign stable IDs to new requirements, record unresolved choices as open
questions, and create an ADR when a decision is primarily technical and has
lasting consequences.
