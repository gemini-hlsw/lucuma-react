package react.highcharts.demo

import org.scalajs.dom
import scala.scalajs.js
import js.|
import js.annotation._
import js.JSConverters._
import react.highcharts.Highcharts
import react.highcharts.Chart
import gpp.highcharts.mod.Options
import gpp.highcharts.mod.SeriesAreasplineOptions
import gpp.highcharts.highchartsStrings.areaspline
import gpp.highcharts.mod.SeriesOptionsType
import gpp.highcharts.mod.XAxisOptions
import gpp.highcharts.mod.TitleOptions
import gpp.highcharts.mod.LegendOptions
import gpp.highcharts.mod.AxisTypeValue
import gpp.highcharts.mod.XAxisPlotBandsOptions
import gpp.highcharts.mod.CreditsOptions
import gpp.highcharts.mod.YAxisOptions
import gpp.highcharts.mod.TooltipOptions
import gpp.highcharts.mod.PlotOptions
import gpp.highcharts.mod.YAxisTitleOptions
import gpp.highcharts.mod.YAxisLabelsOptions
import gpp.highcharts.mod.PlotAreasplineOptions
import gpp.highcharts.mod.SeriesLabelOptionsObject
import gpp.highcharts.mod.CSSObject
import gpp.highcharts.mod.PlotSeriesOptions
import gpp.highcharts.mod.PointMarkerOptionsObject
import gpp.highcharts.mod.SeriesStatesOptionsObject
import gpp.highcharts.mod.SeriesStatesHoverOptionsObject
import gpp.highcharts.mod.CursorValue
import gpp.highcharts.mod.SeriesEventsOptionsObject
import gpp.highcharts.mod.Series
import gpp.highcharts.mod.SeriesClickEventObject
import gpp.highcharts.mod.TooltipShapeValue
import react.highcharts.WrapProceed
import gpp.highcharts.mod.Chart_
import gpp.highcharts.seriesLabelMod.{default => SeriesLabelMod}
import react.highcharts.mods.seriesLabel.SeriesLabelSeries
import react.highcharts.mods.seriesLabel.SeriesLabelPoint
import gpp.highcharts.mod.TooltipPositionerCallbackFunction
import gpp.highcharts.mod.PositionObject
import gpp.highcharts.mod.Point
import gpp.highcharts.mod.XAxisPlotBandsLabelOptions
import gpp.highcharts.mod.PointOptionsObject

// Reproducing https://jsfiddle.net/rpiaggio/xdz4pLg9/105/

@JSExportTopLevel("DemoMain")
object DemoMain {
  type Data =
    Double | scala.scalajs.js.Tuple2[
      Double | String,
      Double | Null
    ] | Null | PointOptionsObject

  implicit def doubleTupleList2Data[T](
      list: List[(Double, Double)]
  ): js.Array[Data] =
    list.map(t => js.Tuple2(t._1, t._2).asInstanceOf[Data]).toJSArray

  @JSExport
  def main(): Unit = {

    // Enable series-label Mod
    SeriesLabelMod(Highcharts)

    // we need the prototype of the series mod Chart!
    Highcharts.wrapThis(
      Highcharts.Chart.asInstanceOf[js.Dynamic].prototype,
      "drawSeriesLabels",
      (chart: Chart_, proceed: WrapProceed) => {
        // chart should be of a subtype of Chart_ specific to series-label mod...
        // https://github.com/highcharts/highcharts/blob/master/ts/modules/series-label.src.ts
        // For the moment, we only make the Series and Point specific, which suffices for our use.

        proceed.apply(chart)

        val plotTop = chart.plotTop
        val plotLeft = chart.plotLeft
        val height =
          chart.yAxis(0).asInstanceOf[js.Dynamic].height.asInstanceOf[Double]
        val series = chart.series.asInstanceOf[js.Array[SeriesLabelSeries]]

        series.foreach {
          s =>
            val left = s.data(0).asInstanceOf[SeriesLabelPoint].plotX
            val right =
              s.data(s.data.length - 1).asInstanceOf[SeriesLabelPoint].plotX
            val center = (right - left) / 2
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

          val left = s.data(0).asInstanceOf[SeriesLabelPoint].plotX
          val right =
            s.data(s.data.length - 1).asInstanceOf[SeriesLabelPoint].plotX
          val center = (right - left) / 2
          // Assumes even X spacing (and odd number of points)
          val y = s
            .data((s.data.length / 2).toInt)
            .asInstanceOf[SeriesLabelPoint]
            .plotY - labelHeight

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
                  .setClick((s: Series, _: SeriesClickEventObject) =>
                    dom.window.alert(s.name)
                  )
              )
          )
      )
      .setSeries(
        List(
          SeriesAreasplineOptions(areaspline)
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
          SeriesAreasplineOptions(areaspline)
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

    Chart(Highcharts, options).renderIntoDOM(container)

    ()
  }
}
