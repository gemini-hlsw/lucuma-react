// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Card => CCard}

import scalajs.js

case class Card(
  id:        js.UndefOr[String] = js.undefined,
  header:    js.UndefOr[VdomNode] = js.undefined,
  footer:    js.UndefOr[VdomNode] = js.undefined,
  title:     js.UndefOr[VdomNode] = js.undefined,
  subTitle:  js.UndefOr[VdomNode] = js.undefined,
  className: js.UndefOr[String] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined
) extends ReactFnPropsWithChildren[Card](Card.component)

object Card {
  private val component =
    ScalaFnComponent
      .withHooks[Card]
      .withPropsChildren
      .render { (props, children) =>
        CCard
          .applyOrNot(props.id, _.id(_))
          .applyOrNot(props.header, (c, p) => c.header(p.rawNode))
          .applyOrNot(props.footer, (c, p) => c.footer(p.rawNode))
          .applyOrNot(props.title, (c, p) => c.title(p.rawNode))
          .applyOrNot(props.subTitle, (c, p) => c.subTitle(p.rawNode))
          .applyOrNot((props.className, props.clazz).toJs, _.className(_))(children)
      }
}
