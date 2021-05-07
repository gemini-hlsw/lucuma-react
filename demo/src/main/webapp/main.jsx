import "./styles.css";

import { Demo } from "@sjs/main.js";
Demo.main()
console.log('.')

if (import.meta.hot) {
  import.meta.hot.accept();
}
