// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.Eq
import cats.derived.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Tag as CTag
import lucuma.typed.primereact.primereactStrings.danger
import lucuma.typed.primereact.primereactStrings.info
import lucuma.typed.primereact.primereactStrings.success
import lucuma.typed.primereact.primereactStrings.warning

import scalajs.js

case class Tag(
  value:     js.UndefOr[VdomNode] = js.undefined,
  icon:      js.UndefOr[Icon] = js.undefined,
  severity:  js.UndefOr[Tag.Severity] =
    js.undefined, // default: same as `Info` but no `p-tag-info` class
  rounded:   js.UndefOr[Boolean] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined,
  modifiers: Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren[Tag](Tag.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  // You can add content to the tag as children without this method, but if you want
  // to add event handlers or other attributes, you'll need to use the modifiers.
  def withMods(mods: TagMod*) = addModifiers(mods)
}

object Tag {
  enum Severity(val value: danger | info | success | warning) derives Eq:
    case Danger  extends Severity(danger)
    case Info    extends Severity(info)
    case Success extends Severity(success)
    case Warning extends Severity(warning)

  private val component =
    ScalaFnComponent.withHooks[Tag].withPropsChildren.render { (props, children) =>
      CTag
        .applyOrNot(props.value, _.value(_))
        .applyOrNot(props.icon, (c, p) => c.icon(p.toPrimeWithClass(PrimeStyles.TagIcon)))
        .applyOrNot(props.severity, (c, p) => c.severity(p.value))
        .applyOrNot(props.rounded, _.rounded(_))
        .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(
          props.modifiers.toTagMod,
          children
        )
    }
}
