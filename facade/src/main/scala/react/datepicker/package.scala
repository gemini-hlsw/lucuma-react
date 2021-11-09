package react

import scalajs.js
import js.|
import java.time.LocalDate
import lucuma.reactDatepicker.components.SharedBuilder_ReactDatePickerProps_1216478921
import japgolly.scalajs.react.vdom.VdomElement

package object datepicker {
  type DateRange = js.Tuple2[js.Date | Null, js.Date | Null]

  type NonNullDate = js.Date | DateRange

  type DateOrRange = NonNullDate | Null

  implicit class LocalDateOps(val localDate: LocalDate) extends AnyVal {
    def toJsDate: js.Date =
      new js.Date(
        localDate.getYear,
        localDate.getMonthValue - 1,
        localDate.getDayOfMonth
      )
  }

  // implicit class LocalDateModuleOps(localDateModule: LocalDate.type) {
  object LocalDateBuilder {
    def fromJsDate(jsDate: js.Date): LocalDate =
      LocalDate.of(
        jsDate.getFullYear().toInt,
        jsDate.getMonth().toInt + 1,
        jsDate.getDate().toInt
      )
  }

  implicit class JSUndefOrNullOrTuple2Ops[A](
    val value: js.UndefOr[A | js.Tuple2[A | Null, A | Null] | Null]
  ) extends AnyVal {
    def toEitherOpt: Option[Either[(A, A), A]] =
      value.toOption
        .flatMap(valueOrNull => Option(valueOrNull.asInstanceOf[A | js.Tuple2[A, A]]))
        .map { valueOrTuple =>
          if (js.Array.isArray(valueOrTuple))
            Left(valueOrTuple.asInstanceOf[js.Tuple2[A, A]])
          else
            Right(valueOrTuple.asInstanceOf[A])
        }

    def toOpt: Option[A] =
      toEitherOpt.flatMap(_.toOption)

    def toTupleOpt: Option[(A, A)] =
      toEitherOpt.flatMap(_.left.toOption)

    def toLocalDateEitherOpt(implicit
      ev: A <:< js.Date
    ): Option[Either[(LocalDate, LocalDate), LocalDate]] =
      toEitherOpt.map {
        case Left((d1, d2)) =>
          Left((LocalDateBuilder.fromJsDate(ev(d1)), LocalDateBuilder.fromJsDate(ev(d2))))
        case Right(d)       =>
          Right(LocalDateBuilder.fromJsDate(ev(d)))
      }

    def toLocalDateOpt(implicit ev: A <:< js.Date): Option[LocalDate] =
      toLocalDateEitherOpt.flatMap(_.toOption)

    def toLocalDateTupleOpt(implicit ev: A <:< js.Date): Option[(LocalDate, LocalDate)] =
      toLocalDateEitherOpt.flatMap(_.left.toOption)
  }

  // We can remove this once ST handles multiple casings of the same type.
  implicit def builder2VdomElement(
    builder: SharedBuilder_ReactDatePickerProps_1216478921[
      lucuma.reactDatepicker.mod.ReactDatePicker[_],
      _
    ]
  ): VdomElement =
    builder.build
}
