const generatedConfig = require("./scalajs.webpack.config");
const parts = require("./webpack.parts");
const merge = require("webpack-merge");

const commonConfig = merge(generatedConfig, parts.resolve());

module.exports = commonConfig;
