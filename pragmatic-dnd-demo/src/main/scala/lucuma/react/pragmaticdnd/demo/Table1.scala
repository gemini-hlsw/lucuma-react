// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.demo

import cats.syntax.option.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.table.*
import lucuma.react.syntax.*
import lucuma.react.pragmaticdnd.*
// import lucuma.react.pragmaticdnd.hitbox.*
import org.scalajs.dom.HTMLElement

import scalajs.js
import japgolly.scalajs.react.vdom.TagOf
import lucuma.react.pragmaticdnd.hitbox.ClosestEdgeRaw
import lucuma.react.pragmaticdnd.hitbox.AttachClosestEdgeArgs
import lucuma.react.pragmaticdnd.hitbox.Edge

object Table1:
  private val ColDef = ColumnDef[Guitar]

  private val Columns =
    Reusable.always:
      List(
        ColDef(ColumnId("handle"), cell = _ => <.span(^.fontSize.large, "≡")).withSize(20.toPx),
        ColDef(ColumnId("id"), _.id, "Id", ctx => s"g-${ctx.value}"),
        ColDef(ColumnId("make"), _.make, _ => "Make"),
        ColDef(ColumnId("model"), _.model, _ => "Model"),
        ColDef.group(
          ColumnId("details"),
          _ => <.div(^.textAlign.center)("Details"),
          List(
            ColDef(ColumnId("year"), _.details.year, _ => "Year"),
            ColDef(ColumnId("pickups"), _.details.pickups, _ => "Pickups"),
            ColDef(ColumnId("color"), _.details.color, _ => "Color")
          )
        )
      )

  val component =
    ScalaFnComponent[List[Guitar]]: guitars =>
      for
        rows       <- useMemo(guitars)(identity)
        dragging   <- useState[Option[Int]](none)
        dragOver   <- useState[Option[(Int, Edge)]](none)
        _          <- useEffect(Callback.log(s"DRAGGING: ${dragging.value} - DRAGOVER: ${dragOver.value}"))
        dndContext <-
          useDragAndDropContext[Int, Int](
            onDrop = payload =>
              dragging.setState(none) >>
                dragOver.setState(none) >>
                Callback.log:
                  s"Dropped ${payload.source.data.value} on: ${payload.location.current.dropTargets.headOption.map(_.data.value).get}"
          )
        table      <-
          useReactTable:
            TableOptions(Columns, rows, enableColumnResizing = true)
      yield dndContext(
        <.h2("Drag and drop table"),
        HTMLTable(
          table,
          Css("guitars"), // In next line, specifying types binds row context type RC
          rowMod = (row, render: Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]) =>
            if dragging.value.contains(row.original.id) then <.tr
            else
              DraggableDropTargetWithHandle(
                handleRef => render(Some(handleRef)),
                getInitialData = _ => row.original.id,
                getData = args =>
                  ClosestEdgeRaw.attachClosestEdge(
                    Data(row.original.id),
                    AttachClosestEdgeArgs(args.element, args.input, Edge.Vertical)
                  ),
                onDraggableDragStart = payload => dragging.setState(payload.source.data.value.some),
                onDraggableDrop = _ => dragging.setState(none),
                onDropTargetDrag = payload =>
                  val data = payload.location.current.dropTargets.headOption.map(_.data).get
                  val edge = ClosestEdgeRaw.extractClosestEdge(data)
                  dragOver.setState(edge.toOption.map((data, _)))
              )
          ,
          cellMod = (cell, context, render) =>
            context
              .filter(_ => cell.column.id.value == "handle")
              .map(handleRef => render.withRef(handleRef))
              .getOrElse(render)(
                dragOver.value match // TODO Transition. Translate instead of padding? Can we translate without border?
                  case Some((id, Edge.Top)) if id == cell.row.original.id    => ^.paddingTop := "20px"
                  case Some((id, Edge.Bottom)) if id == cell.row.original.id =>
                    ^.paddingBottom := "20px"
                  case _                                                     => TagMod.empty
              )
        )
      )
