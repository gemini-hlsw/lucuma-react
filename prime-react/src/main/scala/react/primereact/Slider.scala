// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Slider => CSlider}

import scalajs.js

case class Slider(
  value:       Double,
  id:          js.UndefOr[String] = js.undefined,
  min:         js.UndefOr[Double] = js.undefined, // default: 0
  max:         js.UndefOr[Double] = js.undefined, // default: 100
  step:        js.UndefOr[Double] = js.undefined, // default: 1
  orientation: js.UndefOr[Layout] = js.undefined, // default: horizontal
  disabled:    js.UndefOr[Boolean] = js.undefined,
  clazz:       js.UndefOr[Css] = js.undefined,
  onChange:    js.UndefOr[Double => Callback] = js.undefined,
  modifiers:   Seq[TagMod] = Seq.empty
) extends ReactFnProps[Slider](Slider.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods: TagMod*)              = addModifiers(mods)
}

object Slider {
  private val component = ScalaFnComponent[Slider] { props =>
    CSlider
      .value(props.value)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.min, _.min(_))
      .applyOrNot(props.max, _.max(_))
      .applyOrNot(props.step, _.step(_))
      .applyOrNot(props.orientation, (c, p) => c.orientation(p.value))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(
        props.onChange,
        (c, p) => c.onChange(scp => p(scp.value.asInstanceOf[Double]))
      )(
        props.modifiers.toTagMod
      )
  }
}
