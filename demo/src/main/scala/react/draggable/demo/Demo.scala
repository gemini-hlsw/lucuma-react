package react.draggable.demo

// import scala.scalajs.js
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
        val width = $.state.w
        // <.div(react-resizable-handle
        //   ^.height := 100.pct,
        // ^.width := 100.pct,
        // ^.background := "red",
        // SizeMe(renderOnUndefined = false) { s =>
        //   Resizable(
        <.div(
          Resizable(
            // axis = Axis.X,
            width = width,
            height = 1000,
            onResize = (_: ReactEvent, d: ResizeCallbackData) =>
              Callback.log(s"${d.size.width}") *> $.modState(_.copy(w = d.size.width)),
            // handle = (_: ResizeHandleAxis) => ResizeHandle(500): VdomElement,
            // handle = ResizeHandle(500): VdomElement,
            resizeHandles = List(ResizeHandleAxis.East)
          )(
            <.div(^.width := width.toDouble.px,
                  ^.position := "absolute",
                  ^.left := 0.px,
                  ^.top := 0.px,
                  ^.background := "blue",
                  ^.height := "100vh"
            )("Tree")
          )
        )
      }
      .build

  def apply() = component()
}

final case class ResizeHandle(left: JsNumber)
    extends ReactProps[ResizeHandle](ResizeHandle.component)

object ResizeHandle {
  type Props = ResizeHandle

  val component =
    ScalaComponent
      .builder[Props]
      .stateless
      .render_P { p =>
        <.div(
          ^.left := p.left.toDouble.px,
          ^.top := 0.px,
          ^.position := "absolute",
          ^.zIndex := "5",
          ^.cls := "react-resizable-handle",
          // GPPStyles.ResizeHandle,
          "||"
        )
      }
      .build

}
