// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import org.scalajs.dom.*

import scalajs.js
import scalajs.js.JSConverters.*
import scalajs.js.annotation.JSImport

// The PopupMenu and PopupTieredMenu have the same properties, events, etc. So
// this shares the Facade and Props with the PopupMenu, and uses the same PopupMenuRef
case class PopupTieredMenu(
  model:                           Reusable[List[MenuItem]],
  id:                              js.UndefOr[String] = js.undefined,
  clazz:                           js.UndefOr[Css] = js.undefined,
  baseZIndex:                      js.UndefOr[Int] = js.undefined,                        // default: 0
  autoZIndex:                      js.UndefOr[Boolean] = js.undefined,                    // default: true
  appendTo:                        js.UndefOr[SelfPosition | HTMLElement] = js.undefined, // default: document.body
  onHide:                          js.UndefOr[Callback] = js.undefined,
  onShow:                          js.UndefOr[Callback] = js.undefined,
  modifiers:                       Seq[TagMod] = Seq.empty,
  private[primereact] val menuRef: Option[
    Ref.Handle[Js.RawMounted[PopupMenu.PopupMenuProps, Null] & PopupMenu.Facade]
  ] = None
) extends ReactFnProps[PopupTieredMenu](PopupTieredMenu.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)

  def withRef(
    ref: Ref.ToJsComponent[
      PopupMenu.PopupMenuProps,
      Null,
      facade.React.Component[PopupMenu.PopupMenuProps, Null] & PopupMenu.Facade
    ]
  ): PopupTieredMenu = copy(menuRef = Some(ref))
}

object PopupTieredMenu {
  @js.native
  @JSImport("primereact/tieredmenu/tieredmenu.esm", "TieredMenu")
  private object RawTieredMenu extends js.Object

  private val tieredMenuComponent =
    JsComponent[PopupMenu.PopupMenuProps, Children.None, Null](RawTieredMenu)
      .addFacade[PopupMenu.Facade]

  private val component = ScalaFnComponent[PopupTieredMenu] { pm =>
    for modelArray <- useMemo(pm.model)(_.value.map(_.asInstanceOf[Any]).toJSArray)
    yield {
      val r = (new js.Object).asInstanceOf[PopupMenu.PopupMenuProps]
      r.model = modelArray.value.asInstanceOf[js.Array[MenuItem]]
      r.popup = true
      pm.id.foreach(v => r.asInstanceOf[js.Dynamic].id = v.asInstanceOf[js.Any])
      pm.clazz.foreach(v => r.className = v.htmlClass)
      pm.baseZIndex.foreach(v => r.baseZIndex = v)
      pm.autoZIndex.foreach(v => r.autoZIndex = v)
      pm.appendTo.foreach(v => r.appendTo = v.asInstanceOf[Any])
      pm.onHide.foreach(v => r.onHide = _ => v.runNow())
      pm.onShow.foreach(v => r.onShow = _ => v.runNow())
      new AttrsBuilder(r).toJs(pm.modifiers)
      pm.menuRef.fold(tieredMenuComponent)(tieredMenuComponent.withRef).applyGeneric(r)()
    }
  }
}
