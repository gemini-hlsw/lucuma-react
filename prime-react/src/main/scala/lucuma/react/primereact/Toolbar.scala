// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Toolbar as CToolbar

import scalajs.js

case class Toolbar(
  id:        js.UndefOr[String] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined,
  left:      js.UndefOr[VdomNode] = js.undefined,
  right:     js.UndefOr[VdomNode] = js.undefined,
  modifiers: Seq[TagMod] = Seq.empty
) extends ReactFnProps[Toolbar](Toolbar.component) {
  // It appears as though the mods might only be applied the the `left` nodes?
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
}

object Toolbar {
  private val component = ScalaFnComponent[Toolbar] { props =>
    CToolbar
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.left, (c, p) => c.left(p.rawNode))
      .applyOrNot(props.right, (c, p) => c.right(p.rawNode))(
        props.modifiers.toTagMod
      )
  }
}
