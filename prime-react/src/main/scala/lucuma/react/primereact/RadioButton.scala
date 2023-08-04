// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.{RadioButton => CRadioButton}
import lucuma.typed.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}

import scalajs.js

case class RadioButton[A](
  value:          A,
  id:             js.UndefOr[String] = js.undefined,
  inputId:        js.UndefOr[String] = js.undefined, // id of the input element
  name:           js.UndefOr[String] = js.undefined, // Name of the radio button
  disabled:       js.UndefOr[Boolean] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  checked:        js.UndefOr[Boolean] = js.undefined,
  required:       js.UndefOr[Boolean] = js.undefined,
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  onChange:       js.UndefOr[(A, Boolean) => Callback] = js.undefined,
  modifiers:      Seq[TagMod] = Seq.empty
) extends ReactFnProps[RadioButton[Any]](RadioButton.component) {

  protected def valueFinder(i: Any): A = i.asInstanceOf[A]

  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)
}

object RadioButton {
  private[primereact] val component = ScalaFnComponent[RadioButton[Any]] { props =>
    CRadioButton
      .value(props.value)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.inputId, _.inputId(_))
      .applyOrNot(props.name, _.name(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.checked, _.checked(_))
      .applyOrNot(props.required, _.required(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions, (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions]))
      .applyOrNot(
        props.onChange,
        (c, p) => c.onChange(scp => p(props.valueFinder(scp.value), scp.checked.getOrElse(false)))
      )(
        props.modifiers.toTagMod
      )
  }
}
