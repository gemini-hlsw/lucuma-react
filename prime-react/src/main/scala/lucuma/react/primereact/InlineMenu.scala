// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Menu as CMenu

import scalajs.js
import scalajs.js.JSConverters.*

case class InlineMenu(
  model:     Reusable[List[MenuItem]],
  id:        js.UndefOr[String] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined,
  modifiers: Seq[TagMod] = Seq.empty
) extends ReactFnProps[InlineMenu](InlineMenu.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)
}

object InlineMenu {
  private val component =
    ScalaFnComponent[InlineMenu] { props =>
      for modelArray <- useMemo(props.model)(_.value.map(_.asInstanceOf[Any]).toJSArray)
      yield CMenu
        .model(modelArray.value)
        .applyOrNot(props.id, _.id(_))
        .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(
          props.modifiers.toTagMod
        )
    }
}
