// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
