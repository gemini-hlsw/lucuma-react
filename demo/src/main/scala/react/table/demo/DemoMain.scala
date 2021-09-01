package react.table.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.common.Css
import reactST.reactTable.HTMLTable
import reactST.reactTable.TableHooks.Implicits._
import reactST.reactTable.TableMaker
import reactST.reactTable.mod.{ ^ => _, _ }

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import js.annotation._

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
    )

  val randomData = RandomData.randomPeople(1000)

  implicit def colReusability[D]: Reusability[List[ColumnInterface[D]]]    =
    Reusability.always
  implicit val guitarListReusability: Reusability[List[Guitar]]            = Reusability.always
  implicit val personListReusability: Reusability[List[RandomData.Person]] = Reusability.always

  // TABLE 1
  val SortedTableDef = TableMaker[Guitar].withSort
  import SortedTableDef.syntax._

  val sortedTableState = SortedTableDef.State().setSortByVarargs(SortingRule("model"))

  val SortedTable =
    ScalaFnComponent
      .withHooks[(List[ColumnInterface[Guitar]], List[Guitar])]
      .useTableBy(SortedTableDef)(_._1, _._2, _ => _.setInitialStateFull(sortedTableState))
      .render((_, tableInstance) =>
        HTMLTable(SortedTableDef)(
          tableClass = Css("guitars"),
          headerCellFn = Some(HTMLTable.sortableHeaderCellFn()),
          footer = <.tfoot(<.tr(<.th(^.colSpan := 6, s"Guitar Count: ${guitars.length}")))
        )(tableInstance)
      )

  import ColumnInterfaceBasedOnValue._

  val sortedTableColumns =
    List(
      SortedTableDef
        .Column("id", _.id)
        .setCell(cell => <.span(s"g-${cell.value}"))
        .setSortByAuto
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
    )

  // TABLE 2
  val VirtualizedTableDef = TableMaker[RandomData.Person].withBlockLayout

  val VirtualizedTable =
    ScalaFnComponent
      .withHooks[(List[ColumnInterface[RandomData.Person]], List[RandomData.Person])]
      .useTableBy(VirtualizedTableDef)(_._1, _._2)
      .render((_, tableInstance) =>
        HTMLTable.virtualized(VirtualizedTableDef)(
          tableClass = Css("virtualized"),
          rowClassFn = rowClassEvenOdd,
          headerCellFn = Some(HTMLTable.basicHeaderCellFn(useDiv = true))
        )(tableInstance)
      )

  val virtualizedTableColumns = List(
    VirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
    VirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
    VirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(50)
  )

  // TABLE 3
  val SortedVirtualizedTableDef = TableMaker[RandomData.Person].withSort.withBlockLayout

  val SortedVirtualizedTable =
    ScalaFnComponent
      .withHooks[(List[ColumnInterface[RandomData.Person]], List[RandomData.Person])]
      .useTableBy(SortedVirtualizedTableDef)(_._1, _._2)
      .render((_, tableInstance) =>
        HTMLTable.virtualized(SortedVirtualizedTableDef)(
          tableClass = Css("virtualized"),
          headerCellFn = Some(HTMLTable.sortableHeaderCellFn(useDiv = true))
        )(tableInstance)
      )

  val sortedVirtualizedTableColumns = List(
    SortedVirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
    SortedVirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
    SortedVirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(75)
  )

  // Table 4
  val SortedVariableVirtualizedTableDef = TableMaker[RandomData.Person].withSort.withBlockLayout

  val SortedVariableVirtualizedTable =
    ScalaFnComponent
      .withHooks[(List[ColumnInterface[RandomData.Person]], List[RandomData.Person])]
      .useTableBy(SortedVariableVirtualizedTableDef)(_._1, _._2)
      .render((_, tableInstance) =>
        HTMLTable.virtualized(SortedVariableVirtualizedTableDef)(
          tableClass = Css("virtualized"),
          bodyHeight = Some(300), // make this one a different height
          headerCellFn = Some(HTMLTable.sortableHeaderCellFn(useDiv = true)),
          rowClassFn = (_: Int, p: RandomData.Person) => if (p.id % 2 == 0) Css("") else Css("big")
        )(tableInstance)
      )

  val sortedVariableVirtualizedTableColumns = List(
    SortedVariableVirtualizedTableDef.Column("id", _.id).setHeader("Id").setWidth(50),
    SortedVariableVirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
    SortedVariableVirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
    SortedVariableVirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(75)
  )

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
      SortedTable((sortedTableColumns, guitars)),
      "Click header to sort. Shift-Click for multi-sort.",
      <.h2("Virtualized Table"),
      VirtualizedTable((virtualizedTableColumns, randomData)),
      <.h2("Sortable Virtualized Table"),
      SortedVirtualizedTable((sortedVirtualizedTableColumns, randomData)),
      <.h2("Sortable Variable Row Height Virtualized Table"),
      <.h3("Rows with odd id's are taller via CSS."),
      SortedVariableVirtualizedTable((sortedVariableVirtualizedTableColumns, randomData))
    )
      .renderIntoDOM(container)

    ()
  }
}
