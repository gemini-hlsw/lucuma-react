// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements

import react.common.EnumValueB

package list {
  sealed trait ListRelaxed extends Product with Serializable
  object ListRelaxed {
    implicit val enumValue: EnumValueB[ListRelaxed] = EnumValueB.toLowerCaseStringT(Relaxed)
    case object Relaxed extends ListRelaxed
    case object Very    extends ListRelaxed
  }
}

package object list  {}
