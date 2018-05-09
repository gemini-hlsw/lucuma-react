const generatedConfig = require("./scalajs.webpack.config");
const path = require("path");
const merge = require("webpack-merge");
const common = require("./webpack.common");
const Webpack = require("webpack");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const parts = require("./webpack.parts");

const developmentConfig = {
  mode: "development",
  devtool: "none",
  entry: {
    app: [path.resolve(parts.resourcesDir, "./dev.js")]
  },
  resolve: {
    alias: {
      resources: parts.resourcesDir,
      node_modules: path.resolve(__dirname, "node_modules"),
      root: __dirname
    },
    modules: [path.resolve(__dirname, "node_modules"), parts.resourcesDir]
  },
  output: {
    path: __dirname,
    publicPath: "/"
  },
  module: {
    noParse: function(content) {
      return content.endsWith("-fastopt");
    },
    rules: [
      {
        test: /\.less$/,
        use: [
          {
            loader: "style-loader" // creates style nodes from JS strings
          },
          {
            loader: "css-loader" // translates CSS into CommonJS
          },
          {
            loader: "less-loader" // compiles Less to CSS
          }
        ]
      }
    ]
  },
  devServer: {
    hot: true,
    contentBase: [__dirname, parts.rootDir],
    historyApiFallback: true
  },
  plugins: [
    new Webpack.HotModuleReplacementPlugin(),
    new HtmlWebpackPlugin({
      title: "Draggable Demo",
      filename: "index.html",
      chunks: ["app"]
      // template: path.resolve(parts.resourcesDir, "./index.html")
    })
  ]
};
console.log(merge(generatedConfig, developmentConfig));

module.exports = merge(generatedConfig, developmentConfig);
