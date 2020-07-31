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
  }

  case class State(date: Option[LocalDate])
  object State {
    def now(): State =
      State(
        Some(
          LocalDate.from(Instant.now.atZone(ZoneOffset.UTC))
        )
      )
  }

  val component =
    ScalaComponent
      .builder[Unit]
      .initialState(State.now())
      .render($ =>
        ReactDatepicker(onChange =
          (newValue, _) =>
            $.setState(State(newValue.toOpt.map(LocalDateBuilder.fromJsDate)))
        )
          .selected($.state.date.map(_.toJsDate).orNull)
          .dateFormat("yyyy-MM-dd")
      )
      .build

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    component().renderIntoDOM(container)

    ()
  }

}
