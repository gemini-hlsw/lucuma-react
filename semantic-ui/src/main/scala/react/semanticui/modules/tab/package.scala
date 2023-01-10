// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules

import react.common.EnumValue

package tab {
  sealed trait TabMenuPosition extends Product with Serializable
  object TabMenuPosition {
    implicit val enumValue: EnumValue[TabMenuPosition] = EnumValue.toLowerCaseString
    case object Left  extends TabMenuPosition
    case object Right extends TabMenuPosition
  }
}

package object tab
