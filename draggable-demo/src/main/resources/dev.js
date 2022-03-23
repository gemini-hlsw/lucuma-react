import App from "sjs/demo-fastopt.js";
import "./styles.css";

if (module.hot) {
  module.hot.accept();
  App.Demo.main();
}
