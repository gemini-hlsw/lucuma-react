// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.hotkeys.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.hotkeys.*
import lucuma.react.hotkeys.hooks.*
import org.scalajs.dom

import scalajs.js
import js.annotation.*

@JSExportTopLevel("Demo")
object DemoMain {
  @JSExport
  def main(): Unit = {

    val App =
      ScalaFnComponent
        .withHooks[Unit]
        .useGlobalHotkeys(UseHotkeysProps(List("y", "p"), event => Callback.log("got it")))
        .render(_ => <.div("Hotkeys"))

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    ReactDOMClient.createRoot(container).render(App())

  }
}
