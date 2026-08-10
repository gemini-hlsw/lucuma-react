// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.VdomNode
import lucuma.react.table.facade.{compat, instance}
import lucuma.typed.std.Map as JsMap
import lucuma.typed.tanstackReactTable.distUseLegacyTableMod.LegacyFeatures
import lucuma.typed.tanstackTableCore.distTypesCellMod
import lucuma.typed.tanstackTableCore.distTypesColumnMod
import lucuma.typed.tanstackTableCore.distTypesHeaderGroupMod
import lucuma.typed.tanstackTableCore.distTypesHeaderMod
import lucuma.typed.tanstackTableCore.distTypesRowMod
import lucuma.typed.tanstackTableCore.distTypesTableMod
import lucuma.typed.tanstackTableCore.distTypesTableStateMod
import org.scalajs.dom.HTMLElement

import scala.language.implicitConversions
import scala.scalajs.js.annotation.JSGlobal

import scalajs.js
import scalajs.js.JSConverters.*

package object table extends HooksApiExt:
  // v9's public typed types (distTypes*Mod.X[LegacyFeatures, T]) and our aggregated instance
  // traits (facade.instance.X[T]) describe the same runtime objects but are disjoint to the
  // compiler. These identity conversions bridge the boundary at wrapper construction and at
  // interop call sites, so the facade code needs no scattered `.asInstanceOf` casts.
  given toInstanceRow[T]:    Conversion[distTypesRowMod.Row[LegacyFeatures, T], instance.Row[T]]            = _.asInstanceOf
  given fromInstanceRow[T]:   Conversion[instance.Row[T], distTypesRowMod.Row[LegacyFeatures, T]]            = _.asInstanceOf
  given toInstanceColumn[T, V]: Conversion[distTypesColumnMod.Column[LegacyFeatures, T, V], instance.Column[T, V]] = _.asInstanceOf
  given fromInstanceColumn[T, V]: Conversion[instance.Column[T, V], distTypesColumnMod.Column[LegacyFeatures, T, V]] = _.asInstanceOf
  given toInstanceCell[T, V]:     Conversion[distTypesCellMod.Cell[LegacyFeatures, T, V], instance.Cell[T, V]]     = _.asInstanceOf
  given fromInstanceCell[T, V]:   Conversion[instance.Cell[T, V], distTypesCellMod.Cell[LegacyFeatures, T, V]]     = _.asInstanceOf
  given toInstanceHeader[T, V]:  Conversion[distTypesHeaderMod.Header[LegacyFeatures, T, V], instance.Header[T, V]] = _.asInstanceOf
  given fromInstanceHeader[T, V]: Conversion[instance.Header[T, V], distTypesHeaderMod.Header[LegacyFeatures, T, V]] = _.asInstanceOf
  given toInstanceHeaderGroup[T]: Conversion[distTypesHeaderGroupMod.HeaderGroup[LegacyFeatures, T], instance.HeaderGroup[T]] = _.asInstanceOf
  given fromInstanceHeaderGroup[T]: Conversion[instance.HeaderGroup[T], distTypesHeaderGroupMod.HeaderGroup[LegacyFeatures, T]] = _.asInstanceOf
  given toInstanceTable[T]:    Conversion[distTypesTableMod.Table[LegacyFeatures, T], instance.Table[T]] = _.asInstanceOf
  given fromInstanceTable[T]:  Conversion[instance.Table[T], distTypesTableMod.Table[LegacyFeatures, T]] = _.asInstanceOf
  given toInstanceState[T]:    Conversion[distTypesTableStateMod.TableState[LegacyFeatures], instance.TableState] = _.asInstanceOf

  export TableHook.useReactTable

  /**
   * @tparam T
   *   The type of the row.
   * @tparam TM
   *   The type of the metadata for the table.
   * @tparam CM
   *   The type of the metadata for the column.
   * @tparam TF
   *   The type of the global filter.
   */
  type SortingFn[T, TM, CM, TF] = (Row[T, TM, CM, TF], Row[T, TM, CM, TF], ColumnId) => Int

  opaque type ColumnId = String
  object ColumnId:
    inline def apply(value: String): ColumnId                  = value
    extension (opaqueValue: ColumnId) inline def value: String = opaqueValue

  opaque type RowId = String
  object RowId:
    inline def apply(value: String): RowId                  = value
    extension (opaqueValue: RowId) inline def value: String = opaqueValue

  opaque type CellId = String
  object CellId:
    inline def apply(value: String): CellId                  = value
    extension (opaqueValue: CellId) inline def value: String = opaqueValue

  opaque type HeaderId = String
  object HeaderId:
    inline def apply(value: String): HeaderId                  = value
    extension (opaqueValue: HeaderId) inline def value: String = opaqueValue

  opaque type PlaceholderId = String
  object PlaceholderId:
    inline def apply(value: String): PlaceholderId                  = value
    extension (opaqueValue: PlaceholderId) inline def value: String = opaqueValue

  opaque type HeaderGroupId = String
  object HeaderGroupId:
    inline def apply(value: String): HeaderGroupId                  = value
    extension (opaqueValue: HeaderGroupId) inline def value: String = opaqueValue

  given renderJSArray[A](using ev: A => TagMod): Conversion[js.Array[A], TagMod] =
    new Conversion {
      def apply(x: js.Array[A]): TagMod = TagMod.fromTraversableOnce(x.map(ev))
    }

  extension [B](b: B)
    private[table] def applyOrElseWhen[A](
      cond:   Boolean,
      a:      js.UndefOr[A],
      f:      (B, A) => B,
      orElse: B => B
    ): B =
      if cond then a.fold(orElse(b))(a => f(b, a)) else b

    inline private[table] def applyOrElse[A](a: js.UndefOr[A], f: (B, A) => B, orElse: B => B): B =
      applyOrElseWhen(true, a, f, orElse)

    inline private[table] def applyOrNot[A](a: js.UndefOr[A], f: (B, A) => B): B =
      applyOrElse(a, f, identity)

    inline private[table] def applyOrNull[A](a: Option[A], f: (B, A) => B, fNull: B => B): B =
      applyOrElse(a.orUndefined, f, fNull)

    private[table] def applyWhen(cond: Boolean, f: B => B): B =
      if cond then f(b) else b

  extension [A](opt: Null | A)
    private[table] def nullToOption: Option[A] = opt match
      case null => None
      case a    => Some(a.asInstanceOf[A])

  extension (self: Map.type)
    def fromJsMap[K, V](jsMap: JsMap[K, V]): Map[K, V] =
      var builder: Map[K, V] = Map.empty[K, V]
      jsMap.forEach: (v, k, _) =>
        builder = builder + (k -> v)
      builder

  @js.native
  @JSGlobal("Map")
  class JsMapConstructor[K, V] extends JsMap[K, V]

  extension [K, V](self: Map[K, V])
    def toJsMap: JsMap[K, V] =
      val jsMap = new JsMapConstructor[K, V]
      self.foreach { case (k, v) => jsMap.set(k, v) }
      jsMap

  // Useful for the common case of just adding a TagMod to the row
  def rowTagMod[T, TM, CM, TF, RC](
    f: Row[T, TM, CM, TF] => TagMod
  ): (Row[T, TM, CM, TF], Option[RC] => TagOf[HTMLElement]) => VdomNode =
    (row, render) => render(None)(f(row))

  // Useful for the common case of just adding a TagMod to the cell
  def cellTagMod[T, TM, CM, TF, RC](
    f: Cell[T, Any, TM, CM, TF, Any, Any] => TagMod
  ): (Cell[T, Any, TM, CM, TF, Any, Any], Option[RC], TagOf[HTMLElement]) => VdomNode =
    (cell, _, render) => render(f(cell))
