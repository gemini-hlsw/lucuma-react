package react.draggable.demo

import scala.scalajs.js
import scala.scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.raw.JsNumber
import org.scalajs.dom
import react.resizable._
// import react.sizeme._
// import react.draggable._
import react.common._

@JSExportTopLevel("Demo")
object Demo {
  // @JSImport("react-resizable/styles.css", JSImport.Default)
  // @js.native
  // object ReactVirtualizedStyles extends js.Object
  //
  @JSExport
  def main(): Unit = {
    // needed to load the styles
    // ReactVirtualizedStyles
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    HomeComponent().renderIntoDOM(container)
    ()
  }
}

object HomeComponent {

  final case class State(w: JsNumber)

  val component =
    ScalaComponent
      .builder[Unit]
      .initialState(State(500))
      .render { $ =>
        val width    = $.state.w
        // <.div(react-resizable-handle
        //   ^.height := 100.pct,
        // ^.width := 100.pct,
        // ^.background := "red",
        // SizeMe(renderOnUndefined = false) { s =>
        //   Resizable(
        val toResize =
          <.div(
            // ^.key := "body",
            ^.width := width.toDouble.px,
            ^.position := "absolute",
            ^.left := 0.px,
            ^.top := 0.px,
            ^.background := "blue",
            ^.alignItems := "center",
            ^.justifyContent := "flex-end",
            ^.height := "100vh",
            ^.display := "flex"
          ) //("Tree")
        <.div(
          Resizable(
            // axis = Axis.X,
            content = toResize,
            width = width,
            height = 500,
            onResize = (_: ReactEvent, d: ResizeCallbackData) =>
              Callback.log(s"${d.size.width}") *> $.modState(_.copy(w = d.size.width)),
            // handle = (_: ResizeHandleAxis) => ResizeHandle(500): VdomElement,
            // handle = ResizeHandle(500).asInstanceOf[Resizable.RawReactElement], //: VdomElement,
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
  final case class Props(left: JsNumber)
  // extends ReactProps[ResizeHandle](ResizeHandle.component)

  val component =
    ScalaComponent
      .builder[Props]
      .stateless
      .render_P { p =>
        <.div(
          // ^.left := (p.left.toDouble - 20).px,
          // ^.top := 0.px,
          ^.key := "handle",
          ^.height := 20.px,
          ^.width := 20.px,
          ^.display := "flex",
          ^.justifyContent := "center",
          ^.cursor := "ew-resize",
          // ^.position := "absolute",
          // ^.zIndex := "5",
          // ^.cls := "react-resizable-handle",
          // ^.onClick --> Callback.log("click"),
          // ^.onDragStart --> Callback.log("drag"),
          // GPPStyles.ResizeHandle,
          "||"
        )
      }
      .build
  // This will be the props object used from JS-land
  trait JsProps extends js.Object {
    var left: JsNumber
  }
  object JsProps {
    def apply(left: JsNumber): JsProps = {
      val p = (new js.Object()).asInstanceOf[JsProps]
      p.left = left
      p
    }
  }
  val jsComponent =
    component
      .cmapCtorProps[JsProps](x => Props(x.left)) // Change props from JS to Scala
      .toJsComponent                              // Create a new, real JS component
      .raw                                        // Leave the nice Scala wrappers behind and obtain the underlying JS value
  def apply(left: JsNumber) = jsComponent(JsProps(left))

}
