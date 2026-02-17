// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.demo

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.pragmaticdnd.*
import lucuma.react.pragmaticdnd.hitbox.AttachClosestEdgeArgs
import lucuma.react.pragmaticdnd.hitbox.ClosestEdgeRaw
import lucuma.react.pragmaticdnd.hitbox.Edge
import lucuma.react.syntax.*
import lucuma.react.table.*
import org.scalajs.dom.HTMLElement

import scalajs.js
import japgolly.scalajs.react.feature.Context
import cats.Eq

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

  type Source[D] = (data: D, height: Int)
  type Target[D] = (data: D, edge: Edge)

  final case class RowDraggingInfo[D](isDragging: Option[D], isDraggingOver: Option[Target[D]])

  final case class UseDragAndDropTable[D, T, TM, CM, TF](
    rowMod:     (Row[T, TM, CM, TF], Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]) => (
      (Row[T, TM, CM, TF], RowDraggingInfo[D]) => TagMod
    ) => VdomNode,
    cellMod:    (
      Cell[T, Any, TM, CM, TF, Any, Any],
      Option[Ref.ToVdom[HTMLElement]],
      TagOf[HTMLElement]
    ) => ((Cell[T, Any, TM, CM, TF, Any, Any], RowDraggingInfo[D]) => TagMod) => VdomNode,
    getData:    Row[T, TM, CM, TF] => Data[D],
    dndContext: Context.Provided[DragAndDropContext]
  )

  def useDragAndDropTable[D: Eq, T, TM, CM, TF](
    getData: Row[T, TM, CM, TF] => D,
    onDrop:  (D, Option[Target[D]]) => Callback = (_: D, _: Option[Target[D]]) => Callback.empty
    // rowTagMod:  => TagMod = TagMod.empty, // TODO! Integrate
    // cellTagMod: => TagMod = TagMod.empty  // TODO! Integrate
  ): HookResult[UseDragAndDropTable[D, T, TM, CM, TF]] =
    for
      // preDrag    <- useState[Option[Int]](none)
      dragging   <- useState[Option[Source[D]]](none)
      dragOver   <- useState[Option[Target[D]]](none)
      dndContext <- useDragAndDropContext[D, D](
                      onDrag = payload =>
                        val data: Option[Data[D]] =
                          payload.location.current.dropTargets.headOption.map(_.data)
                        val edge: Option[Edge]    =
                          data.flatMap(ClosestEdgeRaw.extractClosestEdge(_).toOption)
                        dragOver.setState((data.map(_.value), edge).tupled)
                      ,
                      onDrop = payload =>
                        val sourceData: D                     = payload.source.data.value
                        val targetData: Option[Data[D]]       =
                          payload.location.current.dropTargets.headOption.map(_.data)
                        val targetDataEdge: Option[Target[D]] =
                          (targetData.map(_.value),
                           targetData.flatMap(ClosestEdgeRaw.extractClosestEdge(_).toOption)
                          ).tupled

                        // preDrag.setState(none) >>
                        dragging.setState(none) >>
                          dragOver.setState(none) >>
                          onDrop(sourceData, targetDataEdge)
                    )
    yield
      val draggingInfo: RowDraggingInfo[D] =
        RowDraggingInfo(dragging.value.map(_.data), dragOver.value)

      val rowMod =
        (row: Row[T, TM, CM, TF], render: Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]) =>
          (tagMod: (Row[T, TM, CM, TF], RowDraggingInfo[D]) => TagMod) =>
            val rowData: D = getData(row)
            dragging.value match
              case Some((data, height)) if data === rowData =>
                <.tr((^.height := s"${height}px").when(dragOver.value.isEmpty)): VdomNode
              case _                                        =>
                DraggableDropTargetWithHandle(
                  handleRef => render(Some(handleRef))(tagMod(row, draggingInfo)),
                  // ^.boxShadow := "inset 0 -2px green"
                  // .unless(preDrag.value.contains_(row.original.id))
                  // .unless(preDrag.value.isDefined)
                  // ),
                  getInitialData = _ => Data(rowData),
                  getData = args =>
                    ClosestEdgeRaw.attachClosestEdge(
                      Data(rowData),
                      AttachClosestEdgeArgs(args.element, args.input, Edge.Vertical)
                    ),
                  onDraggableDragStart = payload =>
                    dragging.setState(
                      (payload.source.data.value,
                       payload.source.element.getBoundingClientRect().height.toInt
                      ).some
                    ),
                  onDraggableDrop = _ => dragging.setState(none)
                ): VdomNode

      val cellMod = (
        cell:    Cell[T, Any, TM, CM, TF, Any, Any],
        context: Option[Ref.ToVdom[HTMLElement]],
        render:  TagOf[HTMLElement]
      ) =>
        (tagMod: (Cell[T, Any, TM, CM, TF, Any, Any], RowDraggingInfo[D]) => TagMod) =>
          val rowData: D = getData(cell.row)

          context
            .filter(_ => cell.column.id.value == "handle") // TODO Cursor, with grab
            .map(handleRef =>
              render.withRef(handleRef)(
                // ^.onMouseDown --> preDrag.setState(cell.row.original.id.some),
                // ^.onMouseUp --> preDrag.setState(none)
              )
            )
            .getOrElse(render)(
              (dragOver.value, dragging.value) match
                case (Some((data, Edge.Top)), Some((sData, height)))
                    if data === rowData && data =!= sData => // padding color?
                  TagMod(^.paddingTop := s"${height}px", ^.transitionDuration := "0.1s")
                case (Some((data, Edge.Bottom)), Some((sData, height)))
                    if data === rowData && data =!= sData =>
                  TagMod(^.paddingBottom := s"${height}px", ^.transitionDuration := "0.1s")
                case _ => TagMod.empty
            )(tagMod(cell, draggingInfo))

      UseDragAndDropTable[D, T, TM, CM, TF](
        rowMod,
        cellMod,
        null,
        dndContext
      )

  val component =
    ScalaFnComponent[List[Guitar]]: guitars =>
      for
        rows             <- useMemo(guitars)(identity)
        preDrag          <- useState[Option[Int]](none)
        // dragging   <- useState[Option[(Int, Int)]](none)
        // dragOver   <- useState[Option[(Int, Edge)]](none)
        // dndContext <-
        //   useDragAndDropContext[Int, Int](
        //     onDrag = payload =>
        //       val data = payload.location.current.dropTargets.headOption.map(_.data)
        //       val edge = data.flatMap(ClosestEdgeRaw.extractClosestEdge(_).toOption)
        //       dragOver.setState((data.map(_.value), edge).tupled)
        //     ,
        //     onDrop = payload =>
        //       preDrag.setState(none) >>
        //         dragging.setState(none) >>
        //         dragOver.setState(none) >>
        //         Callback.log:
        //           s"Dropped ${payload.source.data.value} on: ${payload.location.current.dropTargets.headOption.map(_.data.value)}"
        //   )
        dragAndDropTable <-
          useDragAndDropTable[Int, Guitar, Nothing, Nothing, Nothing](
            getData = _.original.id,
            onDrop = (sourceData, target) =>
              Callback.log:
                s"Dropped ${sourceData} on: ${target.map(_.data)} - edge: ${target.map(_.edge)}"
          )
        table            <-
          useReactTable:
            TableOptions(Columns, rows, enableColumnResizing = true)
      yield dragAndDropTable.dndContext(
        <.h2("Drag and drop table"),
        HTMLTable(
          table,
          Css("guitars"), // In next line, specifying types binds row context type RC
          rowMod =
            // c, render: Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]) =>
            // dragAndDropTable.rowMod(c, render)
            dragAndDropTable.rowMod(_, _)((_, _) => ^.boxShadow := "inset 0 -2px green"),
          // (row, render: Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]) =>
          // dragging.value match
          //   case Some((id, height)) if id === row.original.id =>
          //     <.tr((^.height := s"${height}px").when(dragOver.value.isEmpty))
          //   case _                                            =>
          //     DraggableDropTargetWithHandle(
          //       handleRef =>
          //         render(Some(handleRef))(
          //           (^.boxShadow := "inset 0 -2px green")
          //             .unless(preDrag.value.contains_(row.original.id))
          //             // .unless(preDrag.value.isDefined)
          //         ),
          //       getInitialData = _ => row.original.id,
          //       getData = args =>
          //         ClosestEdgeRaw.attachClosestEdge(
          //           Data(row.original.id),
          //           AttachClosestEdgeArgs(args.element, args.input, Edge.Vertical)
          //         ),
          //       // onDraggableGenerateDragPreview = payload =>
          //       //   Callback.log(
          //       //     s"Generating preview for ${payload.source.data.value} - boxshadow: ${payload.source.element.style.boxShadow}"
          //       //   ) >>
          //       //     // Callback(payload.source.element.style.setProperty("boxShadow", "none")) >>
          //       //     Callback {
          //       //       println(payload.source.element.style.boxShadow)
          //       //       payload.source.element.style.boxShadow = "none"
          //       //       println(payload.source.element.style.boxShadow)
          //       //       // payload.source.element =
          //       //       //   org.scalajs.dom.document.createElement("div").asInstanceOf[HTMLElement]
          //       //     } >>
          //       //     Callback.log(
          //       //       s"Generated preview for ${payload.source.data.value} - boxshadow: ${payload.source.element.style.boxShadow}"
          //       //     ),
          //       onDraggableDragStart = payload =>
          //         dragging.setState(
          //           (payload.source.data.value,
          //            payload.source.element.getBoundingClientRect().height.toInt
          //           ).some
          //         ),
          //       onDraggableDrop = _ => dragging.setState(none)
          //     )
          cellMod = dragAndDropTable.cellMod(_, _, _)((_, _) => TagMod.empty)
          // (cell, context, render) =>
          // context
          //   .filter(_ => cell.column.id.value == "handle") // TODO Cursor, with grab
          //   .map(handleRef =>
          //     render.withRef(handleRef)(
          //       ^.onMouseDown --> preDrag.setState(cell.row.original.id.some),
          //       ^.onMouseUp --> preDrag.setState(none)
          //     )
          //   )
          //   .getOrElse(render)(
          //     (dragOver.value, dragging.value) match
          //       case (Some((id, Edge.Top)), Some(sid, height))
          //           if id == cell.row.original.id && id =!= sid => // padding color?
          //         TagMod(^.paddingTop := s"${height}px", ^.transitionDuration := "0.1s")
          //       case (Some((id, Edge.Bottom)), Some(sid, height))
          //           if id == cell.row.original.id && id =!= sid =>
          //         TagMod(^.paddingBottom := s"${height}px", ^.transitionDuration := "0.1s")
          //       case _ => TagMod.empty
          //   )
        )
      )
