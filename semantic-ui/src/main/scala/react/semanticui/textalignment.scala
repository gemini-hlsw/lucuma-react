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
