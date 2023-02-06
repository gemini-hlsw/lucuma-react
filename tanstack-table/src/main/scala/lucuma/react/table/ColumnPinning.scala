// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import lucuma.typed.{tanstackTableCore => raw}

import scalajs.js.JSConverters.*

case class ColumnPinning(left: List[ColumnId], right: List[ColumnId]):
  def toJs: raw.buildLibFeaturesPinningMod.ColumnPinningState =
    val base        = raw.buildLibFeaturesPinningMod.ColumnPinningState()
    val leftApplied = left match
      case Nil      => base
      case nonEmpty => base.setLeft(nonEmpty.map(_.value).toJSArray)
    right match
      case Nil      => leftApplied
      case nonEmpty => leftApplied.setRight(nonEmpty.map(_.value).toJSArray)

object ColumnPinning:
  def apply(left: ColumnId*): ColumnPinning = ColumnPinning(left.toList, Nil)

  private[table] def fromJs(rawValue: raw.buildLibFeaturesPinningMod.ColumnPinningState): ColumnPinning =
    ColumnPinning(
      rawValue.left.toOption.map(_.toList.map(ColumnId(_))).orEmpty,
      rawValue.right.toOption.map(_.toList.map(ColumnId(_))).orEmpty
    )
