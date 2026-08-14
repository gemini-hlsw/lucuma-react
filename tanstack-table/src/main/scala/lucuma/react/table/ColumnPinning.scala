// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.react.table.facade.compat as raw

import scalajs.js.JSConverters.*

case class ColumnPinning(left: List[ColumnId] = List.empty, right: List[ColumnId] = List.empty):
  def toJs: raw.buildLibFeaturesColumnPinningMod.ColumnPinningState =
    raw.buildLibFeaturesColumnPinningMod.ColumnPinningState(
      right.map(_.value).toJSArray,
      left.map(_.value).toJSArray
    )

  def addedLeft(columnId: ColumnId): ColumnPinning =
    copy(left = left :+ columnId)

  def removedLeft(columnId: ColumnId): ColumnPinning =
    copy(left = left.filterNot(_ == columnId))

  def addedRight(columnId: ColumnId): ColumnPinning =
    copy(right = right :+ columnId)

  def removedRight(columnId: ColumnId): ColumnPinning =
    copy(right = right.filterNot(_ == columnId))

object ColumnPinning:
  val Empty: ColumnPinning = ColumnPinning()

  def left(left: ColumnId*): ColumnPinning = ColumnPinning(left = left.toList)

  def right(right: ColumnId*): ColumnPinning = ColumnPinning(right = right.toList)

  private[table] def fromJs(
    rawValue: raw.buildLibFeaturesColumnPinningMod.ColumnPinningState
  ): ColumnPinning =
    ColumnPinning(
      rawValue.start.toList.map(ColumnId(_)),
      rawValue.end.toList.map(ColumnId(_))
    )
