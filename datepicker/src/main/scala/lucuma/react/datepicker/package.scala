// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.ZonedDateTime

import scalajs.js

package object datepicker:

  extension (localDate: LocalDate) {
    def toJsDate: js.Date =
      new js.Date(
        localDate.getYear,
        localDate.getMonthValue - 1,
        localDate.getDayOfMonth
      )
  }

  extension (jsDate: js.Date) {
    def fromJsDate: LocalDate =
      LocalDate.of(
        jsDate.getFullYear().toInt,
        jsDate.getMonth().toInt + 1,
        jsDate.getDate().toInt
      )
  }

// DatePicker only works in local timezone, so we trick it by adding the timezone offset.
// See https://github.com/Hacker0x01/react-datepicker/issues/1787
  extension (jsDate: js.Date)
    def fromDatePickerJsDate: Instant =
      Instant.ofEpochMilli((jsDate.getTime() - jsDate.getTimezoneOffset() * 60000).toLong)

  extension (instant: Instant)
    // DatePicker only works in local timezone, so we trick it by adding the timezone offset.
    // See https://github.com/Hacker0x01/react-datepicker/issues/1787
    def toDatePickerJsDate: js.Date =
      val zdt: ZonedDateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC)
      // init removes the Z timezone
      new js.Date(js.Date.parse(zdt.toString.init))

  extension (zdt: ZonedDateTime)
    // DatePicker only works in local timezone, so we trick it by adding the timezone offset.
    // See https://github.com/Hacker0x01/react-datepicker/issues/1787
    def toDatePickerJsDate: js.Date =
      zdt.toInstant.toDatePickerJsDate
