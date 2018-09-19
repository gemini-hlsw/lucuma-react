const path = require("path");
const merge = require("webpack-merge");
const commonConfig = require("./webpack.common");
const Webpack = require("webpack");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const parts = require("./webpack.parts");

const publicFolderName = "../docs";

const productionConfig = merge(
  parts.extractCSS({ use: ["css-loader", parts.autoprefix()] }),
  parts.minifyCSS({
    options: {
      discardComments: {
        removeAll: true
      }
    }
  }),
  {
    mode: "production",
    entry: {
      app: [path.resolve(parts.resourcesDir, "./app.js")]
    },
    output: {
      filename: "[name].[chunkhash].js",
      path: path.resolve(parts.rootDir, publicFolderName),
      publicPath: "/scalajs-react-draggable"
    },
    plugins: [
      new HtmlWebpackPlugin({
        title: "ScalaJS Draggable Demo",
        filename: "index.html",
        chunks: ["app"]
      })
    ]
  }
);

module.exports = merge(commonConfig, productionConfig);
