// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore.{buildLibFeaturesColumnFilteringMod => rawFilter}
import lucuma.typed.tanstackTableCore.{buildLibTypesMod => raw}
import scalajs.js
import raw.FilterMeta

trait FilterFn[T, TM, F, FM] extends ((Row[T, TM], ColumnId, F, FM => Unit) => Boolean) { self =>
// resolveFilterValue?: TransformFilterValueFn<TData>
// autoRemove?: ColumnFilterAutoRemoveTestFn<TData>

  def toJs: rawFilter.FilterFn[T] =
    val fn: js.Function4[raw.Row[T], String, Any, js.Function1[FilterMeta, Unit], Boolean] =
      (
        row:         raw.Row[T],
        colId:       String,
        filterValue: Any,
        addMeta:     js.Function1[FilterMeta, Unit]
      ) =>
        self.apply(
          Row(row),
          ColumnId(colId),
          filterValue.asInstanceOf[F],
          (m: FM) => addMeta(m.asInstanceOf[FilterMeta])
        )

    val p: rawFilter.FilterFn[T] = fn.asInstanceOf[rawFilter.FilterFn[T]]
    // resolveFilterValue?: TransformFilterValueFn<TData>
    // autoRemove?: ColumnFilterAutoRemoveTestFn<TData>

    p
}

object FilterFn:
  given [T, TM, F, FM]
    : Conversion[(Row[T, TM], ColumnId, F, FM => Unit) => Boolean, FilterFn[T, TM, F, FM]] =
    fn =>
      new FilterFn[T, TM, F, FM] {
        override def apply(
          row:         Row[T, TM],
          colId:       ColumnId,
          filterValue: F,
          addMeta:     FM => Unit
        ): Boolean =
          fn(row, colId, filterValue, addMeta)
      }
