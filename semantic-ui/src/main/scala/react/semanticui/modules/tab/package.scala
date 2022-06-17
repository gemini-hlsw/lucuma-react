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
