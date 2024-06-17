// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.NonEmptyRef
import japgolly.scalajs.react.Ref
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import lucuma.react.SizePx
import lucuma.react.common.style.Css
import lucuma.react.virtual.facade.Virtualizer
import lucuma.typed.tanstackVirtualCore as rawVirtual
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLElement

import scalajs.js

trait HTMLTableProps[T, TM]:
  def table: Table[T, TM]
  def tableMod: TagMod
  def headerMod: TagMod
  def headerRowMod: HeaderGroup[T, TM] => TagMod
  def headerCellMod: Header[T, Any, TM, Any] => TagMod
  def bodyMod: TagMod
  def rowMod: Row[T, TM] => TagMod
  def cellMod: Cell[T, Any, TM, Any] => TagMod
  def footerMod: TagMod
  def footerRowMod: HeaderGroup[T, TM] => TagMod
  def footerCellMod: Header[T, Any, TM, Any] => TagMod
  def emptyMessage: VdomNode

  // Allow subtypes to mixin other classes
  private[table] def extraTableClasses: Css = Css.Empty

type HTMLTableVirtualizer = Virtualizer[HTMLElement, Element]

trait HTMLVirtualizedTableProps[T, M] extends HTMLTableProps[T, M]:
  def estimateSize: Int => SizePx
  // Table options
  def containerMod: TagMod
  def containerRef: js.UndefOr[Ref.ToVdom[HTMLElement]]
  // Virtual options
  def overscan: js.UndefOr[Int]
  def getItemKey: js.UndefOr[Int => rawVirtual.mod.Key]
  def onChange: js.UndefOr[HTMLTableVirtualizer => Callback]
  def virtualizerRef: js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]]
  def debugVirtualizer: js.UndefOr[Boolean]

trait HTMLAutoHeightVirtualizedTableProps[T, M] extends HTMLVirtualizedTableProps[T, M]:
  def innerContainerMod: TagMod
