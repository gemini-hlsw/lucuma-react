package react.gridlayout.demo

import scala.scalajs.js
import scala.scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.gridlayout._
import react.sizeme._

object RGLDemo {

  val component = ScalaComponent
    .builder[Unit]("RGLDemo")
    .render { _ =>
      val layout                                                     = Layout(
        List(
          LayoutItem(x = 0, y = 0, w = 6, h = 2, i = "a", static = true),
          LayoutItem(x = 1, y = 0, w = 3, h = 2, i = "b", minW = 2, maxW = 4),
          LayoutItem(x = 4, y = 0, w = 1, h = 2, i = "c", handle = ".item")
        )
      )
      val layouts: Map[BreakpointName, (JsNumber, JsNumber, Layout)] =
        Map(
          (BreakpointName.lg, (1200, 12, layout)),
          (BreakpointName.md, (996, 10, layout)),
          (BreakpointName.sm, (768, 8, layout)),
          (BreakpointName.xs, (480, 6, layout))
        )
      // ReactGridLayout(
      //   ReactGridLayout.props(1200, className = "layout", rowHeight = 30, cols = 12, layout = layout),
      //   <.div(^.key := "a", "a"),
      //   <.div(^.key := "c", "c"),
      //   <.div(^.key := "b", "b"))
      <.div(
        ^.width := "100%",
        SizeMe() { s =>
          println(s.width)
          <.div(
            ResponsiveReactGridLayout(
              s.width,
              margin = (10: JsNumber, 10: JsNumber),
              containerPadding = (10: JsNumber, 10: JsNumber),
              className = "layout",
              draggableHandle = ".item",
              rowHeight = 30,
              layouts = layouts
            )(
              <.div(^.key := "a", "a"),
              <.div(^.key := "c", "c"),
              <.div(^.key := "b", "b")
            )
          )
        }
      )
    }
    .build

  def apply()   = component()

}

@JSExportTopLevel("Demo")
object Demo {
  @JSImport("react-grid-layout/css/styles.css", JSImport.Default)
  @js.native
  object ReactGridLayoutStyles extends js.Object

  @JSExport
  def main(): Unit = {
    // needed to load the styles
    ReactGridLayoutStyles
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    RGLDemo().renderIntoDOM(container)
    ()
  }
}
