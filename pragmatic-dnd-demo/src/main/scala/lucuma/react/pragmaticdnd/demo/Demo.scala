// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.pragmaticdnd.*
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

    val App = ScalaFnComponent[Unit]: _ =>
      for _ <- useDragAndDropMonitor(
                 onDragStart = payload =>
                   Callback.log(s"[Monitor] Drag started with data: ${payload.source.data}"),
                 onDrop = payload =>
                   Callback.log:
                     s"[Monitor] Dropped data: ${payload.source.data} into ${payload.location.current.dropTargets(0).data}"
               )
      yield <.div(
        <.div("DRAG ME").draggable(getInitialData = _ => CallbackTo("Hello, World!")),
        <.div("DROP HERE").dropTarget(
          getData = _ => CallbackTo(42),
          onDragStart = payload => Callback.log(s"Drag started with data: ${payload.source.data}"),
          onDragEnter = _ => Callback.log("Drag Entered Drop Target"),
          onDragLeave = _ => Callback.log("Drag Left Drop Target"),
          onDrop = payload =>
            Callback.log:
              s"Dropped data: ${payload.source.data} into ${payload.location.current.dropTargets(0).data}"
        )
      )

    ReactDOMClient
      .createRoot(container)
      .render(App())
  }
