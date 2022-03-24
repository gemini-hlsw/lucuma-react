// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST

import japgolly.scalajs.react.facade.{ React => ReactRaw }
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.VdomNode
import org.scalablytyped.runtime.StObject
import reactST.reactTable.anon.Data
import reactST.reactTable.facade.cell.CellProps
import reactST.reactTable.facade.columnOptions.ColumnGroupInterface
import reactST.reactTable.facade.columnOptions.ColumnInterfaceBasedOnValue
import reactST.reactTable.facade.columnOptions.ColumnOptions
import reactST.reactTable.facade.row.Row
import reactST.reactTable.mod.AggregatedValue
import reactST.reactTable.mod.CellValue
import reactST.reactTable.mod.ColumnFooter
import reactST.reactTable.mod.DefaultAggregators
import reactST.reactTable.mod.DefaultSortTypes
import reactST.reactTable.mod.Renderer
import reactST.reactTable.mod.SortByFn

import scalajs.js
import scalajs.js.|

package object reactTable extends HooksApiExt {
  type SingleColumnOptions[D, Plugins] =
    ColumnOptions[D, Plugins]
      with reactST.reactTable.anon.IdIdType[D]
      with reactST.reactTable.anon.`0`[D]
      with reactST.reactTable.anon.`1`[D]
      with ColumnFooter[D]

  type ColumnValueOptions[D, V, Plugins] =
    SingleColumnOptions[D, Plugins] with ColumnInterfaceBasedOnValue[D, V, Plugins]

  type ColumnGroupOptions[D, Plugins] =
    ColumnOptions[D, Plugins]
      with ColumnGroupInterface[D, Plugins]
      with (reactST.reactTable.anon.Header | reactST.reactTable.anon.Id[D])
      with reactST.reactTable.anon.Accessor[D]
      with ColumnFooter[D]

  type AggregatorFn[D, Plugins] = js.Function3[
    /* columnValues */ js.Array[CellValue[js.Any]],
    /* rows */ js.Array[Row[D, Plugins]],
    /* isAggregated */ scala.Boolean,
    AggregatedValue
  ]

  type Aggregator[D, Plugins] = AggregatorFn[D, Plugins] | DefaultAggregators | String

  /**
   * Helper method for taking a properties object returned by JS and turning it into a TagMod of
   * attributes.
   */
  implicit def props2Attrs(obj: js.Object): TagMod =
    TagMod(
      TagMod.fn(_.addAttrsObject(obj, _ != "style")),
      obj
        .asInstanceOf[js.Dictionary[js.Object]]
        .get("style")
        .fold(TagMod.empty)(styles => TagMod.fn(_.addStylesObject(styles)))
    )

  implicit class ColumnValueOptionOps[D, V, Plugins](val col: ColumnValueOptions[D, V, Plugins])
      extends AnyVal {

    /**
     * Sets the accessorFunction for the column.
     *
     * @param f
     *   A function from the row type to the column type.
     */
    def setAccessorFn(f: D => V): ColumnValueOptions[D, V, Plugins] =
      col
        .setAccessorFunction3((data, _, _) => f(data).asInstanceOf[js.Any])

    def setAccessorFn(f: (D, Int) => V): ColumnValueOptions[D, V, Plugins] =
      col.setAccessorFunction3((data, index, _) => f(data, index.toInt).asInstanceOf[js.Any])

    def setAccessorFn(f: (D, Int, Data[D]) => V): ColumnValueOptions[D, V, Plugins] =
      col.setAccessorFunction3((data, index, sub) => f(data, index.toInt, sub).asInstanceOf[js.Any])

    @scala.inline
    def setCell(value: CellProps[D, V, Plugins] => VdomNode): ColumnValueOptions[D, V, Plugins] =
      StObject.set(
        col,
        "Cell",
        value.andThen(_.rawNode): js.Function1[CellProps[D, V, Plugins], ReactRaw.Node]
      )

    @scala.inline
    def setCell(value: Renderer[CellProps[D, V, Plugins]]): ColumnValueOptions[D, V, Plugins] =
      StObject.set(col, "Cell", value.asInstanceOf[js.Any])

    @scala.inline
    def setCellComponentClass(
      value: ReactRaw.ComponentClassP[(CellProps[D, V, Plugins]) with js.Object]
    ): ColumnValueOptions[D, V, Plugins] =
      StObject.set(col, "Cell", value.asInstanceOf[js.Any])

    @scala.inline
    def setCellUndefined: ColumnValueOptions[D, V, Plugins] =
      StObject.set(col, "Cell", js.undefined)

    @scala.inline
    def setCellVdomElement(value: VdomElement): ColumnValueOptions[D, V, Plugins] =
      StObject.set(col, "Cell", value.rawElement.asInstanceOf[js.Any])

    /**
     * Sets the sorting for the column based on a function on its value.
     *
     * @param f
     *   A function from the value type to the target type.
     * @param ordering
     *   An implicit ordering for the target type.
     * @param evidence
     *   Evidence that this column is sortable.
     */
    def setSortByFn[U](f: V => U)(implicit
      ordering:           Ordering[U],
      ev:                 Plugins <:< Plugin.SortBy.Tag
    ): ColumnValueOptions[D, V, Plugins] = {
      val sbfn: SortByFn[D] = (d1, d2, col, _) =>
        ordering
          .compare(f(d1.values(col.asInstanceOf[String]).asInstanceOf[CellValue[V]]),
                   f(d2.values(col.asInstanceOf[String]).asInstanceOf[CellValue[V]])
          )
          .toDouble
      col.setSortType(sbfn)
    }

    /**
     * Sets the sorting for the column based on its value.
     *
     * @param ordering
     *   An implicit ordering for the value type.
     * @param evidence
     *   Evidence that this column is sortable.
     */
    def setSortByAuto(implicit
      ordering: Ordering[V],
      ev:       Plugins <:< Plugin.SortBy.Tag
    ): ColumnValueOptions[D, V, Plugins] = setSortByFn(identity)

    /**
     * Sets the sorting for the column based on a function on the row.
     *
     * @param f
     *   A function from the row type to the target type.
     * @param ordering
     *   An implicit ordering for the target type.
     * @param evidence
     *   Evidence that this column is sortable. (See note)
     */
    def setSortByRowFn(
      f:        D => V
    )(implicit
      ordering: Ordering[V],
      ev:       Plugins <:< Plugin.SortBy.Tag
    ): ColumnValueOptions[D, V, Plugins] = {
      val sbfn: SortByFn[D] = (d1, d2, _, _) =>
        ordering.compare(f(d1.original), f(d2.original)).toDouble
      col.setSortType(sbfn)
    }
  }
}
