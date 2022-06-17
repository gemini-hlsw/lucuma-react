package react.semanticui.collections.menu

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class Menu(
  as:                     js.UndefOr[AsC] = js.undefined,
  activeIndex:            js.UndefOr[Int | String] = js.undefined,
  attached:               js.UndefOr[MenuAttached] = js.undefined,
  borderless:             js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  defaultActiveIndex:     js.UndefOr[Int | String] = js.undefined,
  fixed:                  js.UndefOr[MenuFixed] = js.undefined,
  floated:                js.UndefOr[MenuFloated] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[MenuIcon] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  onItemClickE:           js.UndefOr[Menu.OnItemClick] = js.undefined,
  onItemClick:            js.UndefOr[Callback] = js.undefined,
  pagination:             js.UndefOr[Boolean] = js.undefined,
  pointing:               js.UndefOr[Boolean] = js.undefined,
  secondary:              js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  stackable:              js.UndefOr[Boolean] = js.undefined,
  tabular:                js.UndefOr[MenuTabular] = js.undefined,
  text:                   js.UndefOr[Boolean] = js.undefined,
  vertical:               js.UndefOr[Boolean] = js.undefined,
  widths:                 js.UndefOr[SemanticWidth] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Menu.MenuProps, Menu] {
  override protected def cprops                     = Menu.props(this)
  override protected val component                  = Menu.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Menu {
  type OnItemClick = ReactEvent => Callback

  @js.native
  @JSImport("semantic-ui-react", "Menu")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait MenuProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** a menu item can be active. */
    var activeIndex: js.UndefOr[Int | String] = js.native

    /** A menu may be attached to other content segments. */
    var attached: js.UndefOr[Boolean | String] = js.native

    /** A menu item or menu can have no borders. */
    var borderless: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Additional colors can be specified. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** A menu can take up only the space necessary to fit its content. */
    var compact: js.UndefOr[Boolean] = js.native

    /** Initial activeIndex value. */
    var defaultActiveIndex: js.UndefOr[Int | String] = js.native

    /** A menu can be floated. */
    var fixed: js.UndefOr[String] = js.native

    /** A menu item or menu can remove element padding, vertically or horizontally. */
    var floated: js.UndefOr[Boolean | String] = js.native

    /** A vertical menu may take the size of its container. */
    var fluid: js.UndefOr[Boolean] = js.native

    /** A menu may have just icons (bool) or labeled icons. */
    var icon: js.UndefOr[Boolean | String] =
      js.native

    /** A menu may have its colors inverted to show greater contrast. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** Shorthand array of props for Menu. */
    // items: customPropTypes.collectionShorthand,

    /**
     * onClick handler for MenuItem. Mutually exclusive with children.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All item props.
     */
    var onItemClick: js.UndefOr[js.Function1[ReactEvent, Unit]] = js.native

    /** A pagination menu is specially formatted to present links to pages of content. */
    var pagination: js.UndefOr[Boolean] = js.native

    /** A menu can point to show its relationship to nearby content. */
    var pointing: js.UndefOr[Boolean] = js.native

    /** A menu can adjust its appearance to de-emphasize its contents. */
    var secondary: js.UndefOr[Boolean] = js.native

    /** A menu can vary in size. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** A menu can stack at mobile resolutions. */
    var stackable: js.UndefOr[Boolean] = js.native

    /** A menu can be formatted to show tabs of information. */
    var tabular: js.UndefOr[Boolean | String]

    /** A menu can be formatted for text content. */
    var text: js.UndefOr[Boolean] = js.native

    /** A vertical menu displays elements vertically. */
    var vertical: js.UndefOr[Boolean] = js.native

    /** A menu can vary in size. */
    var widths: js.UndefOr[suiraw.SemanticWIDTHS] = js.native
  }

  def props(q: Menu): MenuProps =
    rawprops(
      q.as,
      q.activeIndex,
      q.attached,
      q.borderless,
      q.className,
      q.clazz,
      q.color,
      q.compact,
      q.defaultActiveIndex,
      q.fixed,
      q.floated,
      q.fluid,
      q.icon,
      q.inverted,
      q.onItemClickE,
      q.onItemClick,
      q.pagination,
      q.pointing,
      q.secondary,
      q.size,
      q.stackable,
      q.tabular,
      q.text,
      q.vertical,
      q.widths
    )

  def rawprops(
    as:                 js.UndefOr[AsC] = js.undefined,
    activeIndex:        js.UndefOr[Int | String] = js.undefined,
    attached:           js.UndefOr[MenuAttached] = js.undefined,
    borderless:         js.UndefOr[Boolean] = js.undefined,
    className:          js.UndefOr[String] = js.undefined,
    clazz:              js.UndefOr[Css] = js.undefined,
    color:              js.UndefOr[SemanticColor] = js.undefined,
    compact:            js.UndefOr[Boolean] = js.undefined,
    defaultActiveIndex: js.UndefOr[Int | String] = js.undefined,
    fixed:              js.UndefOr[MenuFixed] = js.undefined,
    floated:            js.UndefOr[MenuFloated] = js.undefined,
    fluid:              js.UndefOr[Boolean] = js.undefined,
    icon:               js.UndefOr[MenuIcon] = js.undefined,
    inverted:           js.UndefOr[Boolean] = js.undefined,
    onItemClickE:       js.UndefOr[OnItemClick] = js.undefined,
    onItemClick:        js.UndefOr[Callback] = js.undefined,
    pagination:         js.UndefOr[Boolean] = js.undefined,
    pointing:           js.UndefOr[Boolean] = js.undefined,
    secondary:          js.UndefOr[Boolean] = js.undefined,
    size:               js.UndefOr[SemanticSize] = js.undefined,
    stackable:          js.UndefOr[Boolean] = js.undefined,
    tabular:            js.UndefOr[MenuTabular] = js.undefined,
    text:               js.UndefOr[Boolean] = js.undefined,
    vertical:           js.UndefOr[Boolean] = js.undefined,
    widths:             js.UndefOr[SemanticWidth] = js.undefined
  ): MenuProps = {
    val p = (new js.Object).asInstanceOf[MenuProps]
    as.toJs.foreach(v => p.as = v)
    activeIndex.foreach(v => p.activeIndex = v)
    attached.toJs.foreach(v => p.attached = v)
    borderless.foreach(v => p.borderless = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    compact.foreach(v => p.compact = v)
    defaultActiveIndex.foreach(v => p.defaultActiveIndex = v)
    fixed.toJs.foreach(v => p.fixed = v)
    floated.toJs.foreach(v => p.floated = v)
    fluid.foreach(v => p.fluid = v)
    icon.toJs.foreach(v => p.icon = v)
    inverted.foreach(v => p.inverted = v)
    (onItemClickE, onItemClick).toJs.foreach(v => p.onItemClick = v)
    pagination.foreach(v => p.pagination = v)
    pointing.foreach(v => p.pointing = v)
    secondary.foreach(v => p.secondary = v)
    size.toJs.foreach(v => p.size = v)
    stackable.foreach(v => p.stackable = v)
    tabular.toJs.foreach(v => p.tabular = v)
    text.foreach(v => p.text = v)
    vertical.foreach(v => p.vertical = v)
    widths.toJs.foreach(v => p.widths = v)
    p
  }

  private val component =
    JsComponent[MenuProps, Children.Varargs, Null](RawComponent)

  val Default: Menu = Menu()

  val defaultProps: MenuProps = props(Default)

  def apply(modifiers: TagMod*): Menu =
    new Menu(modifiers = modifiers)

}
