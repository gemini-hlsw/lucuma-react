package reactST.reactTable

import japgolly.scalajs.react._
import japgolly.scalajs.react.internal.JsUtil
import japgolly.scalajs.react.raw.React.ComponentClassP
import japgolly.scalajs.react.vdom.html_<^._
import react.common.Css
import react.virtuoso.Virtuoso
import reactST.reactTable.anon.Data
import reactST.reactTable.mod.ColumnInterfaceBasedOnValue._
import reactST.reactTable.mod.{ ^ => _, _ }
import reactST.reactTable.syntax._
import reactST.reactTable.util._
import reactST.std.Partial

import scalajs.js
import scalajs.js.|
import scalajs.js.JSConverters._
import TableMaker.LayoutMarker
import reactTableStrings._

object HTMLTableBuilder {

  /**
   * Create a table component based on the configured plugins of a `TableMaker`.
   *
   * @param tableMaker The TableMaker providing the useTable hook.
   * @param headerCellFn A function to use for creating the <th> elements. Simple ones are provided by the TableMaker object.
   * @param tableClass An optional CSS class to apply to the table element
   * @param rowClassFn An option function that takes the index and the rowData and creates a class for the row. Relying on index does not work well for sortable tables since it is the unsorted index.
   * @param footer An optional <tfoot> element. (see note below)
   *
   * Notes:
   * If optimization is required, parts of the options and the data
   *   should be memoized and only change when necessary. See react-table
   *   documentation for details.
   * The headerCellFn is expected to return a <th> element. The function
   *   should make use of the column instance "props" methods.
   * Table footers are fully "Build It Yourself" since it is often useful
   *   for table footers to span multiple columns and react-table does
   *   not support that. At some point, something similar for an "extra"
   *   header row might be useful since header groups have some issues.
   */
  // format: off
  def buildComponent[D,
    TableOptsD <: UseTableOptions[D], 
    TableInstanceD <: TableInstance[D], 
    ColumnOptsD <: ColumnOptions[D], 
    ColumnObjectD <: ColumnObject[D], 
    State <: TableState[D] // format: on
  ](
    tableMaker:   TableMaker[D, TableOptsD, TableInstanceD, ColumnOptsD, ColumnObjectD, State],
    headerCellFn: Option[ColumnObjectD => TagMod],
    tableClass:   Css = Css(""),
    rowClassFn:   (Int, D) => Css = (_: Int, _: D) => Css(""),
    footer:       TagMod = TagMod.empty
  ) =
    ScalaFnComponent[TableOptsD] { options =>
      val tableInstance = tableMaker.use(options)
      val bodyProps     = tableInstance.getTableBodyProps()

      val header = headerCellFn.fold(TagMod.empty) { f =>
        <.thead(
          tableInstance.headerGroups.toTagMod { g =>
            <.tr(
              props2Attrs(g.getHeaderGroupProps()),
              TableMaker.headersFromGroup(g).toTagMod(f(_))
            )
          }
        )
      }

      val rows = tableInstance.rows.toTagMod { rd =>
        tableInstance.prepareRow(rd)
        val rowClass = rowClassFn(rd.index.toInt, rd.original)
        val cells    = rd.cells.toTagMod { cell =>
          <.td(props2Attrs(cell.getCellProps()), cell.renderCell)
        }
        <.tr(rowClass, props2Attrs(rd.getRowProps()), cells)
      }

      <.table(tableClass, header, <.tbody(props2Attrs(bodyProps), rows), footer)
    }

  /**
   * Create a virtualized table based on the configured plugins of a `TableMaker`.
   * The plugins MUST include withBlockLayout.
   *
   * @param tableMaker The TableMaker providing the useTable hook, configured with withBlockLayout.
   * @param options The table options to use for the table.
   * @param bodyHeight The optional height of the table body (virtualized portion) in px. See note.
   * @param headerCellFn See note. A function to use for creating the header elements. Simple ones are provided by the TableMaker object.
   * @param tableClass An optional CSS class to apply to the table element
   * @param rowClassFn An option function that takes the index and the rowData and creates a class for the row. Relying on index does not work well for sortable tables since it is the unsorted index.
   *
   * Notes:
   * Virtualized tables are not built from html table elements such as
   * <table>, <tr>, etc. The virtualization requires they be made from
   * <div> elements and styled as a table. This method adds a class to
   * each div that corresponds to it's role, such as "table", "thead",
   * "tr", etc. The "tr" div is currently wrapped inside an additional
   * div that handles the virtualization.
   * This means that headerCellFn must also return a <div> instead
   * of a <td>, and should probably also have a class of "td". The basic
   * header functions in TableMaker take a boolean flag to handle this.
   *
   * The body height must be set to a value either through the bodyHeight
   * parameter or via CSS or the body will collapse to nothing. In CSS, you
   * MUST NOT use relative values like "100%" or it won't work.
   */
  // format: off
  def buildComponentVirtualized[D,
    TableOptsD <: UseTableOptions[D], 
    TableInstanceD <: TableInstance[D], 
    ColumnOptsD <: ColumnOptions[D], 
    ColumnObjectD <: ColumnObject[D], 
    State <: TableState[D] // format: on
  ](
    tableMaker:        TableMaker[D, TableOptsD, TableInstanceD, ColumnOptsD, ColumnObjectD, State],
    bodyHeight:        Option[Double] = None,
    headerCellFn:      Option[ColumnObjectD => TagMod],
    tableClass:        Css = Css(""),
    rowClassFn:        (Int, D) => Css = (_: Int, _: D) => Css("")
  )(implicit evidence: TableOptsD <:< LayoutMarker) =
    ScalaFnComponent[TableOptsD] { options =>
      val tableInstance = tableMaker.use(options)
      val bodyProps     = tableInstance.getTableBodyProps()

      val rowComp = (_: Int, row: Row[D]) => {
        tableInstance.prepareRow(row)
        val cells = row.cells.toTagMod { cell =>
          <.div(^.className := "td", props2Attrs(cell.getCellProps()), cell.renderCell)
        }

        val rowClass = rowClassFn(row.index.toInt, row.original)
        // This div is being wrapped inside the div that handles virtualization.
        // This means the the `getRowProps` are nested an extra layer in. This does
        // not seem to cause any issues with react-table, but could possibly be an
        // issue with some plugins.
        <.div(^.className := "tr", rowClass, props2Attrs(row.getRowProps()), cells)
      }

      val header = headerCellFn.fold(TagMod.empty) { f =>
        <.div(
          ^.className := "thead",
          tableInstance.headerGroups.toTagMod { g =>
            <.div(^.className := "tr",
                  props2Attrs(g.getHeaderGroupProps()),
                  TableMaker.headersFromGroup(g).toTagMod(f(_))
            )
          }
        )
      }

      val height = bodyHeight.fold(TagMod.empty)(h => ^.height := s"${h}px")
      val rows   = Virtuoso[Row[D]](data = tableInstance.rows, itemContent = rowComp)

      <.div(^.className := "table",
            tableClass,
            header,
            <.div(^.className := "tbody", height, props2Attrs(bodyProps), rows)
      )
    }

  /**
   * A function to use in the builder methods for the
   * headerCellFn parameter. This is a a basic header.
   *
   * @param cellClass An optional CSS class to apply the the <th>.
   * @param useDiv True to use a <div> instead of a <th>. Needed for tables withBlockLayout.
   */
  def basicHeaderCellFn(
    cellClass: Css = Css.Empty,
    useDiv:    Boolean = false
  ): ColumnObject[_] => TagMod =
    col => headerCell(useDiv)(props2Attrs(col.getHeaderProps()), cellClass, col.renderHeader)

  /**
   * A function to use in the builder methods for the headerCellFn
   * parameter. This header will make the maker sortable by clicking
   * on the header. Will only compile if the column instance is a subtype
   * of UseSortByColumnPros.
   *
   * @param cellClass An optional CSS class to apply to the <th>.
   * @param useDiv True to use a <div> instead of a <th>. Needed for tables withBlockLayout.
   * @return
   */
  def sortableHeaderCellFn(
    cellClass: Css = Css.Empty,
    useDiv:    Boolean = false
  ): ColumnObject[_] with UseSortByColumnProps[_] => TagMod =
    col => {
      def sortIndicator(col: UseSortByColumnProps[_]): TagMod =
        if (col.isSorted) {
          val index   = if (col.sortedIndex > 0) (col.sortedIndex + 1).toString else ""
          val ascDesc = if (col.isSortedDesc.getOrElse(false)) "\u2191" else "\u2193"
          <.span(s" $index$ascDesc")
        } else TagMod.empty

      val headerProps = col.getHeaderProps()
      val toggleProps = col.getSortByToggleProps()

      headerCell(useDiv)(
        props2Attrs(headerProps),
        props2Attrs(toggleProps),
        cellClass,
        col.renderHeader,
        <.span(sortIndicator(col))
      )
    }

  /**
   * A function to use in the builder methods for the
   * footerCellFn parameter. This is a a basic footer.
   *
   * @param cellClass An optional CSS class to apply the the <th>.
   * @param useDiv True to use a <div> instead of a <th>. Needed for tables withBlockLayout.
   */
  def basicFooterCellFn(
    cellClass: Css = Css.Empty,
    useDiv:    Boolean = false
  ): ColumnObject[_] => TagMod = { col =>
    col.Footer.map(_ =>
      headerCell(useDiv)(
        props2Attrs(col.getFooterProps()),
        cellClass,
        col.renderFooter
      )
    )
  }

  private def headerCell(useDiv: Boolean) = if (useDiv) <.div(^.className := "th") else <.th()
}
