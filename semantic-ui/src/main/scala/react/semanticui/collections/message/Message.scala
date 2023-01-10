// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.message

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import react.common._
import react.semanticui._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import react.semanticui.{raw => suiraw}

import scala.scalajs.js

import js.|
import js.annotation._
import js.JSConverters._

final case class Message(
  as:                     js.UndefOr[AsC] = js.undefined,
  attached:               js.UndefOr[MessageAttached] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[MessageContent]] = js.undefined,
  error:                  js.UndefOr[Boolean] = js.undefined,
  floating:               js.UndefOr[Boolean] = js.undefined,
  header:                 js.UndefOr[ShorthandS[MessageHeader]] = js.undefined,
  hidden:                 js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[ShorthandSB[Icon]] = js.undefined,
  info:                   js.UndefOr[Boolean] = js.undefined,
  list:                   js.UndefOr[Seq[ShorthandS[MessageItem]] | MessageList] = js.undefined,
  negative:               js.UndefOr[Boolean] = js.undefined,
  onDismissE:             js.UndefOr[Message.OnDismiss] = js.undefined,
  onDismiss:              js.UndefOr[Callback] = js.undefined,
  positive:               js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[MessageSize] = js.undefined,
  success:                js.UndefOr[Boolean] = js.undefined,
  visible:                js.UndefOr[Boolean] = js.undefined,
  warning:                js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Message.MessageProps, Message] {
  override protected def cprops    = Message.props(this)
  override protected val component = Message.component
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
    var as: js.UndefOr[AsT] = js.native

    /** A message can be formatted to attach itself to other content. */
    var attached: js.UndefOr[Boolean | String] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A message can be formatted to be different colors. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** A message can only take up the width of its content. */
    var compact: js.UndefOr[Boolean] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandItemS[MessageContent.MessageContentProps]] =
      js.native

    /** A message may be formatted to display a negative message. Same as `negative`. */
    var error: js.UndefOr[Boolean] = js.native

    /** A message can float above content that it is related to. */
    var floating: js.UndefOr[Boolean] = js.native

    /** Shorthand for MessageHeader. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[MessageHeader.MessageHeaderProps]] =
      js.native

    /** A message can be hidden. */
    var hidden: js.UndefOr[Boolean] = js.native

    /** Add an icon by icon name or pass an <Icon /.> */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemSB[IconProps]] = js.native

    /** A message may be formatted to display information. */
    var info: js.UndefOr[Boolean] = js.native

    /** Array shorthand items for the MessageList. Mutually exclusive with children. */
    var list: js.UndefOr[RawList] =
      js.native

    /** A message may be formatted to display a negative message. Same as `error`. */
    var negative: js.UndefOr[Boolean] = js.native

    /**
     * A message that the user can choose to hide. Called when the user clicks the "x" icon. This
     * also adds the "x" icon.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onDismiss: js.UndefOr[js.Function2[ReactEvent, MessageProps, Unit]] = js.native

    /** A message may be formatted to display a positive message.  Same as `success`. */
    var positive: js.UndefOr[Boolean] = js.native

    /** A message can have different sizes. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** A message may be formatted to display a positive message.  Same as `positive`. */
    var success: js.UndefOr[Boolean] = js.native

    /** A message can be set to visible to force itself to be shown. */
    var visible: js.UndefOr[Boolean] = js.native

    /** A message may be formatted to display warning messages. */
    var warning: js.UndefOr[Boolean] = js.native
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
    as:         js.UndefOr[AsC] = js.undefined,
    attached:   js.UndefOr[MessageAttached] = js.undefined,
    className:  js.UndefOr[String] = js.undefined,
    clazz:      js.UndefOr[Css] = js.undefined,
    color:      js.UndefOr[SemanticColor] = js.undefined,
    compact:    js.UndefOr[Boolean] = js.undefined,
    content:    js.UndefOr[ShorthandS[MessageContent]] = js.undefined,
    error:      js.UndefOr[Boolean] = js.undefined,
    floating:   js.UndefOr[Boolean] = js.undefined,
    header:     js.UndefOr[ShorthandS[MessageHeader]] = js.undefined,
    hidden:     js.UndefOr[Boolean] = js.undefined,
    icon:       js.UndefOr[ShorthandSB[Icon]] = js.undefined,
    info:       js.UndefOr[Boolean] = js.undefined,
    list:       js.UndefOr[Seq[ShorthandS[MessageItem]] | MessageList] = js.undefined,
    negative:   js.UndefOr[Boolean] = js.undefined,
    onDismissE: js.UndefOr[Message.OnDismiss] = js.undefined,
    onDismiss:  js.UndefOr[Callback] = js.undefined,
    positive:   js.UndefOr[Boolean] = js.undefined,
    size:       js.UndefOr[MessageSize] = js.undefined,
    success:    js.UndefOr[Boolean] = js.undefined,
    visible:    js.UndefOr[Boolean] = js.undefined,
    warning:    js.UndefOr[Boolean] = js.undefined
  ): MessageProps = {
    val p = as.toJsObject[MessageProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    attached.toJs.foreachUnchecked(v => p.attached = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    compact.foreach(v => p.compact = v)
    CompToPropsS(content).toJs.foreachUnchecked(v => p.content = v)
    error.foreach(v => p.error = v)
    floating.foreach(v => p.floating = v)
    CompToPropsS(header).toJs.foreachUnchecked(v => p.header = v)
    hidden.foreach(v => p.hidden = v)
    icon.toJs.foreachUnchecked(v => p.icon = v)
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
      .foreachUnchecked(v => p.list = v)
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
