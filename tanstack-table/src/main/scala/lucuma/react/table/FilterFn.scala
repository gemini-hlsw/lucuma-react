// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.option.*
import lucuma.typed.tanstackTableCore as rawCore
import lucuma.typed.tanstackTableCore.buildLibFeaturesColumnFilteringMod as rawFilter
import lucuma.typed.tanstackTableCore.buildLibTypesMod as raw

import scalajs.js
import raw.FilterMeta

case class FilterFn[T, TM, CM, F, FM](fn: FilterFn.Type[T, TM, CM, F, FM]) {
// resolveFilterValue?: TransformFilterValueFn<TData>
// autoRemove?: ColumnFilterAutoRemoveTestFn<TData>

  def toJs: rawFilter.FilterFn[T] =
    val f: js.Function4[raw.Row[T], String, Any, js.Function1[FilterMeta, Unit], Boolean] =
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

    val p: rawFilter.FilterFn[T] = f.asInstanceOf[rawFilter.FilterFn[T]]
    // resolveFilterValue?: TransformFilterValueFn<TData>
    // autoRemove?: ColumnFilterAutoRemoveTestFn<TData>

    p
}

object FilterFn:
  type Type[T, TM, CM, F, FM] = (Row[T, TM, CM], ColumnId, F, FM => Unit) => Boolean

  given [T, TM, CM, F, FM]
    : Conversion[FilterFn.Type[T, TM, CM, F, FM], FilterFn[T, TM, CM, F, FM]] =
    FilterFn[T, TM, CM, F, FM](_)

  def fromJs[T, TM, CM, F, FM](fn: rawFilter.FilterFn[T]): FilterFn[T, TM, CM, F, FM] =
    FilterFn: (row: Row[T, TM, CM], colId: ColumnId, filterValue: F, addMeta: FM => Unit) =>
      fn(row.toJs, colId.value, filterValue, (m: Any) => addMeta(m.asInstanceOf[FM]))
