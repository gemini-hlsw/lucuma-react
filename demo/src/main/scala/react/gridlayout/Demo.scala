package react.gridlayout.demo

// import scala.scalajs.js
import scala.scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.gridlayout._

object RGLDemo {

  val component = ScalaComponent.builder[Unit]("RGLDemo")
    .render(_ =>
      ReactGridLayout(ReactGridLayout.props(200))
    )
    .build

  def apply() = component()

}

@JSExportTopLevel("Demo")
object Demo {
  // @JSImport("react-virtualized/styles.css", JSImport.Default)
  // @js.native
  // object ReactVirtualizedStyles extends js.Object

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

    RGLDemo().renderIntoDOM(container)
    ()
  }
}
