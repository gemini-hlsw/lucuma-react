package react.semanticui.modules

import react.common.EnumValue

package checkbox {
  sealed trait CheckboxType extends Product with Serializable
  object CheckboxType {
    implicit val enumValue: EnumValue[CheckboxType] = EnumValue.toLowerCaseString
    case object Checkbox extends CheckboxType
    case object Radio    extends CheckboxType
  }
}

package object checkbox
