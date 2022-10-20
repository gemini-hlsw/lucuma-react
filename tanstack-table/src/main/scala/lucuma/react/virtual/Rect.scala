// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import lucuma.react.SizePx
import reactST.{tanstackVirtualCore => rawVirtual}

case class Rect(height: SizePx, width: SizePx):
  def toJs: rawVirtual.mod.Rect = rawVirtual.mod.Rect(height.value, width.value)
