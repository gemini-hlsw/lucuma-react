// Copyright (c) 2016-2026 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.highcharts

import lucuma.typed.highcharts.mod.Axis
import lucuma.typed.highcharts.mod.AxisPlotBandsOptions
import lucuma.typed.highcharts.mod.AxisPlotLinesOptions
import lucuma.typed.highcharts.mod.PlotLineOrBand

import scala.scalajs.js

// Highcharts 13 typings dropped these Axis methods, but they still exist at runtime.
extension (a: Axis)
  def addPlotLine(options: AxisPlotLinesOptions): js.UndefOr[PlotLineOrBand] =
    a.asInstanceOf[js.Dynamic].addPlotLine(options).asInstanceOf[js.UndefOr[PlotLineOrBand]]

  def removePlotLine(id: String): Unit =
    a.asInstanceOf[js.Dynamic].removePlotLine(id)
    ()

  def addPlotBand(options: AxisPlotBandsOptions): js.UndefOr[PlotLineOrBand] =
    a.asInstanceOf[js.Dynamic].addPlotBand(options).asInstanceOf[js.UndefOr[PlotLineOrBand]]

  def removePlotBand(id: String): Unit =
    a.asInstanceOf[js.Dynamic].removePlotBand(id)
    ()
