// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.NonEmptyRef
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import lucuma.react.virtual.facade.Virtualizer
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLDivElement
import react.common.style.Css
import reactST.{tanstackTableCore => raw}
import reactST.{tanstackVirtualCore => rawVirtual}

import scalajs.js

trait HTMLTableProps[T]:
  def table: raw.mod.Table[T]
  def tableMod: TagMod
  def headerMod: TagMod
  def headerRowMod: raw.mod.CoreHeaderGroup[T] => TagMod
  def headerCellMod: raw.mod.Header[T, Any] => TagMod
  def bodyMod: TagMod
  def rowMod: raw.mod.Row[T] => TagMod
  def cellMod: raw.mod.Cell[T, Any] => TagMod
  def footerMod: TagMod
  def footerRowMod: raw.mod.CoreHeaderGroup[T] => TagMod
  def footerCellMod: raw.mod.Header[T, Any] => TagMod
  def emptyMessage: VdomNode

  // Allow subtypes to mixin other classes
  protected[table] def extraTableClasses: Css = Css.Empty

type HTMLTableVirtualizer = Virtualizer[HTMLDivElement, Element]

trait HTMLVirtualizedTableProps[T] extends HTMLTableProps[T]:
  def estimateRowHeightPx: Int => Int
  // Table options
  def containerMod: TagMod
  // Virtual options
  def overscan: js.UndefOr[Int]
  def getItemKey: js.UndefOr[Int => rawVirtual.mod.Key]
  def onChange: js.UndefOr[HTMLTableVirtualizer => Callback]
  def virtualizerRef: js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]]

trait HTMLAutoHeightVirtualizedTableProps[T] extends HTMLVirtualizedTableProps[T]:
  def innerContainerMod: TagMod
