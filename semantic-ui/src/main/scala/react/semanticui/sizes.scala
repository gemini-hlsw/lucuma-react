// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
