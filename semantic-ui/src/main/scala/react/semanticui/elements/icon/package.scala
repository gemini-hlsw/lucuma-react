package react.semanticui.elements

import react.common._
import react.semanticui._

package icon {
  sealed trait IconFlip extends Product with Serializable
  object IconFlip {
    implicit val enumValue: EnumValue[IconFlip] = EnumValue.toLowerCaseString

    case object Horizontally extends IconFlip
    case object Vertically   extends IconFlip
  }

  sealed trait IconRotated extends Product with Serializable
  object IconRotated {
    implicit val enumValue: EnumValue[IconRotated] = EnumValue.toLowerCaseString
    case object Clockwise        extends IconRotated
    case object CounterClockwise extends IconRotated
  }

  sealed trait IconCorner extends Product with Serializable
  object IconCorner {
    implicit val enumValue: EnumValueB[IconCorner] = EnumValueB.instance {
      case BottomRight => "bottom right"
      case TopRight    => "top right"
      case TopLeft     => "top left"
      case BottomLeft  => "bottom left"
    }

    case object BottomRight extends IconCorner
    case object TopRight    extends IconCorner
    case object TopLeft     extends IconCorner
    case object BottomLeft  extends IconCorner
  }
}

package object icon {
  implicit class CopyIconOps(val i: Icon) extends AnyVal {
    def bordered(s: Boolean = true): Icon =
      i.copy(bordered = s)

    def circular(s: Boolean = true): Icon =
      i.copy(circular = s)

    def className(c: String): Icon =
      i.copy(className = c)

    def clazz(s: Css): Icon =
      i.copy(clazz = s)

    def color(s: SemanticColor): Icon =
      i.copy(color = s)

    def flipped(s: IconFlip): Icon =
      i.copy(flipped = s)

    def corner(c: IconCorner): Icon =
      i.copy(corner = c)

    def disabled(s: Boolean = true): Icon =
      i.copy(disabled = s)

    def fitted(s: Boolean = true): Icon =
      i.copy(fitted = s)

    def inverted(s: Boolean = true): Icon =
      i.copy(inverted = s)

    def link(s: Boolean = true): Icon =
      i.copy(link = s)

    def loading(s: Boolean = true): Icon =
      i.copy(loading = s)

    def rotated(s: IconRotated): Icon =
      i.copy(rotated = s)

    def size(s: SemanticSize): Icon =
      i.copy(size = s)

  }
}
