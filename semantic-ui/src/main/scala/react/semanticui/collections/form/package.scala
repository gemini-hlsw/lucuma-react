package react.semanticui.collections

import react.common.EnumValue

package form {
  sealed trait FormWidths extends Product with Serializable
  object FormWidths {
    implicit val enumValue: EnumValue[FormWidths] = EnumValue.toLowerCaseString

    case object Equal extends FormWidths
  }
}

package object form
