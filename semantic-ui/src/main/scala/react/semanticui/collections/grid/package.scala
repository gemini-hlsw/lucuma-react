package react.semanticui.collections

import react.common.EnumValue
import react.common.EnumValueB

package grid {
  sealed trait GridOnly extends Product with Serializable
  object GridOnly {
    implicit val enumValue: EnumValue[GridOnly] = EnumValue.instance {
      case Value(str)   => str
      case Computer     => "computer"
      case LargeScreen  => "largeScreen"
      case Mobile       => "mobile"
      case TabletMobile => "tablet mobile"
      case Tablet       => "tablet"
      case WideScreen   => "widescreen"
    }

    final case class Value(str: String) extends GridOnly
    case object Computer                extends GridOnly
    case object LargeScreen             extends GridOnly
    case object Mobile                  extends GridOnly
    case object TabletMobile            extends GridOnly
    case object Tablet                  extends GridOnly
    case object WideScreen              extends GridOnly
  }

  sealed trait GridReversed extends Product with Serializable
  object GridReversed {
    implicit val enumValue: EnumValue[GridReversed] = EnumValue.instance {
      case Value(str)         => str
      case Computer           => "computer"
      case ComputerVertically => "computer vertically"
      case Mobile             => "mobile"
      case MobileVertically   => "mobile vertically"
      case Tablet             => "tablet"
      case TabletVertically   => "tablet vertically"
    }

    final case class Value(str: String) extends GridReversed
    case object Computer                extends GridReversed
    case object ComputerVertically      extends GridReversed
    case object Mobile                  extends GridReversed
    case object MobileVertically        extends GridReversed
    case object Tablet                  extends GridReversed
    case object TabletVertically        extends GridReversed
  }

  sealed trait GridPadded extends Product with Serializable
  object GridPadded {
    implicit val enumValue: EnumValueB[GridPadded] =
      EnumValueB.toLowerCaseStringTF(Padded, NotPadded)

    case object Padded       extends GridPadded
    case object NotPadded    extends GridPadded
    case object Horizontally extends GridPadded
    case object Vertically   extends GridPadded
  }

  sealed trait GridRelaxed extends Product with Serializable
  object GridRelaxed {
    implicit val enumValue: EnumValueB[GridRelaxed] =
      EnumValueB.toLowerCaseStringTF(Relaxed, NotRelaxed)

    case object Relaxed    extends GridRelaxed
    case object NotRelaxed extends GridRelaxed
    case object Very       extends GridRelaxed
  }

  sealed trait GridCelled extends Product with Serializable
  object GridCelled {
    implicit val enumValue: EnumValueB[GridCelled] =
      EnumValueB.toLowerCaseStringTF(Celled, NotCelled)

    case object Celled     extends GridCelled
    case object NotCelled  extends GridCelled
    case object Internally extends GridCelled
  }

  sealed trait GridDivided extends Product with Serializable
  object GridDivided {
    implicit val enumValue: EnumValueB[GridDivided] =
      EnumValueB.toLowerCaseStringTF(Divided, NotDivided)

    case object Divided    extends GridDivided
    case object NotDivided extends GridDivided
    case object Vertically extends GridDivided
  }

  sealed trait GridColumns extends Product with Serializable
  object GridColumns {
    implicit val enumValue: EnumValue[GridColumns] = EnumValue.toLowerCaseString

    case object Equal extends GridColumns
  }
}

package object grid
