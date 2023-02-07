// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.highcharts

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import lucuma.typed.highcharts.anon.TypeofHighcharts
import lucuma.typed.highcharts.anon.TypeofHighchartsAST
import lucuma.typed.highcharts.mod.Chart_
import lucuma.typed.highcharts.mod.HTMLDOMElement
import lucuma.typed.highcharts.mod.Options
import lucuma.typed.highcharts.mod.PointOptionsObject
import org.scalajs.dom.html
import react.common.ReactProps

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

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

@js.native
@JSImport("highcharts/es-modules/masters/modules/accessibility.src.js", JSImport.Default)
object HighchartsAccesibility extends js.Object

case class Chart(
  options:    Options,
  onCreate:   Chart_ => Callback = _ => Callback.empty,
  highcharts: TypeofHighchartsAST = Highcharts
) extends ReactProps(Chart.component)

object Chart {
  type Props = Chart

  type Data =
    Double |
      scala.scalajs.js.Tuple2[
        Double | String,
        Double | Null
      ] | Null | PointOptionsObject

  class Backend($ : BackendScope[Props, Unit]) {
    private val containerRef = Ref[html.Element]
    private val graphRef     = Ref[Chart_]

    def render(props: Props) =
      <.div.withRef(containerRef)

    def destroy(): Callback =
      graphRef.foreach(_.destroy())

    def refresh(props: Props): Callback =
      containerRef.foreach { element =>
        props.highcharts.chart(
          element.asInstanceOf[HTMLDOMElement],
          props.options,
          c => (props.onCreate(c) *> graphRef.set(Some(c))).runNow()
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
      .componentWillUnmount($ => $.backend.destroy())
      // .componentDidUpdate($ => $.backend.init($.currentProps))
      .build
}
