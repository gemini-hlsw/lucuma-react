// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import react.common.*
import reactST.primereact.components.{InputText => CInputText}

import scalajs.js

case class InputText(
  id:          String,
  clazz:       js.UndefOr[Css] = js.undefined,
  value:       js.UndefOr[String] = js.undefined,
  disabled:    js.UndefOr[Boolean] = js.undefined,
  placeholder: js.UndefOr[String] = js.undefined,
  onBlur:      js.UndefOr[ReactFocusEventFromInput => Callback] = js.undefined,
  onChange:    js.UndefOr[ReactEventFromInput => Callback] = js.undefined,
  onKeyDown:   js.UndefOr[ReactKeyboardEventFromInput => Callback] = js.undefined
) extends ReactFnProps[InputText](InputText.component)

object InputText {
  private val component = ScalaFnComponent[InputText] { props =>
    CInputText
      .id(props.id)
      .applyOrNot(props.value, _.value(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.onBlur, _.onBlur(_))
      .applyOrNot(props.onChange, _.onChange(_))
      .applyOrNot(props.onKeyDown, _.onKeyDown(_))
  }
}
