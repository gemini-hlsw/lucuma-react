// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.message

import scala.scalajs.js
import js.|
import js.annotation._
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import japgolly.scalajs.react.vdom.TagMod

final case class Message(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  attached:               MyUndefOr[MessageAttached] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  compact:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[MessageContent]] = MyUndefOr.undefined,
  error:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  floating:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[MessageHeader]] = MyUndefOr.undefined,
  hidden:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
  info:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  list:                   MyUndefOr[Seq[ShorthandS[MessageItem]] | MessageList] = MyUndefOr.undefined,
  negative:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  onDismissE:             MyUndefOr[Message.OnDismiss] = MyUndefOr.undefined,
  onDismiss:              MyUndefOr[Callback] = MyUndefOr.undefined,
  positive:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[MessageSize] = MyUndefOr.undefined,
  success:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  visible:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  warning:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Message.MessageProps, Message] {
  override protected def cprops                     = Message.props(this)
  override protected val component                  = Message.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Message {
  type OnDismiss = (ReactEvent, MessageProps) => Callback
  type RawList   =
    js.Array[
      suiraw.SemanticShorthandItemS[MessageItem.MessageItemProps]
    ] | MessageList.MessageListProps

  @js.native
  @JSImport("semantic-ui-react", "Message")
  object RawComponent extends js.Object

  @js.native
  trait MessageProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = MyUndefOr.undefined

    /** A message can be formatted to attach itself to other content. */
    var attached: MyUndefOr[Boolean | String] = MyUndefOr.undefined

    /** Primary content. */
    var children: MyUndefOr[React.Node] = MyUndefOr.undefined

    /** Additional classes. */
    var className: MyUndefOr[String] = MyUndefOr.undefined

    /** A message can be formatted to be different colors. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = MyUndefOr.undefined

    /** A message can only take up the width of its content. */
    var compact: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandItemS[MessageContent.MessageContentProps]] =
      MyUndefOr.undefined

    /** A message may be formatted to display a negative message. Same as `negative`. */
    var error: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** A message can float above content that it is related to. */
    var floating: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Shorthand for MessageHeader. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[MessageHeader.MessageHeaderProps]] =
      MyUndefOr.undefined

    /** A message can be hidden. */
    var hidden: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Add an icon by icon name or pass an <Icon /.> */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemSB[IconProps]] = MyUndefOr.undefined

    /** A message may be formatted to display information. */
    var info: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Array shorthand items for the MessageList. Mutually exclusive with children. */
    var list: MyUndefOr[RawList] =
      MyUndefOr.undefined

    /** A message may be formatted to display a negative message. Same as `error`. */
    var negative: MyUndefOr[Boolean] = MyUndefOr.undefined

    /**
     * A message that the user can choose to hide. Called when the user clicks the "x" icon. This
     * also adds the "x" icon.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onDismiss: MyUndefOr[js.Function2[ReactEvent, MessageProps, Unit]] = MyUndefOr.undefined

    /** A message may be formatted to display a positive message.  Same as `success`. */
    var positive: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** A message can have different sizes. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /** A message may be formatted to display a positive message.  Same as `positive`. */
    var success: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** A message can be set to visible to force itself to be shown. */
    var visible: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** A message may be formatted to display warning messages. */
    var warning: MyUndefOr[Boolean] = MyUndefOr.undefined
  }

  def props(q: Message): MessageProps =
    rawprops(
      q.as,
      q.attached,
      q.className,
      q.clazz,
      q.color,
      q.compact,
      q.content,
      q.error,
      q.floating,
      q.header,
      q.hidden,
      q.icon,
      q.info,
      q.list,
      q.negative,
      q.onDismissE,
      q.onDismiss,
      q.positive,
      q.size,
      q.success,
      q.visible,
      q.warning
    )

  def rawprops(
    as:         MyUndefOr[AsC] = MyUndefOr.undefined,
    attached:   MyUndefOr[MessageAttached] = MyUndefOr.undefined,
    className:  MyUndefOr[String] = MyUndefOr.undefined,
    clazz:      MyUndefOr[Css] = MyUndefOr.undefined,
    color:      MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    compact:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    content:    MyUndefOr[ShorthandS[MessageContent]] = MyUndefOr.undefined,
    error:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    floating:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    header:     MyUndefOr[ShorthandS[MessageHeader]] = MyUndefOr.undefined,
    hidden:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    icon:       MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
    info:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    list:       MyUndefOr[Seq[ShorthandS[MessageItem]] | MessageList] = MyUndefOr.undefined,
    negative:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    onDismissE: MyUndefOr[Message.OnDismiss] = MyUndefOr.undefined,
    onDismiss:  MyUndefOr[Callback] = MyUndefOr.undefined,
    positive:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:       MyUndefOr[MessageSize] = MyUndefOr.undefined,
    success:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    visible:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    warning:    MyUndefOr[Boolean] = MyUndefOr.undefined
  ): MessageProps = {
    val p = as.toJsObject[MessageProps]
    as.toJs.foreach(v => p.as = v)
    attached.toJs.foreach(v => p.attached = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    compact.foreach(v => p.compact = v)
    content.toJs.foreach(v => p.content = v)
    error.foreach(v => p.error = v)
    floating.foreach(v => p.floating = v)
    header.toJs.foreach(v => p.header = v)
    hidden.foreach(v => p.hidden = v)
    icon.toJs.foreach(v => p.icon = v)
    info.foreach(v => p.info = v)
    list
      .map[RawList](v =>
        (v: Any) match {
          case s: Seq[?]      =>
            s.map(item => compToPropS(item.asInstanceOf[ShorthandS[MessageItem]])).toJSArray
          // .asInstanceOf[raw.SemanticShorthandOrArray[T]]
          case l: MessageList => l.props
          case _              => sys.error("Shouldn't happen")
        }
      )
      .foreach(v => p.list = v)
    negative.foreach(v => p.negative = v)
    (onDismissE, onDismiss).toJs.foreach(v => p.onDismiss = v)
    positive.foreach(v => p.positive = v)
    size.toJs.foreach(v => p.size = v)
    visible.foreach(v => p.visible = v)
    warning.foreach(v => p.warning = v)
    p
  }

  private val component =
    JsComponent[MessageProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Message =
    new Message(modifiers = modifiers)
}
