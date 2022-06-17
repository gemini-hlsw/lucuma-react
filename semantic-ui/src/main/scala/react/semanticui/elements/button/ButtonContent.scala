package react.semanticui.elements.button

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class ButtonContent(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  hidden:                 js.UndefOr[Boolean] = js.undefined,
  visible:                js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[ButtonContent.ButtonContentProps, ButtonContent] {
  override protected def cprops                     = ButtonContent.props(this)
  override protected val component                  = ButtonContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ButtonContent {
  @js.native
  @JSImport("semantic-ui-react", "ButtonContent")
  object RawComponent      extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }
  @js.native
  trait ButtonContentProps extends js.Object                    {
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
    var content: js.UndefOr[suiraw.SemanticShorthandContent] =
      js.native

    /** Initially hidden, visible on hover. */
    var hidden: js.UndefOr[Boolean] = js.native

    /** Initially visible, hidden on hover. */
    var visible: js.UndefOr[Boolean] = js.native
  }

  def props(
    q: ButtonContent
  ): ButtonContentProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.hidden, q.visible)
  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    hidden:    js.UndefOr[Boolean] = js.undefined,
    visible:   js.UndefOr[Boolean] = js.undefined
  ): ButtonContentProps = {
    val p = as.toJsObject[ButtonContentProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    hidden.foreach(v => p.hidden = v)
    visible.foreach(v => p.visible = v)
    p
  }

  private val component =
    JsComponent[ButtonContentProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): ButtonContent =
    new ButtonContent(modifiers = modifiers)
}
