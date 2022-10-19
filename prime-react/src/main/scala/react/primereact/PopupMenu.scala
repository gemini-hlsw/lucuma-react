// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.CtorType.Props
import japgolly.scalajs.react.*
import japgolly.scalajs.react.component.Js.ComponentWithFacade
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.*
import react.common.*

import scalajs.js.annotation.JSImport
import scalajs.js
import scalajs.js.JSConverters.*

case class PopupMenuRef(
  ref: Ref.ToJsComponent[
    PopupMenu.PopupMenuProps,
    Null,
    facade.React.Component[PopupMenu.PopupMenuProps, Null] & PopupMenu.Facade
  ]
) {
  def toggle: ReactEvent => Callback = e => ref.get.map(_.fold(())(_.raw.toggle(e)))
  def show: ReactEvent => Callback   = e => ref.get.map(_.fold(())(_.raw.show(e)))
  def hide: ReactEvent => Callback   = e => ref.get.map(_.fold(())(_.raw.hide(e)))
}

case class PopupMenu(
  model:      Seq[MenuItem],
  id:         js.UndefOr[String] = js.undefined,
  clazz:      js.UndefOr[Css] = js.undefined,
  baseZIndex: js.UndefOr[Int] = js.undefined,                        // default: 0
  autoZIndex: js.UndefOr[Boolean] = js.undefined,                    // default: true
  appendTo:   js.UndefOr[SelfPosition | HTMLElement] = js.undefined, // default: document.body
  onHide:     js.UndefOr[Callback] = js.undefined,
  onShow:     js.UndefOr[Callback] = js.undefined,
  modifiers:  Seq[TagMod] = Seq.empty
) extends GenericComponentPAF[PopupMenu.PopupMenuProps, PopupMenu, PopupMenu.Facade]:
  override protected def cprops = PopupMenu.props(this)
  override protected val component
    : ComponentWithFacade[PopupMenu.PopupMenuProps, Null, PopupMenu.Facade, Props] =
    PopupMenu.component

  override def addModifiers(modifiers: Seq[TagMod]): PopupMenu =
    copy(modifiers = this.modifiers ++ modifiers)

  def withMods(mods: TagMod*) = addModifiers(mods)

object PopupMenu {
  @js.native
  @JSImport("primereact/menu/menu.esm", "Menu")
  object RawPopupMenu extends js.Object

  @js.native
  trait Facade extends js.Object {
    def show(event:   ReactEvent): Unit = js.native
    def hide(event:   ReactEvent): Unit = js.native
    def toggle(event: ReactEvent): Unit = js.native
  }

  @js.native
  trait PopupMenuProps extends js.Object {
    var model: js.UndefOr[js.Array[MenuItem]]                           = js.native
    var popup: js.UndefOr[Boolean]                                      = js.native
    var className: js.UndefOr[String]                                   = js.native
    var baseZIndex: js.UndefOr[Int]                                     = js.native
    var autoZIndex: js.UndefOr[Boolean]                                 = js.native
    var appendTo: js.UndefOr[Any]                                       = js.native
    var onHide: js.UndefOr[js.Function1[ReactEventFrom[Element], Unit]] = js.native
    var onShow: js.UndefOr[js.Function1[ReactEventFrom[Element], Unit]] = js.native
  }

  private def props(pm: PopupMenu): PopupMenuProps = {
    val r = (new js.Object).asInstanceOf[PopupMenuProps]
    r.model = pm.model.toJSArray
    r.popup = true
    pm.clazz.foreach(v => r.className = v.htmlClass)
    pm.baseZIndex.foreach(v => r.baseZIndex = v)
    pm.autoZIndex.foreach(v => r.autoZIndex = v)
    pm.appendTo.foreach(v => r.appendTo = v.asInstanceOf[Any])
    pm.onHide.foreach(v => r.onHide = _ => v.runNow())
    pm.onShow.foreach(v => r.onShow = _ => v.runNow())
    r
  }

  val component =
    JsComponent[PopupMenuProps, Children.None, Null](RawPopupMenu).addFacade[Facade]
}
