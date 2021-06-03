package react.table.demo

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.common.Css
import reactST.reactTable.TableMaker
import reactST.reactTable.HTMLTableBuilder
import reactST.reactTable.mod.{ ^ => _, _ }

import js.annotation._
import scalajs.js.JSConverters._

@JSExportTopLevel("DemoMain")
object DemoMain {

  def logit(message: String, obj: Any) = dom.console.log(message, obj.asInstanceOf[js.Any])

  case class Details(year: Int, pickups: Int, color: String)
  case class Guitar(id: Int, make: String, model: String, details: Details)

  def rowClassEvenOdd[D]: (Int, D) => Css = (i, _) => if (i % 2 == 0) Css("even") else Css("odd")

  val guitars =
    List(
      Guitar(1, "Fender", "Stratocaster", Details(2019, 3, "Sunburst")),
      Guitar(2, "Gibson", "Les Paul", Details(1958, 2, "Gold top")),
      Guitar(3, "Fender", "Telecaster", Details(1971, 2, "Ivory")),
      Guitar(4, "Godin", "LG", Details(2008, 2, "Burgundy"))
    ).toJSArray

  val randomData = RandomData.randomPeople(1000)

  // TABLE 1
  val SortedTableDef = TableMaker[Guitar].withSort
  import SortedTableDef.syntax._

  val SortedTable =
    HTMLTableBuilder.buildComponent(
      SortedTableDef,
      tableClass = Css("guitars"),
      headerCellFn = Some(HTMLTableBuilder.sortableHeaderCellFn()),
      footer = <.tfoot(<.tr(<.th(^.colSpan := 6, s"Guitar Count: ${guitars.length}")))
    )

  import ColumnInterfaceBasedOnValue._

  val sortedTableColumns =
    List(
      SortedTableDef
        .Column("id", _.id)
        .setCell(cell => <.span(s"g-${cell.value}"))
        .setSortByOrdering
        .setHeader("Id"),
      SortedTableDef.Column("make", _.make).setHeader("Make"),
      SortedTableDef.Column("model", _.model).setHeader("Model"),
      SortedTableDef
        .ColumnGroup(
          SortedTableDef.Column("year", _.details.year).setHeader("Year"),
          SortedTableDef.Column("pickups", _.details.pickups).setHeader("Pickups"),
          SortedTableDef.Column("color", _.details.color).setHeader("Color")
        )
        .setHeader("Details")
    ).toJSArray

  val sortedTableState = SortedTableDef.State().setSortByVarargs(SortingRule("model"))

  // TABLE 2
  val VirtualizedTableDef = TableMaker[RandomData.Person].withBlockLayout

  val VirtualizedTable =
    HTMLTableBuilder.buildComponentVirtualized(
      VirtualizedTableDef,
      tableClass = Css("virtualized"),
      rowClassFn = rowClassEvenOdd,
      headerCellFn = Some(HTMLTableBuilder.basicHeaderCellFn(useDiv = true))
    )

  val virtualizedTableColumns = List(
    VirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
    VirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
    VirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(50)
  ).toJSArray

  // TABLE 3
  val SortedVirtualizedTableDef = TableMaker[RandomData.Person].withSort.withBlockLayout

  val SortedVirtualizedTable =
    HTMLTableBuilder.buildComponentVirtualized(
      SortedVirtualizedTableDef,
      tableClass = Css("virtualized"),
      headerCellFn = Some(HTMLTableBuilder.sortableHeaderCellFn(useDiv = true))
    )

  val sortedVirtualizedTableColumns = List(
    SortedVirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
    SortedVirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
    SortedVirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(75)
  ).toJSArray

  // Table 4
  val SortedVariableVirtualizedTableDef = TableMaker[RandomData.Person].withSort.withBlockLayout

  val SortedVariableVirtualizedTable =
    HTMLTableBuilder.buildComponentVirtualized(
      SortedVariableVirtualizedTableDef,
      tableClass = Css("virtualized"),
      bodyHeight = Some(300), // make this one a different height
      headerCellFn = Some(HTMLTableBuilder.sortableHeaderCellFn(useDiv = true)),
      rowClassFn = (_: Int, p: RandomData.Person) => if (p.id % 2 == 0) Css("") else Css("big")
    )

  val sortedVariableVirtualizedTableColumns = List(
    SortedVariableVirtualizedTableDef.Column("id", _.id).setHeader("Id").setWidth(50),
    SortedVariableVirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
    SortedVariableVirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
    SortedVariableVirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(75)
  ).toJSArray

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
      SortedTable(
        SortedTableDef
          .Options(sortedTableColumns, guitars)
          .setInitialStateFull(sortedTableState)
      ),
      "Click header to sort. Shift-Click for multi-sort.",
      <.h2("Virtualized Table"),
      VirtualizedTable(VirtualizedTableDef.Options(virtualizedTableColumns, randomData)),
      <.h2("Sortable Virtualized Table"),
      SortedVirtualizedTable(
        SortedVirtualizedTableDef.Options(sortedVirtualizedTableColumns, randomData)
      ),
      <.h2("Sortable Variable Row Height Virtualized Table"),
      <.h3("Rows with odd id's are taller via CSS."),
      SortedVariableVirtualizedTable(
        SortedVariableVirtualizedTableDef.Options(sortedVariableVirtualizedTableColumns, randomData)
      )
    )
      .renderIntoDOM(container)

    ()
  }
}
