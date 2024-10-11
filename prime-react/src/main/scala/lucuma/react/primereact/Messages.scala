// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scalajs.js.annotation.JSImport
import scalajs.js
import scalajs.js.JSConverters.*
import Messages.*

case class MessagesRef(
  ref: Ref.ToJsComponent[MessagesProps, Null, facade.React.Component[MessagesProps, Null] & Facade]
) {
  def show(messages: MessageItem*): Callback    =
    ref.get.map(_.fold(())(_.raw.show(messages.toJSArray)))
  def replace(messages: MessageItem*): Callback =
    ref.get.map(_.fold(())(_.raw.replace(messages.toJSArray)))
  def remove(message: MessageItem): Callback    =
    ref.get.map(_.fold(())(_.raw.remove(message)))
  def clear(): Callback                         =
    ref.get.map(_.fold(())(_.raw.clear()))
}

case class Messages(
  id:        js.UndefOr[String] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined,
  modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAF[MessagesProps, Messages, Facade]:
  override protected def cprops: MessagesProps                = Messages.props(this)
  override protected val component                            = Messages.component
  override def addModifiers(modifiers: Seq[TagMod]): Messages =
    copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods: TagMod*)                                 = addModifiers(mods)

object Messages {
  @js.native
  @JSImport("primereact/messages/messages.esm", "Messages")
  object RawMessaages extends js.Object

  @js.native
  trait Facade extends js.Object {
    def show(message:    js.Array[MessageItem]): Unit = js.native
    def replace(message: js.Array[MessageItem]): Unit = js.native
    def remove(message:  MessageItem): Unit           = js.native
    def clear(): Unit                                 = js.native
  }

  @js.native
  trait MessagesProps extends js.Object {
    var id: js.UndefOr[String]        = js.native
    var className: js.UndefOr[String] = js.native
  }

  def props(m: Messages): MessagesProps = {
    val r = (new js.Object).asInstanceOf[MessagesProps]
    m.id.foreach(v => r.id = v)
    m.clazz.foreach(v => r.className = v.htmlClass)
    r
  }

  val component = JsComponent[MessagesProps, Children.None, Null](RawMessaages).addFacade[Facade]
}
