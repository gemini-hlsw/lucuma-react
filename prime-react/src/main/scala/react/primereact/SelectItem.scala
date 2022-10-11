// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import react.common.*
import reactST.primereact.selectitemMod.{SelectItem => CSelectItem}

import scalajs.js

object SelectItem {
  def apply[A](
    label:     String,
    value:     A,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined
  ): CSelectItem = {
    val si = CSelectItem().setLabel(label).setValue(value)
    (className, clazz).cssToJs.fold(si)(cls => si.setClassName(cls))
  }

}
