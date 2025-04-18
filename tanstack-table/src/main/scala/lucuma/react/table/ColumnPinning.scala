// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import lucuma.typed.tanstackTableCore as raw

import scalajs.js.JSConverters.*

case class ColumnPinning(left: List[ColumnId] = List.empty, right: List[ColumnId] = List.empty):
  def toJs: raw.buildLibFeaturesColumnPinningMod.ColumnPinningState =
    val base        = raw.buildLibFeaturesColumnPinningMod.ColumnPinningState()
    val leftApplied =
      left match
        case Nil      => base
        case nonEmpty => base.setLeft(nonEmpty.map(_.value).toJSArray)
    right match
      case Nil      => leftApplied
      case nonEmpty => leftApplied.setRight(nonEmpty.map(_.value).toJSArray)

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
      rawValue.left.toOption.map(_.toList.map(ColumnId(_))).orEmpty,
      rawValue.right.toOption.map(_.toList.map(ColumnId(_))).orEmpty
    )
