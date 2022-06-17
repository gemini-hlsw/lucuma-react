package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui._

final case class TableBody(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[TableBody.TableBodyProps, TableBody] {
  override protected def cprops                     = TableBody.props(this)
  override protected val component                  = TableBody.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TableBody {
  @js.native
  @JSImport("semantic-ui-react", "TableBody")
  object RawComponent extends js.Object

  @js.native
  trait TableBodyProps extends js.Object {
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
  }

  def props(q: TableBody): TableBodyProps = {
    val p = q.as.toJsObject[TableBodyProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    p
  }

  private val component = JsComponent[TableBodyProps, Children.Varargs, Null](RawComponent)

  def apply(mods: TagMod*): TableBody = TableBody(modifiers = mods)
}
