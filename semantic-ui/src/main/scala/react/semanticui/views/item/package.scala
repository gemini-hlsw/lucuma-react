// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views

import react.common.EnumValueB

package item {
  sealed trait ItemRelaxed extends Product with Serializable
  object ItemRelaxed {
    implicit val enumValue: EnumValueB[ItemRelaxed] = EnumValueB.toLowerCaseStringT(Relaxed)
    case object Relaxed extends ItemRelaxed
    case object Very    extends ItemRelaxed
  }
}

package object item
