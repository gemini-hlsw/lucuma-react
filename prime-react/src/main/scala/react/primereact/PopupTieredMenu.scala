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

// The PopupMenu and PopupTieredMenu have the same properties, events, etc. So
// this shares the Facade and Props with the PopupMenu, and uses the same PopupMenuRef
case class PopupTieredMenu(
  model:      Seq[MenuItem],
  id:         js.UndefOr[String] = js.undefined,
  clazz:      js.UndefOr[Css] = js.undefined,
  baseZIndex: js.UndefOr[Int] = js.undefined,                        // default: 0
  autoZIndex: js.UndefOr[Boolean] = js.undefined,                    // default: true
  appendTo:   js.UndefOr[SelfPosition | HTMLElement] = js.undefined, // default: document.body
  onHide:     js.UndefOr[Callback] = js.undefined,
  onShow:     js.UndefOr[Callback] = js.undefined,
  modifiers:  Seq[TagMod] = Seq.empty
) extends GenericComponentPAF[PopupMenu.PopupMenuProps, PopupTieredMenu, PopupMenu.Facade]:
  override protected def cprops = PopupTieredMenu.props(this)
  override protected val component
    : ComponentWithFacade[PopupMenu.PopupMenuProps, Null, PopupMenu.Facade, Props] =
    PopupTieredMenu.component

  override def addModifiers(modifiers: Seq[TagMod]): PopupTieredMenu =
    copy(modifiers = this.modifiers ++ modifiers)

  def withMods(mods: TagMod*) = addModifiers(mods)

object PopupTieredMenu {
  @js.native
  @JSImport("primereact/tieredmenu/tieredmenu.esm", "TieredMenu")
  object RawPopupTieredMenu extends js.Object

  private def props(pm: PopupTieredMenu): PopupMenu.PopupMenuProps = {
    val r = (new js.Object).asInstanceOf[PopupMenu.PopupMenuProps]
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
    JsComponent[PopupMenu.PopupMenuProps, Children.None, Null](RawPopupTieredMenu)
      .addFacade[PopupMenu.Facade]
}
