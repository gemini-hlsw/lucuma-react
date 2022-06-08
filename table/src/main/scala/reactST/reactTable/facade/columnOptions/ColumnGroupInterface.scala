// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.columnOptions

import org.scalablytyped.runtime.StObject
import reactST.reactTable.facade.column.Column

import scala.scalajs.js

@js.native
trait ColumnGroupInterface[D, Plugins] extends js.Object {

  var columns: js.Array[Column[D, Plugins]] = js.native
}
object ColumnGroupInterface {

  @scala.inline
  def apply[D, Plugins](
    columns: js.Array[Column[D, Plugins]]
  ): ColumnGroupInterface[D, Plugins] = {
    val __obj = js.Dynamic.literal(columns = columns.asInstanceOf[js.Any])
    __obj.asInstanceOf[ColumnGroupInterface[D, Plugins]]
  }

  @scala.inline
  implicit class ColumnGroupInterfaceMutableBuilder[Self <: ColumnGroupInterface[
    ?,
    ?
  ], D, Plugins](val x: Self with ColumnGroupInterface[D, Plugins])
      extends AnyVal {

    @scala.inline
    def setColumns(value: js.Array[Column[D, Plugins]]): Self =
      StObject.set(x, "columns", value.asInstanceOf[js.Any])

    @scala.inline
    def setColumnsVarargs(value: Column[D, Plugins]*): Self =
      StObject.set(x, "columns", js.Array(value: _*))
  }
}
