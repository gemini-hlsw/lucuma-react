// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.{Checkbox => CCheckbox}
import lucuma.typed.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}

import scalajs.js

case class Checkbox(
  checked:        Boolean,
  id:             js.UndefOr[String] = js.undefined,
  inputId:        js.UndefOr[String] = js.undefined, // id of the input element
  disabled:       js.UndefOr[Boolean] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  onChange:       js.UndefOr[Boolean => Callback] = js.undefined,
  modifiers:      Seq[TagMod] = Seq.empty
) extends ReactFnProps[Checkbox](Checkbox.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)
}

object Checkbox {
  private val component = ScalaFnComponent[Checkbox] { props =>
    CCheckbox(props.checked)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.inputId, _.inputId(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions, (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions]))
      .applyOrNot(props.onChange, (c, p) => c.onChange(iwcp => p(iwcp.checked.getOrElse(false))))(
        props.modifiers.toTagMod
      )
  }
}