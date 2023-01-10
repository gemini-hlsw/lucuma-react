// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
