package react.semanticui

import react.common.EnumValue

object sizes {
  sealed trait SemanticSize extends Product with Serializable
  object SemanticSize {
    implicit val enumValue: EnumValue[SemanticSize] = EnumValue.toLowerCaseString
  }
  case object Mini extends SemanticSize
  case object Tiny    extends SemanticSize
  case object Small   extends SemanticSize
  case object Medium  extends SemanticSize
  case object Large   extends SemanticSize
  case object Big     extends SemanticSize
  case object Huge    extends SemanticSize
  case object Massive extends SemanticSize
}
