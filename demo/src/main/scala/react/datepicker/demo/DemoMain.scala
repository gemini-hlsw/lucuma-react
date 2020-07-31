package react.datepicker.demo

import org.scalajs.dom
import scala.scalajs.js
import js.|
import js.annotation._
import gpp.reactDatepicker.components.ReactDatepicker
import japgolly.scalajs.react._
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneOffset

@JSExportTopLevel("DemoMain")
object DemoMain {
  var dateValue: Option[LocalDate] = Some(
    LocalDate.from(Instant.now.atZone(ZoneOffset.UTC))
  )

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
        jsDate.getMonth().toInt + 1,
        jsDate.getDate().toInt
      )
  }

  implicit class JSUndefOrNullOps[A](val undefOrNull: js.UndefOr[A | Null])
      extends AnyVal {
    def toOpt: Option[A] =
      undefOrNull.toOption.flatMap((valueOrNull: A | Null) =>
        Option(valueOrNull.asInstanceOf[A])
      )
  }

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    val datepicker =
      ReactDatepicker(onChange =
        (newValue, _) =>
          Callback {
            val newDateValue = newValue.toOpt.map(LocalDateBuilder.fromJsDate)
            println(newDateValue)
            DemoMain.dateValue = newDateValue
          }
      )
        .selected(dateValue.map(_.toJsDate).orNull)
        .dateFormat("yyyy-MM-dd")

    datepicker.renderIntoDOM(container)

    ()
  }

}
