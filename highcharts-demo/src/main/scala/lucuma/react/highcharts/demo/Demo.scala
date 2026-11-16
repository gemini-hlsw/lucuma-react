// Copyright (c) 2016-2026 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.highcharts.demo

import japgolly.scalajs.react.React
import japgolly.scalajs.react.ReactDOMClient
import japgolly.scalajs.react.Reusable
import japgolly.scalajs.react.callback.Callback
import lucuma.react.highcharts.Chart
import lucuma.react.highcharts.implicits.*
import lucuma.typed.highcharts.mod as Highcharts
import lucuma.typed.highcharts.mod.AxisTypeValue
import lucuma.typed.highcharts.mod.Chart_
import lucuma.typed.highcharts.mod.CreditsOptions
import lucuma.typed.highcharts.mod.CursorValue
import lucuma.typed.highcharts.mod.LegendOptions
import lucuma.typed.highcharts.mod.Options
import lucuma.typed.highcharts.mod.PlotAreasplineOptions
import lucuma.typed.highcharts.mod.PlotOptions
import lucuma.typed.highcharts.mod.PlotSeriesOptions
import lucuma.typed.highcharts.mod.PointMarkerOptionsObject
import lucuma.typed.highcharts.mod.PointOptionsObject
import lucuma.typed.highcharts.mod.Series
import lucuma.typed.highcharts.mod.SeriesAreasplineOptions
import lucuma.typed.highcharts.mod.SeriesClickEventObject
import lucuma.typed.highcharts.mod.SeriesEventsOptionsObject
import lucuma.typed.highcharts.mod.SeriesOptionsType
import lucuma.typed.highcharts.mod.SeriesStatesHoverOptionsObject
import lucuma.typed.highcharts.mod.SeriesStatesOptionsObject
import lucuma.typed.highcharts.mod.TitleOptions
import lucuma.typed.highcharts.mod.TooltipOptions
import lucuma.typed.highcharts.mod.TooltipShapeValue
import lucuma.typed.highcharts.mod.XAxisOptions
import lucuma.typed.highcharts.mod.XAxisPlotBandsLabelOptions
import lucuma.typed.highcharts.mod.XAxisPlotBandsOptions
import lucuma.typed.highcharts.mod.XrangePointOptionsObject
import lucuma.typed.highcharts.mod.XrangePointPartialFillOptionsObject
import lucuma.typed.highcharts.mod.YAxisLabelsOptions
import lucuma.typed.highcharts.mod.YAxisOptions
import lucuma.typed.highcharts.mod.YAxisTitleOptions
import org.scalajs.dom

import scala.scalajs.js

import js.annotation.*
import js.JSConverters.*

@JSExportTopLevel("Demo")
object Demo {

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

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
          .setShape(TooltipShapeValue.rect)
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
          )
          .setSeries(
            PlotSeriesOptions()
              .setLineWidth(0)
              .setMarker(PointMarkerOptionsObject().setEnabled(false))
              .setStates(
                SeriesStatesOptionsObject()
                  .setHover(SeriesStatesHoverOptionsObject().setEnabled(false))
              )
              .setCursor(CursorValue.pointer)
              .setEvents(
                SeriesEventsOptionsObject()
                  .setClick((s: Series, _: SeriesClickEventObject, _: js.UndefOr[Series]) =>
                    dom.window.alert(s.name)
                  )
              )
          )
      )
      .setSeries(
        List(
          SeriesAreasplineOptions()
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
          SeriesAreasplineOptions()
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

    def xrangePoint(start: Double, end: Double, row: Int, partialFill: Option[Double] = None) =
      partialFill.foldLeft(XrangePointOptionsObject().setX(start).setX2(end).setY(row)): (p, a) =>
        p.setPartialFill(XrangePointPartialFillOptionsObject().setAmount(a))

    // lucuma-typed has no SeriesXrangeOptions, so the series is built as a literal.
    val xrangeSeries =
      js.Dynamic
        .literal(
          `type` = "xrange",
          name = "Project 1",
          borderColor = "gray",
          pointWidth = 20,
          dataLabels = js.Dynamic.literal(enabled = true),
          data = js.Array(
            xrangePoint(js.Date.UTC(2014, 10, 21), js.Date.UTC(2014, 11, 2), 0, Some(0.25)),
            xrangePoint(js.Date.UTC(2014, 11, 2), js.Date.UTC(2014, 11, 5), 1),
            xrangePoint(js.Date.UTC(2014, 11, 8), js.Date.UTC(2014, 11, 9), 2),
            xrangePoint(js.Date.UTC(2014, 11, 9), js.Date.UTC(2014, 11, 19), 1),
            xrangePoint(js.Date.UTC(2014, 11, 10), js.Date.UTC(2014, 11, 23), 2)
          )
        )
        .asInstanceOf[SeriesOptionsType]

    val xrangeOptions = Options()
      .setTitle(TitleOptions().setText("X-range"))
      .setCredits(CreditsOptions().setEnabled(false))
      .setXAxis(XAxisOptions().setType(AxisTypeValue.datetime))
      .setYAxis(
        YAxisOptions()
          .setTitle(YAxisTitleOptions().setText(""))
          .setCategoriesVarargs("Prototyping", "Development", "Testing")
          .setReversed(true)
      )
      .setSeries(js.Array(xrangeSeries))

    def onCreate(chart: Chart_) =
      Callback.log(chart) >>
        Callback(
          chart
            .series(0)
            .addPoint(
              PointOptionsObject()
                .setX(1584655110000.0)
                .setY(35.0)
            )
        )

    ReactDOMClient
      .createRoot(container)
      .render:
        React.Fragment(
          Chart(Reusable.always(options), onCreate = onCreate)
            .withModules(Chart.Module.SeriesLabel)
            .toUnmounted,
          Chart(Reusable.always(xrangeOptions)).withModules(Chart.Module.XRange).toUnmounted
        )

  }
}
