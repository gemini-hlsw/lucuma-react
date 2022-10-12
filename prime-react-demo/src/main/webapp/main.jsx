import "primeicons/primeicons.css"
// import "primereact/resources/themes/lara-light-blue/theme.css"
import "primereact/resources/themes/lara-dark-blue/theme.css"
import "primereact/resources/primereact.min.css"
import "./styles.scss"

import { Demo } from "@sjs/main.js";
Demo.main()

if (import.meta.hot) {
  import.meta.hot.accept();
}
