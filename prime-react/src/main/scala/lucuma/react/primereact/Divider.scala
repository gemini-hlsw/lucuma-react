// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.Eq
import cats.derived.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Divider as CDivider
import lucuma.typed.primereact.primereactStrings.bottom
import lucuma.typed.primereact.primereactStrings.center
import lucuma.typed.primereact.primereactStrings.dashed
import lucuma.typed.primereact.primereactStrings.dotted
import lucuma.typed.primereact.primereactStrings.horizontal
import lucuma.typed.primereact.primereactStrings.left
import lucuma.typed.primereact.primereactStrings.right
import lucuma.typed.primereact.primereactStrings.solid
import lucuma.typed.primereact.primereactStrings.top
import lucuma.typed.primereact.primereactStrings.vertical

import scalajs.js

case class Divider(
  position:   js.UndefOr[Divider.Position] = js.undefined,   // default HorizontalLeft
  borderType: js.UndefOr[Divider.BorderType] = js.undefined, // default: Solid
  clazz:      js.UndefOr[Css] = js.undefined,
  modifiers:  Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren[Divider](Divider.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  // You can add content to the divider as children without this method, but if you want
  // to add event handlers or other attributes, you'll need to use the modifiers.
  def withMods(mods: TagMod*) = addModifiers(mods)
}

object Divider {
  enum Position(val layout: horizontal | vertical, val align: left | right | center | top | bottom)
      derives Eq:
    case HorizontalLeft   extends Position(horizontal, left)
    case HorizontalCenter extends Position(horizontal, center)
    case HorizontalRight  extends Position(horizontal, right)
    case VerticalTop      extends Position(vertical, top)
    case VerticalCenter   extends Position(vertical, center)
    case VerticalBottom   extends Position(vertical, bottom)

  enum BorderType(val value: solid | dashed | dotted) derives Eq:
    case Solid  extends BorderType(solid)
    case Dashed extends BorderType(dashed)
    case Dotted extends BorderType(dotted)

  private val component =
    ScalaFnComponent
      .withHooks[Divider]
      .withPropsChildren
      .render { (props, children) =>
        CDivider
          .applyOrNot(props.position, (c, p) => c.align(p.align))
          .applyOrNot(props.position, (c, p) => c.layout(p.layout))
          .applyOrNot(props.borderType, (c, p) => c.`type`(p.value))
          .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(
            props.modifiers.toTagMod,
            children
          )
      }
}
