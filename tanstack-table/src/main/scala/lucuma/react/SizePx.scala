// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

opaque type SizePx = Int
object SizePx:
  inline def apply(value: Int): SizePx = value
  extension (opaqueValue: SizePx) inline def value: Int = opaqueValue
