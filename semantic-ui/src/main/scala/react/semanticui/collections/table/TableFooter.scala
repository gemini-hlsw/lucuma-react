package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }

final case class TableFooter(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  fullWidth:              js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[TableFooter.TableFooterProps, TableFooter] {
  override protected def cprops                     = TableFooter.props(this)
  override protected val component                  = TableFooter.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TableFooter {
  @js.native
  @JSImport("semantic-ui-react", "TableFooter")
  object RawComponent extends js.Object

  @js.native
  trait TableFooterProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /**
     * A definition table can have a full width header or footer, filling in the gap left by the
     * first column.
     */
    var fullWidth: js.UndefOr[Boolean] = js.native
  }

  def props(q: TableFooter): TableFooterProps = {
    val p = q.as.toJsObject[TableFooterProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.fullWidth.foreach(v => p.fullWidth = v)
    p
  }

  private val component = JsComponent[TableFooterProps, Children.Varargs, Null](RawComponent)

  def apply(mods: TagMod*): TableFooter = TableFooter(modifiers = mods)
}
