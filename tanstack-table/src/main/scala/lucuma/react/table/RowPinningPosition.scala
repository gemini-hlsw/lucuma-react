// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import lucuma.typed.tanstackTableCore.buildLibFeaturesRowPinningMod as raw

enum RowPinningPosition(val toJs: raw.RowPinningPosition):
  case Top    extends RowPinningPosition(raw.RowPinningPosition.top)
  case Bottom extends RowPinningPosition(raw.RowPinningPosition.bottom)

object RowPinningPosition:
  def fromJs(rawValue: raw.RowPinningPosition): Option[RowPinningPosition] =
    if rawValue == raw.RowPinningPosition.top then Top.some
    else if rawValue == raw.RowPinningPosition.bottom then Bottom.some
    else none
