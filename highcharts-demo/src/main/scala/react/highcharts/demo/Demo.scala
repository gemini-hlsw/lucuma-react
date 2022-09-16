// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.highcharts.demo

import japgolly.scalajs.react.callback.Callback
import org.scalajs.dom
import react.common._
import react.highcharts.Chart
import react.highcharts.Highcharts
import react.highcharts.WrapProceed
import react.highcharts.implicits._
import react.highcharts.mods.seriesLabel.SeriesLabelPoint
import react.highcharts.mods.seriesLabel.SeriesLabelSeries
import react.highcharts.seriesLabel
import reactST.highcharts.highchartsStrings.areaspline
import reactST.highcharts.mod.AxisTypeValue
import reactST.highcharts.mod.CSSObject
import reactST.highcharts.mod.Chart_
import reactST.highcharts.mod.CreditsOptions
import reactST.highcharts.mod.CursorValue
import reactST.highcharts.mod.LegendOptions
import reactST.highcharts.mod.Options
import reactST.highcharts.mod.PlotAreasplineOptions
import reactST.highcharts.mod.PlotOptions
import reactST.highcharts.mod.PlotSeriesOptions
import reactST.highcharts.mod.Point
import reactST.highcharts.mod.PointMarkerOptionsObject
import reactST.highcharts.mod.PointOptionsObject
import reactST.highcharts.mod.PositionObject
import reactST.highcharts.mod.Series
import reactST.highcharts.mod.SeriesAreasplineOptions
import reactST.highcharts.mod.SeriesClickEventObject
import reactST.highcharts.mod.SeriesEventsOptionsObject
import reactST.highcharts.mod.SeriesLabelOptionsObject
import reactST.highcharts.mod.SeriesOptionsType
import reactST.highcharts.mod.SeriesStatesHoverOptionsObject
import reactST.highcharts.mod.SeriesStatesOptionsObject
import reactST.highcharts.mod.TitleOptions
import reactST.highcharts.mod.TooltipOptions
import reactST.highcharts.mod.TooltipPositionerCallbackFunction
import reactST.highcharts.mod.TooltipShapeValue
import reactST.highcharts.mod.XAxisOptions
import reactST.highcharts.mod.XAxisPlotBandsLabelOptions
import reactST.highcharts.mod.XAxisPlotBandsOptions
import reactST.highcharts.mod.YAxisLabelsOptions
import reactST.highcharts.mod.YAxisOptions
import reactST.highcharts.mod.YAxisTitleOptions

import scala.scalajs.js

import js.annotation._
import js.JSConverters._

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
          SeriesAreasplineOptions((), (), areaspline)
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
          SeriesAreasplineOptions((), (), areaspline)
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
          PointOptionsObject(accessibility = js.undefined)
            .setX(1584655110000.0)
            .setY(35.0)
        )
    )

    Chart(options, onCreated _).renderIntoDOM(container)

    ()
  }
}
