// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa

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
  fixedWidth:  Boolean = false,
  inverse:     Boolean = false,
  listItem:    Boolean = false,
  flip:        js.UndefOr[Flip] = js.undefined,
  size:        js.UndefOr[IconSize] = js.undefined,
  pull:        js.UndefOr[Pull] = js.undefined,
  pulse:       Boolean = false,
  rotation:    js.UndefOr[Rotation] = js.undefined,
  shake:       Boolean = false,
  spin:        Boolean = false,
  spinPulse:   Boolean = false,
  spinReverse: Boolean = false,
  swapOpacity: Boolean = false,
  modifiers:   List[TagMod] = List.empty
) extends ReactFnProps(CounterLayer.component)
    with IconProps:
  def apply(mods: TagMod*): CounterLayer = copy(modifiers = modifiers ++ mods)
  override def faClasses: Css = super.faClasses // For some reason this is necessary (?!?!?)

object CounterLayer:
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
