// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import japgolly.scalajs.react.vdom.TagMod
import reactST.{tanstackTableCore => raw}

import scalajs.js

package object table extends HooksApiExt:
  type SortingFn[T] = (Row[T], Row[T], ColumnId) => Int

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

  extension [T, A](cellCtx: raw.mod.CellContext[T, A])
    def value: A = cellCtx.getValue().asInstanceOf[A]

  given renderJSArray[A](using ev: A => TagMod): Conversion[js.Array[A], TagMod] =
    new Conversion {
      def apply(x: js.Array[A]): TagMod = TagMod.fromTraversableOnce(x.map(ev))
    }

  extension [B](b: B)
    private[table] def applyOrNot[A](a: js.UndefOr[A], f: (B, A) => B): B =
      a.fold(b)(a => f(b, a))

    private[table] def applyOrNull[A](a: Option[A], f: (B, A) => B, fNull: B => B): B =
      a.fold(fNull(b))(a => f(b, a))

  extension [A](opt: Null | A)
    private[table] def nullToOption: Option[A] = opt match
      case null => None
      case a    => Some(a.asInstanceOf[A])
