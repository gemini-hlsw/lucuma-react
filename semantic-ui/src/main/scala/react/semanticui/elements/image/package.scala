package react.semanticui.elements

import react.common.EnumValueB

package image {
  sealed trait ImageSpaced extends Product with Serializable
  object ImageSpaced {
    implicit val enumValue: EnumValueB[ImageSpaced] = EnumValueB.toLowerCaseStringT(Spaced)
    case object Spaced extends ImageSpaced
    case object Left   extends ImageSpaced
    case object Right  extends ImageSpaced
  }
}

package object image {}
