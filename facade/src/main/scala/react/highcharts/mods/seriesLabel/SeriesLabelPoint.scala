package react.highcharts.mods.seriesLabel

import scalajs.js
import js.|
import typings.highcharts.mod.Series
import typings.highcharts.mod.Point
import typings.highcharts.mod.SVGElement
import typings.highcharts.mod.BBoxObject
import typings.highcharts.mod.PositionObject

// https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts

@js.native
trait SeriesLabelPoint extends Point {
  val chartCenterY: js.UndefOr[Double] = js.native
  val chartX: js.UndefOr[Double] = js.native
  val chartY: js.UndefOr[Double] = js.native
  val plotX: Double = js.native // I can't find where this is actually defined
  val plotY: Double = js.native // I can't find where this is actually defined
}
