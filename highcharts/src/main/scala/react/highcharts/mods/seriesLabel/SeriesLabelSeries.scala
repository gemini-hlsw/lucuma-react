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
trait LabelClearPointObject extends PositionObject {
  val connectorPoint: js.UndefOr[Point]
  val weight: Double
}

@js.native
trait SeriesLabelSeries extends Series {
  val interpolatedPoints: js.UndefOr[js.Array[Point]]
  val labelBySeries: js.UndefOr[SVGElement]
  val sum: js.UndefOr[Double]
  def checkClearPoint(
    x:                           Double,
    y:                           Double,
    bBox:                        BBoxObject,
    checkDistance:               js.UndefOr[Boolean] = js.undefined
  ): Boolean | LabelClearPointObject
  def drawSeriesLabels(): Unit
  def getPointsOnGraph(): js.UndefOr[js.Array[Point]]
  def labelFontSize(minFontSize: Double, maxFontSize: Double): String
}
