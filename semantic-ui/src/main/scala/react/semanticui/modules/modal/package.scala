package react.semanticui.modules

import react.common.EnumValue
import react.common.EnumValueB

package modal {
  sealed trait Dimmer extends Product with Serializable
  object Dimmer {
    implicit val enumValue: EnumValueB[Dimmer] = EnumValueB.toLowerCaseStringT(Dimmer)

    case object Dimmer   extends Dimmer
    case object Blurring extends Dimmer
    case object Inverted extends Dimmer
  }

  sealed trait ModalSize extends Product with Serializable
  object ModalSize {
    implicit val enumValue: EnumValue[ModalSize] = EnumValue.toLowerCaseString

    case object Mini       extends ModalSize
    case object Tiny       extends ModalSize
    case object Small      extends ModalSize
    case object Large      extends ModalSize
    case object Fullscreen extends ModalSize
  }
}

package object modal
