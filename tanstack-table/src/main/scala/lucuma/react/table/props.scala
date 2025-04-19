// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
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
trait HTMLTableProps[T, TM, CM, TF]:
  def table: Table[T, TM, CM, TF]
  def tableMod: TagMod
  def headerMod: TagMod
  def headerRowMod: HeaderGroup[T, TM, CM, TF] => TagMod
  def columnFilterRenderer: Column[T, Any, TM, CM, TF, Any, Any] => VdomNode
  def headerCellMod: Header[T, Any, TM, CM, TF, Any, Any] => TagMod
  def bodyMod: TagMod
  def rowMod: Row[T, TM, CM, TF] => TagMod
  def cellMod: Cell[T, Any, TM, CM, TF, Any, Any] => TagMod
  def footerMod: TagMod
  def footerRowMod: HeaderGroup[T, TM, CM, TF] => TagMod
  def footerCellMod: Header[T, Any, TM, CM, TF, Any, Any] => TagMod
  def emptyMessage: VdomNode

  // Allow subtypes to mixin other classes
  private[table] def extraTableClasses: Css = Css.Empty

type HTMLTableVirtualizer = Virtualizer[HTMLElement, Element]

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
trait HTMLVirtualizedTableProps[T, TM, CM, TF] extends HTMLTableProps[T, TM, CM, TF]:
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
trait HTMLAutoHeightVirtualizedTableProps[T, TM, CM, TF]
    extends HTMLVirtualizedTableProps[T, TM, CM, TF]:
  def innerContainerMod: TagMod
