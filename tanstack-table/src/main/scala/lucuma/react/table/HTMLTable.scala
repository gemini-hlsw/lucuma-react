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

final case class HTMLTable[T, TM](
  table:         Table[T, TM],
  tableMod:      TagMod = TagMod.empty,
  headerMod:     TagMod = TagMod.empty,
  headerRowMod:  HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
  headerCellMod: Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
  bodyMod:       TagMod = TagMod.empty,
  rowMod:        Row[T, TM] => TagMod = (_: Row[T, TM]) => TagMod.empty,
  cellMod:       Cell[T, Any, TM, Any] => TagMod = (_: Cell[T, Any, TM, Any]) => TagMod.empty,
  footerMod:     TagMod = TagMod.empty,
  footerRowMod:  HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
  footerCellMod: Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
  emptyMessage:  VdomNode = EmptyVdom
) extends ReactFnProps(HTMLTable.component)
    with HTMLTableProps[T, TM]

final case class HTMLVirtualizedTable[T, TM](
  table:         Table[T, TM],
  estimateSize:  Int => SizePx,
  // Table options
  containerMod:  TagMod = TagMod.empty,
  containerRef:  js.UndefOr[Ref.ToVdom[HTMLElement]] = js.undefined,
  tableMod:      TagMod = TagMod.empty,
  headerMod:     TagMod = TagMod.empty,
  headerRowMod:  HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
  headerCellMod: Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
  bodyMod:       TagMod = TagMod.empty,
  rowMod:        Row[T, TM] => TagMod = (_: Row[T, TM]) => TagMod.empty,
  cellMod:       Cell[T, Any, TM, Any] => TagMod = (_: Cell[T, Any, TM, Any]) => TagMod.empty,
  footerMod:     TagMod = TagMod.empty,
  footerRowMod:  HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
  footerCellMod: Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
  emptyMessage:  VdomNode = EmptyVdom,

  // Virtual options
  overscan:         js.UndefOr[Int] = js.undefined,
  getItemKey:       js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:         js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:   js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer: js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLVirtualizedTable.component)
    with HTMLVirtualizedTableProps[T, TM]

final case class HTMLAutoHeightVirtualizedTable[T, TM](
  table:             Table[T, TM],
  estimateSize:      Int => SizePx,
  // Table options
  containerMod:      TagMod = TagMod.empty,
  containerRef:      js.UndefOr[Ref.ToVdom[HTMLElement]] = js.undefined,
  innerContainerMod: TagMod = TagMod.empty,
  tableMod:          TagMod = TagMod.empty,
  headerMod:         TagMod = TagMod.empty,
  headerRowMod:      HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
  headerCellMod:     Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
  bodyMod:           TagMod = TagMod.empty,
  rowMod:            Row[T, TM] => TagMod = (_: Row[T, TM]) => TagMod.empty,
  cellMod:           Cell[T, Any, TM, Any] => TagMod = (_: Cell[T, Any, TM, Any]) => TagMod.empty,
  footerMod:         TagMod = TagMod.empty,
  footerRowMod:      HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
  footerCellMod:     Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
  emptyMessage:      VdomNode = EmptyVdom,
  // Virtual options
  overscan:          js.UndefOr[Int] = js.undefined,
  getItemKey:        js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:          js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:    js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer:  js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLAutoHeightVirtualizedTable.component)
    with HTMLAutoHeightVirtualizedTableProps[T, TM]

private val baseHTMLRenderer: HTMLTableRenderer[Any] =
  new HTMLTableRenderer[Any] {}

object HTMLTable:
  private val component = HTMLTableRenderer.componentBuilder[Any, Any, HTMLTable](baseHTMLRenderer)

object HTMLVirtualizedTable:
  private val component =
    HTMLTableRenderer.componentBuilderVirtualized[Any, Any, HTMLVirtualizedTable](baseHTMLRenderer)

object HTMLAutoHeightVirtualizedTable:
  private val component =
    HTMLTableRenderer
      .componentBuilderAutoHeightVirtualized[Any, Any, HTMLAutoHeightVirtualizedTable](
        baseHTMLRenderer
      )
