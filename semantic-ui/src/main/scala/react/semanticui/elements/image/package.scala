// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements

import react.common.EnumValueB

package image {
  sealed trait ImageSpaced extends Product with Serializable
  object ImageSpaced {
    implicit val enumValue: EnumValueB[ImageSpaced] = EnumValueB.toLowerCaseStringT(Spaced)
    case object Spaced extends ImageSpaced
    case object Left   extends ImageSpaced
    case object Right  extends ImageSpaced
  }
}

package object image {}
