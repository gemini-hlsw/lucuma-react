package react.semanticui.modules

import react.common.EnumValue
import react.common.EnumValueB

package dropdown {
  sealed trait MenuDirection extends Product with Serializable
  object MenuDirection {
    implicit val enumValue: EnumValue[MenuDirection] = EnumValue.toLowerCaseString
    case object Left  extends MenuDirection
    case object Right extends MenuDirection
  }

  sealed trait AdditionPosition extends Product with Serializable
  object AdditionPosition {
    implicit val enumValue: EnumValue[AdditionPosition] = EnumValue.toLowerCaseString
    case object Top    extends AdditionPosition
    case object Bottom extends AdditionPosition
  }

  sealed trait Direction extends Product with Serializable
  object Direction {
    implicit val enumValue: EnumValue[Direction] = EnumValue.toLowerCaseString
    case object Left  extends Direction
    case object Right extends Direction
  }

  sealed trait Pointing extends Product with Serializable
  object Pointing {
    implicit val enumValue: EnumValueB[Pointing] = EnumValueB.instance {
      case Left        => "left"
      case Right       => "right"
      case Top         => "top"
      case TopLeft     => "top left"
      case TopRight    => "top right"
      case Bottom      => "bottom"
      case BottomLeft  => "bottom left"
      case BottomRight => "bottom right"
    }
    case object Left extends Pointing
    case object Right       extends Pointing
    case object Top         extends Pointing
    case object TopLeft     extends Pointing
    case object TopRight    extends Pointing
    case object Bottom      extends Pointing
    case object BottomLeft  extends Pointing
    case object BottomRight extends Pointing
  }
}

package object dropdown
