package react.gridlayout.demo

import scala.scalajs.js
import scala.scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.gridlayout._
import react.common._
import react.resizeDetector.hooks._
import react.resizeDetector._

object RGLDemo {

  val component = ScalaFnComponent
    .withHooks[Unit]
    .useCallback((x: Int, y: Int) => Callback.log(s"$x $y"))
    .useResizeDetectorBy((_, f) => UseResizeDetectorProps(onResize = f.value))
    .renderWithReuse { (_, _, useResize) =>
      org.scalajs.dom.window.console.log(useResize.width)

      val layout                                           = Layout(
        List(
          LayoutItem(x = 0, y = 0, w = 6, h = 2, i = "a", static = true),
          LayoutItem(x = 1, y = 0, w = 3, h = 2, i = "b", minW = 2, maxW = 4),
          LayoutItem(x = 4, y = 0, w = 1, h = 2, i = "c")
        )
      )
      val layouts: Map[BreakpointName, (Int, Int, Layout)] =
        Map(
          (BreakpointName.lg, (1200, 12, layout)),
          (BreakpointName.md, (996, 10, layout)),
          (BreakpointName.sm, (768, 8, layout)),
          (BreakpointName.xs, (480, 6, layout))
        )
      <.div(
        ^.width  := "80%",
        ^.border := "red solid 1px",
        <.div(
          ResponsiveReactGridLayout(
            useResize.width,
            // onLayoutChange = (_, b) => Callback.log(pprint.apply(b).toString),
            margin = (10, 10),
            containerPadding = (10, 10),
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
      ).withRef(useResize.ref)
    }

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
