// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
