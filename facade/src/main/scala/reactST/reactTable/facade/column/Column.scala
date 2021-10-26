package reactST.reactTable.facade.column

import japgolly.scalajs.react.facade.React.Node
import reactST.reactTable.Plugin
import reactST.reactTable.facade.cell.CellProps
import reactST.reactTable.mod.FooterPropGetter
import reactST.reactTable.mod.HeaderPropGetter
import reactST.reactTable.mod.HeaderProps
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.Renderer
import reactST.reactTable.mod.TableFooterProps
import reactST.reactTable.mod.TableHeaderProps
import reactST.reactTable.mod.UseSortByColumnProps
import reactST.reactTable.reactTableStrings

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.`|`

@js.native
trait Column[D, Plugins] extends js.Object {

  var Cell: js.UndefOr[Renderer[CellProps[D, js.Any, Plugins]]] = js.native

  var Header: js.UndefOr[Renderer[HeaderProps[D]]] = js.native

  var Footer: js.UndefOr[Renderer[HeaderProps[D]]] = js.native

  var columns: js.UndefOr[js.Array[Column[D, Plugins]]] = js.native

  var depth: Double = js.native

  def getFooterProps(): TableFooterProps                                = js.native
  def getFooterProps(propGetter: FooterPropGetter[D]): TableFooterProps = js.native

  def getHeaderProps(): TableHeaderProps                                = js.native
  def getHeaderProps(propGetter: HeaderPropGetter[D]): TableHeaderProps = js.native

  // not documented
  def getToggleHiddenProps(): js.Any                  = js.native
  def getToggleHiddenProps(userProps: js.Any): js.Any = js.native

  var id: IdType[D] = js.native

  var isVisible: Boolean = js.native

  var maxWidth: js.UndefOr[Double] = js.native

  var minWidth: js.UndefOr[Double] = js.native

  var parent: js.UndefOr[Column[D, Plugins]] = js.native

  // not documented
  var placeholderOf: js.UndefOr[Column[js.Object, Plugins]] = js.native

  def render(`type`: String): Node                   = js.native
  def render(`type`: String, props: js.Object): Node = js.native
  @JSName("render")
  def render_Footer(`type`: reactTableStrings.Footer): Node = js.native
  @JSName("render")
  def render_Footer(`type`: reactTableStrings.Footer, props: js.Object): Node = js.native
  @JSName("render")
  def render_Header(`type`: reactTableStrings.Header): Node = js.native
  @JSName("render")
  def render_Header(`type`: reactTableStrings.Header, props: js.Object): Node = js.native

  def toggleHidden(): Unit               = js.native
  def toggleHidden(value: Boolean): Unit = js.native

  var totalLeft: Double = js.native

  var totalWidth: Double = js.native

  var width: js.UndefOr[Double | String] = js.native
}

object Column {
  @inline
  implicit def UseSortByTableColumnOpts[D, Plugins](tableState: Column[D, Plugins])(implicit
    ev:                                                         Plugins <:< Plugin.SortBy.Tag
  ): UseSortByColumnProps[D] = tableState.asInstanceOf[UseSortByColumnProps[D]]

  implicit class ColumnObjectOps[Self <: Column[_, _]](val col: Self) extends AnyVal {
    def renderHeader: Node = col.render_Header(reactTableStrings.Header)
    def renderFooter: Node = col.render_Footer(reactTableStrings.Footer)
  }
}
