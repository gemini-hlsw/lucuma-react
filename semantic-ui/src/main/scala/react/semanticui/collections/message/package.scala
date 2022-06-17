package react.semanticui.collections

import react.common.EnumValue
import react.common.EnumValueB

package message {
  sealed trait MessageAttached extends Product with Serializable
  object MessageAttached {
    implicit val enumValue: EnumValueB[MessageAttached] = EnumValueB.toLowerCaseStringT(Attached)

    case object Attached extends MessageAttached
    case object Top      extends MessageAttached
    case object Bottom   extends MessageAttached
  }

  // MessageSize cannot be Medium, thus we define a new enum.
  sealed trait MessageSize extends Product with Serializable
  object MessageSize     {
    implicit val enumValue: EnumValue[MessageSize] = EnumValue.toLowerCaseString

    case object Mini    extends MessageSize
    case object Tiny    extends MessageSize
    case object Small   extends MessageSize
    case object Large   extends MessageSize
    case object Big     extends MessageSize
    case object Huge    extends MessageSize
    case object Massive extends MessageSize
  }
}

package object message
