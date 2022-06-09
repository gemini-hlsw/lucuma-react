import react from "@vitejs/plugin-react";
import path from "path";

// https://vitejs.dev/config/
export default ({ command, mode }) => {
  const sjs =
    mode == "production"
      ? path.resolve(__dirname, "target/scala-3.1.2-RC2/highchartsdemo-opt/")
      : path.resolve(__dirname, "target/scala-3.1.2-RC2/highchartsdemo-fastopt/");
  return {
    root: "highcharts-demo/src/main/webapp",
    resolve: {
      alias: [
        {
          find: "@sjs",
          replacement: sjs,
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
