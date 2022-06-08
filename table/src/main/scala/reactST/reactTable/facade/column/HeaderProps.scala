// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.column

import reactST.reactTable.facade.tableInstance.TableInstance

import scala.scalajs.js

@js.native
trait HeaderProps[D, Plugins] extends TableInstance[D, Plugins] {
  var column: Column[D, Plugins] = js.native
}
