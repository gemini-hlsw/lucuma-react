// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.common.style.Css
import lucuma.react.virtual.*
import lucuma.typed.tanstackReactTable as rawReact
import lucuma.typed.tanstackTableCore as raw
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLDivElement

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
  protected val TfootTdClass: Css     = Css.Empty
  protected val EmptyMessage: Css     = Css.Empty

  protected val ResizerClass: Css         = Css("resizer")
  protected val IsResizingTHeadClass: Css = Css("isResizing")
  protected val IsResizingColClass: Css   = Css("isResizingCol")
  protected val ResizerContent: VdomNode  = EmptyVdom

  protected val SortableColClass: Css       = Css("sortableCol")
  protected val SortableIndicator: VdomNode = "⇅"
  protected val SortAscIndicator: VdomNode  = "↑"
  protected val SortDescIndicator: VdomNode = "↓"

  protected def sortIndicator[T, TM](col: Column[T, ?, TM, ?]): VdomNode =
    col
      .getIsSorted()
      .fold(if (col.getCanSort()) SortableIndicator else EmptyVdom): sortDirection =>
        val index   = if (col.getSortIndex() > 0) (col.getSortIndex() + 1).toInt.toString else ""
        val ascDesc =
          if (sortDirection == SortDirection.Descending) SortDescIndicator else SortAscIndicator
        <.span(ascDesc, <.small(index))

  protected def resizer[T, TM](
    header:     Header[T, ?, TM, ?],
    resizeMode: Option[ColumnResizeMode],
    sizingInfo: ColumnSizingInfo
  ): VdomNode =
    if (header.column.getCanResize())
      <.div(
        ^.onMouseDown ==> header.getResizeHandler(),
        ^.onTouchStart ==> header.getResizeHandler(),
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

  def render[T, TM](
    table:         Table[T, TM],
    rows:          List[Row[T, TM]],
    tableMod:      TagMod = TagMod.empty,
    headerMod:     TagMod = TagMod.empty,
    headerRowMod:  HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
    headerCellMod: Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
    bodyMod:       TagMod = TagMod.empty,
    rowMod:        Row[T, TM] => TagMod = (_: Row[T, TM]) => TagMod.empty,
    cellMod:       Cell[T, Any, TM, Any] => TagMod = (_: Cell[T, Any, TM, Any]) => TagMod.empty,
    footerMod:     TagMod = TagMod.empty,
    footerRowMod:  HeaderGroup[T, TM] => TagMod = (_: HeaderGroup[T, TM]) => TagMod.empty,
    footerCellMod: Header[T, Any, TM, Any] => TagMod = (_: Header[T, Any, TM, Any]) => TagMod.empty,
    indexOffset:   Int = 0,
    paddingTop:    Option[Int] = none,
    paddingBottom: Option[Int] = none,
    emptyMessage:  VdomNode = EmptyVdom
  ) =
    val visibleColumnCount: Int = table.getAllLeafColumns().filter(_.getIsVisible()).length

    <.table(TableClass, tableMod)(
      TagMod.when(
        table
          .getHeaderGroups()
          .exists: headerGroup =>
            headerGroup.headers.exists: header =>
              header.column.columnDef.header.isDefined
      )(
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
              <.tr(TheadTrClass, headerRowMod(headerGroup))(^.key := headerGroup.id.value)(
                headerGroup.headers
                  .map(header =>
                    <.th(
                      TheadThClass,
                      ^.key     := header.id.value,
                      ^.colSpan := header.colSpan.toInt,
                      ^.width   := s"${header.getSize().toInt}px",
                      SortableColClass.when(header.column.getCanSort()),
                      headerCellMod(header),
                      header.column
                        .getToggleSortingHandler()
                        .map(handler => ^.onClick ==> handler)
                        .whenDefined
                    )(
                      TagMod.unless(header.isPlaceholder)(
                        React.Fragment(
                          rawReact.mod.flexRender(
                            header.column.columnDef.toJs
                              .asInstanceOf[raw.buildLibCoreHeadersMod.HeaderContext[T, Any]]
                              .header
                              .asInstanceOf[rawReact.mod.Renderable[
                                raw.buildLibCoreHeadersMod.HeaderContext[T, Any]
                              ]],
                            header
                              .getContext()
                              .toJs
                              .asInstanceOf[raw.buildLibCoreHeadersMod.HeaderContext[T, Any]]
                          ),
                          sortIndicator(header.column),
                          resizer(
                            header,
                            table.options.columnResizeMode,
                            table.getState().columnSizingInfo
                          )
                        )
                      )
                    )
                  )
                  .toTagMod
              )
            )
            .toTagMod
        )
      ),
      <.tbody(TbodyClass, bodyMod)(
        paddingTop
          .filter(_ > 1)
          .map(p =>
            <.tr(TbodyTrClass)(
              <.td(
                TbodyTdClass,
                ^.colSpan := visibleColumnCount,
                ^.height  := s"${p}px"
              )
            )
          )
          .whenDefined
      )(
        TagMod.when(rows.isEmpty)(
          <.tr(
            TbodyTrClass,
            <.td(
              EmptyMessage,
              TbodyTdClass,
              ^.colSpan := visibleColumnCount,
              ^.whiteSpace.nowrap
            )(
              emptyMessage
            )
          )
        ),
        rows.zipWithIndex
          .map((row, index) =>
            <.tr(
              TbodyTrClass,
              TbodyTrEvenClass.when((index + indexOffset) % 2 =!= 0),
              rowMod(row),
              ^.key := row.id.value
            )(
              row
                .getVisibleCells()
                .map(cell =>
                  <.td(
                    TbodyTdClass,
                    cellMod(cell),
                    ^.key := cell.id.value
                  )(
                    cell.column.columnDef match
                      case colDef @ ColumnDef.Single[T, Any, TM, Any](_) =>
                        rawReact.mod.flexRender(
                          colDef.toJs.cell
                            .asInstanceOf[rawReact.mod.Renderable[
                              raw.buildLibCoreCellMod.CellContext[T, Any]
                            ]],
                          cell.getContext().toJs
                        )
                  )
                )
                .toTagMod
            ),
          )
          .toTagMod
      )(
        paddingBottom
          .filter(_ > 1)
          .map(p =>
            <.tr(TbodyTrClass)(
              <.td(
                TbodyTdClass,
                ^.colSpan := visibleColumnCount,
                ^.height  := s"${p}px"
              )
            )
          )
          .whenDefined
      ),
      TagMod.when(
        table
          .getFooterGroups()
          .exists: footerGroup =>
            footerGroup.headers.exists: header =>
              header.column.columnDef.footer.isDefined
            || footerMod != TagMod.empty
      )(
        <.tfoot(TfootClass, footerMod)(
          table
            .getFooterGroups()
            .map(footerGroup =>
              <.tr(TfootTrClass, footerRowMod(footerGroup))(^.key := footerGroup.id.value)(
                footerGroup.headers
                  .map(footer =>
                    <.td(TfootTdClass, footerCellMod(footer))(
                      ^.key     := footer.id.value,
                      ^.colSpan := footer.colSpan.toInt
                    )(
                      TagMod.unless(footer.isPlaceholder)(
                        rawReact.mod.flexRender(
                          footer.column.columnDef.toJs.footer
                            .asInstanceOf[rawReact.mod.Renderable[
                              raw.buildLibCoreHeadersMod.HeaderContext[T, Any]
                            ]],
                          footer.getContext().toJs
                        )
                      )
                    )
                  )
                  .toTagMod
              )
            )
            .toTagMod
        )
      )
    )

object HTMLTableRenderer:
  def componentBuilder[T, M, Props <: HTMLTableProps[_, _]](renderer: HTMLTableRenderer[T]) =
    ScalaFnComponent[Props[T, M]]: props =>
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

  def componentBuilderVirtualized[T, M, Props <: HTMLVirtualizedTableProps[_, _]](
    renderer: HTMLTableRenderer[T]
  ) =
    ScalaFnComponent
      .withHooks[Props[T, M]]
      .useRefToVdom[HTMLDivElement]
      .useVirtualizerBy: (props, ref) =>
        VirtualOptions(
          count = props.table.getRowModel().rows.length,
          estimateSize = props.estimateSize,
          getScrollElement = ref.get,
          overscan = props.overscan,
          getItemKey = props.getItemKey,
          onChange = props.onChange,
          debug = props.debugVirtualizer
        )
      .useEffectOnMountBy: (props, _, virtualizer) => // Allow external access to Virtualizer
        props.virtualizerRef.toOption.map(_.set(virtualizer.some)).getOrEmpty
      .render: (props, ref, virtualizer) =>
        val rows =
          props.table.getTopRows() ++ props.table.getCenterRows() ++ props.table.getBottomRows()

        val (indexOffset, paddingBefore, paddingAfter) =
          virtualOffsets(virtualizer, props.debugVirtualizer)

        // TODO Should we attempt to make the <table>  the container (scroll element) and <tbody> the virtualized element?
        <.div.withRef(ref)(^.overflowY.auto, props.containerMod)(
          renderer.render(
            props.table,
            virtualizer.getVirtualItems().toList.map(virtualItem => rows(virtualItem.index.toInt)),
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
            indexOffset,
            paddingBefore.some,
            paddingAfter.some,
            props.emptyMessage
          )
        )

  def componentBuilderAutoHeightVirtualized[T, M, Props <: HTMLAutoHeightVirtualizedTableProps[_,
                                                                                               _
  ]](
    renderer: HTMLTableRenderer[T]
  ) =
    ScalaFnComponent
      .withHooks[Props[T, M]]
      .useRefToVdom[HTMLDivElement]
      .localValBy((props, ownRef) => props.containerRef.getOrElse(ownRef)) // containerRef
      .useVirtualizerBy: (props, _, containerRef) =>
        VirtualOptions(
          count = props.table.getRowModel().rows.length,
          estimateSize = props.estimateSize,
          getScrollElement = containerRef.get,
          overscan = props.overscan,
          getItemKey = props.getItemKey,
          onChange = props.onChange,
          debug = props.debugVirtualizer
        )
      .useEffectOnMountBy: (props, _, _, virtualizer) =>
        // Allow external access to Virtualizer if a ref is passed
        props.virtualizerRef.toOption.map(_.set(virtualizer.some)).getOrEmpty
      .render: (props, _, containerRef, virtualizer) =>
        val rows =
          props.table.getTopRows() ++ props.table.getCenterRows() ++ props.table.getBottomRows()

        // This is artificially added space on top and bottom so that the scroll bar is shown as if there were
        // the right number of elements on either side.
        val (indexOffset, paddingBefore, paddingAfter) =
          virtualOffsets(virtualizer, props.debugVirtualizer)

        // We use this trick to get a component whose height adjusts to the container.
        // See https://stackoverflow.com/a/1230666
        // We create 2 more containers: an outer one, with position: relative and height: 100%,
        // and an inner one, with position: absolute, and top: 0, bottom: 0.
        // The scrolling element has to be the outer one.
        <.div.withRef(containerRef)(
          ^.position.relative,
          ^.height := "100%",
          ^.overflow.auto,
          props.containerMod
        )(
          <.div(
            ^.position.absolute,
            ^.top    := "0",
            ^.bottom := "0",
            props.innerContainerMod
          )(
            renderer.render(
              props.table,
              virtualizer
                .getVirtualItems()
                .toList
                .map(virtualItem => rows(virtualItem.index.toInt)),
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
              indexOffset,
              paddingBefore.some,
              paddingAfter.some,
              props.emptyMessage
            )
          )
        )
