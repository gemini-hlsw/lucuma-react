// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable

import japgolly.scalajs.react.Reusable
import japgolly.scalajs.react.facade
import japgolly.scalajs.react.facade.React.ComponentClassP
import japgolly.scalajs.react.vdom._
import org.scalablytyped.runtime.StObject
import reactST.reactTable.anon.Data
import reactST.reactTable.anon.IdIdType._
import reactST.reactTable.anon.`1`._
import reactST.reactTable.facade.cell.Cell
import reactST.reactTable.facade.cell.CellProps
import reactST.reactTable.facade.column.Column
import reactST.reactTable.facade.column.HeaderProps
import reactST.reactTable.facade.columnOptions
import reactST.reactTable.facade.columnOptions.ColumnInterfaceBasedOnValue
import reactST.reactTable.facade.columnOptions.ColumnOptions
import reactST.reactTable.facade.row.Row
import reactST.reactTable.facade.tableInstance.TableInstance
import reactST.reactTable.facade.tableOptions.TableOptions
import reactST.reactTable.facade.tableState.TableState
import reactST.reactTable.mod.CellValue
import reactST.reactTable.mod.DefaultSortTypes
import reactST.reactTable.mod.Renderer
import reactST.reactTable.mod.SortByFn
import reactST.reactTable.mod.UseResizeColumnsColumnOptions
import reactST.reactTable.mod.UseResizeColumnsOptions
import reactST.reactTable.mod.UseSortByColumnOptions
import reactST.std.Partial

import scalajs.js
import scalajs.js.|
import scalajs.js.JSConverters._

case class TableDefWithOptions[D, Plugins, Layout](
  tableDef: TableDef[D, Plugins, Layout],
  cols:     Reusable[List[ColumnOptions[D, Plugins]]],
  data:     Reusable[List[D]],
  modOpts:  Reusable[TableOptions[D, Plugins] => TableOptions[D, Plugins]]
)

case class TableDef[D, Plugins, Layout](plugins: Set[Plugin]) {
  type OptionsType             = TableOptions[D, Plugins]
  type InstanceType            = TableInstance[D, Plugins]
  type ColumnOptionsType       = ColumnOptions[D, Plugins]
  type SingleColumnOptionsType = SingleColumnOptions[D, Plugins]
  type ColumnGroupOptionsType  = ColumnGroupOptions[D, Plugins]
  type ColumnType              = Column[D, Plugins]
  type HeaderType              = HeaderProps[D, Plugins]
  type RowType                 = Row[D, Plugins]
  type CellType[V]             = Cell[D, V, Plugins]
  type TableStateType          = TableState[D, Plugins]

  def apply(
    cols:    Reusable[List[ColumnOptions[D, Plugins]]],
    data:    Reusable[List[D]],
    modOpts: Reusable[OptionsType => OptionsType] = Reusable.always(identity[OptionsType] _)
  ): TableDefWithOptions[D, Plugins, Layout] =
    TableDefWithOptions(this, cols, data, modOpts)

  private def emptyOptions: OptionsType = js.Dynamic.literal().asInstanceOf[OptionsType]

  /**
   * Create a TableOptsD. columns and data are required. Other options can be `set*`.
   */
  protected[reactTable] def Options(
    columns: js.Array[ColumnOptions[D, Plugins]],
    data:    js.Array[D]
  ): OptionsType =
    emptyOptions
      .setColumns(columns)
      .setData(data)

  /**
   * Create an empty instance of ColumnOptsD. As per react-table's doc: Warning: Only omit accessor
   * if you really know what you're doing.
   */
  def emptyColumn[V]: ColumnValueOptions[D, V, Plugins] =
    js.Dynamic.literal().asInstanceOf[ColumnValueOptions[D, V, Plugins]]

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor string.
   */
  def Column(id: String): ColumnValueOptions[D, Unit, Plugins] =
    emptyColumn[Unit].setId(id)

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor function.
   */
  def Column[V](id: String, accessor: D => V): ColumnValueOptions[D, V, Plugins] =
    emptyColumn[V].setId(id).setAccessorFn(accessor)

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor function.
   */
  def Column[V](id: String, accessor: (D, Int) => V): ColumnValueOptions[D, V, Plugins] =
    emptyColumn[V].setId(id).setAccessorFn(accessor)

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor function.
   */
  def Column[V](id: String, accessor: (D, Int, Data[D]) => V): ColumnValueOptions[D, V, Plugins] =
    emptyColumn.setId(id).setAccessorFn(accessor)

  /**
   * Create a column group with the specified columns in it.
   *
   * @param header
   *   The header for the column group. Seems to be required.
   * @param cols
   *   The columns to include in the group.
   */
  def ColumnGroup(
    cols: (ColumnGroupOptionsType | ColumnOptionsType)*
  ): ColumnGroupOptions[D, Plugins] =
    js.Dynamic
      .literal()
      .asInstanceOf[ColumnGroupOptions[D, Plugins]]
      .setColumns(cols.toJSArray.asInstanceOf[js.Array[ColumnType]])

  /**
   * Create an empty instance of type TableStateD
   */
  def State(): TableState[D, Plugins] /*TableStateType*/ =
    js.Dynamic.literal().asInstanceOf[TableStateType]

  /*
   * When adding new plugins, see https://github.com/DefinitelyTyped/DefinitelyTyped/tree/master/types/react-table
   * for help determining what the necessary type changes are.
   *
   * Hooks need to have facades created in TableHooks.scala, as the facades created by
   * ScalablyTyped won't work as hooks.
   */

  private def withPlugin[L](plugin: Plugin) =
    TableDef[D, Plugins with plugin.Tag, L](plugins + plugin)

  // Keeps current Layout
  private def withFeaturePlugin(plugin: Plugin) = withPlugin[Layout](plugin)

  // Forces Layout.NonTable
  private def withLayoutPlugin(plugin: Plugin) = withPlugin[Layout.NonTable](plugin)

  def withGroupBy = withFeaturePlugin(Plugin.GroupBy)

  def withExpanded(implicit ev: D <:< Expandable[_]) = withFeaturePlugin(Plugin.Expanded)

  /**
   * Add sort capabilities to the table via the useSortBy plugin hook.
   */
  def withSortBy = withFeaturePlugin(Plugin.SortBy)

  /**
   * Adds support for headers and cells to be rendered as inline-block divs (or other non-table
   * elements) with explicit width. This becomes useful if and when you need to virtualize rows and
   * cells for performance.
   *
   * NOTE: Although no additional options are needed for this plugin to work, the core column
   * options width, minWidth and maxWidth are used to calculate column and cell widths and must be
   * set.
   */
  def withBlockLayout(implicit ev: Layout =:= Layout.Table) = withLayoutPlugin(Plugin.BlockLayout)

  /**
   * Adds support for headers and cells to be rendered as inline-block divs (or other non-table
   * elements) with width being used as the flex-basis and flex-grow. This hook becomes useful when
   * implementing both virtualized and resizable tables that must also be able to stretch to fill
   * all available space.
   *
   * NOTE: Although no additional options are needed for this plugin to work, the core column
   * options width, minWidth and maxWidth are used to calculate column and cell widths and must be
   * set.
   */
  def withFlexLayout(implicit ev: Layout =:= Layout.Table) = withLayoutPlugin(Plugin.FlexLayout)

  /**
   * Adds support for headers and cells to be rendered as divs (or other non-table elements) with
   * the immediate parent (table) controlling the layout using CSS Grid. This hook becomes useful
   * when implementing both virtualized and resizable tables that must also be able to stretch to
   * fill all available space. Uses a minimal amount of html to give greater control of styling.
   * Works with useResizeColumns.
   */
  def withGridLayout(implicit ev: Layout =:= Layout.Table) = withLayoutPlugin(Plugin.GridLayout)

  /**
   * Add column resize capabilities to the table via the useResizeColumns plugin hook.
   */
  def withResizeColumns(implicit ev: Layout =:= Layout.NonTable) =
    withFeaturePlugin(Plugin.ResizeColumns)
}

object TableDef {
  def apply[D]: TableDef[D, Plugin.Base, Layout.Table] = TableDef(Set.empty)
}
