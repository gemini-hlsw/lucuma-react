// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.tableState

import org.scalablytyped.runtime.StObject
import reactST.reactTable.Plugin
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.SortingRule
import reactST.reactTable.mod.UseExpandedState
import reactST.reactTable.mod.UseGroupByState
import reactST.reactTable.mod.UseSortByState
import reactST.std.Record

import scala.scalajs.js

@js.native
trait TableState[D, Plugins] extends js.Object {
  var hiddenColumns: js.UndefOr[js.Array[IdType[D]]] = js.native
}
object TableState {

  @scala.inline
  implicit class TableStateMutableBuilder[Self <: TableState[?, ?], D, Plugins](
    val x: Self with TableState[D, Plugins]
  ) extends AnyVal {

    @inline
    def setHiddenColumns(value: js.Array[IdType[D]]): Self =
      StObject.set(x, "hiddenColumns", value.asInstanceOf[js.Any])

    @inline
    def setHiddenColumnsUndefined: Self = StObject.set(x, "hiddenColumns", js.undefined)

    @inline
    def setHiddenColumnsVarargs(value: IdType[D]*): Self =
      StObject.set(x, "hiddenColumns", js.Array(value: _*))

    // useSortBy
    @inline
    def setSortBy(value: SortingRule[D]*)(implicit
      ev:                Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortBy", js.Array(value: _*))

    // useExpanded
    @inline
    def setExpanded(value: Record[IdType[D], Boolean])(implicit
      ev:                  Plugins <:< Plugin.Expanded.Tag
    ): Self =
      StObject.set(x, "expanded", value.asInstanceOf[js.Any])
  }

  // The "conv" mechanism is mainly to get the implicit conversion to kick in when we have a Reusable.
  @inline
  implicit def toGroupByTableState[D, Plugins, Self](tableState: Self)(implicit
    conv:                                                        Self => TableState[D, Plugins],
    ev:                                                          Plugins <:< Plugin.GroupBy.Tag
  ): Self with UseGroupByState[D] =
    conv(tableState).asInstanceOf[Self with UseGroupByState[D]]

  @inline
  implicit def toExpandedTableState[D, Plugins, Self](tableState: Self)(implicit
    conv:                                                         Self => TableState[D, Plugins],
    ev:                                                           Plugins <:< Plugin.Expanded.Tag
  ): Self with UseExpandedState[D] =
    conv(tableState).asInstanceOf[Self with UseExpandedState[D]]

  @inline
  implicit def toSortByTableState[D, Plugins, Self](tableState: Self)(implicit
    conv:                                                       Self => TableState[D, Plugins],
    ev:                                                         Plugins <:< Plugin.SortBy.Tag
  ): Self with UseSortByState[D] =
    conv(tableState).asInstanceOf[Self with UseSortByState[D]]
}
