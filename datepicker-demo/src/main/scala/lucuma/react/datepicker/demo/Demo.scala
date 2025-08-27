// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.datepicker.demo

import japgolly.scalajs.react.*
import lucuma.react.datepicker.*
import org.scalajs.dom

import java.time.Instant
import scala.scalajs.js

import js.annotation.*

@JSExportTopLevel("Demo")
object Demo {
  case class State(date: Option[Instant])
  object State {
    def now(): State = State(Some(Instant.now))
  }

  val component =
    ScalaComponent
      .builder[Unit]
      .initialState(State.now())
      .render($ =>
        Datepicker(
          onChange = newValue =>
            val optInstant = newValue.map(_.fromDatePickerJsDate)
            Callback(println(optInstant)) >>
              $.setState(State(optInstant))
          ,
          selected = $.state.date.map(_.toDatePickerJsDate),
          dateFormat = "yyyy-MM-dd"
        )
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

    ReactDOMClient.createRoot(container).render(component())
  }

}
