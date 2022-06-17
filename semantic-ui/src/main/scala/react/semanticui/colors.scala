package react.semanticui

import react.common.EnumValue

object colors {
  sealed trait SemanticColor extends Product with Serializable

  object SemanticColor {
    implicit val enumValue: EnumValue[SemanticColor] = EnumValue.toLowerCaseString
  }
  case object Red extends SemanticColor
  case object Orange extends SemanticColor
  case object Yellow extends SemanticColor
  case object Olive  extends SemanticColor
  case object Green  extends SemanticColor
  case object Teal   extends SemanticColor
  case object Blue   extends SemanticColor
  case object Violet extends SemanticColor
  case object Purple extends SemanticColor
  case object Pink   extends SemanticColor
  case object Brown  extends SemanticColor
  case object Grey   extends SemanticColor
  case object Black  extends SemanticColor
}
