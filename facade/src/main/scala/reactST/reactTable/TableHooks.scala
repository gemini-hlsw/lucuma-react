package reactST.reactTable

import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.CustomHook
import reactST.reactTable.mod._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSBracketAccess
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.annotation.JSGlobalScope
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.`|`

import scalajs.js.JSConverters._

object TableHooks {
  @JSImport("react-table", "useTable")
  @js.native
  def useTableJS[D, TI <: TableInstance[D]](options: TableOptions[D], plugins: PluginHook[D]*): TI =
    js.native

  // format: off
  def useTableHook[D,
    TableOptsD <: UseTableOptions[D],
    TableInstanceD <: TableInstance[D],
    ColumnOptsD <: ColumnOptions[D],
    ColumnObjectD <: ColumnObject[D],
    TableStateD <: TableState[D],
    Layout
  ](tableMaker: TableMaker[D,
    TableOptsD, 
    TableInstanceD, 
    ColumnOptsD , 
    ColumnObjectD, 
    TableStateD,
    Layout
  ])(implicit
    reuseListC: Reusability[List[ColumnInterface[D]]],
    reuseListD: Reusability[List[D]]
  ) = 
    CustomHook[(List[ColumnInterface[D]], List[D], TableOptsD => TableOptsD)]
      .useMemoBy(_._1)(_ => _.toJSArray)
      .useMemoBy(_.input._2)(_ => _.toJSArray)
      .buildReturning { (props, cols, rows) =>
        val modOpts = props._3
        useTableJS[D, TableInstanceD](
          modOpts(tableMaker.Options(cols, rows)),
          tableMaker.plugins.toList.sorted.map(_.hook: PluginHook[D]): _*
        )
      }
  // format: on

  sealed trait TableHook extends js.Object
  object TableHook {
    implicit def asPluginHook[D](hook: TableHook): PluginHook[D] = hook.asInstanceOf[PluginHook[D]]
  }

  @JSImport("react-table", "useSortBy")
  @js.native
  object useSortBy extends TableHook

  @JSImport("react-table", "useBlockLayout")
  @js.native
  object useBlockLayout extends TableHook

  @JSImport("react-table", "useResizeColumns")
  @js.native
  object useResizeColumns extends TableHook

  @JSImport("react-table", "useGridLayout")
  @js.native
  object useGridLayout extends TableHook

  object HooksApiExt {
    sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

      // format: off
      final def useTable[
        D,
        TableOptsD <: UseTableOptions[D],
        TableInstanceD <: TableInstance[D],
        ColumnOptsD <: ColumnOptions[D],
        ColumnObjectD <: ColumnObject[D],
        TableStateD <: TableState[D],
        Layout
      ](
        tableMaker: TableMaker[
          D,
          TableOptsD,
          TableInstanceD,
          ColumnOptsD,
          ColumnObjectD,
          TableStateD,
          Layout
        ] // format: on
      )(
        cols:       List[ColumnInterface[D]],
        data:       List[D],
        modOpts:    TableOptsD => TableOptsD = identity[TableOptsD] _
      )(implicit
        step:       Step,
        reuseListC: Reusability[List[ColumnInterface[D]]],
        reuseListD: Reusability[List[D]]
      ): step.Next[TableInstanceD] =
        useTableBy(tableMaker)(_ => cols, _ => data, _ => modOpts)

      // format: off
      final def useTableBy[
        D,
        TableOptsD <: UseTableOptions[D],
        TableInstanceD <: TableInstance[D],
        ColumnOptsD <: ColumnOptions[D],
        ColumnObjectD <: ColumnObject[D],
        TableStateD <: TableState[D],
        Layout
      ](
        tableMaker: TableMaker[
          D,
          TableOptsD,
          TableInstanceD,
          ColumnOptsD,
          ColumnObjectD,
          TableStateD,
          Layout
        ] // format: on
      )(
        cols:       Ctx => List[ColumnInterface[D]],
        data:       Ctx => List[D],
        modOpts:    Ctx => TableOptsD => TableOptsD = (_: Ctx) => identity[TableOptsD] _
      )(implicit
        step:       Step,
        reuseListC: Reusability[List[ColumnInterface[D]]],
        reuseListD: Reusability[List[D]]
      ): step.Next[TableInstanceD] =
        api.customBy(ctx => useTableHook(tableMaker).apply((cols(ctx), data(ctx), modOpts(ctx))))
    }

    final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
      api: HooksApi.Secondary[Ctx, CtxFn, Step]
    ) extends Primary[Ctx, Step](api) {

      // format: off
      def useTableBy_[
        D,
        TableOptsD <: UseTableOptions[D],
        TableInstanceD <: TableInstance[D],
        ColumnOptsD <: ColumnOptions[D],
        ColumnObjectD <: ColumnObject[D],
        TableStateD <: TableState[D],
        Layout
      ](
        tableMaker: TableMaker[
          D,
          TableOptsD,
          TableInstanceD,
          ColumnOptsD,
          ColumnObjectD,
          TableStateD,
          Layout
        ] // format: on
      )(
        cols:       CtxFn[List[ColumnInterface[D]]],
        data:       CtxFn[List[D]],
        modOpts:    CtxFn[TableOptsD => TableOptsD]
      )(implicit
        step:       Step,
        reuseListC: Reusability[List[ColumnInterface[D]]],
        reuseListD: Reusability[List[D]]
      ): step.Next[TableInstanceD] =
        super.useTableBy(tableMaker)(step.squash(cols)(_),
                                     step.squash(data)(_),
                                     step.squash(modOpts)(_)
        )

      // We can't lift into CtxFn, which prevents from providing a default modOpts. So we overload to allow calling without modOpts.
      def useTableBy[
        D,
        TableOptsD <: UseTableOptions[D],
        TableInstanceD <: TableInstance[D],
        ColumnOptsD <: ColumnOptions[D],
        ColumnObjectD <: ColumnObject[D],
        TableStateD <: TableState[D],
        Layout
      ](
        tableMaker: TableMaker[
          D,
          TableOptsD,
          TableInstanceD,
          ColumnOptsD,
          ColumnObjectD,
          TableStateD,
          Layout
        ] // format: on
      )(
        cols:       CtxFn[List[ColumnInterface[D]]],
        data:       CtxFn[List[D]]
      )(implicit
        step:       Step,
        reuseListC: Reusability[List[ColumnInterface[D]]],
        reuseListD: Reusability[List[D]]
      ): step.Next[TableInstanceD] =
        super.useTableBy(tableMaker)(step.squash(cols)(_), step.squash(data)(_))
    }
  }

  trait HooksApiExt {
    import HooksApiExt._

    implicit def hooksExtUseTable1[Ctx, Step <: HooksApi.AbstractStep](
      api: HooksApi.Primary[Ctx, Step]
    ): Primary[Ctx, Step] =
      new Primary(api)

    implicit def hooksExtUseTable2[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
      api: HooksApi.Secondary[Ctx, CtxFn, Step]
    ): Secondary[Ctx, CtxFn, Step] =
      new Secondary(api)
  }

  object Implicits extends HooksApiExt
}
