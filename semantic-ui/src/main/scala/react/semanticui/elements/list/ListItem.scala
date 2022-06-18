// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.list

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.elements.image._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class ListItem(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  description:            MyUndefOr[ShorthandS[ListDescription]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[ListHeader]] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[ListIcon]] = MyUndefOr.undefined,
  image:                  MyUndefOr[ShorthandS[Image]] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[ListItem.OnClick] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  value:                  MyUndefOr[String] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[ListItem.ListItemProps, ListItem] {
  override protected def cprops                     = ListItem.props(this)
  override protected val component                  = ListItem.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ListItem {
  type OnClick = (ReactMouseEvent, ListItemProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "ListItem")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ListItemProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A list item can active. */
    var active: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for ListItem component. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Shorthand for ListDescription. */
    var description
      : MyUndefOr[suiraw.SemanticShorthandItemS[ListDescription.ListDescriptionProps]] = js.native

    /** A list item can disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Shorthand for ListHeader. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[ListHeader.ListHeaderProps]] = js.native

    /** Shorthand for ListIcon. */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemS[ListIcon.ListIconProps]] = js.native

    /** Shorthand for Image. */
    var image: MyUndefOr[suiraw.SemanticShorthandItemS[Image.ImageProps]] = js.native

    /**
     * Called on click.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: MyUndefOr[js.Function2[ReactMouseEvent, ListItemProps, Unit]] = js.native

    /** A value for an ordered list. */
    var value: MyUndefOr[String] = js.native
  }

  def props(q: ListItem): ListItemProps =
    rawprops(q.as,
             q.active,
             q.className,
             q.clazz,
             q.content,
             q.description,
             q.disabled,
             q.header,
             q.icon,
             q.image,
             q.onClickE,
             q.onClick,
             q.value
    )

  def rawprops(
    as:          MyUndefOr[AsC] = MyUndefOr.undefined,
    active:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:   MyUndefOr[String] = MyUndefOr.undefined,
    clazz:       MyUndefOr[Css] = MyUndefOr.undefined,
    content:     MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    description: MyUndefOr[ShorthandS[ListDescription]] = MyUndefOr.undefined,
    disabled:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    header:      MyUndefOr[ShorthandS[ListHeader]] = MyUndefOr.undefined,
    icon:        MyUndefOr[ShorthandS[ListIcon]] = MyUndefOr.undefined,
    image:       MyUndefOr[ShorthandS[Image]] = MyUndefOr.undefined,
    onClickE:    MyUndefOr[ListItem.OnClick] = MyUndefOr.undefined,
    onClick:     MyUndefOr[Callback] = MyUndefOr.undefined,
    value:       MyUndefOr[String] = MyUndefOr.undefined
  ): ListItemProps = {
    val p = as.toJsObject[ListItemProps]
    as.toJs.foreach(v => p.as = v)
    active.foreach(v => p.active = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    description.toJs.foreach(v => p.description = v)
    disabled.foreach(v => p.disabled = v)
    header.toJs.foreach(v => p.header = v)
    icon.toJs.foreach(v => p.icon = v)
    image.toJs.foreach(v => p.image = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[ListItemProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: VdomNode*): ListItem =
    new ListItem(modifiers = modifiers)
}
