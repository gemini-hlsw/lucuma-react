// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.derived.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.fa.FontAwesomeIcon
import reactST.primereact.components.{Tag => CTag}
import reactST.primereact.primereactStrings.danger
import reactST.primereact.primereactStrings.info
import reactST.primereact.primereactStrings.success
import reactST.primereact.primereactStrings.warning

import scalajs.js

case class Tag(
  value:    js.UndefOr[VdomNode] = js.undefined,
  icon:     js.UndefOr[FontAwesomeIcon] = js.undefined,
  severity: js.UndefOr[Tag.Severity] =
    js.undefined, // default: same as `Info` but no `p-tag-info` class
  rounded:   js.UndefOr[Boolean] = js.undefined,
  className: js.UndefOr[String] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined
) extends ReactFnPropsWithChildren[Tag](Tag.component)

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
        .applyOrNot(props.icon, (c, p) => c.icon(p.clazz(PrimeStyles.TagIcon).raw))
        .applyOrNot(props.severity, (c, p) => c.severity(p.value))
        .applyOrNot(props.rounded, _.rounded(_))
        .applyOrNot((props.className, props.clazz).toJs, _.className(_))(children)
    }
}
