// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{ProgressBar => CProgressBar}
import reactST.primereact.primereactStrings.determinate
import reactST.primereact.primereactStrings.indeterminate

import scalajs.js
import scalajs.js.JSConverters.*

case class ProgressBar(
  id:                   js.UndefOr[String] = js.undefined,
  value:                js.UndefOr[Double] = js.undefined,           // not needed for indeterminate mode
  showValue:            js.UndefOr[Boolean] = js.undefined,          // default: true
  unit:                 js.UndefOr[String] = js.undefined,           // default: %
  mode:                 js.UndefOr[ProgressBar.Mode] = js.undefined, // default: determinate
  color:                js.UndefOr[String] = js.undefined,           // default: undefined
  displayValueTemplate: js.UndefOr[Double => VdomNode] = js.undefined,
  className:            js.UndefOr[String] = js.undefined,
  clazz:                js.UndefOr[Css] = js.undefined
) extends ReactFnProps[ProgressBar](ProgressBar.component)

object ProgressBar {
  enum Mode(val value: determinate | indeterminate):
    case Determinate   extends Mode(determinate)
    case Indeterminate extends Mode(indeterminate)

  private val component = ScalaFnComponent[ProgressBar] { props =>
    CProgressBar
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.value, _.value(_))
      .applyOrNot(props.showValue, _.showValue(_))
      .applyOrNot(props.unit, _.unit(_))
      .applyOrNot(props.mode, (c, p) => c.mode(p.value))
      .applyOrNot(props.color, _.color(_))
      .applyOrNot(props.displayValueTemplate,
                  (c, p) => c.displayValueTemplate(v => p(v.asInstanceOf[Double]).rawNode)
      )
      .applyOrNot((props.className, props.clazz).toJs, _.className(_))
  }
}
