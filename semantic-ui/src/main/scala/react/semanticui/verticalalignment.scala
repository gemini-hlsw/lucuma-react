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
