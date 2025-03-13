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

case class FilterFn[T, TM, CM, F, FM](
  fn:                 FilterFn.Type[T, TM, CM, F, FM],
  resolveFilterValue: Option[FilterFn.TransformFilterValueFn[T, TM, CM, F, FM]] = none,
  autoRemove:         Option[FilterFn.ColumnFilterAutoRemoveTestFn[T, TM, CM, F, FM]] = none
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
  type Type[T, TM, CM, F, FM]                         = (Row[T, TM, CM], ColumnId, F, FM => Unit) => Boolean
  type TransformFilterValueFn[T, TM, CM, F, FM]       = (F, Option[Column[T, Any, TM, CM, F, FM]]) => F
  type ColumnFilterAutoRemoveTestFn[T, TM, CM, F, FM] =
    (F, Option[Column[T, Any, TM, CM, F, FM]]) => Boolean

  def fromJs[T, TM, CM, F, FM](fn: rawFilter.FilterFn[T]): FilterFn[T, TM, CM, F, FM] =
    FilterFn(
      fn = (row: Row[T, TM, CM], colId: ColumnId, filterValue: F, addMeta: FM => Unit) =>
        fn(row.toJs, colId.value, filterValue, (m: Any) => addMeta(m.asInstanceOf[FM])),
      resolveFilterValue = fn.resolveFilterValue.toOption.map: f =>
        (filterValue: F, col: Option[Column[T, Any, TM, CM, F, FM]]) =>
          f(filterValue, col.map(_.toJs).orUndefined).asInstanceOf[F],
      autoRemove = fn.autoRemove.toOption.map: f =>
        (filterValue: F, col: Option[Column[T, Any, TM, CM, F, FM]]) =>
          f(filterValue, col.map(_.toJs).orUndefined)
    )
