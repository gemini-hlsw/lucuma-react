# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

# lucuma-react

Scala.js facades for React component libraries, built on scalajs-react. This repo hosts only
manually-maintained facades; auto-generated ScalablyTyped facades live separately in
[lucuma-typed](https://github.com/gemini-hlsw/lucuma-typed).

## Build environment

The build targets **JDK 25** (enforced by sbt-typelevel — an older JDK fails with
"Target JDK is 25 but you are using an older JDK"). The pinned toolchain is provided by the Nix
devshell, so run sbt through it:

```bash
nix develop -c sbt <task>
```

`flake.nix` pins the JDK (`jdk.package`); keep it in sync with the build's target. If sbt picks up
the wrong JDK, a stale sbt/BSP server (often kept alive by an IDE/Metals) is usually the culprit —
`sbt shutdown` and retry.

## Build Commands

```bash
# Run all tests
nix develop -c sbt test

# Run tests for a specific module
nix develop -c sbt "gridLayout/test"

# Format code (run before committing)
nix develop -c sbt scalafmtAll

# Lint check
nix develop -c sbt "scalafixAll --check"

# Compile full project
nix develop -c sbt compile

# Run a demo (example: grid-layout) — vite runs from the root, pointed at the module's config
nix develop -c sbt gridLayoutDemo/fastLinkJS && npx vite -c grid-layout-demo/vite.config.js

# Regenerate a demo's vite config (goes stale on every Scala 3 patch release)
nix develop -c sbt gridLayoutDemo/viteConfigGenerate
```

## Key Versions

- Scala 3.8.3 · scalajs-react 3.0.0 · cats 2.13.0 · lucuma-typed 0.11.1

## Architecture

Multi-module monorepo using a **facade + demo** pattern. Each facade module wraps one JS React
library (`gridLayout`→react-grid-layout, `tanstackTable`→@tanstack/*, `highcharts`, `primeReact`,
`markdown`, `datepicker`, `draggable`, `resizable`, `resizeDetector`, `floatingui`, `fontAwesome`,
`hotkeys`, `circularProgressbar`, `pragmaticDnd`, `clipboard`, `virtuoso`, …). `beautifulDnd` is
deprecated in favor of `pragmaticDnd`.

Core modules: `common` (shared base types and the `GenericComponent*` machinery), `cats`, and
`test` (`TestUtils` with `assertRender` / `assertOuterHTML`).

In `build.sbt`, most facades pair a facade module with a `*Demo` module under a `root*` aggregate
that also defines a CI job. Demos use `NoPublishPlugin`.

### Facade module pattern

A facade typically has three layers — understanding one facade (e.g. `grid-layout/`) transfers to
all of them:

1. **Public Scala component** — a `final case class` with ergonomic Scala-typed fields, extending a
   `GenericComponent*` alias from `common` (e.g. `GenericComponentPAC[Props, Self]` for
   props+children). It implements `cprops` (build the raw props), `component`, and `addModifiers`.
   The matching letters encode the shape: `P`=props, `A`=apply-returns-self, `C`=children,
   `F`=has-facade — `Fn` variants wrap function components.
2. **Raw props trait** — a `@js.native trait ... extends js.Object` mirroring the JS component's
   props 1:1, plus a `props`/`rawprops` builder that translates the Scala case class into the raw
   object. The component is bound with `JsComponent.force[...](RawComponent)` where `RawComponent`
   is a `@JSImport(<npm-pkg>, ...)` object.
3. **`raw` subpackage** — low-level `js.Object` bindings, `@JSImport` function/namespace bindings,
   and `js.Function*` callback type aliases. Scala-friendly wrapper types (opaque types, enums with
   `EnumValue` givens, `Eq` instances) live in the module's `package.scala` with `fromRaw`/`toRaw`
   conversions.

When the underlying JS library changes its API, prefer keeping the **public Scala case-class API
stable** and absorbing the change in the raw-props builder, so downstream consumers don't churn.

## Testing

- MUnit + utest for unit tests; discipline-munit for typeclass law tests.
- `lucuma-jsdom` enables server-side render tests on Node — `ReactTestUtils.withRenderedSync` plus
  `assertOuterHTML` assert on real rendered DOM, which also verifies that `@JSImport` bindings
  resolve against the actual npm package at runtime.

## Conventions

- Single root `package.json` shared by all modules (keeps shared dep versions in sync); add deps
  with `npm install --save-dev <pkg>`.
- License headers (BSD-3-Clause) required on all source files.
- Spaces, not tabs; no trailing whitespace.
