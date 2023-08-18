// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scalajs.js

case class LayeredIcon(
  clazz:       Css = Css.Empty,
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
) extends ReactFnPropsWithChildren(LayeredIcon.component)
    with IconProps:
  def withMods(mods: TagMod*): LayeredIcon = copy(modifiers = modifiers ++ mods)
  override def faClasses: Css = super.faClasses // For some reason this is necessary (?!?!?)

object LayeredIcon:
  private type Props = LayeredIcon

  private val component =
    ScalaFnComponent
      .withHooks[Props]
      .withPropsChildren
      .render: (props, children) =>
        <.span(Css("fa-layers") |+| props.faClasses, props.modifiers.toTagMod)(children)
