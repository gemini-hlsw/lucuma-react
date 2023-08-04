// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.typed.primereact.components.{InputTextarea => CInputTextarea}
import lucuma.typed.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}
import lucuma.react.common.*

import scalajs.js

case class InputTextarea(
  autoResize:     js.UndefOr[Boolean] = js.undefined,
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  modifiers:      Seq[TagMod] = Seq.empty
) extends ReactFnProps[InputTextarea](InputTextarea.component):
  inline def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  inline def withMods(mods:          TagMod*)     = addModifiers(mods)
  inline def apply(mods:             TagMod*)     = addModifiers(mods)

object InputTextarea:
  private val component = ScalaFnComponent[InputTextarea] { props =>
    val cita = CInputTextarea
      .applyOrNot(props.autoResize, _.autoResize(_))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions, (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions]))

    cita(props.modifiers.toTagMod)
  }
