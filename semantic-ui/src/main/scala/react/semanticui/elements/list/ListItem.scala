package react.semanticui.elements.list

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.elements.image._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class ListItem(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  description:            js.UndefOr[ShorthandS[ListDescription]] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  header:                 js.UndefOr[ShorthandS[ListHeader]] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[ListIcon]] = js.undefined,
  image:                  js.UndefOr[ShorthandS[Image]] = js.undefined,
  onClickE:               js.UndefOr[ListItem.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  value:                  js.UndefOr[String] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** A list item can active. */
    var active: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for ListItem component. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Shorthand for ListDescription. */
    var description
      : js.UndefOr[suiraw.SemanticShorthandItemS[ListDescription.ListDescriptionProps]] = js.native

    /** A list item can disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** Shorthand for ListHeader. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[ListHeader.ListHeaderProps]] = js.native

    /** Shorthand for ListIcon. */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemS[ListIcon.ListIconProps]] = js.native

    /** Shorthand for Image. */
    var image: js.UndefOr[suiraw.SemanticShorthandItemS[Image.ImageProps]] = js.native

    /**
     * Called on click.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: js.UndefOr[js.Function2[ReactMouseEvent, ListItemProps, Unit]] = js.native

    /** A value for an ordered list. */
    var value: js.UndefOr[String] = js.native
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
    as:          js.UndefOr[AsC] = js.undefined,
    active:      js.UndefOr[Boolean] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    content:     js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    description: js.UndefOr[ShorthandS[ListDescription]] = js.undefined,
    disabled:    js.UndefOr[Boolean] = js.undefined,
    header:      js.UndefOr[ShorthandS[ListHeader]] = js.undefined,
    icon:        js.UndefOr[ShorthandS[ListIcon]] = js.undefined,
    image:       js.UndefOr[ShorthandS[Image]] = js.undefined,
    onClickE:    js.UndefOr[ListItem.OnClick] = js.undefined,
    onClick:     js.UndefOr[Callback] = js.undefined,
    value:       js.UndefOr[String] = js.undefined
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
