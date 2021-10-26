package reactST.reactTable.facade.columnOptions

import japgolly.scalajs.react.vdom.VdomElement
import reactST.reactTable.Aggregator
import reactST.reactTable.facade.cell.CellProps
import reactST.reactTable.mod.Renderer

import scala.scalajs.js

@js.native
trait UseGroupByColumnOptions[D, Plugins] extends js.Object {
  var Aggregated: js.UndefOr[Renderer[CellProps[D, js.Any, Plugins]]] = js.native

  var aggregate: js.UndefOr[Aggregator[D, Plugins]] = js.native

  var defaultCanGroupBy: js.UndefOr[Boolean] = js.native

  var disableGroupBy: js.UndefOr[Boolean] = js.native
}
