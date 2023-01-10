// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui

import react.common.EnumValue

object floats {

  sealed trait SemanticFloat extends Product with Serializable
  object SemanticFloat {
    implicit val enumValue: EnumValue[SemanticFloat] = EnumValue.toLowerCaseString
  }
  case object Left extends SemanticFloat
  case object Right extends SemanticFloat
}
