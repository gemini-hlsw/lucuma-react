// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.demo

import japgolly.scalajs.react.ReactDOMClient
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom

import scala.scalajs.js

import js.annotation.*

@JSExportTopLevel("Demo")
object Demo:

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    ReactDOMClient
      .createRoot(container)
      .render(
        <.div(
          <.h1("Demo for lucuma-prime-react"),
          DemoComponents.component(),
          DemoHooks.component()
        )
      )
  }
