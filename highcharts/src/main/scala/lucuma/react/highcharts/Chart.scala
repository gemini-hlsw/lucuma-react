// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.highcharts

import cats.syntax.option.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.ReactFnProps
import lucuma.typed.highcharts.anon.TypeofHighchartsAST
import lucuma.typed.highcharts.mod.Chart_
import lucuma.typed.highcharts.mod.HTMLDOMElement
import lucuma.typed.highcharts.mod.Options
import lucuma.typed.highcharts.mod.PointOptionsObject
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

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
  options:      Reusable[Options],
  allowUpdate:  Boolean = true,
  containerMod: TagMod = TagMod.empty,
  onCreate:     Chart_ => Callback = _ => Callback.empty,
  highcharts:   TypeofHighchartsAST = Highcharts
) extends ReactFnProps(Chart.component)

object Chart:
  private type Props = Chart

  type Data =
    Double |
      scala.scalajs.js.Tuple2[
        Double | String,
        Double | Null
      ] | Null | PointOptionsObject

  private val component =
    ScalaFnComponent
      .withHooks[Props]
      .useRefToVdom[html.Element] // containerRef
      .useRef(none[Chart_])       // chartRef
      .useLayoutEffectWithDepsBy((props, _, _) => (props.options, props.allowUpdate)):
        (props, containerRef, chartRef) =>
          (options, allowUpdate) =>
            chartRef.get >>= { chartOpt =>
              chartOpt
                .filter(_ => allowUpdate)
                .fold( // No chartRef yet, or not allowed to Update
                  containerRef.foreach { element =>
                    props.highcharts.chart(
                      element.asInstanceOf[HTMLDOMElement],
                      options,
                      c => (chartRef.set(c.some) >> props.onCreate(c)).runNow()
                    )
                    ()
                  }
                ): chart => // We have a chartRef and we are allowed to update
                  Callback(chart.update(options))
            }
      .useEffectOnMountBy: (_, _, chartRef) =>
        CallbackTo(chartRef.get.map(_.foreach(_.destroy()))) // Destroy at unmount
      .render: (props, containerRef, chartRef) =>
        <.div(props.containerMod).withRef(containerRef)
