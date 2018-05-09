const generatedConfig = require("./scalajs.webpack.config");
const parts = require("./webpack.parts");
const merge = require("webpack-merge");

const commonConfig = merge([
  generatedConfig,
  parts.resolve()
  // parts.devServer()
]);
// console.log(parts.resolve());
// console.log(commonConfig);

module.exports = commonConfig;
