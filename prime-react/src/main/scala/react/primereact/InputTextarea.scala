// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{InputTextarea => CInputTextarea}
import reactST.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}

import scalajs.js

case class InputTextarea(
  id:             String,
  clazz:          js.UndefOr[Css] = js.undefined,
  value:          js.UndefOr[String] = js.undefined,
  autoResize:     js.UndefOr[Boolean] = js.undefined,
  disabled:       js.UndefOr[Boolean] = js.undefined,
  placeholder:    js.UndefOr[String] = js.undefined,
  rows:           js.UndefOr[Int] = js.undefined,
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  onBlur:         js.UndefOr[ReactFocusEventFromTextArea => Callback] = js.undefined,
  onChange:       js.UndefOr[ReactEventFromTextArea => Callback] = js.undefined,
  onKeyDown:      js.UndefOr[ReactKeyboardEventFromTextArea => Callback] = js.undefined,
  modifiers:      Seq[TagMod] = Seq.empty
) extends ReactFnProps[InputTextarea](InputTextarea.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
}

object InputTextarea {
  private val component = ScalaFnComponent[InputTextarea] { props =>
    val cita = CInputTextarea
      .id(props.id)
      .applyOrNot(props.value, _.value(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.autoResize, _.autoResize(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions, (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions]))
      .applyOrNot(props.onBlur, _.onBlur(_))
      .applyOrNot(props.onChange, _.onChange(_))
      .applyOrNot(props.onKeyDown, _.onKeyDown(_))
      .set("cols", 15)

    // The primereact typescript facade doesn't expose `rows` and `cols`. At least
    // ScalablyTyped didn't find them. `rows` is useful, so I forced it here. `cols`
    // didn't seem to do anything, but it might be CSS related.
    props.rows.fold(cita)(rows => cita.set("rows", rows))(props.modifiers.toTagMod)
  }
}
