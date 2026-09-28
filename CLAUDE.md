# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A sample project showing how to use Selenide with Cucumber (JUnit 4 runner) and Maven. The tests run against the live site https://duckduckgo.com in a real Chrome browser, so results depend on network access and on DuckDuckGo's current markup (selectors like `[data-testid="mainline"]`, `[data-testid="duckbar"]`, `[data-testid="zci-images"]`).

Everything lives under `src/test`. There is no main code.

## Commands

- Run all tests: `mvn test` (`test` is also the default goal, so plain `mvn` works too)
- Run one feature file: `mvn test -Dcucumber.features=src/test/resources/org/selenide/examples/cucumber/text-search.feature`
- Run scenarios by name: `mvn test -Dcucumber.filter.name="user can search images"`
- HTML test report (same as CI): `mvn org.apache.maven.plugins:maven-surefire-report-plugin:report-only`

Requirements: Java 25. CI (`.github/workflows/build.yml`) installs `ffmpeg` and runs under Xvfb, because `selenide-video-recorder` needs both.

## Architecture

- `SearchTest` is the only JUnit class. It is an empty `@RunWith(Cucumber.class)` runner. Cucumber finds `.feature` files and glue code by the runner's package (`org.selenide.examples.cucumber`), so feature files must stay under `src/test/resources/org/selenide/examples/cucumber/`, and step and hook classes must stay in that Java package.
- Step definitions are shared across all features. For example, `an open browser with duckduckgo.com` (in `TextSearchStepDefinitions`) is also used by `image-search.feature`. Step text must be globally unique: the two `enterKeyword` methods have different wording for this reason.
- `TextReport` holds the Cucumber hooks:
  - `@BeforeAll` sets global Selenide `Configuration`: reports go to `target/surefire-reports`, downloads go to `target/downloads`, and Chrome runs with `--disable-blink-features=AutomationControlled` so it is less likely to be flagged as a bot.
  - `@Before` and `@After` wrap each scenario in a Selenide `SimpleReport` and a `VideoRecorder`. The video is kept only when the scenario fails (its path is logged in the scenario); otherwise it is discarded.
- The browser is managed implicitly by Selenide's static API (`open`, `$`, `$$`). No driver setup code is needed.

## Dependency updates

Dependabot bumps versions, and a CI job auto-merges its minor-version PRs with rebase after the tests pass. Versions for Selenide, Cucumber and Surefire are set as properties in `pom.xml`. The `cucumber-bom` import in `<dependencyManagement>` is required: Cucumber 8's transitive dependencies (`messages`, `query`, `gherkin`, the formatters) declare version ranges that don't overlap, and Maven can't resolve them without the BOM. Keep `selenide-junit4` and `selenide-video-recorder` on the same version (`selenideVersion`).
