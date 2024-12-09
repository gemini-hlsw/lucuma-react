// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scala.scalajs.js

case class CounterLayer(
  content:     String | Float | Double | Int | Short | Byte,
  clazz:       Css = Css.Empty,
  title:       js.UndefOr[String] = js.undefined,
  titleId:     js.UndefOr[String] = js.undefined,
  beat:        Boolean = false,
  beatFade:    Boolean = false,
  border:      Boolean = false,
  bounce:      Boolean = false,
  fade:        Boolean = false,
  flash:       Boolean = false,
  flip:        js.UndefOr[Flip] = js.undefined,
  fixedWidth:  Boolean = false,
  inverse:     Boolean = false,
  listItem:    Boolean = false,
  pull:        js.UndefOr[Pull] = js.undefined,
  pulse:       Boolean = false,
  rotation:    js.UndefOr[Rotation] = js.undefined,
  shake:       Boolean = false,
  size:        js.UndefOr[IconSize] = js.undefined,
  spin:        Boolean = false,
  spinPulse:   Boolean = false,
  spinReverse: Boolean = false,
  swapOpacity: Boolean = false,
  modifiers:   List[TagMod] = List.empty
) extends ReactFnProps(CounterLayer.component)
    with IconProps:
  def apply(mods: TagMod*): CounterLayer = copy(modifiers = modifiers ++ mods)
  override def faClasses: Css            = super.faClasses // For some reason this is necessary (?!?!?)

  def addClass(value:        Css)            = copy(clazz = clazz |+| value)
  def withClass(value:       Css)            = copy(clazz = value)
  def withTitle(value:       String)         = copy(title = value)
  def withTitleId(value:     String)         = copy(titleId = value)
  def withBeat(value:        Boolean = true) = copy(beat = value)
  def withBeatFade(value:    Boolean = true) = copy(beatFade = value)
  def withBorder(value:      Boolean = true) = copy(border = value)
  def withBounce(value:      Boolean = true) = copy(bounce = value)
  def withFade(value:        Boolean = true) = copy(fade = value)
  def withFlash(value:       Boolean = true) = copy(flash = value)
  def withFlip(value:        Flip)           = copy(flip = value)
  def withFixedWidth(value:  Boolean = true) = copy(fixedWidth = value)
  def withInverse(value:     Boolean = true) = copy(inverse = value)
  def withListItem(value:    Boolean = true) = copy(listItem = value)
  def withPull(value:        Pull)           = copy(pull = value)
  def withPulse(value:       Boolean = true) = copy(pulse = value)
  def withRotation(value:    Rotation)       = copy(rotation = value)
  def withShake(value:       Boolean = true) = copy(shake = value)
  def withSize(value:        IconSize)       = copy(size = value)
  def withSpin(value:        Boolean = true) = copy(spin = value)
  def withSpinPulse(value:   Boolean = true) = copy(spinPulse = value)
  def withSpinReverse(value: Boolean = true) = copy(spinReverse = value)
  def withSwapOpacity(value: Boolean = true) = copy(swapOpacity = value)

object CounterLayer {
  private type Props = CounterLayer

  private val component = ScalaFnComponent[Props]: props =>
    FontAwesome
      .counter(
        props.content,
        CounterParams(
          classes = props.faClasses,
          title = props.title,
          titleId = props.titleId,
          modifiers = props.modifiers
        )
      )
      .abstractDef(0)
      .renderVdom

}
