// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.demo

import cats.Eq
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.feature.Context
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

import scala.annotation.unused

import scalajs.js

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

  final case class UseTableDragAndDrop[D, T, TM, CM, TF](
    rowMod:  ((Row[T, TM, CM, TF], RowDraggingInfo[D]) => TagMod) => (
      Row[T, TM, CM, TF],
      Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]
    ) => VdomNode,
    cellMod: ((Cell[T, Any, TM, CM, TF, Any, Any], RowDraggingInfo[D]) => TagMod) => (
      Cell[T, Any, TM, CM, TF, Any, Any],
      Option[Ref.ToVdom[HTMLElement]],
      TagOf[HTMLElement]
    ) => VdomNode,
    context: Context.Provided[DragAndDropContext]
  )

  // Auto apply with TagMod.empty if no parameters specified
  given autoApplyRowMod[D, T, TM, CM, TF]: Conversion[
    ((Row[T, TM, CM, TF], RowDraggingInfo[D]) => TagMod) => (
      Row[T, TM, CM, TF],
      Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]
    ) => VdomNode,
    (Row[T, TM, CM, TF], Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]) => VdomNode
  ] with
    def apply(
      rowMod: ((Row[T, TM, CM, TF], RowDraggingInfo[D]) => TagMod) => (
        Row[T, TM, CM, TF],
        Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]
      ) => VdomNode
    ): (Row[T, TM, CM, TF], Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]) => VdomNode =
      rowMod((_, _) => TagMod.empty)

  given autoApplyCellMod[D, T, TM, CM, TF]: Conversion[
    ((Cell[T, Any, TM, CM, TF, Any, Any], RowDraggingInfo[D]) => TagMod) => (
      Cell[T, Any, TM, CM, TF, Any, Any],
      Option[Ref.ToVdom[HTMLElement]],
      TagOf[HTMLElement]
    ) => VdomNode,
    (
      Cell[T, Any, TM, CM, TF, Any, Any],
      Option[Ref.ToVdom[HTMLElement]],
      TagOf[HTMLElement]
    ) => VdomNode
  ] with
    def apply(
      cellMod: ((Cell[T, Any, TM, CM, TF, Any, Any], RowDraggingInfo[D]) => TagMod) => (
        Cell[T, Any, TM, CM, TF, Any, Any],
        Option[Ref.ToVdom[HTMLElement]],
        TagOf[HTMLElement]
      ) => VdomNode
    ): (
      Cell[T, Any, TM, CM, TF, Any, Any],
      Option[Ref.ToVdom[HTMLElement]],
      TagOf[HTMLElement]
    ) => VdomNode =
      cellMod((_, _) => TagMod.empty)

  def useTableDragAndDrop[D: Eq, T, TM, CM, TF](
    @unused table: Table[T, TM, CM, TF], // Not used, just to infer type parameters.
    getData:       Row[T, TM, CM, TF] => D,
    onDrop:        (D, Option[Target[D]]) => Callback = (_: D, _: Option[Target[D]]) => Callback.empty
  ): HookResult[UseTableDragAndDrop[D, T, TM, CM, TF]] =
    for
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
        (tagMod: (Row[T, TM, CM, TF], RowDraggingInfo[D]) => TagMod) =>
          (
            row:    Row[T, TM, CM, TF],
            render: Option[Ref.ToVdom[HTMLElement]] => TagOf[HTMLElement]
          ) =>
            val rowData: D = getData(row)
            dragging.value match
              case Some((data, height)) if data === rowData =>
                <.tr((^.height := s"${height}px").when(dragOver.value.isEmpty)): VdomNode
              case _                                        =>
                DraggableDropTargetWithHandle(
                  handleRef => render(Some(handleRef))(tagMod(row, draggingInfo)),
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

      val cellMod =
        (tagMod: (Cell[T, Any, TM, CM, TF, Any, Any], RowDraggingInfo[D]) => TagMod) =>
          (
            cell:    Cell[T, Any, TM, CM, TF, Any, Any],
            context: Option[Ref.ToVdom[HTMLElement]],
            render:  TagOf[HTMLElement]
          ) =>
            val rowData: D = getData(cell.row)

            context
              .filter(_ => cell.column.id.value == "handle") // TODO Cursor, with grab
              .map(handleRef => render.withRef(handleRef))
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

      UseTableDragAndDrop[D, T, TM, CM, TF](rowMod, cellMod, dndContext)

  val component =
    ScalaFnComponent[List[Guitar]]: guitars =>
      for
        rows     <- useMemo(guitars)(identity)
        table    <- useReactTable:
                      TableOptions(Columns, rows, enableColumnResizing = true)
        tableDnd <- useTableDragAndDrop(
                      table,
                      getData = _.original.id,
                      onDrop = (sourceData, target) =>
                        Callback.log:
                          s"Dropped $sourceData on: $target"
                    )
      yield tableDnd.context(
        <.h2("Drag and drop table"),
        HTMLTable(
          table,
          Css("guitars"),
          rowMod = tableDnd.rowMod((_, _) => ^.boxShadow := "inset 0 -2px green"),
          cellMod = tableDnd.cellMod
        )
      )
