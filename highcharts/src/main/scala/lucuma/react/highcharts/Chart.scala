// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.highcharts

import cats.syntax.option.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.ReactFnProps
import lucuma.typed.highcharts.mod as Highcharts
import lucuma.typed.highcharts.mod.Chart_
import lucuma.typed.highcharts.mod.HTMLDOMElement
import lucuma.typed.highcharts.mod.Options
import lucuma.typed.highcharts.mod.PointOptionsObject
import org.scalajs.dom.html

import scala.scalajs.js

@js.native
trait WrapProceed extends js.ThisFunction0[Chart_, Unit]

case class Chart(
  options:      Reusable[Options],
  allowUpdate:  Boolean = true,
  containerMod: TagMod = TagMod.empty,
  modules:      List[Chart.Module] = List(Chart.Module.Accessibility),
  onCreate:     Chart_ => Callback = _ => Callback.empty
) extends ReactFnProps(Chart.component):
  def withModules(addModules: Chart.Module*): Chart = copy(modules = modules ++ addModules)

object Chart:
  private type Props = Chart

  enum Module(val init: Callback):
    case Accessibility
        extends Module(Callback(lucuma.typed.highcharts.modulesAccessibilityMod.default.^))
    case SeriesLabel
        extends Module(Callback(lucuma.typed.highcharts.modulesSeriesLabelMod.default.^))

  type Data =
    Double |
      scala.scalajs.js.Tuple2[
        Double | String,
        Double | Null
      ] | Null | PointOptionsObject

  private val component =
    ScalaFnComponent[Props]: props =>
      for
        _            <- useEffectOnMount(Callback.traverse(props.modules)(_.init))
        containerRef <- useRefToVdom[html.Element]
        chartRef     <- useRef(none[Chart_])
        isUnmounting <- useRef(false)
        _            <- useEffectOnMount(CallbackTo(isUnmounting.set(true)))
        _            <- useLayoutEffectWithDeps(
                          (props.options, props.allowUpdate)
                        ): (options, allowUpdate) =>
                          (chartRef.get >>= { (chartOpt: Option[Chart_]) =>
                            chartOpt
                              .filter(_ => allowUpdate)
                              .fold( // No chartRef yet, or not allowed to Update
                                containerRef.foreach { element =>
                                  // Highcharts.chart(
                                  Highcharts.chart(
                                    element.asInstanceOf[HTMLDOMElement],
                                    options,
                                    (c: Chart_) => (chartRef.set(c.some) >> props.onCreate(c)).runNow()
                                  )
                                  ()
                                }
                              ): chart => // We have a chartRef and we are allowed to update
                                Callback(chart.update(options))
                          }).map: _ =>
                            isUnmounting.get >>= (Callback.when(_)(chartRef.get.map(_.foreach(_.destroy()))))
      yield <.div(props.containerMod).withRef(containerRef)
