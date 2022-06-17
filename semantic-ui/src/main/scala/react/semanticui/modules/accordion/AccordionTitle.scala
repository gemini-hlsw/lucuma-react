package react.semanticui.modules.accordion

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.raw._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class AccordionTitle(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[Icon]] = js.undefined,
  index:                  js.UndefOr[Double | String] = js.undefined,
  onClickE:               js.UndefOr[AccordionTitle.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[AccordionTitle.AccordionTitleProps, AccordionTitle] {
  override protected def cprops                     = AccordionTitle.props(this)
  override protected val component                  = AccordionTitle.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object AccordionTitle {
  type OnClick    = (ReactMouseEvent, AccordionTitleProps) => Callback
  type RawOnClick = js.Function2[ReactMouseEvent, AccordionTitleProps, Unit]

  @js.native
  @JSImport("semantic-ui-react", "AccordionTitle")
  object RawComponent extends js.Object

  @js.native
  trait AccordionTitleProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Whether or not the title is in the open state. */
    var active: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[SemanticShorthandContent] = js.native

    /** Shorthand for Icon. */
    var icon: js.UndefOr[SemanticShorthandItemS[IconProps]] = js.native

    /** AccordionTitle index inside Accordion. */
    var index: js.UndefOr[Double | String] = js.native

    /**
     * Called on click.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: js.UndefOr[RawOnClick]
  }

  def props(q: AccordionTitle): AccordionTitleProps = {
    val p = q.as.toJsObject[AccordionTitleProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.icon.toJs.foreach(v => p.icon = v)
    q.index.foreach(v => p.index = v)
    (q.onClickE, q.onClick).toJs.foreach(v => p.onClick = v)
    p
  }

  private val component =
    JsComponent[AccordionTitleProps, Children.Varargs, Null](RawComponent)

  def apply(c: ShorthandS[VdomNode]): AccordionTitle =
    new AccordionTitle(content = c)
}
