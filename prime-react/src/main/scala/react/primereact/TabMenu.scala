// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{TabMenu => CTabMenu}

import scalajs.js
import scalajs.js.JSConverters.*

// Note: You don't need `activeIndex` or `onTabChange`, but if you have
// `activeIndex` you NEED `onTabChange`.
case class TabMenu(
  model:       List[MenuItem.Item],
  id:          js.UndefOr[String] = js.undefined,
  activeIndex: js.UndefOr[Int] = js.undefined,
  clazz:       js.UndefOr[Css] = js.undefined,
  onTabChange: js.UndefOr[(Int, MenuItem.Item) => Callback] = js.undefined,
  modifiers:   Seq[TagMod] = Seq.empty
) extends ReactFnProps[TabMenu](TabMenu.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods: TagMod*)              = addModifiers(mods)
  def apply(mods: TagMod*)                 = addModifiers(mods)
}

object TabMenu {
  private val component = ScalaFnComponent[TabMenu] { props =>
    CTabMenu
      .model(props.model.toJSArray)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.activeIndex, _.activeIndex(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(
        props.onTabChange,
        (c, p) => c.onTabChange(e => p(e.index.toInt, e.value.asInstanceOf[MenuItem.Item]))
      )(props.modifiers.toTagMod)
  }
}
