// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.table.*
import lucuma.react.syntax.*
import lucuma.react.pragmaticdnd.*
import org.scalajs.dom.HTMLElement

import scalajs.js
import japgolly.scalajs.react.vdom.TagOf

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
        dndContext <-
          useDragAndDropContext[Int, Int](
            onDrop = payload =>
              Callback.log(
                s"Dropped ${payload.source.data} on ${payload.location.current.dropTargets.headOption.map(_.data).getOrElse("nothing")}"
              )
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
            React.Fragment(
              DraggableDropTargetWithHandle(
                handleRef => render(Some(handleRef)),
                getInitialData = _ => row.original.id,
                getData = _ => row.original.id
              )
            ),
          cellMod = (cell, context, render) =>
            context
              .filter(_ => cell.column.id.value == "handle")
              .map(handleRef => render.withRef(handleRef))
              .getOrElse(render)
        )
      )
