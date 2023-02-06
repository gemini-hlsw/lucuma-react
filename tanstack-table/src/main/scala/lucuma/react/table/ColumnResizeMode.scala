// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.{tanstackTableCore => raw}

enum ColumnResizeMode:
  case OnChange, OnEnd

  def toJs: raw.buildLibFeaturesColumnSizingMod.ColumnResizeMode = this match
    case OnChange => raw.tanstackTableCoreStrings.onChange
    case OnEnd    => raw.tanstackTableCoreStrings.onEnd

object ColumnResizeMode:
  def fromJs(rawValue: raw.buildLibFeaturesColumnSizingMod.ColumnResizeMode): ColumnResizeMode =
    if (rawValue == raw.tanstackTableCoreStrings.onEnd) OnEnd
    else OnChange
