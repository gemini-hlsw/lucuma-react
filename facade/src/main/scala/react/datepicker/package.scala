package react

import scalajs.js
import js.|
import java.time.LocalDate

package object datepicker {
  val Datepicker = gpp.reactDatepicker.components.ReactDatepicker

  implicit class LocalDateOps(val localDate: LocalDate) extends AnyVal {
    def toJsDate: js.Date =
      new js.Date(
        localDate.getYear,
        localDate.getMonthValue,
        localDate.getDayOfMonth
      )
  }

  // implicit class LocalDateModuleOps(localDateModule: LocalDate.type) {
  object LocalDateBuilder {
    def fromJsDate(jsDate: js.Date): LocalDate =
      LocalDate.of(
        jsDate.getFullYear().toInt,
        jsDate.getMonth().toInt,
        jsDate.getDate().toInt
      )
  }

  implicit class JSUndefOrNullOps[A](val undefOrNull: js.UndefOr[A | Null])
      extends AnyVal {
    def toOpt: Option[A] =
      undefOrNull.toOption.flatMap((valueOrNull: A | Null) =>
        Option(valueOrNull.asInstanceOf[A])
      )

    def toLocalDateOpt(implicit ev: A <:< js.Date): Option[LocalDate] =
      undefOrNull.toOpt.map(ev).map(LocalDateBuilder.fromJsDate)
  }
}
