package react.semanticui.elements

import react.common.EnumValueB

package header {
  sealed trait HeaderAttached extends Product with Serializable
  object HeaderAttached {
    implicit val enumValue: EnumValueB[HeaderAttached] = EnumValueB.toLowerCaseStringT(Attached)
    case object Attached extends HeaderAttached
    case object Top      extends HeaderAttached
    case object Bottom   extends HeaderAttached
  }
}

package object header
