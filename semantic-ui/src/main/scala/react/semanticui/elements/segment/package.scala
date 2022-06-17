package react.semanticui.elements

import react.common.EnumValueB

package segment {
  sealed trait SegmentAttached extends Product with Serializable
  object SegmentAttached {
    implicit val enumValue: EnumValueB[SegmentAttached] = EnumValueB.toLowerCaseStringT(Attached)
    case object Attached extends SegmentAttached
    case object Top      extends SegmentAttached
    case object Bottom   extends SegmentAttached
  }

  sealed trait SegmentPadded extends Product with Serializable

  object SegmentPadded {
    implicit val enumValue: EnumValueB[SegmentPadded] = EnumValueB.toLowerCaseStringT(Padded)
    case object Padded extends SegmentPadded
    case object Very   extends SegmentPadded
  }
}

package object segment
