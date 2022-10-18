// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Slider => CSlider}

import scalajs.js

case class SliderRange(
  value:       (Double, Double),
  id:          js.UndefOr[String] = js.undefined,
  min:         js.UndefOr[Double] = js.undefined, // default: 0
  max:         js.UndefOr[Double] = js.undefined, // default: 100
  step:        js.UndefOr[Double] = js.undefined, // default: 1
  orientation: js.UndefOr[Layout] = js.undefined, // default: horizontal
  disabled:    js.UndefOr[Boolean] = js.undefined,
  clazz:       js.UndefOr[Css] = js.undefined,
  onChange:    js.UndefOr[((Double, Double)) => Callback] = js.undefined,
  modifiers:   Seq[TagMod] = Seq.empty
) extends ReactFnProps[SliderRange](SliderRange.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods: TagMod*)              = addModifiers(mods)
  def apply(mods: TagMod*)                 = addModifiers(mods)
}

object SliderRange {
  private val component = ScalaFnComponent[SliderRange] { props =>
    CSlider
      .value(props.value)
      .range(true)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.min, _.min(_))
      .applyOrNot(props.max, _.max(_))
      .applyOrNot(props.step, _.step(_))
      .applyOrNot(props.orientation, (c, p) => c.orientation(p.value))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(
        props.onChange,
        (c, p) => c.onChange(scp => p(scp.value.asInstanceOf[js.Tuple2[Double, Double]]))
      )(
        props.modifiers.toTagMod
      )
  }
}
