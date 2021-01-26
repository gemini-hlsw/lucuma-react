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

  val sortedTable = {
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

    val guitarFooter = <.tfoot(<.tr(<.th(^.colSpan := 6, s"Guitar Count: ${guitars.length}")))

    tableMaker
      .makeTable(options = options,
                 data = guitars,
                 tableClass = Css("guitars"),
                 headerCellFn = Some(TableMaker.sortableHeaderCellFn()),
                 footer = guitarFooter
      )
  }

  val virtualizedTable = {
    val tm = TableMaker[RandomData.Person].withBlockLayout

    val cols = tm.columnArray(
      tm.accessorColumn("first", _.first).setHeader("First").setWidth(100),
      tm.accessorColumn("last", _.last).setHeader("Last").setWidth(100),
      tm.accessorColumn("age", _.age).setHeader("Age").setWidth(50)
    )

    val options =
      tm.emptyOptions.setColumns(cols)

    tm.makeVirtualizedTable(
      options = options,
      data = RandomData.randomPeople(1000),
      rowIdFn = _.id.toString,
      rowHeight = 36.2,
      bodyHeight = 200,
      tableClass = Css("virtualized"),
      headerCellFn = Some(TableMaker.basicHeaderCellFn(useDiv = true))
    )
  }

  val sortedVirtualizedTable = {
    val tm = TableMaker[RandomData.Person].withSort.withBlockLayout

    val cols = tm.columnArray(
      tm.accessorColumn("first", _.first).setHeader("First").setWidth(100),
      tm.accessorColumn("last", _.last).setHeader("Last").setWidth(100),
      tm.accessorColumn("age", _.age).setHeader("Age").setWidth(75)
    )

    val options =
      tm.emptyOptions.setColumns(cols)

    tm.makeVirtualizedTable(
      options = options,
      data = RandomData.randomPeople(1000),
      rowIdFn = _.id.toString,
      rowHeight = 36.2,
      bodyHeight = 200,
      tableClass = Css("virtualized"),
      headerCellFn = Some(TableMaker.sortableHeaderCellFn(useDiv = true))
    )
  }

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    <.div(
      <.h1("Demo for scalajs-react-table"),
      <.h2("Sortable table"),
      sortedTable,
      "Click header to sort. Shift-Click for multi-sort.",
      <.h2("Virtualized Table"),
      virtualizedTable,
      <.h2("Sortable Virtualized Table"),
      sortedVirtualizedTable
    )
      .renderIntoDOM(container)

    ()
  }
}
