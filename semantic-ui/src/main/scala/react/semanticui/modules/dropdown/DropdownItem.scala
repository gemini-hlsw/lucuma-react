package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui.raw._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps

import react.semanticui.elements.flag.Flag.FlagProps
import react.semanticui.elements.flag.Flag
import react.semanticui.elements.label.Label.LabelProps
import react.semanticui.elements.label.Label
import react.semanticui.elements.image.Image.ImageProps
import react.semanticui.elements.image.Image
import react.semanticui._

final case class DropdownItem(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  description:            js.UndefOr[String] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  flag:                   js.UndefOr[ShorthandS[Flag]] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[Icon]] = js.undefined,
  image:                  js.UndefOr[ShorthandS[Image]] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  onClickE:               js.UndefOr[DropdownItem.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  selected:               js.UndefOr[Boolean] = js.undefined,
  text:                   js.UndefOr[String] = js.undefined,
  value:                  js.UndefOr[Boolean | Double | String] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[DropdownItem.DropdownItemProps, DropdownItem] {
  override protected def cprops                     = DropdownItem.props(this)
  override protected val component                  = DropdownItem.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownItem {
  type OnClick = (ReactMouseEvent, DropdownItemProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "DropdownItem")
  object RawComponent extends js.Object

  @js.native
  trait DropdownItemProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Style as the currently chosen item. */
    var active: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[SemanticShorthandContent] = js.native

    /** Additional text with less emphasis. */
    var description: js.UndefOr[suiraw.SemanticShorthandItemS[String]] = js.native

    /** A dropdown item can be disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** Shorthand for Flag. */
    var flag: js.UndefOr[suiraw.SemanticShorthandItemS[FlagProps]] = js.native

    /** Shorthand for Icon. */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native

    /** Shorthand for Image. */
    var image: js.UndefOr[suiraw.SemanticShorthandItemS[ImageProps]] = js.native

    /** Shorthand for Label. */
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[LabelProps]] = js.native

    /**
     * Called on click.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: js.UndefOr[js.Function2[ReactMouseEvent, DropdownItemProps, Unit]] = js.native

    /**
     * The item currently selected by keyboard shortcut. This is not the active item.
     */
    var selected: js.UndefOr[Boolean] = js.native

    /** Display text. */
    var text: js.UndefOr[SemanticShorthandContent] = js.native

    /** Stored value. */
    var value: js.UndefOr[Boolean | Double | String] = js.native
  }

  def props(
    q: DropdownItem
  ): DropdownItemProps = {
    val p = q.as.toJsObject[DropdownItemProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.description.foreach(v => p.description = v)
    q.disabled.foreach(v => p.disabled = v)
    q.flag.toJs.foreach(v => p.flag = v)
    q.icon.toJs.foreach(v => p.icon = v)
    q.image.toJs.foreach(v => p.image = v)
    q.label.toJs.foreach(v => p.label = v)
    (q.onClickE, q.onClick).toJs.foreach(v => p.onClick = v)
    q.selected.foreach(v => p.selected = v)
    q.text.foreach(v => p.text = v)
    q.value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[DropdownItemProps, Children.Varargs, Null](RawComponent)

  def apply(v: Boolean | Double | String): DropdownItem =
    new DropdownItem(value = v, modifiers = Seq(v.toString))

}
