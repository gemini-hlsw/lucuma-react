// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.virtual.*
import org.scalajs.dom.HTMLDivElement
import react.common.*
import react.common.style.Css
import reactST.{tanstackReactTable => rawReact}
import reactST.{tanstackTableCore => raw}

import scalajs.js

trait HTMLTableRenderer[T]:
  protected val TableClass: Css   = Css("react-table")
  protected val TheadClass: Css   = Css.Empty
  protected val TheadTrClass: Css = Css.Empty
  protected val TheadThClass: Css = Css.Empty
  protected val TbodyClass: Css   = Css.Empty
  protected val TbodyTrClass: Css = Css.Empty
  protected val TbodyTdClass: Css = Css.Empty
  protected val TfootClass: Css   = Css.Empty
  protected val TfootTrClass: Css = Css.Empty
  protected val TfootThClass: Css = Css.Empty

  protected val ResizerClass: Css         = Css("resizer")
  protected val IsResizingTHeadClass: Css = Css("isResizing")
  protected val IsResizingColClass: Css   = Css("isResizingCol")
  protected val ResizerContent: VdomNode  = EmptyVdom

  protected val SortableColClass: Css       = Css("sortableCol")
  protected val SortAscIndicator: VdomNode  = "↑"
  protected val SortDescIndicator: VdomNode = "↓"

  protected def sortIndicator[T](col: raw.mod.Column[T, ?]): VdomNode =
    col.getIsSorted().asInstanceOf[Boolean | String] match
      case sorted: String =>
        val index   = if (col.getSortIndex() > 0) (col.getSortIndex() + 1).toInt.toString else ""
        val ascDesc = if (sorted == "desc") SortDescIndicator else SortAscIndicator
        <.span(s" $index", ascDesc)
      case _              => EmptyVdom

  protected def resizer[T](
    header:     raw.mod.Header[T, ?],
    resizeMode: Option[raw.mod.ColumnResizeMode],
    sizingInfo: raw.mod.ColumnSizingInfoState
  ): VdomNode =
    if (header.column.getCanResize())
      <.div(
        ^.onMouseDown ==> (e => Callback(header.getResizeHandler()(e))),
        ^.onTouchStart ==> (e => Callback(header.getResizeHandler()(e))),
        ^.onClick ==> (_.stopPropagationCB),
        ResizerClass,
        IsResizingColClass.when(header.column.getIsResizing()),
        TagMod.when(
          resizeMode.contains(raw.mod.ColumnResizeMode.onEnd) && header.column.getIsResizing()
        )(
          ^.transform := s"translateX(${sizingInfo.deltaOffset}px)"
        )
      )(ResizerContent)
    else EmptyVdom

  def render[T](
    table:         raw.mod.Table[T],
    rows:          js.Array[raw.mod.Row[T]],
    tableClass:    Css = Css.Empty,
    rowClassFn:    (Int, T) => Css = (_, _: T) => Css.Empty,
    paddingTop:    Option[Int] = none,
    paddingBottom: Option[Int] = none
  ) =
    <.table(tableClass, TableClass)(
      <.thead(
        TheadClass,
        IsResizingTHeadClass.when(
          table.getHeaderGroups().exists(_.headers.exists(_.column.getIsResizing()))
        )
      )(
        table
          .getHeaderGroups()
          .map(headerGroup =>
            TagMod.when(
              headerGroup.headers
                .exists(header =>
                  js.typeOf(
                    header.column.columnDef.asInstanceOf[raw.mod.HeaderContext[T, Any]].header
                  ) != "undefined"
                )
            )(
              <.tr(TheadTrClass)(^.key := headerGroup.id)(
                headerGroup.headers
                  .map(header =>
                    <.th(TheadThClass)(
                      ^.key     := header.id,
                      ^.colSpan := header.colSpan.toInt,
                      ^.width   := s"${header.getSize().toInt}px",
                      SortableColClass.when(header.column.getCanSort()),
                      header.column
                        .getToggleSortingHandler()
                        .map(handler => ^.onClick ==> (e => Callback(handler(e))))
                        .whenDefined
                    )(
                      TagMod.unless(header.isPlaceholder)(
                        React.Fragment(
                          rawReact.mod.flexRender(
                            header.column.columnDef
                              .asInstanceOf[raw.mod.HeaderContext[T, Any]]
                              .header
                              .asInstanceOf[rawReact.mod.Renderable[raw.mod.HeaderContext[T, Any]]],
                            header.getContext().asInstanceOf[raw.mod.HeaderContext[T, Any]]
                          ),
                          sortIndicator(header.column),
                          resizer(
                            header,
                            table.options
                              .asInstanceOf[raw.mod.TableOptionsResolved[T]]
                              .columnResizeMode
                              .toOption,
                            // ColumnResizeMode.onEnd, // Can we get this from the table??
                            table.getState().columnSizingInfo
                          )
                        )
                      )
                    )
                  )
              )
            )
          )
      ),
      <.tbody(TbodyClass)(
        paddingTop
          .filter(_ > 0)
          .map(p => <.tr(TbodyTrClass)(<.td(TbodyTdClass, ^.height := s"${p}px")))
          .whenDefined
      )(
        rows
          .map(row =>
            <.tr(TbodyTrClass)(^.key := row.id, rowClassFn(row.index.toInt, row.original))(
              row
                .getVisibleCells()
                .map(cell =>
                  <.td(TbodyTdClass)(^.key := cell.id)(
                    rawReact.mod.flexRender(
                      cell.column.columnDef.cell
                        .asInstanceOf[rawReact.mod.Renderable[raw.mod.CellContext[T, Any]]],
                      cell.getContext().asInstanceOf[raw.mod.CellContext[T, Any]]
                    )
                  )
                )
            )
          )
      )(
        paddingBottom
          .filter(_ > 0)
          .map(p => <.tr(TbodyTrClass)(<.td(TbodyTdClass, ^.height := s"${p}px")))
          .whenDefined
      ),
      <.tfoot(TfootClass)(
        table
          .getFooterGroups()
          .map(footerGroup =>
            TagMod.when(
              footerGroup.headers
                .exists(footer => js.typeOf(footer.column.columnDef.footer) != "undefined")
            )(
              <.tr(TfootTrClass)(^.key := footerGroup.id)(
                footerGroup.headers.map(footer =>
                  <.th(TfootThClass)(^.key := footer.id, ^.colSpan := footer.colSpan.toInt)(
                    TagMod.unless(footer.isPlaceholder)(
                      rawReact.mod.flexRender(
                        footer.column.columnDef.footer
                          .asInstanceOf[rawReact.mod.Renderable[raw.mod.HeaderContext[T, Any]]],
                        footer.getContext()
                      )
                    )
                  )
                )
              )
            )
          )
      )
    )

object HTMLTableRenderer:
  def componentBuilder[T, Props <: HTMLTableProps[_]](renderer: HTMLTableRenderer[T]) =
    ScalaFnComponent[Props[T]](props =>
      renderer.render(
        props.table,
        props.table.getRowModel().rows,
        props.tableClass,
        props.rowClassFn
      )
    )

  def componentBuilderVirtualized[T, Props <: HTMLVirtualizedTableProps[_]](
    renderer: HTMLTableRenderer[T]
  ) =
    ScalaFnComponent
      .withHooks[Props[T]]
      .useRefToVdom[HTMLDivElement]
      .useVirtualizerBy((props, ref) =>
        VirtualOptions(
          count = props.table.getRowModel().rows.length,
          estimateSize = props.estimateRowHeightPx,
          getScrollElement = ref.get,
          overscan = props.overscan,
          getItemKey = props.getItemKey
        )
      )
      .render { (props, ref, virtualizer) =>
        val rows                        = props.table.getRowModel().rows
        val (paddingTop, paddingBottom) = virtualVerticalPadding(virtualizer)

        <.div.withRef(ref)(props.containerClass)(
          renderer.render(
            props.table,
            virtualizer.getVirtualItems().map(virtualItem => rows(virtualItem.index.toInt)),
            props.tableClass,
            props.rowClassFn,
            paddingTop.some,
            paddingBottom.some
          )
        )
      }
