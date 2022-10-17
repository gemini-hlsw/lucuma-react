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
  clazz:     js.UndefOr[Css] = js.undefined,
  modifiers: Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren[Card](Card.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  // You can add content to the card as children without this method, but if you want
  // to add event handlers or other attributes, you'll need to use the modifiers.
  def withMods(mods: TagMod*) = addModifiers(mods)
}

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
          .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(
            props.modifiers.toTagMod,
            children
          )
      }
}
