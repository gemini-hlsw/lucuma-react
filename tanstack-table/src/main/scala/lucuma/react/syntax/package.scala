// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.syntax

import japgolly.scalajs.react.vdom.Attr.ValueType
import lucuma.react.SizePx

extension (int: Int) inline def toPx: SizePx = SizePx(int)

lazy val vdomAttrVtSizePx: ValueType.Simple[SizePx] =
  ValueType.byImplicit(using s => s"${s.value}px")
