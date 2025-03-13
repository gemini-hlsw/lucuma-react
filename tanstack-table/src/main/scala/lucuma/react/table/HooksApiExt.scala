// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*

object HooksApiExt:
  sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]):
    /**
     * @tparam T
     *   The type of the row.
     * @tparam A
     *   The type of the column.
     * @tparam TM
     *   The type of the metadata for the table.
     * @tparam CM
     *   The type of the metadata for the column.
     * @tparam TF
     *   The type of the global filter.
     */
    final def useReactTable[T, TM, CM, TF](
      options: TableOptions[T, TM, CM, TF]
    )(using
      step:    Step
    ): step.Next[Table[T, TM, CM, TF]] =
      useReactTableBy(_ => options)

    /**
     * @tparam T
     *   The type of the row.
     * @tparam A
     *   The type of the column.
     * @tparam TM
     *   The type of the metadata for the table.
     * @tparam CM
     *   The type of the metadata for the column.
     * @tparam TF
     *   The type of the global filter.
     */
    final def useReactTableBy[T, TM, CM, TF](
      options: Ctx => TableOptions[T, TM, CM, TF]
    )(using
      step:    Step
    ): step.Next[Table[T, TM, CM, TF]] =
      api.customBy(ctx => TableHook.useTableHook(options(ctx)))

  final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ) extends Primary[Ctx, Step](api):
    /**
     * @tparam T
     *   The type of the row.
     * @tparam A
     *   The type of the column.
     * @tparam TM
     *   The type of the metadata for the table.
     * @tparam CM
     *   The type of the metadata for the column.
     * @tparam TF
     *   The type of the global filter.
     */
    def useReactTableBy[T, TM, CM, TF](
      tableDefWithOptions: CtxFn[TableOptions[T, TM, CM, TF]]
    )(implicit
      step:                Step
    ): step.Next[Table[T, TM, CM, TF]] =
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
