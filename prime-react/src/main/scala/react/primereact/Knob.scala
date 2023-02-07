// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.typed.primereact.components.{Knob => CKnob}
import react.common.*

import scalajs.js

case class Knob(
  value:         Double,
  id:            js.UndefOr[String] = js.undefined,
  min:           js.UndefOr[Double] = js.undefined,  // default: 0
  max:           js.UndefOr[Double] = js.undefined,  // default: 100
  step:          js.UndefOr[Double] = js.undefined,  // default: 1
  size:          js.UndefOr[Double] = js.undefined,  // size in pixels. default: 100
  strokeWidth:   js.UndefOr[Double] = js.undefined,  // size in pixels. default: 14
  showValue:     js.UndefOr[Boolean] = js.undefined, // default: true
  valueTemplate: js.UndefOr[String] = js.undefined,  // js template literal: default "{value}"
  readOnly:      js.UndefOr[Boolean] = js.undefined, // default: false
  disabled:      js.UndefOr[Boolean] = js.undefined,
  clazz:         js.UndefOr[Css] = js.undefined,
  onChange:      js.UndefOr[Double => Callback] = js.undefined,
  modifiers:     Seq[TagMod] = Seq.empty
) extends ReactFnProps[Knob](Knob.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)
}

object Knob {
  private val component = ScalaFnComponent[Knob] { props =>
    CKnob
      .value(props.value)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.min, _.min(_))
      .applyOrNot(props.max, _.max(_))
      .applyOrNot(props.step, _.step(_))
      .applyOrNot(props.size, _.size(_))
      .applyOrNot(props.strokeWidth, _.strokeWidth(_))
      .applyOrNot(props.showValue, _.showValue(_))
      .applyOrNot(props.valueTemplate, _.valueTemplate(_))
      .applyOrNot(props.readOnly, _.readOnly(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(
        props.onChange,
        (c, p) => c.onChange(scp => p(scp.value.asInstanceOf[Double]))
      )(props.modifiers.toTagMod)
  }
}
