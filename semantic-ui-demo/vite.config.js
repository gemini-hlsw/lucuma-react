import react from "@vitejs/plugin-react";
import path from "path";

// https://vitejs.dev/config/
export default ({ command, mode }) => {
  const sjs =
    mode == "production"
      ? path.resolve(__dirname, "target/scala-3.2.1-RC1/semanticuidemo-opt/")
      : path.resolve(__dirname, "target/scala-3.2.1-RC1/semanticuidemo-fastopt/");
  const webapp = path.resolve(__dirname, "src/main/webapp/");
  const themeConfig = path.resolve(webapp, "theme/theme.config");
  const themeSite = path.resolve(webapp, "theme/site");
  return {
    root: "semantic-ui-demo/src/main/webapp",
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
      minify: 'terser',
      // minify: false,
      terserOptions: {
        // sourceMap: false,
        toplevel: true
      },
      outDir: path.resolve(__dirname, "../docs"),
    },
    plugins: [react()],
  };
};
