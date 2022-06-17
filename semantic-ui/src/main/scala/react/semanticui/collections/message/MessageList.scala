package react.semanticui.collections.message

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class MessageList(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  items:                  js.UndefOr[Seq[ShorthandS[MessageItem]]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[MessageList.MessageListProps, MessageList] {
  override protected def cprops                     = MessageList.props(this)
  override protected val component                  = MessageList.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object MessageList {
  @js.native
  @JSImport("semantic-ui-react", "MessageList")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait MessageListProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.undefined

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.undefined

    /** Additional classes. */
    var className: js.UndefOr[String] = js.undefined

    /** Shorthand for primary content. */
    var items: js.UndefOr[suiraw.SemanticShorthandArray[MessageItem.MessageItemProps]] =
      js.undefined
  }

  def props(q: MessageList): MessageListProps =
    rawprops(
      q.as,
      q.className,
      q.clazz,
      q.items
    )

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    items:     js.UndefOr[Seq[ShorthandS[MessageItem]]] = js.undefined
  ): MessageListProps = {
    val p = as.toJsObject[MessageListProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    items.toJs.foreach(v => p.items = v)
    p
  }

  private val component =
    JsComponent[MessageListProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): MessageList =
    new MessageList(modifiers = modifiers)
}
