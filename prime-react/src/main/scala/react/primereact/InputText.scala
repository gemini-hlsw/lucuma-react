// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import react.common.*
import reactST.primereact.components.{InputText => CInputText}

import scalajs.js

object InputText {
  def apply(
    id:          String,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    value:       js.UndefOr[String] = js.undefined,
    disabled:    js.UndefOr[Boolean] = js.undefined,
    placeholder: js.UndefOr[String] = js.undefined,
    onBlur:      js.UndefOr[ReactFocusEventFromInput => Callback] = js.undefined,
    onChange:    js.UndefOr[ReactEventFromInput => Callback] = js.undefined,
    onKeyDown:   js.UndefOr[ReactKeyboardEventFromInput => Callback] = js.undefined
  ): CInputText.Builder =
    CInputText
      .id(id)
      .applyOrNot(value, _.value(_))
      .applyOrNot((className, clazz).toJs, _.className(_))
      .applyOrNot(disabled, _.disabled(_))
      .applyOrNot(placeholder, _.placeholder(_))
      .applyOrNot(onBlur, _.onBlur(_))
      .applyOrNot(onChange, _.onChange(_))
      .applyOrNot(onKeyDown, _.onKeyDown(_))
}
