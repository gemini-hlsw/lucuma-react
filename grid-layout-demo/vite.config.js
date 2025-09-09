import react from "@vitejs/plugin-react";
import path from "path";

// https://vitejs.dev/config/
export default ({ command, mode }) => {
  const sjs =
    mode == "production"
      ? path.resolve(__dirname, "target/scala-3.7.3/gridlayoutdemo-opt/")
      : path.resolve(__dirname, "target/scala-3.7.3/gridlayoutdemo-fastopt/");
  const webapp = path.resolve(__dirname, "src/main/webapp/");
  const themeConfig = path.resolve(webapp, "theme/theme.config");
  const themeSite = path.resolve(webapp, "theme/site");
  return {
    root: "grid-layout-demo/src/main/webapp",
    resolve: {
      alias: [
        {
          find: "@sjs",
          replacement: sjs,
        },
        {
          find: "../../theme.config",
          replacement: themeConfig,
        },
        {
          find: "theme/site",
          replacement: themeSite,
        },
      ],
    },
    server: {
      watch: {
        ignored: [
          function ignoreThisPath(_path) {
            const sjsIgnored =
              _path.includes("/target/stream") ||
              _path.includes("/zinc/") ||
              _path.includes("/classes");
            return sjsIgnored;
          },
        ],
      },
    },
    build: {
      outDir: path.resolve(__dirname, "../docs"),
    },
    plugins: [react()],
  };
};
