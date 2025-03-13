// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

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
enum RowPinningEnabled[T, TM, CM, TF](
  val toJs: Boolean | js.Function1[raw.buildLibTypesMod.Row[T], Boolean]
):
  case ForAllRows extends RowPinningEnabled[Nothing, Nothing, Nothing, Nothing](true)
  case ForNoRows  extends RowPinningEnabled[Nothing, Nothing, Nothing, Nothing](false)
  case When[T, TM, CM, TF](isEnabled: Row[T, TM, CM, TF] => Boolean)
      extends RowPinningEnabled[T, TM, CM, TF]((row: raw.buildLibTypesMod.Row[T]) =>
        isEnabled(Row(row))
      )

  private def covary[T1, TM1, CM1, TF1]: RowPinningEnabled[T1, TM1, CM1, TF1] =
    this.asInstanceOf[RowPinningEnabled[T1, TM1, CM1, TF1]]

object RowPinningEnabled:
  def fromJs[T, TM, CM, TF](
    rp: Boolean | js.Function1[raw.buildLibTypesMod.Row[T], Boolean]
  ): RowPinningEnabled[T, TM, CM, TF] =
    js.typeOf(rp) match
      case "boolean" =>
        if rp.asInstanceOf[Boolean] then ForAllRows.covary[T, TM, CM, TF]
        else ForNoRows.covary[T, TM, CM, TF]
      case _         =>
        When: (row: Row[T, TM, CM, TF]) =>
          rp.asInstanceOf[js.Function1[raw.buildLibTypesMod.Row[T], Boolean]](row.toJs)

  // We can't make RowPinningEnabled covariant on its type parameters but we can do this.
  given [T, TM, CM, TF]: Conversion[
    RowPinningEnabled[Nothing, Nothing, Nothing, Nothing],
    js.UndefOr[RowPinningEnabled[T, TM, CM, TF]]
  ] =
    _.covary[T, TM, CM, TF]
