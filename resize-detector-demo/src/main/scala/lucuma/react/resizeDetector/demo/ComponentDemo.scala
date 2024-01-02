// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.resizeDetector.demo

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.resizeDetector.*

case class ComponentDemo() extends ReactFnProps(ComponentDemo.component)

object ComponentDemo:
  type Props = ComponentDemo

  def component =
    ScalaFnComponent
      .withHooks[Props]
      .render { _ =>
        ResizeDetector() { resize =>
          <.div(
            resize.targetRef,
            Css("resize-detector-demo")
          )(
            <.span("Component demo"),
            <.span(s"Width: ${resize.width.orEmpty}px"),
            <.span(s"Height: ${resize.height.orEmpty}px")
          )
        }
      }
