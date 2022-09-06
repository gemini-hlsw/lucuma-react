// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.highcharts

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.html
import react.common.ReactFnProps
import react.common.style.Css
import react.common.syntax.style.*
import react.highcharts.Highcharts
import react.resizeDetector.UseResizeDetectorProps
import react.resizeDetector.hooks.*
import reactST.highcharts.anon.TypeofHighchartsAST
import reactST.highcharts.mod.Chart_
import reactST.highcharts.mod.HTMLDOMElement
import reactST.highcharts.mod.Options
import reactST.highcharts.mod.PointOptionsObject

case class ResizingChart(
  options:    Options,
  onCreate:   Chart_ => Callback = _ => Callback.empty,
  wrapperCss: Css = Css.Empty,
  highcharts: TypeofHighchartsAST = Highcharts
) extends ReactFnProps(ResizingChart.component)

object ResizingChart {
  private type Props = ResizingChart

  type Data =
    Double |
      scala.scalajs.js.Tuple2[
        Double | String,
        Double | Null
      ] | Null | PointOptionsObject

  private val component =
    ScalaFnComponent
      .withHooks[Props]
      // ref to the chart div
      .useRefToVdom[html.Element]
      // ref to the chart
      .useRef(none[Chart_])
      // Build the chart and setuup the destroy
      .useEffectOnMountBy((props, containerRef, graphRef) =>
        containerRef.foreach { element =>
          props.highcharts.chart(
            element.asInstanceOf[HTMLDOMElement],
            props.options,
            c => (graphRef.set(Some(c)) *> props.onCreate(c)).runNow()
          )
          ()
        } *>
          // We need to destroy at unmount or we'll leak memory
          CallbackTo(graphRef.foreach(_.foreach(_.destroy())))
      )
      // On resize do a reflow
      .useResizeDetectorBy((_, _, graphRef) =>
        // Reflow on resize to adapt to the size
        UseResizeDetectorProps(onResize = (_, _) => graphRef.foreach(_.foreach(c => c.reflow())))
      )
      .render { (props, containerRef, _, resize) =>
        // Unfortunately we need two divs to have two references setup
        <.div(props.wrapperCss, <.div().withRef(containerRef)).withRef(resize.ref)
      }
}
