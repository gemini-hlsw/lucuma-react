// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore.buildLibFeaturesColumnPinningMod as raw

enum ColumnPinningPosition(private[table] val toJs: raw.ColumnPinningPosition):
  case Left  extends ColumnPinningPosition(raw.ColumnPinningPosition.left)
  case Right extends ColumnPinningPosition(raw.ColumnPinningPosition.right)
