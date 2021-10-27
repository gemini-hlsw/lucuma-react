package reactST.reactTable

import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.CustomHook
import reactST.reactTable.facade.tableInstance.TableInstance
import reactST.reactTable.facade.tableOptions.TableOptions
import reactST.reactTable.facade.tableState.TableState
import reactST.reactTable.mod.PluginHook

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSImport

object TableHooks {
  @JSImport("react-table", "useTable")
  @js.native
  def useTableJS[D, Plugins, TI <: TableInstance[D, Plugins]](
    options: TableOptions[D, Plugins],
    plugins: PluginHook[D]*
  ): TI =
    js.native

  // According to documentation, react-table memoizes the table state.
  private implicit def reuseTableState[T <: TableState[_, _]]: Reusability[T] =
    Reusability.byRef

  def useTableHook[D, Plugins, Layout] =
    CustomHook[TableDefWithOptions[D, Plugins, Layout]]
      .useMemoBy(_.cols)(_ => _.toJSArray)
      .useMemoBy(_.input.data)(_ => _.toJSArray)
      .buildReturning { (props, cols, rows) =>
        val tableInstance =
          useTableJS[D, Plugins, TableInstance[D, Plugins]](
            props.modOpts(props.tableDef.Options(cols, rows)),
            props.tableDef.plugins.toList.sorted.map(_.hook: PluginHook[D]): _*
          )
        Reusable
          .implicitly((cols, rows, props.modOpts, tableInstance.state))
          .withValue(tableInstance)
      }

  sealed trait TableHook extends js.Object
  object TableHook {
    implicit def asPluginHook[D](hook: TableHook): PluginHook[D] = hook.asInstanceOf[PluginHook[D]]
  }

  @JSImport("react-table", "useGroupBy")
  @js.native
  object useGroupBy extends TableHook

  @JSImport("react-table", "useExpanded")
  @js.native
  object useExpanded extends TableHook

  @JSImport("react-table", "useSortBy")
  @js.native
  object useSortBy extends TableHook

  @JSImport("react-table", "useResizeColumns")
  @js.native
  object useResizeColumns extends TableHook

  @JSImport("react-table", "useBlockLayout")
  @js.native
  object useBlockLayout extends TableHook

  @JSImport("react-table", "useGridLayout")
  @js.native
  object useGridLayout extends TableHook
}
