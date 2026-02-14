// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.demo

import cats.syntax.eq.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.pragmaticdnd.*
import lucuma.react.pragmaticdnd.facade.*
import monocle.Focus
import monocle.Lens
import org.scalajs.dom

import scala.scalajs.js

import js.annotation.*

@JSExportTopLevel("Demo")
object Demo:

  @JSExport
  def main(): Unit = {

    // ElementAdapterRaw.monitorForElements(
    //   MonitorArgs(onDrop =
    //     payload =>
    //       Callback.log:
    //         s"[MONITOR] Dropped ${payload.source.data.value} on: ${payload.location.current.dropTargets.headOption.map(_.data.value)}"
    //   )
    // )

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
        dropped    <- useState[StateType]((None, None))
        dndContext <- useDragAndDropContext[Int, LensType](
                        onDrop = payload =>
                          if payload.location.current.dropTargets.length === 0 then Callback.empty
                          else
                            dropped.modState: current =>
                              val lens = payload.location.current.dropTargets(0).data.value
                              lens.replace(Some(payload.source.data.value))(current)
                      )
      yield dndContext(
        <.div(
          <.div(DraggableStyle, "DRAG ME").draggable(getInitialData = _ => 1),
          <.div(DraggableStyle, "OR ME").draggable(getInitialData = _ => 2),
          <.div(DropTargetStyle)(
            "DROP HERE",
            <.br,
            dropped.value._1.map(i => s"You dropped #$i HERE!")
          ).dropTarget(getData = _ => first),
          <.div(DropTargetStyle)(
            "OR HERE",
            <.br,
            dropped.value._2.map(i => s"You dropped #$i HERE!")
          )
            .dropTarget(getData = _ => second)
        )
      )

    val guitars =
      List(
        Guitar(1, "Fender", "Stratocaster", Details(2019, 3, "Sunburst")),
        Guitar(2, "Gibson", "Les Paul", Details(1958, 2, "Gold top")),
        Guitar(3, "Fender", "Telecaster", Details(1971, 2, "Ivory")),
        Guitar(4, "Godin", "LG", Details(2008, 2, "Burgundy"))
      )

    ReactDOMClient
      .createRoot(container)
      .render:
        <.div(
          <.div(^.display.flex)(App(), App()),
          Table1.component(guitars)
        )
  }
