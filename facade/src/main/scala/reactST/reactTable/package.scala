package reactST

import reactST.reactTable.mod._

package object reactTable {
  type ColumnOptions[D]      = ColumnWithLooseAccessor[D]
    with ColumnInterfaceBasedOnValue[D, Any]
    with ColumnFooter[D]
  type ColumnGroupOptions[D] = ColumnGroup[D] with ColumnFooter[D]
  type ColumnObject[D]       = ColumnInstance[D] with UseTableColumnFooter[D]
}
