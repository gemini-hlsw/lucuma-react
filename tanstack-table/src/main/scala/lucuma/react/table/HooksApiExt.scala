// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*

object HooksApiExt:
  sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]):
    final def useReactTable[T, M](
      options: TableOptions[T, M]
    )(using
      step:    Step
    ): step.Next[Table[T, M]] =
      useReactTableBy(_ => options)

    final def useReactTableBy[T, M](
      options: Ctx => TableOptions[T, M]
    )(using
      step:    Step
    ): step.Next[Table[T, M]] =
      api.customBy(ctx => TableHook.useTableHook(options(ctx)))

  final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ) extends Primary[Ctx, Step](api):
    def useReactTableBy[T, M](
      tableDefWithOptions: CtxFn[TableOptions[T, M]]
    )(implicit
      step:                Step
    ): step.Next[Table[T, M]] =
      super.useReactTableBy(step.squash(tableDefWithOptions)(_))

trait HooksApiExt:
  import HooksApiExt._

  implicit def hooksExtUseReactTable1[Ctx, Step <: HooksApi.AbstractStep](
    api: HooksApi.Primary[Ctx, Step]
  ): Primary[Ctx, Step] =
    new Primary(api)

  implicit def hooksExtUseReactTable2[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ): Secondary[Ctx, CtxFn, Step] =
    new Secondary(api)
