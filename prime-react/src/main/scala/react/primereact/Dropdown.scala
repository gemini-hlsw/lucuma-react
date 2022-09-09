// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import react.common.*
import reactST.primereact.components.{Dropdown => CDropdown}
import reactST.primereact.selectitemMod.SelectItem

import scalajs.js
import scalajs.js.JSConverters.*

object Dropdown {
  def apply(
    value:           Any,
    options:         List[SelectItem],
    id:              js.UndefOr[String] = js.undefined,
    className:       js.UndefOr[String] = js.undefined,
    clazz:           js.UndefOr[Css] = js.undefined,
    showClear:       js.UndefOr[Boolean] = js.undefined,
    filter:          js.UndefOr[Boolean] = js.undefined,
    showFilterClear: js.UndefOr[Boolean] = js.undefined,
    placeholder:     js.UndefOr[String] = js.undefined,
    disabled:        js.UndefOr[Boolean] = js.undefined,
    onChange:        js.UndefOr[Any => Callback] = js.undefined
  ): CDropdown.Builder =
    CDropdown
      .value(value)
      .options(options.toJSArray)
      .applyOrNot(id, _.id(_))
      .applyOrNot((className, clazz).toJs, _.className(_))
      .applyOrNot(showClear, _.showClear(_))
      .applyOrNot(filter, _.filter(_))
      .applyOrNot(showFilterClear, _.showFilterClear(_))
      .applyOrNot(placeholder, _.placeholder(_))
      .applyOrNot(disabled, _.disabled(_))
      .applyOrNot(onChange, (b, a) => b.onChange(e => a(e.value)))
}
