package react.semanticui.elements

import react.common.EnumValue

package placeholder {
  sealed trait LineLength extends Product with Serializable
  object LineLength        {
    implicit val enumValue: EnumValue[LineLength] = EnumValue.instance {
      case Full      => "full"
      case VeryLong  => "very long"
      case Long      => "long"
      case Medium    => "medium"
      case Short     => "short"
      case VeryShort => "very short"
    }
    case object Full extends LineLength
    case object VeryLong  extends LineLength
    case object Long      extends LineLength
    case object Medium    extends LineLength
    case object Short     extends LineLength
    case object VeryShort extends LineLength
  }
}

package object placeholder {}
