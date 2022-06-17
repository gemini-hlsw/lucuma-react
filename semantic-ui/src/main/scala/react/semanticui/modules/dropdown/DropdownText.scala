package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.JsFnComponent
import react.semanticui._
import react.semanticui.raw._
import react.common._
import japgolly.scalajs.react.vdom.TagMod

final case class DropdownText(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[DropdownText.DropdownTextProps, DropdownText] {
  override protected def cprops                     = DropdownText.props(this)
  override protected val component                  = DropdownText.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownText {
  @js.native
  @JSImport("semantic-ui-react", "DropdownText")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait DropdownTextProps extends js.Object {
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
    var content: js.UndefOr[SemanticShorthandContent] = js.native

  }

  def props(q: DropdownText): DropdownTextProps =
    rawprops(q.as, q.className, q.clazz, q.content)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined
  ): DropdownTextProps = {
    val p = as.toJsObject[DropdownTextProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsFnComponent[DropdownTextProps, Children.Varargs](RawComponent)

  def apply(children: TagMod*): DropdownText =
    new DropdownText(modifiers = children)
}
