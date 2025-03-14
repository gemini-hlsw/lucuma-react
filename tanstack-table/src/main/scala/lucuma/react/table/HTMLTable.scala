// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.SizePx
import lucuma.react.common.*
import lucuma.typed.tanstackVirtualCore as rawVirtual
import org.scalajs.dom.HTMLElement

import scalajs.js

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
final case class HTMLTable[T, TM, CM, TF](
  table:                Table[T, TM, CM, TF],
  tableMod:             TagMod = TagMod.empty,
  headerMod:            TagMod = TagMod.empty,
  headerRowMod:         HeaderGroup[T, TM, CM, TF] => TagMod = (_: HeaderGroup[T, TM, CM, TF]) =>
    TagMod.empty,
  headerCellMod:        Header[T, Any, TM, CM, TF, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, TF, Any, Any]) => TagMod.empty,
  columnFilterRenderer: Column[T, Any, TM, CM, TF, Any, Any] => VdomNode =
    (_: Column[T, Any, TM, CM, TF, Any, Any]) => EmptyVdom,
  bodyMod:              TagMod = TagMod.empty,
  rowMod:               Row[T, TM, CM, TF] => TagMod = (_: Row[T, TM, CM, TF]) => TagMod.empty,
  cellMod:              Cell[T, Any, TM, CM, TF, Any, Any] => TagMod = (_: Cell[T, Any, TM, CM, TF, Any, Any]) =>
    TagMod.empty,
  footerMod:            TagMod = TagMod.empty,
  footerRowMod:         HeaderGroup[T, TM, CM, TF] => TagMod = (_: HeaderGroup[T, TM, CM, TF]) =>
    TagMod.empty,
  footerCellMod:        Header[T, Any, TM, CM, TF, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, TF, Any, Any]) => TagMod.empty,
  emptyMessage:         VdomNode = EmptyVdom
) extends ReactFnProps(HTMLTable.component)
    with HTMLTableProps[T, TM, CM, TF]

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
final case class HTMLVirtualizedTable[T, TM, CM, TF](
  table:                Table[T, TM, CM, TF],
  estimateSize:         Int => SizePx,
  // Table options
  containerMod:         TagMod = TagMod.empty,
  containerRef:         js.UndefOr[Ref.ToVdom[HTMLElement]] = js.undefined,
  tableMod:             TagMod = TagMod.empty,
  headerMod:            TagMod = TagMod.empty,
  headerRowMod:         HeaderGroup[T, TM, CM, TF] => TagMod = (_: HeaderGroup[T, TM, CM, TF]) =>
    TagMod.empty,
  headerCellMod:        Header[T, Any, TM, CM, TF, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, TF, Any, Any]) => TagMod.empty,
  columnFilterRenderer: Column[T, Any, TM, CM, TF, Any, Any] => VdomNode =
    (_: Column[T, Any, TM, CM, TF, Any, Any]) => EmptyVdom,
  bodyMod:              TagMod = TagMod.empty,
  rowMod:               Row[T, TM, CM, TF] => TagMod = (_: Row[T, TM, CM, TF]) => TagMod.empty,
  cellMod:              Cell[T, Any, TM, CM, TF, Any, Any] => TagMod = (_: Cell[T, Any, TM, CM, TF, Any, Any]) =>
    TagMod.empty,
  footerMod:            TagMod = TagMod.empty,
  footerRowMod:         HeaderGroup[T, TM, CM, TF] => TagMod = (_: HeaderGroup[T, TM, CM, TF]) =>
    TagMod.empty,
  footerCellMod:        Header[T, Any, TM, CM, TF, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, TF, Any, Any]) => TagMod.empty,
  emptyMessage:         VdomNode = EmptyVdom,

  // Virtual options
  overscan:         js.UndefOr[Int] = js.undefined,
  getItemKey:       js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:         js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:   js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer: js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLVirtualizedTable.component)
    with HTMLVirtualizedTableProps[T, TM, CM, TF]

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
final case class HTMLAutoHeightVirtualizedTable[T, TM, CM, TF](
  table:                Table[T, TM, CM, TF],
  estimateSize:         Int => SizePx,
  // Table options
  containerMod:         TagMod = TagMod.empty,
  containerRef:         js.UndefOr[Ref.ToVdom[HTMLElement]] = js.undefined,
  innerContainerMod:    TagMod = TagMod.empty,
  tableMod:             TagMod = TagMod.empty,
  headerMod:            TagMod = TagMod.empty,
  headerRowMod:         HeaderGroup[T, TM, CM, TF] => TagMod = (_: HeaderGroup[T, TM, CM, TF]) =>
    TagMod.empty,
  headerCellMod:        Header[T, Any, TM, CM, TF, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, TF, Any, Any]) => TagMod.empty,
  columnFilterRenderer: Column[T, Any, TM, CM, TF, Any, Any] => VdomNode =
    (_: Column[T, Any, TM, CM, TF, Any, Any]) => EmptyVdom,
  bodyMod:              TagMod = TagMod.empty,
  rowMod:               Row[T, TM, CM, TF] => TagMod = (_: Row[T, TM, CM, TF]) => TagMod.empty,
  cellMod:              Cell[T, Any, TM, CM, TF, Any, Any] => TagMod = (_: Cell[T, Any, TM, CM, TF, Any, Any]) =>
    TagMod.empty,
  footerMod:            TagMod = TagMod.empty,
  footerRowMod:         HeaderGroup[T, TM, CM, TF] => TagMod = (_: HeaderGroup[T, TM, CM, TF]) =>
    TagMod.empty,
  footerCellMod:        Header[T, Any, TM, CM, TF, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, TF, Any, Any]) => TagMod.empty,
  emptyMessage:         VdomNode = EmptyVdom,
  // Virtual options
  overscan:             js.UndefOr[Int] = js.undefined,
  getItemKey:           js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:             js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:       js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer:     js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLAutoHeightVirtualizedTable.component)
    with HTMLAutoHeightVirtualizedTableProps[T, TM, CM, TF]

private val baseHTMLRenderer: HTMLTableRenderer[Any, Any, Any, Any] =
  new HTMLTableRenderer[Any, Any, Any, Any] {}

object HTMLTable:
  private val component =
    HTMLTableRenderer.componentBuilder[Any, Any, Any, Any, HTMLTable](baseHTMLRenderer)

object HTMLVirtualizedTable:
  private val component =
    HTMLTableRenderer.componentBuilderVirtualized[Any, Any, Any, Any, HTMLVirtualizedTable](
      baseHTMLRenderer
    )

object HTMLAutoHeightVirtualizedTable:
  private val component =
    HTMLTableRenderer
      .componentBuilderAutoHeightVirtualized[Any, Any, Any, Any, HTMLAutoHeightVirtualizedTable](
        baseHTMLRenderer
      )
