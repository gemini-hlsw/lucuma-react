package react.highcharts

import scalajs.js
import js.annotation.JSImport

package object seriesLabel {
  @js.native
  @JSImport("highcharts/es-modules/masters/modules/series-label.src.js", JSImport.Namespace)
  object enable extends js.Any
}
