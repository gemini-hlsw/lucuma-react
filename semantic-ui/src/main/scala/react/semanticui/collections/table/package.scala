package react.semanticui.collections

import react.common.EnumValue
import react.common.EnumValueB

package table {
  sealed trait TableTextAlign
  object TableTextAlign {
    implicit val enumValue: EnumValue[TableTextAlign] = EnumValue.toLowerCaseString
    case object Center extends TableTextAlign
    case object Left   extends TableTextAlign
    case object Right  extends TableTextAlign
  }

  sealed trait TableSorted
  object TableSorted {
    implicit val enumValue: EnumValue[TableSorted] = EnumValue.toLowerCaseString
    case object Ascending  extends TableSorted
    case object Descending extends TableSorted
  }

  sealed trait TableAttached
  object TableAttached {
    implicit val enumValue: EnumValueB[TableAttached] =
      EnumValueB.toLowerCaseStringTF(Attached, NotAttached)
    case object Attached    extends TableAttached
    case object NotAttached extends TableAttached
    case object Top         extends TableAttached
    case object Bottom      extends TableAttached
  }

  sealed trait TableBasic
  object TableBasic {
    implicit val enumValue: EnumValueB[TableBasic] = EnumValueB.toLowerCaseStringTF(Basic, NotBasic)
    case object Basic    extends TableBasic
    case object NotBasic extends TableBasic
    case object Very     extends TableBasic
  }

  sealed trait TableCompact
  object TableCompact {
    implicit val enumValue: EnumValueB[TableCompact] =
      EnumValueB.toLowerCaseStringTF(Compact, NotCompact)
    case object Compact    extends TableCompact
    case object NotCompact extends TableCompact
    case object Very       extends TableCompact
  }

  sealed trait TablePadded
  object TablePadded {
    implicit val enumValue: EnumValueB[TablePadded] =
      EnumValueB.toLowerCaseStringTF(Padded, NotPadded)
    case object Padded    extends TablePadded
    case object NotPadded extends TablePadded
    case object Very      extends TablePadded
  }

  sealed trait TableSize
  object TableSize {
    implicit val enumValue: EnumValue[TableSize] = EnumValue.toLowerCaseString
    case object Small extends TableSize
    case object Large extends TableSize
  }
}
