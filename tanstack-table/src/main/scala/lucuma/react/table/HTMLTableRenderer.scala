// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.virtual.*
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLDivElement
import react.common.*
import react.common.style.Css
import lucuma.typed.{tanstackReactTable => rawReact}
import lucuma.typed.{tanstackTableCore => raw}
import lucuma.typed.{tanstackVirtualCore => rawVirtual}

import scala.util.Random

import scalajs.js

/**
 * Customizable renderer for tables.
 *
 * Allows intermediate customization via constant overriding as well as via properties.
 */
trait HTMLTableRenderer[T]:
  protected val TableClass: Css                   = Css("react-table")
  protected val TheadClass: Css                   = Css.Empty
  protected val TheadTrClass: Css                 = Css.Empty
  protected val TheadThClass: Css                 = Css.Empty
  protected val TbodyClass: Css                   = Css.Empty
  protected val TbodyTrClass: Css                 = Css.Empty
  protected val TbodyTrWithSubcomponentClass: Css = Css("has-subcomponent")
  protected val TbodyTrSubcomponentClass: Css     = Css("is-subcomponent")
  protected val TbodyTrEvenClass: Css             = Css("row-even")
  protected val TbodyTdClass: Css                 = Css.Empty
  protected val TfootClass: Css                   = Css.Empty
  protected val TfootTrClass: Css                 = Css.Empty
  protected val TfootThClass: Css                 = Css.Empty
  protected val EmptyMessage: Css                 = Css.Empty

  protected val ResizerClass: Css         = Css("resizer")
  protected val IsResizingTHeadClass: Css = Css("isResizing")
  protected val IsResizingColClass: Css   = Css("isResizingCol")
  protected val ResizerContent: VdomNode  = EmptyVdom

  protected val SortableColClass: Css       = Css("sortableCol")
  protected val SortableIndicator: VdomNode = "⇅"
  protected val SortAscIndicator: VdomNode  = "↑"
  protected val SortDescIndicator: VdomNode = "↓"

  protected def sortIndicator[T](col: raw.buildLibTypesMod.Column[T, ?]): VdomNode =
    col.getIsSorted().asInstanceOf[Boolean | String] match
      case sorted: String =>
        val index   = if (col.getSortIndex() > 0) (col.getSortIndex() + 1).toInt.toString else ""
        val ascDesc = if (sorted == "desc") SortDescIndicator else SortAscIndicator
        <.span(ascDesc, <.small(index))
      case _              =>
        if (col.getCanSort()) SortableIndicator else EmptyVdom

  protected def resizer[T](
    header:     raw.buildLibTypesMod.Header[T, ?],
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
    table:              Table[T],
    rows:               js.Array[raw.buildLibTypesMod.Row[T]],
    tableMod:           TagMod = TagMod.empty,
    headerMod:          TagMod = TagMod.empty,
    headerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
      (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
    headerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
      (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
    bodyMod:            TagMod = TagMod.empty,
    rowMod:             raw.buildLibTypesMod.Row[T] => TagMod = (_: raw.buildLibTypesMod.Row[T]) =>
      TagMod.empty,
    cellMod:            raw.buildLibTypesMod.Cell[T, Any] => TagMod = (_: raw.buildLibTypesMod.Cell[T, Any]) =>
      TagMod.empty,
    footerMod:          TagMod = TagMod.empty,
    footerRowMod:       raw.buildLibCoreHeadersMod.CoreHeaderGroup[T] => TagMod =
      (_: raw.buildLibCoreHeadersMod.CoreHeaderGroup[T]) => TagMod.empty,
    footerCellMod:      raw.buildLibTypesMod.Header[T, Any] => TagMod =
      (_: raw.buildLibTypesMod.Header[T, Any]) => TagMod.empty,
    indexOffset:        Int = 0,
    paddingTop:         Option[Int] = none,
    paddingBottom:      Option[Int] = none,
    emptyMessage:       VdomNode = EmptyVdom,
    renderSubComponent: raw.buildLibTypesMod.Row[T] => Option[VdomNode] =
      (_: raw.buildLibTypesMod.Row[T]) => none
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
                    header.column.columnDef
                      .asInstanceOf[raw.buildLibCoreHeadersMod.HeaderContext[T, Any]]
                      .header
                  ) != "undefined"
                )
            )(
              <.tr(TheadTrClass, headerRowMod(headerGroup))(^.key := headerGroup.id)(
                headerGroup.headers
                  .map(header =>
                    <.th(
                      TheadThClass,
                      ^.key     := header.id,
                      ^.colSpan := header.colSpan.toInt,
                      ^.width   := s"${header.getSize().toInt}px",
                      SortableColClass.when(header.column.getCanSort()),
                      headerCellMod(header),
                      header.column
                        .getToggleSortingHandler()
                        .map(handler => ^.onClick ==> (e => Callback(handler(e))))
                        .whenDefined
                    )(
                      TagMod.unless(header.isPlaceholder)(
                        React.Fragment(
                          rawReact.mod.flexRender(
                            header.column.columnDef
                              .asInstanceOf[raw.buildLibCoreHeadersMod.HeaderContext[T, Any]]
                              .header
                              .asInstanceOf[rawReact.mod.Renderable[
                                raw.buildLibCoreHeadersMod.HeaderContext[T, Any]
                              ]],
                            header
                              .getContext()
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
          <.tr(TbodyTrClass,
               <.td(EmptyMessage,
                    TbodyTdClass,
                    ^.colSpan := table.getAllLeafColumns().length,
                    ^.whiteSpace.nowrap
               )(
                 emptyMessage
               )
          )
        ),
        rows.zipWithIndex
          .map((row, index) =>
            val subcomponent: Option[VdomNode] = renderSubComponent(row)

            React.Fragment(
              <.tr(
                TbodyTrClass,
                TbodyTrWithSubcomponentClass.when(subcomponent.isDefined),
                TbodyTrEvenClass.when((index + indexOffset) % 2 =!= 0),
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
                          .asInstanceOf[rawReact.mod.Renderable[
                            raw.buildLibCoreCellMod.CellContext[T, Any]
                          ]],
                        cell.getContext().asInstanceOf[raw.buildLibCoreCellMod.CellContext[T, Any]]
                      )
                    )
                  )
              ),
              subcomponent.map(subComponent =>
                <.tr(
                  TbodyTrSubcomponentClass,
                  TbodyTrEvenClass.when((index + indexOffset) % 2 =!= 0),
                  ^.key := s"${row.id}-subcomponent"
                )(
                  <.td(^.colSpan := table.getAllLeafColumns().length)(subComponent)
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
                          .asInstanceOf[rawReact.mod.Renderable[
                            raw.buildLibCoreHeadersMod.HeaderContext[T, Any]
                          ]],
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
        emptyMessage = props.emptyMessage,
        renderSubComponent = props.renderSubComponent
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
      .render { (props, ref, virtualizer) =>
        val rows                                       = props.table.getRowModel().rows
        val (indexOffset, paddingBefore, paddingAfter) =
          virtualOffsets(virtualizer, props.debugVirtualizer)

        // TODO Should we attempt to make the <table>  the container (scroll element) and <tbody> the virtualized element?
        <.div.withRef(ref)(^.overflowY.auto, props.containerMod)(
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
            indexOffset,
            paddingBefore.some,
            paddingAfter.some,
            props.emptyMessage,
            props.renderSubComponent
          )
        )
      }

  def componentBuilderAutoHeightVirtualized[T, Props <: HTMLAutoHeightVirtualizedTableProps[_]](
    renderer: HTMLTableRenderer[T]
  ) =
    ScalaFnComponent
      .withHooks[Props[T]]
      .useRefToVdom[HTMLDivElement]
      .localValBy((props, ownRef) => props.containerRef.getOrElse(ownRef)) // containerRef
      .useVirtualizerBy((props, _, containerRef) =>
        VirtualOptions(
          count = props.table.getRowModel().rows.length,
          estimateSize = props.estimateSize,
          getScrollElement = containerRef.get,
          overscan = props.overscan,
          getItemKey = props.getItemKey,
          onChange = props.onChange,
          debug = props.debugVirtualizer
        )
      )
      .useEffectOnMountBy((props, _, _, virtualizer) =>
        // Allow external access to Virtualizer if a ref is passed
        props.virtualizerRef.toOption.map(_.set(virtualizer.some)).getOrEmpty
      )
      .render { (props, _, containerRef, virtualizer) =>
        val rows                                       = props.table.getRowModel().rows
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
              indexOffset,
              paddingBefore.some,
              paddingAfter.some,
              props.emptyMessage,
              props.renderSubComponent
            )
          )
        )
      }
