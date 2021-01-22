package react.table.demo

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.common.Css
import reactST.reactTable.TableMaker
import reactST.reactTable.mod._

import js.annotation._

@JSExportTopLevel("DemoMain")
object DemoMain {

  def logit(message: String, obj: Any) = dom.console.log(message, obj.asInstanceOf[js.Any])

  case class Details(year: Int, pickups: Int, color: String)
  case class Guitar(id: Int, make: String, model: String, details: Details)

  val guitars =
    js.Array(
      Guitar(1, "Fender", "Stratocaster", Details(2019, 3, "Sunburst")),
      Guitar(2, "Gibson", "Les Paul", Details(1958, 2, "Gold top")),
      Guitar(3, "Fender", "Telecaster", Details(1971, 2, "Ivory")),
      Guitar(4, "Godin", "LG", Details(2008, 2, "Burgundy"))
    )

  val idRenderer = ScalaComponent
    .builder[Guitar]
    .render_P(props => <.span(s"g-${props.id}"))
    .build
    .cmapCtorProps[(CellProps[Guitar, _]) with js.Object](_.cell.row.original)
    .toJsComponent
    .raw

  val tableMaker = TableMaker[Guitar].withSort
  import tableMaker.syntax._

  val columns = tableMaker.columnArray(
    tableMaker
      .componentColumn("id", idRenderer)
      .setSortByFn[Int](_.id)
      .setHeader("Id")
      .setAccessorFn(_.id),
    tableMaker.accessorColumn("make", _.make).setHeader("Make"),
    tableMaker.accessorColumn("model", _.model).setHeader("Model"),
    tableMaker.columnGroup(
      "Details",
      tableMaker.accessorColumn("year", _.details.year).setHeader("Year"),
      tableMaker.accessorColumn("pickups", _.details.pickups).setHeader("Pickups"),
      tableMaker.accessorColumn("color", _.details.color).setHeader("Color")
    )
  )

  val state   = tableMaker.emptyState.setSortByVarargs(SortingRule("model"))
  val options = tableMaker.emptyOptions
    .setRowIdFn(_.id.toString)
    .setInitialStateFull(state)
    .setColumns(columns)

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    val guitarFooter = <.tfoot(<.tr(<.th(^.colSpan := 6, s"Guitar Count: ${guitars.length}")))

    <.div(
      <.h1("Demo for scalajs-react-table"),
      tableMaker
        .makeTable(options = options,
                   data = guitars,
                   tableClass = Css("guitars"),
                   headerCellFn = Some(TableMaker.sortableHeaderCellFn()),
                   footer = guitarFooter
        ),
      "Click header to sort. Shift-Click for multi-sort."
    )
      .renderIntoDOM(container)

    ()
  }
}
