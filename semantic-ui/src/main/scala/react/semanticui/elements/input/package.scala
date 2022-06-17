package react.semanticui.elements

import react.common.EnumValue

package input {
  sealed trait ActionPosition extends Product with Serializable
  object ActionPosition {
    implicit val enumValue: EnumValue[ActionPosition] = EnumValue.toLowerCaseString
    case object Left  extends ActionPosition
    case object Right extends ActionPosition
  }

  sealed trait IconPosition extends Product with Serializable
  object IconPosition {
    implicit val enumValue: EnumValue[IconPosition] = EnumValue.toLowerCaseString
    case object Left  extends IconPosition
    case object Right extends IconPosition
  }

  sealed trait LabelPosition extends Product with Serializable
  object LabelPosition {
    implicit val enumValue: EnumValue[LabelPosition] = EnumValue.instance {
      case Left        => "left"
      case Right       => "right"
      case LeftCorner  => "left corner"
      case RightCorner => "right corner"
    }
    case object Left extends LabelPosition
    case object Right       extends LabelPosition
    case object LeftCorner  extends LabelPosition
    case object RightCorner extends LabelPosition
  }
}

package object input
