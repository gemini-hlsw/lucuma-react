package react.semanticui.modules

import react.common.EnumValue
import react.common.EnumValueB

package popup {
  sealed trait PopupPosition extends Product with Serializable
  object PopupPosition {
    implicit val enumValue: EnumValue[PopupPosition] = EnumValue.instance {
      case TopLeft      => "top left"
      case TopRight     => "top right"
      case BottomRight  => "bottom right"
      case BottomLeft   => "bottom left"
      case RightCenter  => "right center"
      case LeftCenter   => "left center"
      case TopCenter    => "top center"
      case BottomCenter => "bottom center"
    }
    case object TopLeft extends PopupPosition
    case object TopRight     extends PopupPosition
    case object BottomRight  extends PopupPosition
    case object BottomLeft   extends PopupPosition
    case object RightCenter  extends PopupPosition
    case object LeftCenter   extends PopupPosition
    case object TopCenter    extends PopupPosition
    case object BottomCenter extends PopupPosition
  }

  sealed trait PopupOn extends Product with Serializable
  object PopupOn {
    implicit val enumValue: EnumValue[PopupOn] = EnumValue.toLowerCaseString

    case object Hover extends PopupOn
    case object Click extends PopupOn
    case object Focus extends PopupOn
  }

  sealed trait PopupWide extends Product with Serializable
  object PopupWide {
    implicit val enumValue: EnumValueB[PopupWide] =
      EnumValueB.toLowerCaseStringTF(WideTrue, WideFalse)
    case object WideTrue  extends PopupWide
    case object WideFalse extends PopupWide
    case object Very      extends PopupWide
  }
}

package object popup
