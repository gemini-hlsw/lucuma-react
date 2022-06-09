package react.highcharts.mods.seriesLabel

import scalajs.js
import js.|
import gpp.highcharts.mod.Series
import gpp.highcharts.mod.Point
import gpp.highcharts.mod.SVGElement
import gpp.highcharts.mod.BBoxObject
import gpp.highcharts.mod.PositionObject

// https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts

@js.native
trait SeriesLabelPoint extends Point {
  val chartCenterY: js.UndefOr[Double] = js.native
  val chartX: js.UndefOr[Double]       = js.native
  val chartY: js.UndefOr[Double]       = js.native
  val plotX: Double                    = js.native // I can't find where this is actually defined
  val plotY: Double                    = js.native // I can't find where this is actually defined
}
