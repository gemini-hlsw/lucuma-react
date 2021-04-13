package reactST.reactTable

import reactST.reactTable.mod._
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation.{ JSBracketAccess, JSGlobal, JSGlobalScope, JSImport, JSName }

object Hooks {
  @JSImport("react-table", "useTable")
  @js.native
  object useTable extends js.Object {

    def apply[D /* <: js.Object */ ](
      options: TableOptions[D],
      plugins: PluginHook[D]*
    ): TableInstance[D] = js.native
  }

  @JSImport("react-table", "useSortBy")
  @js.native
  object useSortBy extends js.Object {

    def apply[D /* <: js.Object */ ](hooks: Hooks[D]): Unit = js.native

    val pluginName: /* "useSortBy" */ String = js.native
  }

  @JSImport("react-table", "useBlockLayout")
  @js.native
  object useBlockLayout extends js.Object {

    def apply[D /* <: js.Object */ ](hooks: Hooks[D]): Unit = js.native

    val pluginName: /* "useBlockLayout" */ String = js.native
  }
}
