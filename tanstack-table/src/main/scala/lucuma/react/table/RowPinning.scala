// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import lucuma.typed.tanstackTableCore.buildLibFeaturesRowPinningMod as raw

import scalajs.js.JSConverters.*

case class RowPinning(bottom: List[RowId] = List.empty, top: List[RowId] = List.empty):
  def toJs: raw.RowPinningState =
    raw
      .RowPinningState()
      .setBottom(bottom.map(_.value).toJSArray)
      .setTop(top.map(_.value).toJSArray)

  def addedBottom(rowId: RowId): RowPinning =
    copy(bottom = bottom :+ rowId)

  def removedBottom(rowId: RowId): RowPinning =
    copy(bottom = bottom.filterNot(_ == rowId))

  def addedTop(rowId: RowId): RowPinning =
    copy(top = top :+ rowId)

  def removedTop(rowId: RowId): RowPinning =
    copy(top = top.filterNot(_ == rowId))

object RowPinning:
  val Empty: RowPinning = RowPinning()

  def bottom(bottom: RowId*): RowPinning = RowPinning(bottom = bottom.toList)

  def top(top: RowId*): RowPinning = RowPinning(top = top.toList)

  def fromJs(rawValue: raw.RowPinningState): RowPinning =
    RowPinning(
      rawValue.bottom.map(_.toList.map(RowId(_))).toOption.orEmpty,
      rawValue.top.map(_.toList.map(RowId(_))).toOption.orEmpty
    )
