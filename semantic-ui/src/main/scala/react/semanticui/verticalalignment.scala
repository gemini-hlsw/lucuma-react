// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui

import react.common.EnumValue

object verticalalignment {

  sealed trait SemanticVerticalAlignment extends Product with Serializable
  object SemanticVerticalAlignment {
    implicit val enumValue: EnumValue[SemanticVerticalAlignment] = EnumValue.toLowerCaseString
  }
  case object Bottom extends SemanticVerticalAlignment
  case object Middle extends SemanticVerticalAlignment
  case object Top    extends SemanticVerticalAlignment
}
