import App from "sjs/demo-fastopt.js";
import React from "react";

if (module.hot) {
  module.hot.accept();
  App.DemoMain.main();
}
