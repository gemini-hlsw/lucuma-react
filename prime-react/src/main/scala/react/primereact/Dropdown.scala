// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import react.common.*
import reactST.primereact.components.{Dropdown => CDropdown}
import reactST.primereact.selectitemMod.SelectItem

import scalajs.js
import scalajs.js.JSConverters.*

final case class Dropdown(
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
) extends ReactFnProps[Dropdown](Dropdown.component)

object Dropdown {
  private val component = ScalaFnComponent[Dropdown] { props =>
    CDropdown
      .value(props.value)
      .options(props.options.toJSArray)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot((props.className, props.clazz).toJs, _.className(_))
      .applyOrNot(props.showClear, _.showClear(_))
      .applyOrNot(props.filter, _.filter(_))
      .applyOrNot(props.showFilterClear, _.showFilterClear(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.onChange, (b, a) => b.onChange(e => a(e.value)))
  }
}
