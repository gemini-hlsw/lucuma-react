// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.SizePx
import lucuma.typed.{tanstackTableCore => raw}
import lucuma.typed.{tanstackVirtualCore => rawVirtual}
import org.scalajs.dom.HTMLElement
import react.common.*
import react.common.style.Css

import javax.swing.text.html.HTML

import scalajs.js

final case class HTMLTable[T](
  table:              Table[T],
  tableMod:           TagMod = TagMod.empty,
  headerMod:          TagMod = TagMod.empty,
  headerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
    (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
  headerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
    (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
  bodyMod:            TagMod = TagMod.empty,
  rowMod:             raw.buildLibTypesMod.Row[T] => TagMod = (_: raw.buildLibTypesMod.Row[T]) => TagMod.empty,
  cellMod:            raw.buildLibTypesMod.Cell[T, Any] => TagMod = (_: raw.buildLibTypesMod.Cell[T, Any]) =>
    TagMod.empty,
  footerMod:          TagMod = TagMod.empty,
  footerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
    (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
  footerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
    (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
  emptyMessage:       VdomNode = EmptyVdom,
  renderSubComponent: raw.buildLibTypesMod.Row[T] => Option[VdomNode] =
    (_: raw.buildLibTypesMod.Row[T]) => none
) extends ReactFnProps(HTMLTable.component)
    with HTMLTableProps[T]

final case class HTMLVirtualizedTable[T](
  table:              Table[T],
  estimateSize:       Int => SizePx,
  // Table options
  containerMod:       TagMod = TagMod.empty,
  containerRef:       js.UndefOr[Ref.Simple[HTMLElement]] = js.undefined,
  tableMod:           TagMod = TagMod.empty,
  headerMod:          TagMod = TagMod.empty,
  headerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
    (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
  headerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
    (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
  bodyMod:            TagMod = TagMod.empty,
  rowMod:             raw.buildLibTypesMod.Row[T] => TagMod = (_: raw.buildLibTypesMod.Row[T]) => TagMod.empty,
  cellMod:            raw.buildLibTypesMod.Cell[T, Any] => TagMod = (_: raw.buildLibTypesMod.Cell[T, Any]) =>
    TagMod.empty,
  footerMod:          TagMod = TagMod.empty,
  footerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
    (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
  footerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
    (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
  emptyMessage:       VdomNode = EmptyVdom,
  renderSubComponent: raw.buildLibTypesMod.Row[T] => Option[VdomNode] =
    (_: raw.buildLibTypesMod.Row[T]) => none,

  // Virtual options
  overscan:           js.UndefOr[Int] = js.undefined,
  getItemKey:         js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:           js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:     js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer:   js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLVirtualizedTable.component)
    with HTMLVirtualizedTableProps[T]

final case class HTMLAutoHeightVirtualizedTable[T](
  table:              Table[T],
  estimateSize:       Int => SizePx,
  // Table options
  containerMod:       TagMod = TagMod.empty,
  containerRef:       js.UndefOr[Ref.Simple[HTMLElement]] = js.undefined,
  innerContainerMod:  TagMod = TagMod.empty,
  tableMod:           TagMod = TagMod.empty,
  headerMod:          TagMod = TagMod.empty,
  headerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
    (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
  headerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
    (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
  bodyMod:            TagMod = TagMod.empty,
  rowMod:             raw.buildLibTypesMod.Row[T] => TagMod = (_: raw.buildLibTypesMod.Row[T]) => TagMod.empty,
  cellMod:            raw.buildLibTypesMod.Cell[T, Any] => TagMod = (_: raw.buildLibTypesMod.Cell[T, Any]) =>
    TagMod.empty,
  footerMod:          TagMod = TagMod.empty,
  footerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
    (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
  footerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
    (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
  emptyMessage:       VdomNode = EmptyVdom,
  renderSubComponent: raw.buildLibTypesMod.Row[T] => Option[VdomNode] =
    (_: raw.buildLibTypesMod.Row[T]) => none,
  // Virtual options
  overscan:           js.UndefOr[Int] = js.undefined,
  getItemKey:         js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  onChange:           js.UndefOr[HTMLTableVirtualizer => Callback] = js.undefined,
  virtualizerRef:     js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]] = js.undefined,
  debugVirtualizer:   js.UndefOr[Boolean] = js.undefined
) extends ReactFnProps(HTMLAutoHeightVirtualizedTable.component)
    with HTMLAutoHeightVirtualizedTableProps[T]

private val baseHTMLRenderer: HTMLTableRenderer[Any] =
  new HTMLTableRenderer[Any] {}

object HTMLTable:
  private val component = HTMLTableRenderer.componentBuilder[Any, HTMLTable](baseHTMLRenderer)

object HTMLVirtualizedTable:
  private val component =
    HTMLTableRenderer.componentBuilderVirtualized[Any, HTMLVirtualizedTable](baseHTMLRenderer)

object HTMLAutoHeightVirtualizedTable:
  private val component =
    HTMLTableRenderer.componentBuilderAutoHeightVirtualized[Any, HTMLAutoHeightVirtualizedTable](
      baseHTMLRenderer
    )
