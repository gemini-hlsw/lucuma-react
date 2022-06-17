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
