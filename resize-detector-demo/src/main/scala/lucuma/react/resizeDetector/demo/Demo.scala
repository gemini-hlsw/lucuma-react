// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.resizeDetector.demo
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
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
        React
          .StrictMode(
            <.div(
              Css("resize-detector-container"),
              ^.width  := "100vw",
              ^.height := "100vh",
              HookDemo()
            )
          )
      )
  }
