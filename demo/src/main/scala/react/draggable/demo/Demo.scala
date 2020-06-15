package react.draggable.demo

// import scala.scalajs.js
import scala.scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.raw.JsNumber
import org.scalajs.dom
import react.resizable._
import react.sizeme._
import react.draggable._
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
      .render { _ =>
        <.div(
          ^.height := 100.pct,
          ^.width := 100.pct,
          ^.background := "red",
          SizeMe(renderOnUndefined = false) { s =>
            println("Render")
            println("h")
            println(s.height)
            println("w")
            println(s.width)
            println(s.width.toDouble)
            Resizable(
              axis = Axis.X,
              width = 500,
              height = 1000,
              resizeHandles = List(ResizeHandleAxis.East)
            )(<.div(^.background := "blue", ^.height := "100vh")("Tree"))
          }
        )
      }
      .build

  def apply() = component()
}
