// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import js.annotation._

@JSExportTopLevel("Demo")
object Demo:

  // def logit(message: String, obj: Any) = dom.console.log(message, obj.asInstanceOf[js.Any])

  val guitars =
    List(
      Guitar(1, "Fender", "Stratocaster", Details(2019, 3, "Sunburst")),
      Guitar(2, "Gibson", "Les Paul", Details(1958, 2, "Gold top")),
      Guitar(3, "Fender", "Telecaster", Details(1971, 2, "Ivory")),
      Guitar(4, "Godin", "LG", Details(2008, 2, "Burgundy"))
    )

  val randomData = RandomData.randomPeople(1000)

  val randomExpandableData = RandomData.randomExpandablePeople(1000)

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    <.div(
      <.h1("Demo for scalajs-tanstack-table"),
      Table1.component(guitars),
      Table2.component(randomData),
      Table3.component(randomExpandableData)
    )
      .renderIntoDOM(container)

    ()
  }
