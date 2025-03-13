// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.option.*
import lucuma.typed.tanstackTableCore as rawCore
import lucuma.typed.tanstackTableCore.buildLibFeaturesColumnFilteringMod as rawFilter
import lucuma.typed.tanstackTableCore.buildLibTypesMod as raw

import scalajs.js
import scalajs.js.JSConverters.*
import raw.FilterMeta

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
 * @tparam F
 *   The type of the filter value. (could be global or column specific)
 * @tparam FM
 *   The type of the filter metadata (column specific).
 */
case class FilterFn[T, TM, CM, TF, F, FM](
  fn:                 FilterFn.Type[T, TM, CM, TF, F, FM],
  resolveFilterValue: Option[FilterFn.TransformFilterValueFn[T, TM, CM, TF, F, FM]] = none,
  autoRemove:         Option[FilterFn.ColumnFilterAutoRemoveTestFn[T, TM, CM, TF, F, FM]] = none
) {

  def toJs: rawFilter.FilterFn[T] =
    val jsFn: js.Function4[raw.Row[T], String, Any, js.Function1[FilterMeta, Unit], Boolean] =
      (
        row:         raw.Row[T],
        colId:       String,
        filterValue: Any,
        addMeta:     js.Function1[FilterMeta, Unit]
      ) =>
        fn(
          Row(row),
          ColumnId(colId),
          filterValue.asInstanceOf[F],
          (m: FM) => addMeta(m.asInstanceOf[FilterMeta])
        )

    val p: rawFilter.FilterFn[T] = jsFn.asInstanceOf[rawFilter.FilterFn[T]]
    p.resolveFilterValue = resolveFilterValue
      .map: f =>
        val jsFn: js.Function2[Any, js.UndefOr[raw.Column[T, Any]], Any] =
          (filterValue: Any, col: js.UndefOr[raw.Column[T, Any]]) =>
            f(filterValue.asInstanceOf[F], col.map(Column(_)).toOption)
        jsFn
      .orUndefined
    p.autoRemove = autoRemove
      .map: f =>
        val jsFn: js.Function2[Any, js.UndefOr[raw.Column[T, Any]], Boolean] =
          (filterValue: Any, col: js.UndefOr[raw.Column[T, Any]]) =>
            f(filterValue.asInstanceOf[F], col.map(Column(_)).toOption)
        jsFn
      .orUndefined

    p
}

object FilterFn:
  type Type[T, TM, CM, TF, F, FM]                         = (Row[T, TM, CM, TF], ColumnId, F, FM => Unit) => Boolean
  type TransformFilterValueFn[T, TM, CM, TF, F, FM]       =
    (F, Option[Column[T, Any, TM, CM, TF, F, FM]]) => F
  type ColumnFilterAutoRemoveTestFn[T, TM, CM, TF, F, FM] =
    (F, Option[Column[T, Any, TM, CM, TF, F, FM]]) => Boolean

  def fromJs[T, TM, CM, TF, F, FM](fn: rawFilter.FilterFn[T]): FilterFn[T, TM, CM, TF, F, FM] =
    FilterFn(
      fn = (row: Row[T, TM, CM, TF], colId: ColumnId, filterValue: F, addMeta: FM => Unit) =>
        fn(row.toJs, colId.value, filterValue, (m: Any) => addMeta(m.asInstanceOf[FM])),
      resolveFilterValue = fn.resolveFilterValue.toOption.map: f =>
        (filterValue: F, col: Option[Column[T, Any, TM, CM, TF, F, FM]]) =>
          f(filterValue, col.map(_.toJs).orUndefined).asInstanceOf[F],
      autoRemove = fn.autoRemove.toOption.map: f =>
        (filterValue: F, col: Option[Column[T, Any, TM, CM, TF, F, FM]]) =>
          f(filterValue, col.map(_.toJs).orUndefined)
    )
