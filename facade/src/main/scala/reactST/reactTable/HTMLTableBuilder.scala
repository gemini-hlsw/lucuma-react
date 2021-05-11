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
import reactST.std.Partial

import scalajs.js
import scalajs.js.|
import scalajs.js.JSConverters._
import TableMaker.LayoutMarker
import reactTableStrings._

object HTMLTableBuilder {

  /**
   * Create a table component based on the configured plugins.
   *
   * @param options The table options to use for the table.
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
   * The typescript definitions don't seem to include the react-table
   *   support for footers. So, they aren't available here, either.
   *   So, table footers are fully "Build It Yourself". This actually
   *   has some advantages, because it is often useful for table footers
   *   to span multiple columns and react-table does not support that.
   *   At some point, something similar for an "extra" header row might
   *   be useful since header groups have some issues.
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
    options:      TableOptsD,
    headerCellFn: Option[ColumnObjectD => TagMod],
    tableClass:   Css = Css(""),
    rowClassFn:   (Int, D) => Css = (_: Int, _: D) => Css(""),
    footer:       TagMod = TagMod.empty
  ) =
    ScalaFnComponent[js.Array[D]] { data =>
      val tableInstance = tableMaker.use(options.setData(data.toJSArray))
      val bodyProps     = tableInstance.getTableBodyProps()

      val header = headerCellFn.fold(TagMod.empty) { f =>
        <.thead(
          tableInstance.headerGroups.toTagMod { g =>
            <.tr(
              TableMaker.props2Attrs(g.getHeaderGroupProps()),
              TableMaker.headersFromGroup(g).toTagMod(f(_))
            )
          }
        )
      }

      val rows = tableInstance.rows.toTagMod { rd =>
        tableInstance.prepareRow(rd)
        val rowClass = rowClassFn(rd.index.toInt, rd.original)
        val cells    = rd.cells.toTagMod { cell =>
          <.td(TableMaker.props2Attrs(cell.getCellProps()), cell.renderCell)
        }
        <.tr(rowClass, TableMaker.props2Attrs(rd.getRowProps()), cells)
      }

      <.table(tableClass, header, <.tbody(TableMaker.props2Attrs(bodyProps), rows), footer)
    }

  /**
   * Create a virtualized table based on the configured plugins.
   * The plugins MUST include withBlockLayout.
   *
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
  def makeVirtualizedTable[D,
    TableOptsD <: UseTableOptions[D], 
    TableInstanceD <: TableInstance[D], 
    ColumnOptsD <: ColumnOptions[D], 
    ColumnObjectD <: ColumnObject[D], 
    State <: TableState[D] // format: on
  ](
    tableMaker:        TableMaker[D, TableOptsD, TableInstanceD, ColumnOptsD, ColumnObjectD, State],
    options:           TableOptsD,
    bodyHeight:        Option[Double] = None,
    headerCellFn:      Option[ColumnObjectD => TagMod],
    tableClass:        Css = Css(""),
    rowClassFn:        (Int, D) => Css = (_: Int, _: D) => Css("")
  )(implicit evidence: TableOptsD <:< LayoutMarker) =
    ScalaFnComponent[js.Array[D]] { data =>
      val tableInstance = tableMaker.use(options.setData(data.toJSArray))
      val bodyProps     = tableInstance.getTableBodyProps()

      val rowComp = (_: Int, row: Row[D]) => {
        tableInstance.prepareRow(row)
        val cells = row.cells.toTagMod { cell =>
          <.div(^.className := "td", TableMaker.props2Attrs(cell.getCellProps()), cell.renderCell)
        }

        val rowClass = rowClassFn(row.index.toInt, row.original)
        // This div is being wrapped inside the div that handles virtualization.
        // This means the the `getRowProps` are nested an extra layer in. This does
        // not seem to cause any issues with react-table, but could possibly be an
        // issue with some plugins.
        <.div(^.className := "tr", rowClass, TableMaker.props2Attrs(row.getRowProps()), cells)
      }

      val header = headerCellFn.fold(TagMod.empty) { f =>
        <.div(
          ^.className := "thead",
          tableInstance.headerGroups.toTagMod { g =>
            <.div(^.className := "tr",
                  TableMaker.props2Attrs(g.getHeaderGroupProps()),
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
            <.div(^.className := "tbody", height, TableMaker.props2Attrs(bodyProps), rows)
      )
    }

  /**
   * A function to use in TableMaker.makeTable for the
   * headerCellFn parameter. This is a a basic header.
   *
   * @param cellClass An optional CSS class to apply the the <th>.
   * @param useDiv True to use a <div> instead of a <th>. Needed for tables withBlockLayout.
   */
  def basicHeaderCellFn(
    cellClass: Css = Css.Empty,
    useDiv:    Boolean = false
  ): ColumnObject[_] => TagMod =
    col =>
      headerCell(useDiv)(TableMaker.props2Attrs(col.getHeaderProps()), cellClass, col.renderHeader)

  /**
   * A function to use in TableMaker.makeTable for the headerCellFn
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

      // if, for example, the table also has block layout, both properties objs will have styles.
      val styles = combineStyles(headerProps, toggleProps)

      headerCell(useDiv)(
        TableMaker.props2Attrs(headerProps),
        TableMaker.props2Attrs(toggleProps),
        styles,
        cellClass,
        col.renderHeader,
        <.span(sortIndicator(col))
      )
    }

  /**
   * A function to use in TableMaker.makeTable for the
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
        TableMaker.props2Attrs(col.getFooterProps()),
        cellClass,
        col.renderFooter
      )
    )
  }

  private def headerCell(useDiv: Boolean) = if (useDiv) <.div(^.className := "th") else <.th()

  /**
   * Merge the styles from properties objects returned by react-table.
   * If we just use the properties objects directly, and more than one have "style",
   * only the last one gets used. Apparently, scalajs-react doesn't merge them. So, we have to combine them manually.
   * In the case of identical style keys, the last one wins. This is probably why scalajs-react doesn't merge.
   *
   * Styles are only combined if more than one propertyObjs have styles. Otherwise, the props2Attrs
   * method works fine and combining is a waste of time. If one or fewer propertyObjs have styles,
   * TagMod.empty is returned. See sortableHeaderCellFn for example usage.
   *
   * @param propertyObs A varargs of "properties objects" from react-table.
   * @return A TagMod.
   */
  def combineStyles(propertyObs: js.Object*): TagMod = {
    val styles = propertyObs.collect {
      case o if o.hasOwnProperty("style") =>
        js.Object.getOwnPropertyDescriptor(o, "style").value.asInstanceOf[js.Dynamic]
    }
    if (styles.length < 2) TagMod.empty
    else ^.style := mergeJSObjects(styles: _*)
  }

  // taken from https://stackoverflow.com/questions/36561209/is-it-possible-to-combine-two-js-dynamic-objects
  private def mergeJSObjects(objs: js.Dynamic*): js.Object = {
    val result = js.Dictionary.empty[Any]
    for (source <- objs)
      for ((key, value) <- source.asInstanceOf[js.Dictionary[Any]])
        result(key) = value
    result.asInstanceOf[js.Object]
  }
}
