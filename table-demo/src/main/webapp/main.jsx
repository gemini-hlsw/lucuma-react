import "./style.less";

import { Demo } from "@sjs/main.js";
Demo.main()

if (import.meta.hot) {
  import.meta.hot.accept();
}
