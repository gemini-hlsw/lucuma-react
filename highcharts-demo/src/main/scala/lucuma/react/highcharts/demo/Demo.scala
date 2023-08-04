// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.highcharts.demo

import japgolly.scalajs.react.callback.Callback
import lucuma.typed.highcharts.highchartsStrings.areaspline
import lucuma.typed.highcharts.mod.AxisTypeValue
import lucuma.typed.highcharts.mod.CSSObject
import lucuma.typed.highcharts.mod.Chart_
import lucuma.typed.highcharts.mod.CreditsOptions
import lucuma.typed.highcharts.mod.CursorValue
import lucuma.typed.highcharts.mod.LegendOptions
import lucuma.typed.highcharts.mod.Options
import lucuma.typed.highcharts.mod.PlotAreasplineOptions
import lucuma.typed.highcharts.mod.PlotOptions
import lucuma.typed.highcharts.mod.PlotSeriesOptions
import lucuma.typed.highcharts.mod.Point
import lucuma.typed.highcharts.mod.PointMarkerOptionsObject
import lucuma.typed.highcharts.mod.PointOptionsObject
import lucuma.typed.highcharts.mod.PositionObject
import lucuma.typed.highcharts.mod.Series
import lucuma.typed.highcharts.mod.SeriesAreasplineOptions
import lucuma.typed.highcharts.mod.SeriesClickEventObject
import lucuma.typed.highcharts.mod.SeriesEventsOptionsObject
import lucuma.typed.highcharts.mod.SeriesLabelOptionsObject
import lucuma.typed.highcharts.mod.SeriesOptionsType
import lucuma.typed.highcharts.mod.SeriesStatesHoverOptionsObject
import lucuma.typed.highcharts.mod.SeriesStatesOptionsObject
import lucuma.typed.highcharts.mod.TitleOptions
import lucuma.typed.highcharts.mod.TooltipOptions
import lucuma.typed.highcharts.mod.TooltipPositionerCallbackFunction
import lucuma.typed.highcharts.mod.TooltipShapeValue
import lucuma.typed.highcharts.mod.XAxisOptions
import lucuma.typed.highcharts.mod.XAxisPlotBandsLabelOptions
import lucuma.typed.highcharts.mod.XAxisPlotBandsOptions
import lucuma.typed.highcharts.mod.YAxisLabelsOptions
import lucuma.typed.highcharts.mod.YAxisOptions
import lucuma.typed.highcharts.mod.YAxisTitleOptions
import org.scalajs.dom
import lucuma.react.common.*
import lucuma.react.highcharts.Chart
import lucuma.react.highcharts.Highcharts
import lucuma.react.highcharts.WrapProceed
import lucuma.react.highcharts.implicits.*
import lucuma.react.highcharts.mods.seriesLabel.SeriesLabelPoint
import lucuma.react.highcharts.mods.seriesLabel.SeriesLabelSeries
import lucuma.react.highcharts.seriesLabel

import scala.annotation.nowarn
import scala.scalajs.js

import js.annotation.*
import js.JSConverters.*

// Reproducing https://jsfiddle.net/rpiaggio/xdz4pLg9/105/

@JSExportTopLevel("Demo")
object Demo {

  @JSExport
  def main(): Unit = {

    // Enable series-label Mod
    seriesLabel.enable

    // we need the prototype of the series mod Chart!
    Highcharts.wrapThis(
      Highcharts.Chart.asInstanceOf[js.Dynamic].prototype,
      "drawSeriesLabels",
      (chart: Chart_, proceed: WrapProceed) => {
        // chart should be of a subtype of Chart_ specific to series-label mod...
        // https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts
        // For the moment, we only make the Series and Point specific, which suffices for our use.

        proceed.apply(chart)

        val plotTop  = chart.plotTop
        val plotLeft = chart.plotLeft
        val height   =
          chart.yAxis(0).asInstanceOf[js.Dynamic].height.asInstanceOf[Double]
        val series   = chart.series.asInstanceOf[js.Array[SeriesLabelSeries]]

        series.foreach { s =>
          val left        = s.data(0).asInstanceOf[SeriesLabelPoint].plotX.toOption.get
          val right       =
            s.data(s.data.length - 1).asInstanceOf[SeriesLabelPoint].plotX.toOption.get
          val center      = (right - left) / 2
          val labelHeight = s.labelBySeries
            .map(_.attr("height").asInstanceOf[Double] / 2)
            .getOrElse(0.0)

          s.labelBySeries.foreach(
            _.attr("x", (plotLeft + left + center - labelHeight).toString)
              .attr("y", (height + plotTop - 20).toString)
              .attr("rotation", "-90")
          )
        }
      }
    )

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    val tooltipPositioner
      : TooltipPositionerCallbackFunction = // https://www.highcharts.com/forum/viewtopic.php?t=41971
      (tooltip, _, labelHeight, _) =>
        Option(tooltip.chart.hoverPoint.asInstanceOf[Point]).map { point =>
          val s = point.series

          val left   = s.data(0).asInstanceOf[SeriesLabelPoint].plotX.toOption.get
          val right  =
            s.data(s.data.length - 1).asInstanceOf[SeriesLabelPoint].plotX.toOption.get
          val center = (right - left) / 2
          // Assumes even X spacing (and odd number of points)
          val y      = s
            .data((s.data.length / 2).toInt)
            .asInstanceOf[SeriesLabelPoint]
            .plotY
            .toOption
            .get - labelHeight

          PositionObject(left + center, y)
        }.get

    @nowarn
    val options = Options()
      .setTitle(TitleOptions().setText(""))
      .setLegend(LegendOptions().setEnabled(false))
      .setCredits(CreditsOptions().setEnabled(false))
      .setTooltip(
        TooltipOptions()
          .setHeaderFormat("{series.name}")
          .setPointFormat("")
          .setSnap(0)
          .setAnimation(false)
          .setShape(TooltipShapeValue.square)
          .setPositioner(tooltipPositioner)
      )
      .setXAxis(
        XAxisOptions()
          .setType(AxisTypeValue.datetime)
          .setPlotBands(
            List(
              XAxisPlotBandsOptions()
                .setFrom(1584655030000.0)
                .setTo(1584655120000.0)
                .setColor("#FFEFEF")
                .setLabel(
                  XAxisPlotBandsLabelOptions()
                    .setText("<b><i>Fault</i></b>")
                    .setUseHTML(true)
                ),
              XAxisPlotBandsOptions()
                .setFrom(1584655180000.0)
                .setTo(1584655360000.0)
                .setColor("#EFFFFF")
                .setLabel(
                  XAxisPlotBandsLabelOptions()
                    .setText("<i>Weather</i>")
                    .setUseHTML(true)
                )
            ).toJSArray
          )
      )
      .setYAxis(
        List(
          YAxisOptions()
            .setTitle(YAxisTitleOptions().setText(""))
            .setAllowDecimals(false)
            .setMax(90)
            .setTickInterval(10)
            .setLabels(YAxisLabelsOptions().setFormat("{value}°")),
          YAxisOptions()
            .setLinkedTo(0)
            .setOpposite(true)
            .setTitle(YAxisTitleOptions().setText(""))
            .setLabels(YAxisLabelsOptions().setFormat("{value}°"))
        ).toJSArray
      )
      .setPlotOptions(
        PlotOptions()
          .setAreaspline(
            PlotAreasplineOptions()
              .setTrackByArea(true)
              .setLabel(
                SeriesLabelOptionsObject()
                  .setStyle(CSSObject().setColor("black"))
              )
          )
          .setSeries(
            PlotSeriesOptions()
              .setLineWidth(0)
              .setMarker(PointMarkerOptionsObject().setEnabled(false))
              .setStates(
                SeriesStatesOptionsObject()
                  .setHover(SeriesStatesHoverOptionsObject().setEnabled(false))
              )
              // .setOpacity(0.5)
              .setCursor(CursorValue.pointer)
              .setEvents(
                SeriesEventsOptionsObject()
                  .setClick((s: Series, _: SeriesClickEventObject) => dom.window.alert(s.name))
              )
          )
      )
      .setSeries(
        List(
          SeriesAreasplineOptions(areaspline, js.undefined, areaspline)
            .setName("Observation-1")
            .setData(
              List(
                (1584655000000.0, 10.0),
                (1584655060000.0, 17.0),
                (1584655120000.0, 20.0),
                (1584655180000.0, 18.0),
                (1584655240000.0, 14.0)
              )
            ),
          SeriesAreasplineOptions(areaspline, js.undefined, areaspline)
            .setName("Observation-2")
            .setData(
              List(
                (1584655300000.0, 19.0),
                (1584655360000.0, 25.0),
                (1584655420000.0, 30.0),
                (1584655480000.0, 34.0),
                (1584655540000.0, 36.0)
              )
            )
        ).map(_.asInstanceOf[SeriesOptionsType]).toJSArray
      )

    def onCreated(chart: Chart_) = Callback(
      // dom.console.log(chart)
      chart
        .series(0)
        .addPoint(
          PointOptionsObject()
            .setX(1584655110000.0)
            .setY(35.0)
        )
    )

    Chart(options, onCreated _).renderIntoDOM(container)

    ()
  }
}
