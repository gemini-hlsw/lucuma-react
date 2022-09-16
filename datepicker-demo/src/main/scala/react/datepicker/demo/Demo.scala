// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.datepicker.demo

import japgolly.scalajs.react._
import org.scalajs.dom
import react.datepicker._
import reactST.reactDatepicker.components.ReactDatepicker

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import scala.scalajs.js

import js.annotation._

@JSExportTopLevel("Demo")
object Demo {
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
