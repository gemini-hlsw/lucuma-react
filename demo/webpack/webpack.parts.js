const path = require("path");

const rootDir = path.resolve(__dirname, "../../../..");
const resourcesDir = path.resolve(rootDir, "src/main/resources");

module.exports.rootDir = rootDir;
module.exports.resourcesDir = resourcesDir;
module.exports.devServer = ({ host, port } = {}) => ({
  devServer: {
    stats: "errors-only",
    host, // Defaults to `localhost`
    port, // Defaults to 8080
    open: true,
    overlay: true,
    historyApiFallback: true,
    contentBase: [__dirname, rootDir],
    watchContentBase: true,
    hot: true
  }
});
module.exports.resolve = () => ({
  resolve: {
    alias: {
      // resources: resourcesDir;
      // node_modules: path.resolve(__dirname, "node_modules"),
      root: __dirname
    }
  }
});
