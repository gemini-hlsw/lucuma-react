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

final case class HTMLTable[T, TM, CM](
  table:                Table[T, TM, CM],
  tableMod:             TagMod = TagMod.empty,
  headerMod:            TagMod = TagMod.empty,
  headerRowMod:         HeaderGroup[T, TM, CM] => TagMod = (_: HeaderGroup[T, TM, CM]) => TagMod.empty,
  headerCellMod:        Header[T, Any, TM, CM, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, Any, Any]) => TagMod.empty,
  columnFilterRenderer: Column[T, Any, TM, CM, Any, Any] => VdomNode =
    (_: Column[T, Any, TM, CM, Any, Any]) => EmptyVdom,
  bodyMod:              TagMod = TagMod.empty,
  rowMod:               Row[T, TM, CM] => TagMod = (_: Row[T, TM, CM]) => TagMod.empty,
  cellMod:              Cell[T, Any, TM, CM, Any, Any] => TagMod = (_: Cell[T, Any, TM, CM, Any, Any]) =>
    TagMod.empty,
  footerMod:            TagMod = TagMod.empty,
  footerRowMod:         HeaderGroup[T, TM, CM] => TagMod = (_: HeaderGroup[T, TM, CM]) => TagMod.empty,
  footerCellMod:        Header[T, Any, TM, CM, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, Any, Any]) => TagMod.empty,
  emptyMessage:         VdomNode = EmptyVdom
) extends ReactFnProps(HTMLTable.component)
    with HTMLTableProps[T, TM, CM]

final case class HTMLVirtualizedTable[T, TM, CM](
  table:                Table[T, TM, CM],
  estimateSize:         Int => SizePx,
  // Table options
  containerMod:         TagMod = TagMod.empty,
  containerRef:         js.UndefOr[Ref.ToVdom[HTMLElement]] = js.undefined,
  tableMod:             TagMod = TagMod.empty,
  headerMod:            TagMod = TagMod.empty,
  headerRowMod:         HeaderGroup[T, TM, CM] => TagMod = (_: HeaderGroup[T, TM, CM]) => TagMod.empty,
  headerCellMod:        Header[T, Any, TM, CM, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, Any, Any]) => TagMod.empty,
  columnFilterRenderer: Column[T, Any, TM, CM, Any, Any] => VdomNode =
    (_: Column[T, Any, TM, CM, Any, Any]) => EmptyVdom,
  bodyMod:              TagMod = TagMod.empty,
  rowMod:               Row[T, TM, CM] => TagMod = (_: Row[T, TM, CM]) => TagMod.empty,
  cellMod:              Cell[T, Any, TM, CM, Any, Any] => TagMod = (_: Cell[T, Any, TM, CM, Any, Any]) =>
    TagMod.empty,
  footerMod:            TagMod = TagMod.empty,
  footerRowMod:         HeaderGroup[T, TM, CM] => TagMod = (_: HeaderGroup[T, TM, CM]) => TagMod.empty,
  footerCellMod:        Header[T, Any, TM, CM, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, Any, Any]) => TagMod.empty,
  emptyMessage:         VdomNode = EmptyVdom,

  // Virtual options
  overscan:         js.UndefOr[Int] = js.undefined,
  getItemKey:       js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:         js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:   js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer: js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLVirtualizedTable.component)
    with HTMLVirtualizedTableProps[T, TM, CM]

final case class HTMLAutoHeightVirtualizedTable[T, TM, CM](
  table:                Table[T, TM, CM],
  estimateSize:         Int => SizePx,
  // Table options
  containerMod:         TagMod = TagMod.empty,
  containerRef:         js.UndefOr[Ref.ToVdom[HTMLElement]] = js.undefined,
  innerContainerMod:    TagMod = TagMod.empty,
  tableMod:             TagMod = TagMod.empty,
  headerMod:            TagMod = TagMod.empty,
  headerRowMod:         HeaderGroup[T, TM, CM] => TagMod = (_: HeaderGroup[T, TM, CM]) => TagMod.empty,
  headerCellMod:        Header[T, Any, TM, CM, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, Any, Any]) => TagMod.empty,
  columnFilterRenderer: Column[T, Any, TM, CM, Any, Any] => VdomNode =
    (_: Column[T, Any, TM, CM, Any, Any]) => EmptyVdom,
  bodyMod:              TagMod = TagMod.empty,
  rowMod:               Row[T, TM, CM] => TagMod = (_: Row[T, TM, CM]) => TagMod.empty,
  cellMod:              Cell[T, Any, TM, CM, Any, Any] => TagMod = (_: Cell[T, Any, TM, CM, Any, Any]) =>
    TagMod.empty,
  footerMod:            TagMod = TagMod.empty,
  footerRowMod:         HeaderGroup[T, TM, CM] => TagMod = (_: HeaderGroup[T, TM, CM]) => TagMod.empty,
  footerCellMod:        Header[T, Any, TM, CM, Any, Any] => TagMod =
    (_: Header[T, Any, TM, CM, Any, Any]) => TagMod.empty,
  emptyMessage:         VdomNode = EmptyVdom,
  // Virtual options
  overscan:             js.UndefOr[Int] = js.undefined,
  getItemKey:           js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:             js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:       js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer:     js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLAutoHeightVirtualizedTable.component)
    with HTMLAutoHeightVirtualizedTableProps[T, TM, CM]

private val baseHTMLRenderer: HTMLTableRenderer[Any] =
  new HTMLTableRenderer[Any] {}

object HTMLTable:
  private val component =
    HTMLTableRenderer.componentBuilder[Any, Any, Any, HTMLTable](baseHTMLRenderer)

object HTMLVirtualizedTable:
  private val component =
    HTMLTableRenderer.componentBuilderVirtualized[Any, Any, Any, HTMLVirtualizedTable](
      baseHTMLRenderer
    )

object HTMLAutoHeightVirtualizedTable:
  private val component =
    HTMLTableRenderer
      .componentBuilderAutoHeightVirtualized[Any, Any, Any, HTMLAutoHeightVirtualizedTable](
        baseHTMLRenderer
      )
