// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.demo

import cats.syntax.option.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.table.*

import scala.util.Try

import scalajs.js
import scalajs.js.JSConverters.*

object Table1:
  private val ColDef = ColumnDef[Guitar].WithGlobalFilter[String]

  private val Columns =
    Reusable.always:
      List(
        ColDef(
          ColumnId("id"),
          _.id,
          "Id",
          ctx => s"g-${ctx.value}",
          enableColumnFilter = false
        ).sortable,
        ColDef(ColumnId("make"), _.make, _ => "Make", filterFn = BuiltInFilter.IncludesString),
        ColDef(ColumnId("model"), _.model, _ => "Model", filterFn = BuiltInFilter.IncludesString)
          .sortableBy(_.length),
        ColDef.group(
          ColumnId("details"),
          _ => <.div(^.textAlign.center)("Details"),
          List(
            ColDef(
              ColumnId("year"),
              _.details.year,
              _ => "Year",
              filterFn = BuiltInFilter.InNumberRange
            ),
            ColDef(
              ColumnId("pickups"),
              _.details.pickups,
              _ => "Pickups",
              filterFn = (
                row,
                colId,
                filterValue: Int,
                _
              ) => row.getValue[Int](colId) >= filterValue,
              footer = _.table.getRowModel().rows.map(_.original.details.pickups).sum
            ),
            ColDef(ColumnId("color"), _.details.color, _ => "Color", enableSorting = false)
          )
        )
      )

  private def filterRenderer(col: ColDef.ColType): VdomNode =
    col.id.value match
      case "pickups" =>
        <.input(
          ^.`type`      := "tel",
          ^.placeholder := "At least",
          ^.width       := "100%",
          ^.value       := col
            .getFilterValue()
            .orElse(col.getFacetedMinMaxValues().map(_._1))
            .map(_.toString)
            .getOrElse(""),
          ^.onChange ==> ((e: ReactEventFromInput) =>
            col.setFilterValue(Try(e.target.value.toInt).toOption)
          )
        )
      case "year"    =>
        val value: js.Tuple2[js.UndefOr[Int], js.UndefOr[Int]] =
          col
            .getFilterValue()
            .map(_.asInstanceOf[js.Tuple2[js.UndefOr[Int], js.UndefOr[Int]]])
            .orElse(col.getFacetedMinMaxValues().map((min, max) => js.Tuple2(min.toInt, max.toInt)))
            .getOrElse(js.Tuple2(js.undefined, js.undefined))
        <.div(^.display.flex)(
          <.input(
            ^.`type`      := "tel",
            ^.placeholder := "From",
            ^.width       := "100%",
            ^.value       := value._1.map(_.toString).getOrElse(""),
            ^.onChange ==> ((e: ReactEventFromInput) =>
              col.setFilterValue:
                js.Tuple2(Try(e.target.value.toDouble).toOption.orUndefined, value._2).some
            )
          ),
          <.input(
            ^.`type`      := "tel",
            ^.placeholder := "To",
            ^.width       := "100%",
            ^.value       := value._2.map(_.toString).getOrElse(""),
            ^.onChange ==> ((e: ReactEventFromInput) =>
              col.setFilterValue:
                js.Tuple2(value._1, Try(e.target.value.toDouble).toOption.orUndefined).some
            )
          )
        )
      case "model"   =>
        <.input(
          ^.`type`      := "text",
          ^.placeholder := "Filter",
          ^.width       := "100%",
          ^.value       := col.getFilterValue().map(_.toString).getOrElse(""),
          ^.onChange ==> ((e: ReactEventFromInput) =>
            col.setFilterValue(e.target.value.some.filter(_.nonEmpty))
          )
        )
      case _         =>
        <.span(^.display.flex)(
          <.select(
            ^.`type`      := "text",
            ^.placeholder := "Filter",
            ^.width       := "100%",
            ^.value       := col.getFilterValue().map(_.toString).getOrElse(""),
            ^.onChange ==> ((e: ReactEventFromInput) =>
              col.setFilterValue(e.target.value.some.filter(_.nonEmpty))
            )
          )(
            <.option(^.value := "")("<all>"),
            col
              .getFacetedUniqueValues()
              .map: (value, count) =>
                <.option(^.value := value.toString)(s"${value.toString} ($count)")
              .toTagMod
          ),
          <.span(^.onClick --> col.setFilterValue(none))("X")
        )

  val component =
    ScalaFnComponent[List[Guitar]]: guitars =>
      for
        rows         <- useMemo(guitars)(identity)
        globalFilter <- useState("")
        table        <-
          useReactTable:
            TableOptions(
              Columns,
              rows,
              enableSorting = true,
              enableColumnResizing = true,
              enableFilters = true,
              enableGlobalFilter = true,
              enableFacetedUniqueValues = true,
              enableFacetedMinMaxValues = true,
              globalFilterFn = (row, colId, value: String, _) =>
                row.getValue(colId).toString.toLowerCase.contains(value.toLowerCase),
              initialState =
                TableState(sorting = Sorting(ColumnId("model") -> SortDirection.Descending))
            )
      yield React.Fragment(
        <.h2("Sortable table"),
        <.input(
          ^.`type`      := "text",
          ^.placeholder := "Global Filter",
          ^.value       := table.getState().globalFilter.getOrElse(""),
          ^.onChange ==> ((e: ReactEventFromInput) => table.setGlobalFilter(e.target.value.some))
        ),
        HTMLTable(
          table,
          Css("guitars"),
          columnFilterRenderer = filterRenderer
        ),
        "Click header to sort. Shift-Click for multi-sort."
      )
