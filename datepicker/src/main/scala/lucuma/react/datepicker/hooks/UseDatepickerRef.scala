// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.datepicker.hooks

import japgolly.scalajs.react.*
import lucuma.react.datepicker.Datepicker
import lucuma.react.datepicker.DatepickerRef

object UseDatepickerRef:
  val useDatepickerRef: HookResult[DatepickerRef] =
    useRefToJsComponentWithMountedFacade[Datepicker.Props, Null, Datepicker.Facade].map:
      DatepickerRef(_)
