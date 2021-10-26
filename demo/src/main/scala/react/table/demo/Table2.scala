package react.table.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.style.Css
import reactST.reactTable.HTMLTable
import reactST.reactTable.TableDef

object Table2 {
  private def rowClassEvenOdd[D]: (Int, D) => Css = (i, _) =>
    if (i % 2 == 0) Css("even") else Css("odd")

  private val VirtualizedTableDef = TableDef[Person].withBlockLayout

  private val VirtualizedTable =
    ScalaFnComponent
      .withHooks[List[Person]]
      // cols
      .useMemo(())(_ =>
        List(
          VirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
          VirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
          VirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(50)
        )
      )
      // rows
      .useMemoBy((people, _) => people)((_, _) => people => people)
      .useTableBy((_, cols, rows) => VirtualizedTableDef(cols, rows))
      .render((_, _, _, tableInstance) =>
        HTMLTable.virtualized(VirtualizedTableDef)(
          tableClass = Css("virtualized"),
          rowClassFn = rowClassEvenOdd,
          headerCellFn = Some(HTMLTable.basicHeaderCellFn(useDiv = true))
        )(tableInstance)
      )

  val component = ScalaFnComponent[List[Person]] { people =>
    React.Fragment(
      <.h2("Virtualized Table"),
      VirtualizedTable(people)
    )
  }
}
