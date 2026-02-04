// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.pragmaticdnd.*
import monocle.Focus
import monocle.Lens
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

    val DraggableStyle = TagMod(
      ^.width           := "100px",
      ^.height          := "50px",
      ^.margin          := "10px",
      ^.padding         := "10px",
      ^.backgroundColor := "#4CAF50",
      ^.color           := "white",
      ^.textAlign       := "center",
      ^.cursor          := "move"
    )

    val DropTargetStyle = TagMod(
      ^.width           := "220px",
      ^.height          := "100px",
      ^.margin          := "10px",
      ^.padding         := "10px",
      ^.backgroundColor := "#f1f1f1",
      ^.color           := "#333",
      ^.textAlign       := "center",
      ^.border          := "2px dashed #ccc"
    )

    type StateType = (Option[Int], Option[Int])
    type LensType  = Lens[StateType, Option[Int]]

    val first: LensType  = Focus[(Option[Int], Option[Int])](_._1)
    val second: LensType = Focus[(Option[Int], Option[Int])](_._2)

    val App = ScalaFnComponent[Unit]: _ =>
      for
        dropped <- useState[StateType]((None, None))
        _       <- useDragAndDropMonitor[Int, LensType](
                     onDrop = payload =>
                       dropped.modState { current =>
                         val lens = payload.location.current.dropTargets(0).data
                         lens.replace(Some(payload.source.data))(current)
                       }
                   )
      yield <.div(
        <.div(DraggableStyle, "DRAG ME").draggable(getInitialData = _ => CallbackTo(1)),
        <.div(DraggableStyle, "OR ME").draggable(getInitialData = _ => CallbackTo(2)),
        <.div(DropTargetStyle)(
          "DROP HERE",
          <.br,
          dropped.value._1.map(i => s"You dropped #$i HERE!")
        ).dropTarget(getData = _ => CallbackTo(first)),
        <.div(DropTargetStyle)("OR HERE", <.br, dropped.value._2.map(i => s"You dropped #$i HERE!"))
          .dropTarget(getData = _ => CallbackTo(second))
      )

    ReactDOMClient
      .createRoot(container)
      .render(App())
  }
