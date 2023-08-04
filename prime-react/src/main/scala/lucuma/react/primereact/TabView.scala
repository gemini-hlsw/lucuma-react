// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.typed.primereact.components.{TabView => CTabView}
import lucuma.react.common.*

import scalajs.js

// Note: You only need to specify activeIndex if you specify onTabChange, which then needs to set activeIndex
// TODO: There are 2 events that are not in the ScalablyTyped facade: `onBeforeTabChange` and `onBeforeTabClose`
// that allow for the prevention of changing or closing tabs, respectively. I could not immediately get these
// to work with the `set` method, so I am delaying implementation until needed.
// There is also supposed to be a `reset` function that I think restores closed tabs, but there is no
// example in the documentation and I don't know how it would work in the React context.
final case class TabView(
  id:                  js.UndefOr[String] = js.undefined,
  activeIndex:         js.UndefOr[Int] = js.undefined,
  renderActiveOnly:    js.UndefOr[Boolean] = js.undefined, // default: true
  scrollable:          js.UndefOr[Boolean] = js.undefined, // can scroll the tab headers? default: false
  clazz:               js.UndefOr[Css] = js.undefined,
  panelContainerClass: js.UndefOr[Css] = js.undefined,
  onTabClose:          js.UndefOr[Int => Callback] = js.undefined,
  onTabChange:         js.UndefOr[Int => Callback] = js.undefined,
  panels:              List[TabPanel] = List.empty,
  modifiers:           Seq[TagMod] = Seq.empty
) extends ReactFnProps[TabView](TabView.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)

  def apply(panel: TabPanel, morePanels: TabPanel*): TabView =
    copy(panels = (panels :+ panel) ++ morePanels)
}

object TabView {
  private val component = ScalaFnComponent[TabView] { props =>
    CTabView
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.activeIndex, _.activeIndex(_))
      .applyOrNot(props.renderActiveOnly, _.renderActiveOnly(_))
      .applyOrNot(props.scrollable, _.scrollable(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.panelContainerClass, (c, p) => c.panelContainerClassName(p.htmlClass))
      .applyOrNot(props.onTabClose, (c, p) => c.onTabClose(e => p(e.index.toInt)))
      .applyOrNot(props.onTabChange, (c, p) => c.onTabChange(e => p(e.index.toInt)))(
        props.modifiers.toTagMod,
        props.panels.toTagMod
      )
  }
}
