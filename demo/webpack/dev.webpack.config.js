const path = require("path");
const Webpack = require("webpack");
const Merge = require("webpack-merge");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const parts = require("./webpack.parts");
const ScalaJSConfig = require("./scalajs.webpack.config");

const isDev = true;
const isDevServer = process.argv.some(s => s.match(/webpack-dev-server\.js$/));

const Web = Merge(
  ScalaJSConfig,
  parts.resolve,
  parts.resolveSemanticUI,
  parts.resourceModules,
  parts.extractCSS({
    devMode: isDev,
    use: ["css-loader", parts.lessLoader(isDev), parts.sassLoader(isDev)]
  }),
  parts.extraAssets,
  parts.fontAssets,
  {
    mode: "development",
    entry: {
      demo: [path.resolve(parts.resourcesDir, "./dev.js")]
    },
    output: {
      publicPath: "/" // Required to make the url navigation work
    },
    module: {
      // Don't parse scala.js code. it's just too slow
      noParse: function(content) {
        return content.endsWith("-fastopt");
      }
    },
    // Custom dev server for the demo as we need a ws proxy
    devServer: {
      host: "0.0.0.0",
      hot: true,
      contentBase: [__dirname, parts.rootDir],
      historyApiFallback: true
    },
    plugins: [
      // Needed to enable HMR
      new Webpack.HotModuleReplacementPlugin(),
      new HtmlWebpackPlugin({
        filename: "index.html",
        title: "Demo for react-datepicker",
        chunks: ["demo"]
      })
    ]
  }
);

// Enable status bar to display on the page when webpack is reloading
if (isDevServer) {
  Web.entry.demo.push("webpack-dev-server-status-bar");
}

module.exports = Web;
