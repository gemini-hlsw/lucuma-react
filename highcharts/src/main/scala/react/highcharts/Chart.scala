package react.highcharts

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import org.scalajs.dom.html
import gpp.highcharts.anon.TypeofHighcharts
import gpp.highcharts.mod.HTMLDOMElement
import gpp.highcharts.mod.Options
import gpp.highcharts.mod.Chart_
import gpp.highcharts.anon.TypeofHighchartsAST
import react.common.ReactProps
import gpp.highcharts.mod.PointOptionsObject

@js.native
trait WrapProceed extends js.ThisFunction0[Chart_, Unit]

@js.native
@JSImport("highcharts/es-modules/masters/highcharts.src.js", JSImport.Default)
object Highcharts extends TypeofHighchartsAST {
  @JSName("wrap")
  def wrapThis(
    obj:    js.Any,
    method: String,
    func:   js.ThisFunction1[Chart_, WrapProceed, Unit]
  ): Unit = js.native
}

final case class Chart(
  options:    Options,
  onCreate:   Chart_ => Callback = _ => Callback.empty,
  highcharts: TypeofHighchartsAST = Highcharts
) extends ReactProps[Chart](Chart.component)

object Chart {
  type Props = Chart

  type Data =
    Double | scala.scalajs.js.Tuple2[
      Double | String,
      Double | Null
    ] | Null | PointOptionsObject

  class Backend($ : BackendScope[Props, Unit]) {
    private val containerRef = Ref[html.Element]

    def render(props: Props) =
      <.div.withRef(containerRef)

    def refresh(props: Props): Callback =
      containerRef.foreach { element =>
        props.highcharts.chart(
          element.asInstanceOf[HTMLDOMElement],
          props.options,
          props.onCreate.andThen(_.runNow())
        )
        ()
      }
  }

  // We are purposefully not updating the chart on each rerender.
  // To update the chart either:
  //  A) Call the refresh method via a Ref; or
  //  B) Remount with a different key.
  val component =
    ScalaComponent
      .builder[Props]
      .renderBackend[Backend]
      .componentDidMount($ => $.backend.refresh($.props))
      // .componentDidUpdate($ => $.backend.init($.currentProps))
      .build
}
