# lucuma-react

Common utilities and facades for React and Scala.js

Example running a demo:
```sh
sbt semanticUIDemo/fastLinkJS
npm install
vite -c semantic-ui-demo/vite.config.js
```

Note that vite should be run from the root directory, but point at the config file for a particular model. To generate/update the vite config for a module use `sbt viteConfigGenerate`. It will get stale with every Scala 3 patch release.

## Modules

- lucuma-react-common
- lucuma-react-cats
- lucuma-react-test

## Facades

- lucuma-react-beautiful-dnd
- lucuma-react-clipboard
- lucuma-react-datepicker
- lucuma-react-draggable
- lucuma-react-grid-layout
- lucuma-react-highcharts
- lucuma-react-semantic-ui
- lucuma-react-table
- lucuma-react-tree
- lucuma-react-virtuoso

## Contributing

To add a new module follow roughly these steps:
1. Add the module(s) to the build, and add them to the root aggregate project.
2. If there is more than one module (e.g. facade+demo), create an aggregate project for them.
3. Add the aggregate (or the sole module) to the projects list. This adds a separate CI job.
4. Use `yarn add --dev whatever` to add the npm dep to the `package.json` in the root. Except for any type-related dependencies for ScalablyTyped, see the next step.
5. Type-related dependencies should be put in a `package.json` specific for that module as a normal dep (not a dev dep). This helps to focus ScalablyTyped on generating facades specifically for that dep. Also, make sure to register that `package.json` with the dependabot config

This repo uses a single `package.json` at the root that is used by all the modules. This ensures that the version of shared dependencies are kept in sync. However, each module has its own `yarn.lock`, mostly because this is how ScalaJS Bundler wants it. Unfortunately, this means that all the lockfiles must be kept synchronized.

If a PR is complaining about outdated lockfiles, you can run the "Update lockfiles" workfow on that branch. Lucuma-steward will run `yarn install` and `sbt npmInstallDependencies` and commit the changes. Of course, you can also run these steps locally and commit.

https://github.com/gemini-hlsw/lucuma-react/actions/workflows/update-lockfiles.yml
