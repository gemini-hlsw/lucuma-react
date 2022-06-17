package react.semanticui.collections.menu

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }

import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class MenuItem(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  fitted:                 js.UndefOr[MenuItemFitted] = js.undefined,
  header:                 js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[ShorthandSB[Icon]] = js.undefined,
  index:                  js.UndefOr[Int] = js.undefined,
  link:                   js.UndefOr[Boolean] = js.undefined,
  name:                   js.UndefOr[String] = js.undefined,
  onClickE:               js.UndefOr[MenuItem.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  position:               js.UndefOr[MenuItemPosition] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[MenuItem.MenuItemProps, MenuItem] {
  override protected def cprops                     = MenuItem.props(this)
  override protected val component                  = MenuItem.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object MenuItem {
  type OnClick = ReactEvent => Callback

  @js.native
  @JSImport("semantic-ui-react", "MenuItem")
  object RawComponent extends js.Object

  @js.native
  trait MenuItemProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** A menu item can be active. */
    var active: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Additional colors can be specified. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A menu item can be disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** A menu item or menu can remove element padding, vertically or horizontally. */
    var fitted: js.UndefOr[Boolean | String] = js.native

    /** A menu item may include a header or may itself be a header. */
    var header: js.UndefOr[Boolean] = js.native

    /** Item can be only icon. */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemSB[IconProps]] =
      js.native

    /** Item index inside Menu. */
    var index: js.UndefOr[Int] = js.native

    /** A menu item can be link. */
    var link: js.UndefOr[Boolean] = js.native

    /** Internal name of the Item. */
    var name: js.UndefOr[String] = js.native

    /**
     * Called on click. When passed, the component will render as an `a` tag by default instead of a
     * `div`.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: js.UndefOr[js.Function1[ReactEvent, Unit]] = js.native

    /** A menu item can take left or right position. */
    var position: js.UndefOr[String] = js.native
  }

  def props(
    q: MenuItem
  ): MenuItemProps =
    rawprops(q.as,
             q.active,
             q.className,
             q.clazz,
             q.color,
             q.content,
             q.disabled,
             q.fitted,
             q.header,
             q.icon,
             q.index,
             q.link,
             q.name,
             q.onClickE,
             q.onClick,
             q.position
    )

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    active:    js.UndefOr[Boolean] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    color:     js.UndefOr[SemanticColor] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    disabled:  js.UndefOr[Boolean] = js.undefined,
    fitted:    js.UndefOr[MenuItemFitted] = js.undefined,
    header:    js.UndefOr[Boolean] = js.undefined,
    icon:      js.UndefOr[ShorthandSB[Icon]] = js.undefined,
    index:     js.UndefOr[Int] = js.undefined,
    link:      js.UndefOr[Boolean] = js.undefined,
    name:      js.UndefOr[String] = js.undefined,
    onClickE:  js.UndefOr[OnClick] = js.undefined,
    onClick:   js.UndefOr[Callback] = js.undefined,
    position:  js.UndefOr[MenuItemPosition] = js.undefined
  ): MenuItemProps = {
    val p = as.toJsObject[MenuItemProps]
    as.toJs.foreach(v => p.as = v)
    active.foreach(v => p.active = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    content.toJs.foreach(v => p.content = v)
    disabled.foreach(v => p.disabled = v)
    fitted.toJs.foreach(v => p.fitted = v)
    header.foreach(v => p.header = v)
    icon.toJs.foreach(v => p.icon = v)
    index.foreach(v => p.index = v)
    link.foreach(v => p.link = v)
    name.foreach(v => p.name = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    position.toJs.foreach(v => p.position = v)
    p
  }

  private val component =
    JsComponent[MenuItemProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): MenuItem =
    new MenuItem(modifiers = modifiers)
}
