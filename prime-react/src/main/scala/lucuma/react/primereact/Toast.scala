// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.Eq
import cats.derived.*
import japgolly.scalajs.react.CtorType.Props
import japgolly.scalajs.react.*
import japgolly.scalajs.react.component.Js.ComponentWithFacade
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scalajs.js.annotation.JSImport
import scalajs.js
import scalajs.js.JSConverters.*
import Toast.*

case class ToastRef(
  ref: Ref.ToJsComponent[ToastProps, Null, facade.React.Component[ToastProps, Null] & Facade]
) {
  def show(message: MessageItem*): Callback    =
    ref.get.map(_.fold(())(_.raw.show(message.toJSArray)))
  def replace(message: MessageItem*): Callback =
    ref.get.map(_.fold(())(_.raw.replace(message.toJSArray)))
  def remove(message: MessageItem*): Callback  =
    ref.get.map(_.fold(())(_.raw.remove(message.toJSArray)))
  def clear(): Callback                        =
    ref.get.map(_.fold(())(_.raw.clear()))
}

// NOTE: Toasts have onRemove, onClick, onShow, onHide, onMouseEnter, and onMouseLeave events that are not yet implemented.
case class Toast(
  position:   js.UndefOr[Toast.Position] = js.undefined, // default: TopRight
  modifiers:  Seq[TagMod] = Seq.empty,
  baseZIndex: js.UndefOr[Int] = js.undefined
) extends GenericComponentPAF[ToastProps, Toast, Facade]:
  override protected def cprops: ToastProps                                              = Toast.props(this)
  override protected val component: ComponentWithFacade[ToastProps, Null, Facade, Props] =
    Toast.component
  override def addModifiers(modifiers: Seq[TagMod]): Toast                               =
    copy(modifiers = this.modifiers ++ modifiers)

  def withMods(mods: TagMod*) = addModifiers(mods)

object Toast {
  enum Position(val value: String) derives Eq:
    case BottomLeft  extends Position("bottom-left")
    case BottomRight extends Position("bottom-right")
    case TopLeft     extends Position("top-left")
    case TopRight    extends Position("top-right")

  @js.native
  @JSImport("primereact/toast/toast.esm", "Toast")
  object RawToast extends js.Object

  @js.native
  trait Facade extends js.Object {
    def show(message:    js.Array[MessageItem]): Unit = js.native
    def replace(message: js.Array[MessageItem]): Unit = js.native
    def remove(message:  js.Array[MessageItem]): Unit = js.native
    def clear(): Unit = js.native
  }

  @js.native
  trait ToastProps extends js.Object {
    var position: js.UndefOr[String] = js.native
    var baseZIndex: js.UndefOr[Int]  = js.native
  }

  def props(t: Toast): ToastProps = {
    val r = (new js.Object).asInstanceOf[ToastProps]
    t.position.foreach(v => r.position = v.value)
    t.baseZIndex.foreach(v => r.baseZIndex = v)
    r
  }

  val component = JsComponent[ToastProps, Children.None, Null](RawToast).addFacade[Facade]
}
