package reactST.reactTable

import japgolly.scalajs.react._
import reactST.reactTable.mod._

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
      step: Step
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
      step: Step
    ): step.Next[Reusable[TableInstanceD]] =
      api.customBy(ctx => TableHooks.useTableHook(tableDefWithOptions(ctx)))
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
      step: Step
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
