// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements

import react.common.EnumValue
import react.common.EnumValueB

package button {
  sealed trait ButtonAnimated extends Product with Serializable
  object ButtonAnimated {
    implicit val enumValue: EnumValueB[ButtonAnimated] = EnumValueB.toLowerCaseStringT(Animated)
    case object Animated extends ButtonAnimated
    case object Fade     extends ButtonAnimated
    case object Vertical extends ButtonAnimated
  }

  sealed trait ButtonAttached extends Product with Serializable
  object ButtonAttached {
    implicit val enumValue: EnumValueB[ButtonAttached] = EnumValueB.toLowerCaseStringT(Attached)
    case object Attached extends ButtonAttached
    case object Top      extends ButtonAttached
    case object Bottom   extends ButtonAttached
    case object Right    extends ButtonAttached
    case object Left     extends ButtonAttached
  }

  sealed trait LabelPosition extends Product with Serializable
  object LabelPosition {
    implicit val enumValue: EnumValue[LabelPosition] = EnumValue.toLowerCaseString
    case object Right extends LabelPosition
    case object Left  extends LabelPosition
  }
}

package object button
