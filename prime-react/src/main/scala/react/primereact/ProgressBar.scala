// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import lucuma.typed.primereact.components.{ProgressBar => CProgressBar}
import lucuma.typed.primereact.primereactStrings.determinate
import lucuma.typed.primereact.primereactStrings.indeterminate

import scalajs.js
import scalajs.js.JSConverters.*

case class ProgressBar(
  id:                   js.UndefOr[String] = js.undefined,
  value:                js.UndefOr[Double] = js.undefined,           // not needed for indeterminate mode
  showValue:            js.UndefOr[Boolean] = js.undefined,          // default: true
  unit:                 js.UndefOr[String] = js.undefined,           // default: %
  mode:                 js.UndefOr[ProgressBar.Mode] = js.undefined, // default: determinate
  color:                js.UndefOr[String] = js.undefined,           // default: undefined
  displayValueTemplate: js.UndefOr[Double => VdomNode] = js.undefined,
  clazz:                js.UndefOr[Css] = js.undefined,
  modifiers:            Seq[TagMod] = Seq.empty
) extends ReactFnProps[ProgressBar](ProgressBar.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)
}

object ProgressBar {
  enum Mode(val value: determinate | indeterminate):
    case Determinate   extends Mode(determinate)
    case Indeterminate extends Mode(indeterminate)

  private val component = ScalaFnComponent[ProgressBar] { props =>
    CProgressBar
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.value, _.value(_))
      .applyOrNot(props.showValue, _.showValue(_))
      .applyOrNot(props.unit, _.unit(_))
      .applyOrNot(props.mode, (c, p) => c.mode(p.value))
      .applyOrNot(props.color, _.color(_))
      .applyOrNot(props.displayValueTemplate,
                  (c, p) => c.displayValueTemplate(v => p(v.asInstanceOf[Double]).rawNode)
      )
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(
        props.modifiers.toTagMod
      )
  }
}
