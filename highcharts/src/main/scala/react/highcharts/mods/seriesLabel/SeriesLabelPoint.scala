// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.highcharts.mods.seriesLabel

import gpp.highcharts.mod.BBoxObject
import gpp.highcharts.mod.Point
import gpp.highcharts.mod.PositionObject
import gpp.highcharts.mod.SVGElement
import gpp.highcharts.mod.Series

import scalajs.js
import js.|

// https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts

@js.native
trait SeriesLabelPoint extends Point {
  val chartCenterY: js.UndefOr[Double] = js.native
  val chartX: js.UndefOr[Double]       = js.native
  val chartY: js.UndefOr[Double]       = js.native
  override val plotX: Double           = js.native // I can't find where this is actually defined
  override val plotY: Double           = js.native // I can't find where this is actually defined
}
