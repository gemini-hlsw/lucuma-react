package react.semanticui.collections.message

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class MessageItem(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[MessageItem]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[MessageItem.MessageItemProps, MessageItem] {
  override protected def cprops                     = MessageItem.props(this)
  override protected val component                  = MessageItem.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object MessageItem {
  @js.native
  @JSImport("semantic-ui-react", "MessageItem")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait MessageItemProps extends js.Object {
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
    var content: js.UndefOr[suiraw.SemanticShorthandItemS[MessageItem.MessageItemProps]] =
      js.undefined
  }

  def props(q: MessageItem): MessageItemProps =
    rawprops(
      q.as,
      q.className,
      q.clazz,
      q.content
    )

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[MessageItem]] = js.undefined
  ): MessageItemProps = {
    val p = as.toJsObject[MessageItemProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsComponent[MessageItemProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): MessageItem =
    new MessageItem(modifiers = modifiers)
}
