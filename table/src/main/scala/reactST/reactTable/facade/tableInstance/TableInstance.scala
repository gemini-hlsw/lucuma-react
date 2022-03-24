// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.tableInstance

import reactST.reactTable.Plugin
import reactST.reactTable.anon.PartialTableToggleHideAll
import reactST.reactTable.facade.column.Column
import reactST.reactTable.facade.column.HeaderGroup
import reactST.reactTable.facade.columnOptions.ColumnOptions
import reactST.reactTable.facade.row.Row
import reactST.reactTable.facade.tableState.TableState
import reactST.reactTable.mod.ActionType
import reactST.reactTable.mod.Hooks
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.Meta
import reactST.reactTable.mod.MetaBase
import reactST.reactTable.mod.PluginHook
import reactST.reactTable.mod.TableBodyPropGetter
import reactST.reactTable.mod.TableBodyProps
import reactST.reactTable.mod.TableDispatch
import reactST.reactTable.mod.TablePropGetter
import reactST.reactTable.mod.TableProps
import reactST.reactTable.mod.TableToggleHideAllColumnProps
import reactST.reactTable.mod.UpdateHiddenColumns
import reactST.std.Partial
import reactST.std.Record

import scala.scalajs.js

@js.native
trait TableInstance[D, Plugins] extends js.Object {

  var allColumns: js.Array[Column[D, Plugins]] = js.native

  var allColumnsHidden: Boolean = js.native

  var autoResetHiddenColumns: js.UndefOr[Boolean] = js.native

  var columns: js.Array[Column[D, Plugins]] = js.native

  var data: js.Array[D] = js.native

  var defaultColumn: js.UndefOr[Partial[ColumnOptions[D, Plugins]]] = js.native

  var dispatch: TableDispatch[js.Any] = js.native

  var flatHeaders: js.Array[Column[D, Plugins]] = js.native

  var flatRows: js.Array[Row[D, Plugins]] = js.native

  var footerGroups: js.Array[HeaderGroup[D, Plugins]] = js.native

  def getHooks(): Hooks[D] = js.native

  var getRowId: js.UndefOr[
    js.Function3[
      /* originalRow */ D,
      /* relativeIndex */ Double,
      /* parent */ js.UndefOr[Row[D, Plugins]],
      String
    ]
  ] = js.native

  var getSubRows
    : js.UndefOr[js.Function2[ /* originalRow */ D, /* relativeIndex */ Double, js.Array[D]]] =
    js.native

  def getTableBodyProps(): TableBodyProps                                   = js.native
  def getTableBodyProps(propGetter: TableBodyPropGetter[D]): TableBodyProps = js.native

  def getTableProps(): TableProps                               = js.native
  def getTableProps(propGetter: TablePropGetter[D]): TableProps = js.native

  def getToggleHideAllColumnsProps(): TableToggleHideAllColumnProps = js.native
  def getToggleHideAllColumnsProps(
    props: PartialTableToggleHideAll
  ): TableToggleHideAllColumnProps = js.native

  var headerGroups: js.Array[HeaderGroup[D, Plugins]] = js.native

  var headers: js.Array[Column[D, Plugins]] = js.native

  var initialState: js.UndefOr[Partial[TableState[D, Plugins]]] = js.native

  var plugins: js.Array[PluginHook[D]] = js.native

  def prepareRow(row: Row[D, Plugins]): Unit = js.native

  var rows: js.Array[Row[D, Plugins]] = js.native

  var rowsById: Record[String, Row[D, Plugins]] = js.native

  def setHiddenColumns(param: js.Array[IdType[D]]): Unit    = js.native
  def setHiddenColumns(param: UpdateHiddenColumns[D]): Unit = js.native

  var state: TableState[D, Plugins] = js.native

  var stateReducer: js.UndefOr[
    js.Function4[
      /* newState */ TableState[D, Plugins],
      /* action */ ActionType,
      /* previousState */ TableState[D, Plugins],
      /* instance */ js.UndefOr[TableInstance[D, Plugins]],
      TableState[D, Plugins]
    ]
  ] = js.native

  def toggleHideAllColumns(): Unit               = js.native
  def toggleHideAllColumns(value: Boolean): Unit = js.native

  def toggleHideColumn(columnId: IdType[D]): Unit                 = js.native
  def toggleHideColumn(columnId: IdType[D], value: Boolean): Unit = js.native

  var totalColumnsWidth: Double = js.native

  var useControlledState: js.UndefOr[
    js.Function2[
      /* state */ TableState[D, Plugins],
      /* meta */ Meta[D, scala.Nothing, MetaBase[D]],
      TableState[D, Plugins]
    ]
  ] = js.native

  var visibleColumns: js.Array[Column[D, Plugins]] = js.native
}

object TableInstance {

  // The "conv" mechanism is mainly to get the implicit conversion to kick in when we have a Reusable.
  @inline
  implicit def toGroupByTableInstance[D, Plugins, Self](tableInstance: Self)(implicit
    conv:                                                              Self => TableInstance[D, Plugins],
    ev:                                                                Plugins <:< Plugin.GroupBy.Tag
  ): Self with UseGroupByTableInstance[D, Plugins] =
    conv(tableInstance).asInstanceOf[Self with UseGroupByTableInstance[D, Plugins]]

  @inline
  implicit def toSortByTableInstance[D, Plugins, Self](tableInstance: Self)(implicit
    conv:                                                             Self => TableInstance[D, Plugins],
    ev:                                                               Plugins <:< Plugin.SortBy.Tag
  ): Self with UseSortByTableInstance[D, Plugins] =
    conv(tableInstance).asInstanceOf[Self with UseSortByTableInstance[D, Plugins]]

  @inline
  implicit def toExpandedTableInstance[D, Plugins, Self](tableInstance: Self)(implicit
    conv:                                                               Self => TableInstance[D, Plugins],
    ev:                                                                 Plugins <:< Plugin.Expanded.Tag
  ): Self with UseExpandedTableInstance[D, Plugins] =
    conv(tableInstance).asInstanceOf[Self with UseExpandedTableInstance[D, Plugins]]

}
