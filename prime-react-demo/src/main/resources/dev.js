import "resources/less/style.scss"

import App from "sjs/demo-fastopt.js";

if (module.hot) {
  module.hot.accept();
  App.DemoMain.main();
}
