console.log("Here");
import App from "root/demo-fastopt.js";
console.log(App);

if (module.hot) {
  module.hot.accept();
  App.Demo.main();
}
