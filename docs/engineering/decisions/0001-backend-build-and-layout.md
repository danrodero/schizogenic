# ADR 0001: Backend build DSL and initial project layout

Status: Proposed

Date: 2026-07-28

Owners: Human developer and lead agent

## Context

SpringBoot application has to be initialized and verify minimal functionality.
Therefore, it was needed to choose which DSL (Domain Specific Language) for
configuring the build system.
Also a layout need to be chosen.

## Decision

Implemented the minimal version of a working backend java application.
Choose .gradle (Groovy DSL) and gradle wrapper for easy of use.
Choose the monorepo layout under backend as to limit the complexity
of this project at this initial stage.

## Alternatives considered

We considered using .gradle.kts for being statically type, but is more
complex and more difficult to read.
Other layouts may be more extensible but are unnecessary at this point.
Statically type DSL provide compile-time error checking and better IDE
autocompletion, however is a file that is rarely touched and should not
cause too many problems, instead we value more conciseness and legibility.
We consider a multi-module layout for separation of concerns, among api,
core and db.

## Consequences

Gradle is well supported and reliable, and using Groovy for the DSL make
it easy to read and be concise, which is important as the configuration
grows in size. Current layout work for our use case.

## Validation

Currently the application builds and runs. If at any moment we need a more
powerful build system, DSL or layout. For example, if compile times are too
slow, or we need to support shared libraries outside our backend project,
or even to speed up CI/CD pipeline through parallelization, all of them are
valid reasons to reconsider this decision.
