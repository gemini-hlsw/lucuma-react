package reactST.reactTable.facade.column

import reactST.reactTable.facade.tableInstance.TableInstance

import scala.scalajs.js

@js.native
trait HeaderProps[D, Plugins] extends TableInstance[D, Plugins] {
  var column: Column[D, Plugins] = js.native
}
