// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import lucuma.react.table.facade.compat.buildLibFeaturesColumnPinningMod as raw

enum ColumnPinningPosition(private[table] val toJs: raw.ColumnPinningPosition):
  case Left  extends ColumnPinningPosition(raw.ColumnPinningPosition.start)
  case Right extends ColumnPinningPosition(raw.ColumnPinningPosition.end)

object ColumnPinningPosition:
  def fromJs(rawValue: raw.ColumnPinningPosition): Option[ColumnPinningPosition] =
    if rawValue == raw.ColumnPinningPosition.start then Left.some
    else if rawValue == raw.ColumnPinningPosition.end then Right.some
    else none
