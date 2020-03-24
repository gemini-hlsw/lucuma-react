package react.highcharts.demo

import org.scalajs.dom
import scala.scalajs.js
import js.|
import js.annotation._
import js.JSConverters._
import japgolly.scalajs.react._
import react.highcharts.Highcharts
import react.highcharts.Chart
import typings.highcharts.mod.Options
import typings.highcharts.mod.SeriesAreasplineOptions
import typings.highcharts.highchartsStrings.areaspline
import typings.highcharts.mod.SeriesAreasplineDataOptions
import typings.highcharts.mod.SeriesOptionsType
import typings.highcharts.mod.XAxisOptions
import typings.highcharts.mod.TitleOptions
import typings.highcharts.mod.LegendOptions
import typings.highcharts.mod.AxisTypeValue
import typings.highcharts.mod.XAxisPlotBandsOptions
import typings.highcharts.mod.CreditsOptions
import typings.std.stdStrings.enabled
import typings.highcharts.mod.YAxisOptions
import typings.highcharts.mod.TooltipOptions
import typings.highcharts.mod.PlotOptions
import typings.highcharts.mod.YAxisTitleOptions
import typings.highcharts.mod.YAxisLabelsOptions
import typings.highcharts.mod.PlotAreasplineOptions
import typings.highcharts.mod.PlotAreasplineLabelOptions
import typings.highcharts.mod.CSSObject
import typings.highcharts.mod.PlotSeriesOptions
import typings.highcharts.mod.PlotSeriesMarkerOptions
import typings.highcharts.mod.PlotSeriesStatesOptions
import typings.highcharts.mod.PlotSeriesStatesHoverOptions
import typings.highcharts.mod.CursorValue
import typings.highcharts.mod.PlotSeriesEventsOptions
import org.scalajs.dom.raw.Event
import typings.highcharts.mod.Series
import typings.highcharts.mod.SeriesClickCallbackFunction
import typings.highcharts.mod.SeriesClickEventObject

@JSExportTopLevel("DemoMain")
object DemoMain {
  type Data[T] = Double | scala.scalajs.js.Tuple2[Double | String,Double | Null] | Null | T

  implicit def doubleTupleList2Data[T](list: List[(Double, Double)]): js.Array[Data[T]] =
    list.map(t => js.Tuple2(t._1, t._2).asInstanceOf[Data[T]]).toJSArray

  @JSExport
  def main(): Unit = {
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    val options = Options(
      title = TitleOptions(
        text = ""
      ),

      legend = LegendOptions(
        enabled = false
      ),

      credits = CreditsOptions(
        enabled = false
      ),

      tooltip = TooltipOptions(

      ),

      xAxis = XAxisOptions(
        `type` = AxisTypeValue.datetime,
        plotBands = List[XAxisPlotBandsOptions]().toJSArray
      ),

      yAxis = List(
        YAxisOptions(
          title = YAxisTitleOptions(
            text = ""
          ),
          allowDecimals = false,
          max = 90,
          tickInterval = 10,
          labels = YAxisLabelsOptions(
            format = "{value}°"
          )
        ),
        YAxisOptions(          
          linkedTo = 0,
          opposite = true,
          title = YAxisTitleOptions(
            text = ""
          ),
          labels = YAxisLabelsOptions(
            format = "{value}°"
          )
        )
      ).toJSArray,

      plotOptions = PlotOptions(
        areaspline = PlotAreasplineOptions(
          trackByArea = true,
          label = PlotAreasplineLabelOptions(
            style = CSSObject(
              color = "black"
            )
          )
        ),
        series = PlotSeriesOptions(
          lineWidth = 0,
          marker = PlotSeriesMarkerOptions(
            enabled = false
          ),
          states = PlotSeriesStatesOptions(
            hover = PlotSeriesStatesHoverOptions(
              enabled = false
            )
          ),
          opacity = 0.5,
          cursor = CursorValue.pointer,
          events = PlotSeriesEventsOptions(
            click = ((s: Series, _: SeriesClickEventObject) => dom.window.alert(s.name)) : SeriesClickCallbackFunction
          )
        )
      ),

      series = List[SeriesOptionsType](
        SeriesAreasplineOptions(
          areaspline,
          name = "Observation-1",
          data = List(
            (1584655000000.0, 10.0),
            (1584655060000.0, 17.0),
            (1584655120000.0, 20.0),
            (1584655180000.0, 18.0),
            (1584655240000.0, 14.0)
          )
        ),
        SeriesAreasplineOptions(
          areaspline,
          name = "Observation-2",
          data = List(
            (1584655300000.0, 19.0),
            (1584655360000.0, 25.0),
            (1584655420000.0, 30.0),
            (1584655480000.0, 34.0),
            (1584655540000.0, 36.0)
          )
        )
      ).toJSArray
    )

    Chart(options).renderIntoDOM(container)

    ()
  }
}