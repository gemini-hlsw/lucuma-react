// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules

import react.common.EnumValue
import react.common.EnumValueB

package progress {
  sealed trait Attached extends Product with Serializable
  object Attached {
    implicit val enumValue: EnumValue[Attached] = EnumValue.toLowerCaseString
    case object Top    extends Attached
    case object Bottom extends Attached
  }

  sealed trait ProgressText extends Product with Serializable
  object ProgressText {
    implicit val enumValue: EnumValueB[ProgressText] = EnumValueB.toLowerCaseStringT(Text)
    case object Text    extends ProgressText
    case object Percent extends ProgressText
    case object Ratio   extends ProgressText
    case object Value   extends ProgressText
  }
}

package object progress
