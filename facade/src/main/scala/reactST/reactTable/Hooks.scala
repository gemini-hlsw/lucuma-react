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

  trait Hook extends js.Object
  object Hook {
    implicit def asPluginHook[D](hook: Hook): PluginHook[D] = hook.asInstanceOf[PluginHook[D]]
  }

  @JSImport("react-table", "useSortBy")
  @js.native
  object useSortBy extends Hook

  @JSImport("react-table", "useBlockLayout")
  @js.native
  object useBlockLayout extends Hook
}
