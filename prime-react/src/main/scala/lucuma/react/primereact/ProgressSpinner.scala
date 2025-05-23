// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.ProgressSpinner as CProgressSpinner

import scalajs.js

case class ProgressSpinner(
  id:                js.UndefOr[String] = js.undefined,
  strokeWidth:       js.UndefOr[String] = js.undefined,
  fill:              js.UndefOr[String] = js.undefined,
  animationDuration: js.UndefOr[String] = js.undefined,
  clazz:             js.UndefOr[Css] = js.undefined,
  modifiers:         Seq[TagMod] = Seq.empty
) extends ReactFnProps(ProgressSpinner.component):
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)

object ProgressSpinner:
  private val component = ScalaFnComponent[ProgressSpinner] { props =>
    CProgressSpinner
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.strokeWidth, _.strokeWidth(_))
      .applyOrNot(props.fill, _.fill(_))
      .applyOrNot(props.animationDuration, _.animationDuration(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(
        props.modifiers.toTagMod
      )
  }
