// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.message

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class MessageList(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  items:                  MyUndefOr[Seq[ShorthandS[MessageItem]]] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = MyUndefOr.undefined

    /** Primary content. */
    var children: MyUndefOr[React.Node] = MyUndefOr.undefined

    /** Additional classes. */
    var className: MyUndefOr[String] = MyUndefOr.undefined

    /** Shorthand for primary content. */
    var items: MyUndefOr[suiraw.SemanticShorthandArray[MessageItem.MessageItemProps]] =
      MyUndefOr.undefined
  }

  def props(q: MessageList): MessageListProps =
    rawprops(
      q.as,
      q.className,
      q.clazz,
      q.items
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    items:     MyUndefOr[Seq[ShorthandS[MessageItem]]] = MyUndefOr.undefined
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
