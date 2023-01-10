// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements

import react.common.EnumValueB

package rail {
  sealed trait RailClose extends Product with Serializable
  object RailClose  {
    implicit val enumValue: EnumValueB[RailClose] = EnumValueB.toLowerCaseStringT(Close)

    case object Close extends RailClose
    case object Very  extends RailClose
  }

}

package object rail {}
