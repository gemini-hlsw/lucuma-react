package react.semanticui.modules.accordion

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.raw._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class AccordionContent(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[AccordionContent.AccordionContentProps, AccordionContent] {
  override protected def cprops                     = AccordionContent.props(this)
  override protected val component                  = AccordionContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object AccordionContent {
  @js.native
  @JSImport("semantic-ui-react", "AccordionContent")
  object RawComponent extends js.Object

  @js.native
  trait AccordionContentProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Whether or not the content is visible. */
    var active: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[SemanticShorthandContent] = js.native
  }

  def props(q: AccordionContent): AccordionContentProps = {
    val p = q.as.toJsObject[AccordionContentProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    p
  }

  private val component = JsComponent[AccordionContentProps, Children.Varargs, Null](RawComponent)

  def apply(c: ShorthandS[VdomNode]): AccordionContent =
    new AccordionContent(content = c)
}
