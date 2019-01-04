package react.gridlayout.demo

import scala.scalajs.js
import scala.scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.gridlayout._

object RGLDemo {

  val component = ScalaComponent
    .builder[Unit]("RGLDemo")
    .render { _ =>
      val layout = List(
        LayoutItem(x = 0, y = 0, w = 1, h = 2, i = "a", static = true),
        LayoutItem(x = 1, y = 0, w = 3, h = 2, i = "b", minW   = 2, maxW = 4),
        LayoutItem(x = 4, y = 0, w = 1, h = 2, i = "c")
      )
      ReactGridLayout(
        ReactGridLayout.props(1200, rowHeight = 30, cols = 12, layout = Layout(layout)),
        <.div(^.key := "a", "a"),
        <.div(^.key := "c", "c"),
        <.div(^.key := "b", "b"))
    }
    .build

  def apply() = component()

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
