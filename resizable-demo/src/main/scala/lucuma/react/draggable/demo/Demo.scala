// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.draggable.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.resizable.*
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.*

@JSExportTopLevel("Demo")
object Demo {
  @JSExport
  def main(): Unit = {
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    ReactDOMClient.createRoot(container).render(React.StrictMode(HomeComponent()))
  }
}

object HomeComponent {

  final case class State(w: Double)

  val component =
    ScalaComponent
      .builder[Unit]
      .initialState(State(500))
      .render { $ =>
        val width    = $.state.w
        val toResize =
          <.div(
            ^.width          := width.px,
            ^.position       := "absolute",
            ^.left           := 0.px,
            ^.top            := 0.px,
            ^.background     := "blue",
            ^.alignItems     := "center",
            ^.justifyContent := "flex-end",
            ^.height         := "100vh",
            ^.display        := "flex"
          )
        <.div(
          Resizable(
            content = toResize,
            width = width,
            height = 500,
            onResize = (_: ReactEvent, d: ResizeCallbackData) =>
              Callback.log(s"${d.size.width}") *> $.modState(_.copy(w = d.size.width.toDouble)),
            resizeHandles = List(ResizeHandleAxis.East)
          )(
          )
        )
      }
      .build

  def apply() = component()
}

object ResizeHandle {
  // type Props = ResizeHandle
  final case class Props(left: Double)
  // extends ReactProps[ResizeHandle](ResizeHandle.component)

  val component =
    ScalaComponent
      .builder[Props]
      .stateless
      .render_P { _ =>
        <.div(
          ^.key            := "handle",
          ^.height         := 20.px,
          ^.width          := 20.px,
          ^.display        := "flex",
          ^.justifyContent := "center",
          ^.cursor         := "ew-resize",
          "||"
        )
      }
      .build
  // This will be the props object used from JS-land
  trait JsProps extends js.Object {
    var left: Double
  }
  object JsProps {
    def apply(left: Double): JsProps = {
      val p = (new js.Object).asInstanceOf[JsProps]
      p.left = left
      p
    }
  }
  val jsComponent =
    component
      .cmapCtorProps[JsProps](x => Props(x.left)) // Change props from JS to Scala
      .toJsComponent                              // Create a new, real JS component
      .raw                                        // Leave the nice Scala wrappers behind and obtain the underlying JS value
  def apply(left: Double) = jsComponent(JsProps(left))

}
