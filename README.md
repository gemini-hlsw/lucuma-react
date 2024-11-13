# lucuma-react

Common utilities and facades for React and Scala.js

Example running a demo:
```sh
sbt primeReactDemo/fastLinkJS
npm install
npx vite -c primeReactDemo/vite.config.js
```

Note that vite should be run from the root directory, but point at the config file for a particular module. To generate/update the vite config for a module use `sbt viteConfigGenerate`. It will get stale with every Scala 3 patch release.

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
- lucuma-react-table
- lucuma-tanstack-table
- lucuma-react-virtuoso

## Contributing

This repository hosts only manually-maintained facades and wrapper APIs. Scalably Typed auto-generated facades are published separately in [lucuma-typed](https://github.com/gemini-hlsw/lucuma-typed).

To add a new module follow roughly these steps:
1. Add the module(s) to the build, and add them to the root aggregate project.
2. If there is more than one module (e.g. facade+demo), create an aggregate project for them.
3. Add the aggregate (or the sole module) to the projects list. This adds a separate CI job.
4. Use `npm install --save-dev whatever` to add the npm dep to the `package.json` in the root.

This repo uses a single `package.json` at the root that is used by all the modules. This ensures that the version of shared dependencies are kept in sync.
