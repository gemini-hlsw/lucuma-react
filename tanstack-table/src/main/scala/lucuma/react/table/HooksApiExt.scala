// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import reactST.{tanstackTableCore => raw}

object HooksApiExt:
  sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]):
    final def useReactTable[T](
      options: TableOptions[T]
    )(using
      step:    Step
    ): step.Next[raw.mod.Table[T]] =
      useReactTableBy(_ => options)

    final def useReactTableBy[T](
      options: Ctx => TableOptions[T]
    )(using
      step:    Step
    ): step.Next[raw.mod.Table[T]] =
      api.customBy(ctx => TableHook.useTableHook(options(ctx)))

  final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ) extends Primary[Ctx, Step](api):
    def useReactTableBy[T](
      tableDefWithOptions: CtxFn[TableOptions[T]]
    )(implicit
      step:                Step
    ): step.Next[raw.mod.Table[T]] =
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
