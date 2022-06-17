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

final case class TableHeader(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  fullWidth:              js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[TableHeader.TableHeaderProps, TableHeader] {
  override protected def cprops                     = TableHeader.props(this)
  override protected val component                  = TableHeader.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TableHeader {
  @js.native
  @JSImport("semantic-ui-react", "TableHeader")
  object RawComponent extends js.Object

  @js.native
  trait TableHeaderProps extends js.Object {
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

  def props(q: TableHeader): TableHeaderProps = {
    val p = q.as.toJsObject[TableHeaderProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.fullWidth.foreach(v => p.fullWidth = v)
    p
  }

  private val component = JsComponent[TableHeaderProps, Children.Varargs, Null](RawComponent)

  def apply(mods: TagMod*): TableHeader = TableHeader(modifiers = mods)
}
