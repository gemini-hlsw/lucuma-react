// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.NonEmptyRef
import japgolly.scalajs.react.Ref
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import lucuma.react.SizePx
import lucuma.react.virtual.facade.Virtualizer
import lucuma.typed.{tanstackTableCore => raw}
import lucuma.typed.{tanstackVirtualCore => rawVirtual}
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLDivElement
import react.common.style.Css

import scalajs.js

trait HTMLTableProps[T]:
  def table: Table[T]
  def tableMod: TagMod
  def headerMod: TagMod
  def headerRowMod: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod
  def headerCellMod: raw.buildLibTypesMod.Header[T, Any] => TagMod
  def bodyMod: TagMod
  def rowMod: raw.buildLibTypesMod.Row[T] => TagMod
  def cellMod: raw.buildLibTypesMod.Cell[T, Any] => TagMod
  def footerMod: TagMod
  def footerRowMod: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod
  def footerCellMod: raw.buildLibTypesMod.Header[T, Any] => TagMod
  def emptyMessage: VdomNode
  def renderSubComponent: raw.buildLibTypesMod.Row[T] => Option[VdomNode]

  // Allow subtypes to mixin other classes
  private[table] def extraTableClasses: Css = Css.Empty

type HTMLTableVirtualizer = Virtualizer[HTMLDivElement, Element]

trait HTMLVirtualizedTableProps[T] extends HTMLTableProps[T]:
  def estimateSize: Int => SizePx
  // Table options
  def containerMod: TagMod
  def containerRef: js.UndefOr[Ref.Simple[HTMLDivElement]]
  // Virtual options
  def overscan: js.UndefOr[Int]
  def getItemKey: js.UndefOr[Int => rawVirtual.mod.Key]
  def onChange: js.UndefOr[HTMLTableVirtualizer => Callback]
  def virtualizerRef: js.UndefOr[NonEmptyRef.Simple[Option[HTMLTableVirtualizer]]]
  def debugVirtualizer: js.UndefOr[Boolean]

trait HTMLAutoHeightVirtualizedTableProps[T] extends HTMLVirtualizedTableProps[T]:
  def innerContainerMod: TagMod
