// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import lucuma.typed.tanstackTableCore.buildLibFeaturesColumnPinningMod as raw

enum ColumnPinningPosition(private[table] val toJs: raw.ColumnPinningPosition):
  case Left  extends ColumnPinningPosition(raw.ColumnPinningPosition.left)
  case Right extends ColumnPinningPosition(raw.ColumnPinningPosition.right)

object ColumnPinningPosition:
  def fromJs(rawValue: raw.ColumnPinningPosition): Option[ColumnPinningPosition] =
    if rawValue == raw.ColumnPinningPosition.left then Left.some
    else if rawValue == raw.ColumnPinningPosition.right then Right.some
    else none
