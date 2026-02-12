// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import japgolly.scalajs.react.vdom.TagMod
import lucuma.typed.std.Map as JsMap

import scala.scalajs.js.annotation.JSGlobal

import scalajs.js
import scalajs.js.JSConverters.*

package object table extends HooksApiExt:
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

  // enum RowContext[T]:
  // TODO Propagate context type
  // enum RowContext:
  //   case Empty            extends RowContext
  //   case Data(value: Any) extends RowContext

  type RowContext[RC] = Option[RC]
