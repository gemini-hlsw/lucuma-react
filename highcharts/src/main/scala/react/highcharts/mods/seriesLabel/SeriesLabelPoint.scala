// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.highcharts.mods.seriesLabel

import lucuma.typed.highcharts.mod.BBoxObject
import lucuma.typed.highcharts.mod.Point
import lucuma.typed.highcharts.mod.PositionObject
import lucuma.typed.highcharts.mod.SVGElement
import lucuma.typed.highcharts.mod.Series

import scalajs.js
import js.|

// https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts

@js.native
trait SeriesLabelPoint extends Point {
  val chartCenterY: js.UndefOr[Double] = js.native
  val chartX: js.UndefOr[Double]       = js.native
  val chartY: js.UndefOr[Double]       = js.native
}
