// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.syntax

import lucuma.react.SizePx

extension (int: Int) inline def toPx: SizePx = SizePx(int)
