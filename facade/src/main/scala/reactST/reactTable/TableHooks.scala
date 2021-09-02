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

  def useTableHook[
    D,
    TableOptsD <: UseTableOptions[D],
    TableInstanceD <: TableInstance[D],
    ColumnOptsD <: ColumnOptions[D],
    ColumnObjectD <: ColumnObject[D],
    TableStateD <: TableState[D],
    Layout
  ](implicit
    reuseListC: Reusability[List[ColumnInterface[D]]],
    reuseListD: Reusability[List[D]]
  ) =
    CustomHook[
      TableDefWithOptions[
        D,
        TableOptsD,
        TableInstanceD,
        ColumnOptsD,
        ColumnObjectD,
        TableStateD,
        Layout
      ]
    ]
      .useMemoBy(_.cols)(_ => _.toJSArray)
      .useMemoBy(_.input.data)(_ => _.toJSArray)
      .buildReturning { (props, cols, rows) =>
        Reusable.byRef(
          useTableJS[D, TableInstanceD](
            props.modOpts(props.tableDef.Options(cols, rows)),
            props.tableDef.plugins.toList.sorted.map(_.hook: PluginHook[D]): _*
          )
        )
      }

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

      final def useTable[
        D,
        TableOptsD <: UseTableOptions[D],
        TableInstanceD <: TableInstance[D],
        ColumnOptsD <: ColumnOptions[D],
        ColumnObjectD <: ColumnObject[D],
        TableStateD <: TableState[D],
        Layout
      ](
        tableDefWithOptions: TableDefWithOptions[
          D,
          TableOptsD,
          TableInstanceD,
          ColumnOptsD,
          ColumnObjectD,
          TableStateD,
          Layout
        ]
      )(implicit
        step:                Step,
        reuseListC:          Reusability[List[ColumnInterface[D]]],
        reuseListD:          Reusability[List[D]]
      ): step.Next[Reusable[TableInstanceD]] =
        useTableBy(_ => tableDefWithOptions)

      final def useTableBy[
        D,
        TableOptsD <: UseTableOptions[D],
        TableInstanceD <: TableInstance[D],
        ColumnOptsD <: ColumnOptions[D],
        ColumnObjectD <: ColumnObject[D],
        TableStateD <: TableState[D],
        Layout
      ](
        tableDefWithOptions: Ctx => TableDefWithOptions[
          D,
          TableOptsD,
          TableInstanceD,
          ColumnOptsD,
          ColumnObjectD,
          TableStateD,
          Layout
        ]
      )(implicit
        step:                Step,
        reuseListC:          Reusability[List[ColumnInterface[D]]],
        reuseListD:          Reusability[List[D]]
      ): step.Next[Reusable[TableInstanceD]] =
        api.customBy(ctx => useTableHook(reuseListC, reuseListD)(tableDefWithOptions(ctx)))
    }

    final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
      api: HooksApi.Secondary[Ctx, CtxFn, Step]
    ) extends Primary[Ctx, Step](api) {

      def useTableBy[
        D,
        TableOptsD <: UseTableOptions[D],
        TableInstanceD <: TableInstance[D],
        ColumnOptsD <: ColumnOptions[D],
        ColumnObjectD <: ColumnObject[D],
        TableStateD <: TableState[D],
        Layout
      ](
        tableDefWithOptions: CtxFn[TableDefWithOptions[
          D,
          TableOptsD,
          TableInstanceD,
          ColumnOptsD,
          ColumnObjectD,
          TableStateD,
          Layout
        ]]
      )(implicit
        step:                Step,
        reuseListC:          Reusability[List[ColumnInterface[D]]],
        reuseListD:          Reusability[List[D]]
      ): step.Next[Reusable[TableInstanceD]] =
        super.useTableBy(step.squash(tableDefWithOptions)(_))

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
