// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import japgolly.scalajs.react.vdom.TagMod

import scalajs.js

package object table extends HooksApiExt:
  export TableHook.useReactTable

  type SortingFn[T, TM, CM] = (Row[T, TM, CM], Row[T, TM, CM], ColumnId) => Int

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
    private[table] def applyOrNot[A](a: js.UndefOr[A], f: (B, A) => B): B =
      a.fold(b)(a => f(b, a))

    private[table] def applyOrNull[A](a: Option[A], f: (B, A) => B, fNull: B => B): B =
      a.fold(fNull(b))(a => f(b, a))

    private[table] def applyWhen[A](cond: Boolean, f: B => B): B =
      if cond then f(b) else b

  extension [A](opt: Null | A)
    private[table] def nullToOption: Option[A] = opt match
      case null => None
      case a    => Some(a.asInstanceOf[A])
