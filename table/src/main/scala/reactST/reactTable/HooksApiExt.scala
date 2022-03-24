// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable

import japgolly.scalajs.react._
import reactST.reactTable.facade.tableInstance.TableInstance

object HooksApiExt {
  sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

    final def useTable[D, Plugins, Layout](
      tableDefWithOptions: TableDefWithOptions[D, Plugins, Layout]
    )(implicit
      step:                Step
    ): step.Next[Reusable[TableInstance[D, Plugins]]] =
      useTableBy(_ => tableDefWithOptions)

    final def useTableBy[D, Plugins, Layout](
      tableDefWithOptions: Ctx => TableDefWithOptions[D, Plugins, Layout]
    )(implicit
      step:                Step
    ): step.Next[Reusable[TableInstance[D, Plugins]]] =
      api.customBy(ctx => TableHooks.useTableHook(tableDefWithOptions(ctx)))
  }

  final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ) extends Primary[Ctx, Step](api) {

    def useTableBy[D, Plugins, Layout](
      tableDefWithOptions: CtxFn[TableDefWithOptions[D, Plugins, Layout]]
    )(implicit
      step:                Step
    ): step.Next[Reusable[TableInstance[D, Plugins]]] =
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
