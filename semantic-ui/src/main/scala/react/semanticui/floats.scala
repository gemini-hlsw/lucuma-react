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
