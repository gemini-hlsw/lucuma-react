// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

import scalajs.js

enum RowPinningEnabled[T, TM, CM](
  val toJs: Boolean | js.Function1[raw.buildLibTypesMod.Row[T], Boolean]
):
  case ForAllRows extends RowPinningEnabled[Nothing, Nothing, Nothing](true)
  case ForNoRows  extends RowPinningEnabled[Nothing, Nothing, Nothing](false)
  case When[T, TM, CM](isEnabled: Row[T, TM, CM] => Boolean)
      extends RowPinningEnabled[T, TM, CM]((row: raw.buildLibTypesMod.Row[T]) =>
        isEnabled(Row(row))
      )

  private def covary[T, TM, CM]: RowPinningEnabled[T, TM, CM] =
    this.asInstanceOf[RowPinningEnabled[T, TM, CM]]

object RowPinningEnabled:
  def fromJs[T, TM, CM](
    rp: Boolean | js.Function1[raw.buildLibTypesMod.Row[T], Boolean]
  ): RowPinningEnabled[T, TM, CM] =
    js.typeOf(rp) match
      case "boolean" =>
        if rp.asInstanceOf[Boolean] then ForAllRows.covary[T, TM, CM]
        else ForNoRows.covary[T, TM, CM]
      case _         =>
        When: (row: Row[T, TM, CM]) =>
          rp.asInstanceOf[js.Function1[raw.buildLibTypesMod.Row[T], Boolean]](row.toJs)

  // We can't make RowPinningEnabled covariant on its type parameters but we can do this.
  given [T, TM, CM]: Conversion[
    RowPinningEnabled[Nothing, Nothing, Nothing],
    js.UndefOr[RowPinningEnabled[T, TM, CM]]
  ] =
    _.covary[T, TM, CM]
