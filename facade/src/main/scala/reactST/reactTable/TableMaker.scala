// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable

import japgolly.scalajs.react.internal.JsUtil
import japgolly.scalajs.react.raw
import japgolly.scalajs.react.raw.React.ComponentClassP
import japgolly.scalajs.react.vdom._
import reactST.reactTable.anon.Data
import reactST.reactTable.mod.ColumnInterfaceBasedOnValue._
import reactST.reactTable.mod.{ ^ => _, _ }
import reactST.std.Partial

import scalajs.js
import scalajs.js.|
import scalajs.js.JSConverters._

// format: off
case class TableMaker[D, 
  TableOptsD <: UseTableOptions[D], 
  TableInstanceD <: TableInstance[D], 
  ColumnOptsD <: ColumnOptions[D], 
  ColumnObjectD <: ColumnObject[D], 
  TableStateD <: TableState[D] // format: on
](plugins: List[PluginHook[D]]) {
  type OptionsType       = TableOptsD
  type InstanceType      = TableInstanceD
  type ColumnOptionsType = ColumnOptsD
  type ColumnType        = ColumnObjectD
  type StateType         = TableStateD

  import syntax._

  private def emptyOptions: TableOptsD = js.Dynamic.literal().asInstanceOf[TableOptsD]

  /**
   * Create a TableOptsD. columns and data are required. Other options can be `set*`.
   */
  // TODO When scalajs-react supports hooks, we can probably expose Lists in the API and useMemo.
  def Options[C <: UseTableColumnOptions[D]](
    columns: js.Array[C],
    data:    js.Array[D]
  ): TableOptsD =
    emptyOptions
      .setColumns(columns.asInstanceOf[js.Array[Column[D]]])
      .setData(data)

  /**
   * Create an empty instance of ColumnOptsD.
   * As per react-table's doc: Warning: Only omit accessor if you really know what you're doing.
   */
  def emptyColumn: ColumnOptsD = js.Dynamic.literal().asInstanceOf[ColumnOptsD]

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor string.
   */
  def Column(accessor: String): ColumnOptsD =
    emptyColumn.setAccessor(accessor)

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor function.
   */
  def Column[V](id: String, accessor: D => V): ColumnOptsD =
    emptyColumn.setId(id).setAccessorFn(accessor)

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor function.
   */
  def Column[V](id: String, accessor: (D, Int) => V): ColumnOptsD =
    emptyColumn.setId(id).setAccessorFn(accessor)

  /**
   * Create a ColumnOptsD setup up for a simple column with an accessor function.
   */
  def Column[V](id: String, accessor: (D, Int, Data[D]) => V): ColumnOptsD =
    emptyColumn.setId(id).setAccessorFn(accessor)

  /**
   * Create a column group with the specified columns in it.
   *
   * @param header The header for the column group. Seems to be required.
   * @param cols The columns to include in the group.
   */
  def ColumnGroup(cols: (ColumnGroup[D] | ColumnOptsD)*): ColumnGroupOptions[D] =
    js.Dynamic
      .literal()
      .asInstanceOf[ColumnGroupOptions[D]]
      .setColumns(cols.toJSArray.asInstanceOf[js.Array[Column[D]]])

  /**
   * Create an empty instance of type TableStateD
   */
  def State(): TableStateD = js.Dynamic.literal().asInstanceOf[TableStateD]

  /**
   * Create a TableInstanceD instance by calling useTable with the
   * provided options and the plugins that have been configured by
   * the with* methods.
   *
   * @param options The table options.
   */
  def use(options: TableOptsD): TableInstanceD =
    Hooks.useTable(options, plugins: _*).asInstanceOf[TableInstanceD]

  /*
   * Convience method to create a TableInstanceD instance by calling
   * useTable with the provided columns and data and the plugins that
   * have been configured by the with* methods.
   *
   * @param columns The table columns.
   * @param data The table data.
   */
  def use(columns: js.Array[_ <: UseTableColumnOptions[D]], data: js.Array[D]): TableInstanceD =
    use(Options(columns, data))

  /*
   * When adding new plugins, see https://github.com/DefinitelyTyped/DefinitelyTyped/tree/master/types/react-table
   * for help determining what the necessary type changes are.
   *
   * Hooks need to have facades created in Hooks.scala, as the facades created by
   * ScalablyTyped won't work as hooks.
   *
   * Heads Up! It doesn't seem to be documented, but the order of plugins seems
   * to matter. If you put useSortBy before useFilters you will get a runtime error.
   * We may want to add some means of ordering the plugs when we submit them to
   * "useTable" if we can figure out what the required order is.
   */

  // format: off
  def withPlugin[
    NewTableOptsD <: UseTableOptions[D],
    NewTableInstanceD <: TableInstance[D], 
    NewColumnOptsD <: ColumnOptions[D], 
    NewColumnObjectD <: ColumnObject[D],
    NewState <: TableState[D]
  ](plugin: Hooks.Hook) =
    TableMaker[D, NewTableOptsD, NewTableInstanceD, NewColumnOptsD, NewColumnObjectD, NewState](plugins :+ plugin)

  /**
   * Add sort capabilities to the table via the useSortBy plugin hook.
   */
  def withSort = withPlugin[
    TableOptsD with UseSortByOptions[D],
    TableInstanceD with UseSortByInstanceProps[D],
    ColumnOptsD with UseSortByColumnOptions[D],
    ColumnObjectD with UseSortByColumnProps[D],
    TableStateD with UseSortByState[D]
  ](Hooks.useSortBy)

  /**
   * Adds support for headers and cells to be rendered as inline-block divs
   * (or other non-table elements) with explicit width. This becomes useful if and when you need
   * to virtualize rows and cells for performance.
   *
   * NOTE: Although no additional options are needed for this plugin to work, the core column
   * options width, minWidth and maxWidth are used to calculate column and cell widths and must
   * be set.
   */
  def withBlockLayout = withPlugin[
    TableOptsD with TableMaker.LayoutMarker, // A marker trait to limit calls to makeVirtualizedTable.
    TableInstanceD,
    ColumnOptsD,
    ColumnObjectD,
    TableStateD
  ](Hooks.useBlockLayout)
  // format: on

  // When trying to use a more traditional "syntax" package and implicit
  // classes, the compiler seems to somehow loose type information along
  // the way. Putting the syntax here allows resolution to work correctly.
  object syntax {
    implicit class TableOptionOps[Self <: TableOptsD](val table: Self) {

      /**
       * Sets the initial state of the table.
       *
       * The provided setInitialState method takes a Partial[TableState[D]]
       */
      def setInitialStateFull(s: TableState[D]): Self =
        table.setInitialState(s.asInstanceOf[Partial[TableState[D]]])

      /**
       * Sets the row id for the rows of the table based on a function.
       *
       * @param f A function from the row type to the row id.
       */
      def setRowIdFn(f: D => String): Self =
        table.setGetRowId((originalRow, _, _) => f(originalRow))

      /**
       * Sets the row id for the rows of the table based on a function.
       *
       * @param f A function from the row type and index to the row id.
       */
      def setRowIdFn(f: (D, Int) => String): Self =
        table.setGetRowId((originalRow, relativeIndex, _) => f(originalRow, relativeIndex.toInt))

      /**
       * Sets the row id for the rows of the table based on a function.
       *
       * @param f A function from the row type, index and parent to the row id.
       */
      def setRowIdFn(f: (D, Int, js.UndefOr[Row[D]]) => String): Self =
        table.setGetRowId((originalRow, relativeIndex, parent) =>
          f(originalRow, relativeIndex.toInt, parent)
        )
    }

    implicit class ColumnOptionOps[Self <: ColumnOptsD](val col: Self) {

      /**
       * Sets the accessorFunction for the column.
       *
       * @param f A function from the row type to the column type.
       */
      def setAccessorFn[V](f: D => V): Self =
        col.setAccessorFunction3((data, _, _) => f(data).asInstanceOf[js.Any])

      def setAccessorFn[V](f: (D, Int) => V): Self =
        col.setAccessorFunction3((data, index, _) => f(data, index.toInt).asInstanceOf[js.Any])

      def setAccessorFn[V](f: (D, Int, Data[D]) => V): Self =
        col.setAccessorFunction3((data, index, sub) =>
          f(data, index.toInt, sub).asInstanceOf[js.Any]
        )

      /**
       * Sets the sorting for the column based on a function.
       *
       * @param f A function from the row type to the column type.
       * @param ordering An implicit ordering for the column type.
       * @param evidence Evidence that this column is sortable. (See note)
       *
       * Note:
       * This method is only valid for columns that are sortable via the
       *   useSortBy plugin. The compiler was unable to resolve the types if
       *   an implicit class requiring UseSortByColumnOptions[D] was used, so
       *   I switched to requiring evidence that ColumnOptsD is a subtype
       *   of UseSortByColumnOptions[D] and that worked. Unfortunately, requires
       *   asInstanceOfs.
       */
      def setSortByFn[V](
        f:        D => V
      )(implicit
        ordering: Ordering[V],
        ev:       ColumnOptsD <:< UseSortByColumnOptions[D]
      ): Self = {
        val sbfn: SortByFn[D] = (d1, d2, _, _) =>
          ordering.compare(f(d1.original), f(d2.original)).toDouble
        ev(col).setSortType(sbfn).asInstanceOf[Self]
      }
    }
  }
}

object TableMaker {
  // format: off
  def apply[D]: TableMaker[
    D,
    UseTableOptions[D],
    TableInstance[D],
    ColumnOptions[D],
    ColumnObject[D],
    TableState[D]
  ] = TableMaker(List.empty)
  // format: on

  def headersFromGroup[D, ColumnD <: Column[D]](headerGroup: HeaderGroup[D]) =
    headerGroup.headers.asInstanceOf[js.Array[ColumnD]]

  trait LayoutMarker
}
