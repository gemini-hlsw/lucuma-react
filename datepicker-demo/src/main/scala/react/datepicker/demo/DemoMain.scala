package react.datepicker.demo

import org.scalajs.dom
import scala.scalajs.js
import js.annotation._
import react.datepicker._
import japgolly.scalajs.react._
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneOffset

@JSExportTopLevel("DemoMain")
object DemoMain {
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
        Datepicker(onChange =
          (newValue, _) =>
            Callback(println(newValue.toLocalDateOpt)) >>
              $.setState(State(newValue.toLocalDateOpt))
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
