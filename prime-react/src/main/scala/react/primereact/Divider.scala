// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.derived.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Divider => CDivider}
import reactST.primereact.primereactStrings.bottom
import reactST.primereact.primereactStrings.center
import reactST.primereact.primereactStrings.dashed
import reactST.primereact.primereactStrings.dotted
import reactST.primereact.primereactStrings.horizontal
import reactST.primereact.primereactStrings.left
import reactST.primereact.primereactStrings.right
import reactST.primereact.primereactStrings.solid
import reactST.primereact.primereactStrings.top
import reactST.primereact.primereactStrings.vertical

import scalajs.js

case class Divider(
  position:   js.UndefOr[Divider.Position] = js.undefined,   // default HorizontalLeft
  borderType: js.UndefOr[Divider.BorderType] = js.undefined, // default: Solid
  clazz:      js.UndefOr[Css] = js.undefined
) extends ReactFnPropsWithChildren[Divider](Divider.component)

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
          .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(children)
      }
}
