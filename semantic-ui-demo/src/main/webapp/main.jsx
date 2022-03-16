import "./theme/semantic.less";
import "./less/style.less";
import "react-semantic-toasts/styles/react-semantic-alert.css";

import { SUIMain } from "@sjs/main.js";
SUIMain.main();

if (import.meta.hot) {
  import.meta.hot.accept();
}
