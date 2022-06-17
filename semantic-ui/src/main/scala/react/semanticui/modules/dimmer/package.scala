package react.semanticui.modules

import react.common.EnumValue

package dimmer {
  sealed trait DimmerVerticalAlign extends Product with Serializable
  object DimmerVerticalAlign {
    implicit val enumValue: EnumValue[DimmerVerticalAlign] = EnumValue.toLowerCaseString
    case object Bottom extends DimmerVerticalAlign
    case object Top    extends DimmerVerticalAlign
  }
}

package object dimmer
