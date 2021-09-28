package react.table.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.virtuoso._
import react.virtuoso.raw.ListRange

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExportTopLevel

import js.annotation._

@JSExportTopLevel("DemoMain")
object DemoMain {

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    val data = (1 to 1000).toJSArray

    val groups = List.fill(10)(100)

    val toRow = (index: Int, item: Int) => {
      val className = if (index % 2 == 0) "row even" else "row odd"
      <.div("index ", index, " item ", item, ^.className := className)
    }

    val groupHeader = (groupIndex: Int) =>
      <.div(^.backgroundColor := "white")(
        <.b(s"GROUP $groupIndex")
      )

    val toGroupedRow = (index: Int, groupIndex: Int, item: Int) => {
      val className = if (index % 2 == 0) "row even" else "row odd"
      <.div("index ", index, " item ", item, " group ", groupIndex, ^.className := className)
    }

    val ref = Ref.toJsComponent(Virtuoso.component[Int])

    <.div(
      <.h1("Demo for scalajs-react-virtuoso"),
      <.h2("Basic List"),
      <.div("List of 1000 elements."),
      Virtuoso[Int](
        data = data,
        itemContent = toRow
      )(
        ^.height := "400px",
        ^.width  := "200px"
      ).withRef(ref),
      <.button(^.onClick --> ref.foreach(
                 _.raw.scrollTo(ScrollToOptions(top = 1, behavior = ScrollBehavior.Smooth))
               ),
               "Scroll to top"
      ),
      <.h2("Infinite List"),
      <.div("Starts with 100 elements and adds more as needed."),
      <.div("Information from callbacks is printed to the console."),
      Infinite.component(),
      <.h2("Grouped List"),
      <.div("List of 1000 elements."),
      GroupedVirtuoso[Int](
        data = data,
        itemContent = toGroupedRow,
        groupContent = groupHeader,
        groupCounts = groups
      )(
        ^.height := "400px",
        ^.width  := "300px"
      )
    )
      .renderIntoDOM(container)

    ()
  }

  object Infinite {
    class Backend($ : BackendScope[Unit, js.Array[Int]]) {
      def render(state: js.Array[Int]) = {
        // In order to load additional items before the end is reached, you
        // could use rangeChanged instead of endReached.
        val endReached: Int => Callback = _ =>
          CallbackTo {
            println(s"endReached - loading more")
            val l = state.length
            l to (l + 20)
          }.flatMap(newEles => $.setState(state ++ newEles))

        // these callbacks just print info to the console.
        val isScrolling: Boolean => Callback         = b => Callback(println(s"isScrolling: $b"))
        val startReached: Int => Callback            = i => Callback(println(s"startReached index: $i"))
        val rangeChanged: ListRange => Callback      = ls =>
          Callback(println(s"rangeChanged: ${ls.startIndex}-${ls.endIndex}"))
        val atBottomStateChange: Boolean => Callback = b =>
          Callback(println(s"atBottomStateChange: $b"))
        val atTopStateChange: Boolean => Callback    = b => Callback(println(s"atTopStateChange: $b"))

        val toRow = (index: Int, item: Int) => {
          val className = if (index % 2 == 0) "row even" else "row odd"
          <.div("index ", index, " item ", item, ^.className := className)
        }
        Virtuoso[Int](
          data = state,
          itemContent = toRow,
          isScrolling = isScrolling,
          endReached = endReached,
          startReached = startReached,
          rangeChanged = rangeChanged,
          atBottomStateChange = atBottomStateChange,
          atTopStateChange = atTopStateChange
        )(
          ^.height := "400px",
          ^.width := "200px"
        )
      }
    }

    val component =
      ScalaComponent.builder[Unit].initialState((1 to 100).toJSArray).renderBackend[Backend].build
  }
}
