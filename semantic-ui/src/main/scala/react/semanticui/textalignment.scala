// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui

import react.common.EnumValue

object textalignment {
  sealed trait SemanticTextAlignment extends Product with Serializable
  object SemanticTextAlignment {
    implicit val enumValue: EnumValue[SemanticTextAlignment] = EnumValue.toLowerCaseString
  }
  case object Left extends SemanticTextAlignment
  case object Center    extends SemanticTextAlignment
  case object Right     extends SemanticTextAlignment
  case object Justified extends SemanticTextAlignment
}
