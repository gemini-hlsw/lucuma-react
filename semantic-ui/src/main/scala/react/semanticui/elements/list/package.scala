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
