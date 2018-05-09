import App from "sjs/demo-fastopt.js";

if (module.hot) {
  module.hot.accept();
  App.Demo.main();
}
