// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import react.common.*
import reactST.primereact.components.{Checkbox => CCheckbox}

import scalajs.js

case class Checkbox(
  id:       js.UndefOr[String] = js.undefined,
  inputId:  js.UndefOr[String] = js.undefined,  // id of the input element
  checked:  js.UndefOr[Boolean] = js.undefined, // id of the input element
  disabled: js.UndefOr[Boolean] = js.undefined,
  clazz:    js.UndefOr[Css] = js.undefined,
  onChange: js.UndefOr[Boolean => Callback] = js.undefined
) extends ReactFnProps[Checkbox](Checkbox.component)

object Checkbox {
  private val component = ScalaFnComponent[Checkbox] { props =>
    CCheckbox
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.inputId, _.inputId(_))
      .applyOrNot(props.checked, _.checked(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.onChange, (c, p) => c.onChange(iwcp => p(iwcp.checked)))
  }
}
