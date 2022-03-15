const path = require("path");
const merge = require("webpack-merge");
const commonConfig = require("./webpack.common");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const parts = require("./webpack.parts");

const developmentConfig = merge(parts.devServer(), {
  mode: "development",
  entry: {
    app: [path.resolve(parts.resourcesDir, "./dev.js")]
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
          {
            loader: "style-loader" // creates style nodes from JS strings
          },
          {
            loader: "css-loader" // translates CSS into CommonJS
          }
        ]
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      title: "RGL Demo",
      filename: "index.html",
      chunks: ["app"]
      // template: path.resolve(parts.resourcesDir, "./index.html")
    })
  ]
});

module.exports = merge(commonConfig, developmentConfig);
