// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.resizeDetector.demo

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.resizeDetector.*
import lucuma.react.resizeDetector.hooks.*

case class HookDemo() extends ReactFnProps(HookDemo.component)

object HookDemo:
  type Props = HookDemo

  val component = 
    ScalaFnComponent
      .withHooks[Props]
      .useResizeDetector()
      .useEffectBy((_, resize) => Callback(println(resize)))
      .render { (_, resize) =>
        <.div(
          Css("resize-detector-demo")
        )(
          <.span("Hook demo"),
          <.span(s"Width: ${resize.width.orEmpty}px"),
          <.span(s"Height: ${resize.height.orEmpty}px")
        ).withRef(resize.ref)
      }
