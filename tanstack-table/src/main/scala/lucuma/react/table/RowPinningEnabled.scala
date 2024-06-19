// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

import scalajs.js

enum RowPinningEnabled[T, TM](
  val toJs: Boolean | js.Function1[raw.buildLibTypesMod.Row[T], Boolean]
):
  case ForAllRows extends RowPinningEnabled[Nothing, Nothing](true)
  case ForNoRows  extends RowPinningEnabled[Nothing, Nothing](false)
  case When[T, TM](isEnabled: Row[T, TM] => Boolean)
      extends RowPinningEnabled[T, TM]((row: raw.buildLibTypesMod.Row[T]) => isEnabled(Row(row)))

  private def covary[T, TM]: RowPinningEnabled[T, TM] = this.asInstanceOf[RowPinningEnabled[T, TM]]

object RowPinningEnabled:
  def fromJs[T, TM](
    rp: Boolean | js.Function1[raw.buildLibTypesMod.Row[T], Boolean]
  ): RowPinningEnabled[T, TM] =
    js.typeOf(rp) match
      case "boolean" =>
        if rp.asInstanceOf[Boolean] then ForAllRows.covary[T, TM] else ForNoRows.covary[T, TM]
      case _         =>
        When((row: Row[T, TM]) =>
          rp.asInstanceOf[js.Function1[raw.buildLibTypesMod.Row[T], Boolean]](row.toJs)
        )
