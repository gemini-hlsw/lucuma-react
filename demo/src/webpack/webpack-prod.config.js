const path = require("path");
const merge = require("webpack-merge");
const commonConfig = require("./webpack.common");
const Webpack = require("webpack");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const parts = require("./webpack.parts");
console.log(parts);

const productionConfig = merge(parts.extractCSS({ use: "css-loader" }), {
  mode: "production",
  entry: {
    app: [path.resolve(parts.resourcesDir, "./app.js")]
  },
  plugins: [
    new HtmlWebpackPlugin({
      title: "ScalJS Draggable Demo",
      filename: "index.html",
      chunks: ["app"]
    })
  ]
});

module.exports = merge(commonConfig, productionConfig);
