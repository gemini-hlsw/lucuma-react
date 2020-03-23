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

@JSExportTopLevel("DemoMain")
object DemoMain {
  type Data = Double | scala.scalajs.js.Tuple2[Double | String,Double | Null] | Null | SeriesAreasplineDataOptions

  implicit def doubleTuple2Data(t: (Double, Double)): Data =
    js.Tuple2(t._1, t._2).asInstanceOf[Data]

  @JSExport
  def main(): Unit = {
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    val options = Options(
      series = List[SeriesOptionsType](
        SeriesAreasplineOptions(
          areaspline,
          data = List[Data](
            (1584655000000.0, 10.0),
            (1584655060000.0, 17.0),
            (1584655120000.0, 20.0),
            (1584655180000.0, 18.0),
            (1584655240000.0, 14.0)
          ).toJSArray
        )
      ).toJSArray
    )

    Chart(options).renderIntoDOM(container)

    ()
  }
}