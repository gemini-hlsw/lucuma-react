// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.menu

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class MenuItem(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  fitted:                 MyUndefOr[MenuItemFitted] = MyUndefOr.undefined,
  header:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
  index:                  MyUndefOr[Int] = MyUndefOr.undefined,
  link:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  name:                   MyUndefOr[String] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[MenuItem.OnClick] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  position:               MyUndefOr[MenuItemPosition] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = js.native

    /** A menu item can be active. */
    var active: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Additional colors can be specified. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A menu item can be disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** A menu item or menu can remove element padding, vertically or horizontally. */
    var fitted: MyUndefOr[Boolean | String] = js.native

    /** A menu item may include a header or may itself be a header. */
    var header: MyUndefOr[Boolean] = js.native

    /** Item can be only icon. */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemSB[IconProps]] =
      js.native

    /** Item index inside Menu. */
    var index: MyUndefOr[Int] = js.native

    /** A menu item can be link. */
    var link: MyUndefOr[Boolean] = js.native

    /** Internal name of the Item. */
    var name: MyUndefOr[String] = js.native

    /**
     * Called on click. When passed, the component will render as an `a` tag by default instead of a
     * `div`.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: MyUndefOr[js.Function1[ReactEvent, Unit]] = js.native

    /** A menu item can take left or right position. */
    var position: MyUndefOr[String] = js.native
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
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    active:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    color:     MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    disabled:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    fitted:    MyUndefOr[MenuItemFitted] = MyUndefOr.undefined,
    header:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    icon:      MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
    index:     MyUndefOr[Int] = MyUndefOr.undefined,
    link:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    name:      MyUndefOr[String] = MyUndefOr.undefined,
    onClickE:  MyUndefOr[OnClick] = MyUndefOr.undefined,
    onClick:   MyUndefOr[Callback] = MyUndefOr.undefined,
    position:  MyUndefOr[MenuItemPosition] = MyUndefOr.undefined
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
