package react.semanticui.elements

import react.common.EnumValueB

package loader {
  sealed trait LoaderInline extends Product with Serializable
  object LoaderInline {
    implicit val enumValue: EnumValueB[LoaderInline] = EnumValueB.toLowerCaseStringT(Inline)
    case object Inline   extends LoaderInline
    case object Centered extends LoaderInline
  }
}

package object loader {}
