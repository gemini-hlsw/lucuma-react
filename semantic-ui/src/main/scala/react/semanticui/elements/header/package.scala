// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements

import react.common.EnumValueB

package header {
  sealed trait HeaderAttached extends Product with Serializable
  object HeaderAttached {
    implicit val enumValue: EnumValueB[HeaderAttached] = EnumValueB.toLowerCaseStringT(Attached)
    case object Attached extends HeaderAttached
    case object Top      extends HeaderAttached
    case object Bottom   extends HeaderAttached
  }
}

package object header
