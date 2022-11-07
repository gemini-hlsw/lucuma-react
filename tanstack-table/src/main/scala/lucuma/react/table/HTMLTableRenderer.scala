// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.virtual.*
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLDivElement
import react.common.*
import react.common.style.Css
import reactST.{tanstackReactTable => rawReact}
import reactST.{tanstackTableCore => raw}
import reactST.{tanstackVirtualCore => rawVirtual}

import scala.util.Random
import scalajs.js

/**
 * Customizable renderer for tables.
 *
 * Allows intermediate customization via constant overriding as well as via properties.
 */
trait HTMLTableRenderer[T]:
  protected val TableClass: Css       = Css("react-table")
  protected val TheadClass: Css       = Css.Empty
  protected val TheadTrClass: Css     = Css.Empty
  protected val TheadThClass: Css     = Css.Empty
  protected val TbodyClass: Css       = Css.Empty
  protected val TbodyTrClass: Css     = Css.Empty
  protected val TbodyTrEvenClass: Css = Css("row-even")
  protected val TbodyTdClass: Css     = Css.Empty
  protected val TfootClass: Css       = Css.Empty
  protected val TfootTrClass: Css     = Css.Empty
  protected val TfootThClass: Css     = Css.Empty
  protected val EmptyMessage: Css     = Css.Empty

  protected val ResizerClass: Css         = Css("resizer")
  protected val IsResizingTHeadClass: Css = Css("isResizing")
  protected val IsResizingColClass: Css   = Css("isResizingCol")
  protected val ResizerContent: VdomNode  = EmptyVdom

  protected val SortableColClass: Css       = Css("sortableCol")
  protected val SortableIndicator: VdomNode = "⇅"
  protected val SortAscIndicator: VdomNode  = "↑"
  protected val SortDescIndicator: VdomNode = "↓"

  protected def sortIndicator[T](col: raw.mod.Column[T, ?]): VdomNode =
    col.getIsSorted().asInstanceOf[Boolean | String] match
      case sorted: String =>
        val index   = if (col.getSortIndex() > 0) (col.getSortIndex() + 1).toInt.toString else ""
        val ascDesc = if (sorted == "desc") SortDescIndicator else SortAscIndicator
        <.span(ascDesc, <.small(index))
      case _              =>
        if (col.getCanSort()) SortableIndicator else EmptyVdom

  protected def resizer[T](
    header:     raw.mod.Header[T, ?],
    resizeMode: Option[ColumnResizeMode],
    sizingInfo: ColumnSizingInfo
  ): VdomNode =
    if (header.column.getCanResize())
      <.div(
        ^.onMouseDown ==> (e => Callback(header.getResizeHandler()(e))),
        ^.onTouchStart ==> (e => Callback(header.getResizeHandler()(e))),
        ^.onClick ==> (_.stopPropagationCB),
        ResizerClass,
        IsResizingColClass.when(header.column.getIsResizing()),
        TagMod.when(
          resizeMode.contains(ColumnResizeMode.OnEnd) && header.column.getIsResizing()
        )(
          sizingInfo.deltaOffset
            .map(offset => ^.transform := s"translateX(${offset}px)")
            .whenDefined
        )
      )(ResizerContent)
    else EmptyVdom

  def render[T](
    table:         Table[T],
    rows:          js.Array[raw.mod.Row[T]],
    tableMod:      TagMod = TagMod.empty,
    headerMod:     TagMod = TagMod.empty,
    headerRowMod:  raw.mod.CoreHeaderGroup[T] => TagMod = (_: raw.mod.CoreHeaderGroup[T]) =>
      TagMod.empty,
    headerCellMod: raw.mod.Header[T, Any] => TagMod = (_: raw.mod.Header[T, Any]) => TagMod.empty,
    bodyMod:       TagMod = TagMod.empty,
    rowMod:        raw.mod.Row[T] => TagMod = (_: raw.mod.Row[T]) => TagMod.empty,
    cellMod:       raw.mod.Cell[T, Any] => TagMod = (_: raw.mod.Cell[T, Any]) => TagMod.empty,
    footerMod:     TagMod = TagMod.empty,
    footerRowMod:  raw.mod.CoreHeaderGroup[T] => TagMod = (_: raw.mod.CoreHeaderGroup[T]) =>
      TagMod.empty,
    footerCellMod: raw.mod.Header[T, Any] => TagMod = (_: raw.mod.Header[T, Any]) => TagMod.empty,
    paddingTop:    Option[Int] = none,
    paddingBottom: Option[Int] = none,
    emptyMessage:  VdomNode = EmptyVdom
  ) =
    <.table(TableClass, tableMod)(
      <.thead(
        TheadClass,
        headerMod,
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
              <.tr(TheadTrClass, headerRowMod(headerGroup))(^.key := headerGroup.id)(
                headerGroup.headers
                  .map(header =>
                    <.th(TheadThClass, headerCellMod(header))(
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
                            table.options.columnResizeMode.toOption,
                            table.getState().columnSizingInfo
                          )
                        )
                      )
                    )
                  )
              )
            )
          )
          .toTagMod
      ),
      <.tbody(TbodyClass, bodyMod)(
        paddingTop
          .filter(_ > 1)
          .map(p =>
            <.tr(TbodyTrClass)(
              <.td(
                TbodyTdClass,
                ^.colSpan := table.getAllLeafColumns().length,
                ^.height  := s"${p}px"
              )
            )
          )
          .whenDefined
      )(
        TagMod.when(rows.isEmpty)(
          <.tr(
            TbodyTrClass,
            EmptyMessage,
            ^.colSpan := table.getAllLeafColumns().length,
            ^.whiteSpace.nowrap
          )(
            emptyMessage
          )
        ),
        rows
          .map(row =>
            <.tr(
              TbodyTrClass,
              TbodyTrEvenClass.when(row.index.toInt % 2 =!= 0), // Index starts at 0
              rowMod(row),
              ^.key := row.id
            )(
              row
                .getVisibleCells()
                .map(cell =>
                  <.td(
                    TbodyTdClass,
                    cellMod(cell),
                    ^.key := cell.id
                  )(
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
          .filter(_ > 1)
          .map(p =>
            <.tr(TbodyTrClass)(
              <.td(
                TbodyTdClass,
                ^.colSpan := table.getAllLeafColumns().length,
                ^.height  := s"${p}px"
              )
            )
          )
          .whenDefined
      ),
      <.tfoot(TfootClass, footerMod)(
        table
          .getFooterGroups()
          .map(footerGroup =>
            TagMod.when(
              footerGroup.headers
                .exists(footer => js.typeOf(footer.column.columnDef.footer) != "undefined")
            )(
              <.tr(TfootTrClass, footerRowMod(footerGroup))(^.key := footerGroup.id)(
                footerGroup.headers.map(footer =>
                  <.th(TfootThClass, footerCellMod(footer))(
                    ^.key     := footer.id,
                    ^.colSpan := footer.colSpan.toInt
                  )(
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
          .toTagMod
      )
    )

object HTMLTableRenderer:
  def componentBuilder[T, Props <: HTMLTableProps[_]](renderer: HTMLTableRenderer[T]) =
    ScalaFnComponent[Props[T]](props =>
      renderer.render(
        props.table,
        props.table.getRowModel().rows,
        TagMod(props.tableMod, props.extraTableClasses),
        props.headerMod,
        props.headerRowMod,
        props.headerCellMod,
        props.bodyMod,
        props.rowMod,
        props.cellMod,
        props.footerMod,
        props.footerRowMod,
        props.footerCellMod,
        emptyMessage = props.emptyMessage
      )
    )

  private val random = new Random()

  def componentBuilderVirtualized[T, Props <: HTMLVirtualizedTableProps[_]](
    renderer: HTMLTableRenderer[T]
  ) =
    ScalaFnComponent
      .withHooks[Props[T]]
      .useRefToVdom[HTMLDivElement]
      .useVirtualizerBy((props, ref) =>
        VirtualOptions(
          count = props.table.getRowModel().rows.length,
          estimateSize = props.estimateSize,
          getScrollElement = ref.get,
          overscan = props.overscan,
          getItemKey = props.getItemKey,
          onChange = props.onChange,
          debug = props.debugVirtualizer
        )
      )
      .useEffectOnMountBy((props, _, virtualizer) => // Allow external access to Virtualizer
        props.virtualizerRef.toOption.map(_.set(virtualizer.some)).getOrEmpty
      )
      // The virtualizer seems to get confused when we change the number of rendered rows.
      // Therefore, we use a key to force a remount when the number of rows changes.
      .useMemoBy((props, _, _) => props.table.getRowModel().rows.length)((_, _, _) =>
        _ => random.nextInt()
      )
      .render { (props, ref, virtualizer, rowsRandom) =>
        val rows                        = props.table.getRowModel().rows
        val (paddingTop, paddingBottom) =
          virtualVerticalPadding(virtualizer, props.debugVirtualizer)

        // TODO Should we attempt to make the <table>  the container (scroll element) and <tbody> the virtualized element?
        <.div.withRef(ref)(
          ^.overflowY.auto,
          props.containerMod,
          ^.key := s"table-${rowsRandom.value}"
        )(
          renderer.render(
            props.table,
            virtualizer.getVirtualItems().map(virtualItem => rows(virtualItem.index.toInt)),
            TagMod(props.tableMod, props.extraTableClasses),
            props.headerMod,
            props.headerRowMod,
            props.headerCellMod,
            props.bodyMod,
            props.rowMod,
            props.cellMod,
            props.footerMod,
            props.footerRowMod,
            props.footerCellMod,
            paddingTop.some,
            paddingBottom.some,
            props.emptyMessage
          )
        )
      }

  def componentBuilderAutoHeightVirtualized[T, Props <: HTMLAutoHeightVirtualizedTableProps[_]](
    renderer: HTMLTableRenderer[T]
  ) =
    ScalaFnComponent
      .withHooks[Props[T]]
      .useRefToVdom[HTMLDivElement]
      .useVirtualizerBy((props, ref) =>
        VirtualOptions(
          count = props.table.getRowModel().rows.length,
          estimateSize = props.estimateSize,
          getScrollElement = ref.get,
          overscan = props.overscan,
          getItemKey = props.getItemKey,
          onChange = props.onChange,
          debug = props.debugVirtualizer
        )
      )
      .useEffectOnMountBy((props, _, virtualizer) => // Allow external access to Virtualizer
        props.virtualizerRef.toOption.map(_.set(virtualizer.some)).getOrEmpty
      )
      // The virtualizer seems to get confused when we change the number of rendered rows.
      // Therefore, we use a key to force a remount when the number of rows changes.
      .useMemoBy((props, _, _) => props.table.getRowModel().rows.length)((_, _, _) =>
        _ => random.nextInt()
      )
      .render { (props, ref, virtualizer, rowsRandom) =>
        val rows                        = props.table.getRowModel().rows
        // This is artificially added space on top and bottom so that the scroll bar is shown as if there were
        // the right number of elements on either side.
        val (paddingTop, paddingBottom) =
          virtualVerticalPadding(virtualizer, props.debugVirtualizer)

        // We use this trick to get a component whose height adjusts to the container.
        // See https://stackoverflow.com/a/1230666
        // We create 2 more containers: an outer one, with position: relative and height: 100%,
        // and an inner one, with position: absolute, and top: 0, bottom: 0.
        // The scrolling element has to be the outer one.
        <.div.withRef(ref)(
          ^.position.relative,
          ^.height := "100%",
          ^.overflow.auto,
          props.containerMod,
          ^.key    := s"table-${rowsRandom.value}"
        )(
          <.div(
            ^.position.absolute,
            ^.top    := "0",
            ^.bottom := "0",
            props.innerContainerMod
          )(
            renderer.render(
              props.table,
              virtualizer.getVirtualItems().map(virtualItem => rows(virtualItem.index.toInt)),
              TagMod(props.tableMod, props.extraTableClasses),
              props.headerMod,
              props.headerRowMod,
              props.headerCellMod,
              props.bodyMod,
              props.rowMod,
              props.cellMod,
              props.footerMod,
              props.footerRowMod,
              props.footerCellMod,
              paddingTop.some,
              paddingBottom.some,
              props.emptyMessage
            )
          )
        )
      }
