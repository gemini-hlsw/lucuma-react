// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.highcharts.mods.seriesLabel

import reactST.highcharts.mod.BBoxObject
import reactST.highcharts.mod.Point
import reactST.highcharts.mod.PositionObject
import reactST.highcharts.mod.SVGElement
import reactST.highcharts.mod.Series

import scalajs.js
import js.|

// https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts

@js.native
trait SeriesLabelPoint extends Point {
  val chartCenterY: js.UndefOr[Double] = js.native
  val chartX: js.UndefOr[Double]       = js.native
  val chartY: js.UndefOr[Double]       = js.native
}
