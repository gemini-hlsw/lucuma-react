const path = require("path");

const rootDir = path.resolve(__dirname, "../../../..");
const resourcesDir = path.resolve(rootDir, "src/main/resources");
const Webpack = require("webpack");

module.exports.rootDir = rootDir;
module.exports.resourcesDir = resourcesDir;
module.exports.devServer = ({ host, port } = {}) => ({
  devServer: {
    stats: "errors-only",
    host, // Defaults to `localhost`
    port, // Defaults to 8080
    overlay: true,
    historyApiFallback: true,
    contentBase: [__dirname, rootDir],
    hot: true,
    historyApiFallback: true
  },
  module: {
    noParse: function(content) {
      return content.endsWith("-fastopt");
    }
  },
  plugins: [new Webpack.HotModuleReplacementPlugin()]
});
module.exports.resolve = () => ({
  resolve: {
    alias: {
      // resources: resourcesDir,
      // node_modules: path.resolve(__dirname, "node_modules"),
      sjs: __dirname
    }
    //   modules: [path.resolve(__dirname, "node_modules"), parts.resourcesDir]
  }
});
