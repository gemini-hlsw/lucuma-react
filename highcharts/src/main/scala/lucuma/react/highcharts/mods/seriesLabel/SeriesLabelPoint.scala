// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.highcharts.mods.seriesLabel

import lucuma.typed.highcharts.mod.Point

import scalajs.js

// https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts

@js.native
trait SeriesLabelPoint extends Point {
  val chartCenterY: js.UndefOr[Double] = js.native
  val chartX: js.UndefOr[Double]       = js.native
  val chartY: js.UndefOr[Double]       = js.native
}
