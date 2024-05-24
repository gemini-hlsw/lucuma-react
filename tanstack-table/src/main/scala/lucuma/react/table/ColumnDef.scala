// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.VdomNode
import lucuma.react.SizePx
import lucuma.react.table.facade.*
import lucuma.typed.tanstackTableCore as raw

import scalajs.js
import scalajs.js.JSConverters.*

sealed trait ColumnDef[T, A, TM, CM]:
  def id: ColumnId
  def header: js.UndefOr[String | (HeaderContext[T, A, TM, CM] => VdomNode)]
  def footer: js.UndefOr[HeaderContext[T, A, TM, CM] => VdomNode]
  def meta: js.UndefOr[CM]

  // Column Sizing
  def enableResizing: js.UndefOr[Boolean]
  def size: js.UndefOr[SizePx]
  def minSize: js.UndefOr[SizePx]
  def maxSize: js.UndefOr[SizePx]

  // Column Visibility
  def enableHiding: js.UndefOr[Boolean]

  private[table] def toJs: ColumnDefJs[T, A, TM, CM]

object ColumnDef:
  type NoMeta[T, A]             = ColumnDef[T, A, Nothing, Nothing]
  type WithTableMeta[T, A, TM]  = ColumnDef[T, A, TM, Nothing]
  type WithColumnMeta[T, A, CM] = ColumnDef[T, A, Nothing, CM]

  case class Single[T, A, TM, CM](private[table] val toJs: ColumnDefJs[T, A, TM, CM])
      extends ColumnDef[T, A, TM, CM]:
    lazy val id: ColumnId = ColumnId(toJs.id)

    /** WARNING: This mutates the object in-place. */
    def setId(id: ColumnId): Single[T, A, TM, CM] = Single { toJs.id = id.value; toJs }

    lazy val accessor: js.UndefOr[T => A] =
      toJs.accessorFn.map(identity) // This makes implicit conversions kick-in

    /** WARNING: This mutates the object in-place. */
    def setAccessor(accessor: js.UndefOr[T => A]): Single[T, A, TM, CM] = Single {
      toJs.accessorFn = accessor.map(identity); toJs
    }

    lazy val header: js.UndefOr[String | (HeaderContext[T, A, TM, CM] => VdomNode)] =
      toJs.header.map: v =>
        js.typeOf(v) match
          case "string" => v.asInstanceOf[String]
          case renderFn =>
            (headerContext: HeaderContext[T, A, TM, CM]) =>
              VdomNode:
                renderFn
                  .asInstanceOf[js.Function1[raw.buildLibCoreHeadersMod.HeaderContext[T, A], Node]](
                    headerContext.toJs
                  )

    /** WARNING: This mutates the object in-place. */
    def setHeader(
      header: js.UndefOr[String | (HeaderContext[T, A, TM, CM] => VdomNode)]
    ): Single[T, A, TM, CM] = Single {
      toJs.header = header.map:
        _ match
          case s: String                                           => s
          case renderFn: (HeaderContext[T, A, TM, CM] => VdomNode) =>
            (headerContextJs: raw.buildLibCoreHeadersMod.HeaderContext[T, A]) =>
              renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    lazy val cell: js.UndefOr[CellContext[T, A, TM, CM] => VdomNode] =
      toJs.cell.map(renderFn => cellContext => VdomNode(renderFn(cellContext.toJs)))

    /** WARNING: This mutates the object in-place. */
    def setCell(
      cell: js.UndefOr[CellContext[T, A, TM, CM] => VdomNode]
    ): Single[T, A, TM, CM] =
      Single {
        toJs.cell =
          cell.map(renderFn => cellContextJs => renderFn(CellContext(cellContextJs)).rawNode);
        toJs
      }

    lazy val footer: js.UndefOr[HeaderContext[T, A, TM, CM] => VdomNode] =
      toJs.footer.map: renderFn =>
        (headerContext: HeaderContext[T, A, TM, CM]) =>
          VdomNode:
            renderFn
              .asInstanceOf[js.Function1[raw.buildLibCoreHeadersMod.HeaderContext[T, A], Node]](
                headerContext.toJs
              )

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: js.UndefOr[HeaderContext[T, A, TM, CM] => VdomNode]
    ): Single[T, A, TM, CM] = Single {
      toJs.footer = footer.map: renderFn =>
        headerContextJs => renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    lazy val meta: js.UndefOr[CM] = toJs.meta

    /** WARNING: This mutates the object in-place. */
    def setMeta(meta: CM): Single[T, A, TM, CM] = Single { toJs.meta = meta; toJs }

    // Column Sizing
    lazy val enableResizing: js.UndefOr[Boolean] = toJs.enableResizing

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: js.UndefOr[Boolean]): Single[T, A, TM, CM] =
      Single { toJs.enableResizing = enableResizing; toJs }

    lazy val size: js.UndefOr[SizePx] = toJs.size.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: js.UndefOr[SizePx]): Single[T, A, TM, CM] =
      Single { toJs.size = size.map(_.value); toJs }

    lazy val minSize: js.UndefOr[SizePx] = toJs.minSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: js.UndefOr[SizePx]): Single[T, A, TM, CM] =
      Single { toJs.minSize = minSize.map(_.value); toJs }

    lazy val maxSize: js.UndefOr[SizePx] = toJs.maxSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: js.UndefOr[SizePx]): Single[T, A, TM, CM] =
      Single { toJs.maxSize = maxSize.map(_.value); toJs }

    // Column Visibility
    lazy val enableHiding: js.UndefOr[Boolean] = toJs.enableHiding

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: js.UndefOr[Boolean]): Single[T, A, TM, CM] =
      Single { toJs.enableHiding = enableHiding; toJs }

    // Sorting
    lazy val enableSorting: js.UndefOr[Boolean] = toJs.enableSorting

    /** WARNING: This mutates the object in-place. */
    def setEnableSorting(enableSorting: js.UndefOr[Boolean]): Single[T, A, TM, CM] =
      Single { toJs.enableSorting = enableSorting; toJs }

    lazy val enableMultiSort: js.UndefOr[Boolean] = toJs.enableMultiSort

    /** WARNING: This mutates the object in-place. */
    def setEnableMultiSort(enableMultiSort: js.UndefOr[Boolean]): Single[T, A, TM, CM] =
      Single { toJs.enableMultiSort = enableMultiSort; toJs }

    lazy val invertSorting: js.UndefOr[Boolean] = toJs.invertSorting

    /** WARNING: This mutates the object in-place. */
    def setInvertSorting(invertSorting: js.UndefOr[Boolean]): Single[T, A, TM, CM] =
      Single { toJs.invertSorting = invertSorting; toJs }

    lazy val sortDescFirst: js.UndefOr[Boolean] = toJs.sortDescFirst

    /** WARNING: This mutates the object in-place. */
    def setSortDescFirst(sortDescFirst: js.UndefOr[Boolean]): Single[T, A, TM, CM] =
      Single { toJs.sortDescFirst = sortDescFirst; toJs }

    lazy val sortUndefined: js.UndefOr[UndefinedPriority] = // Baffingly, we need this cast.
      toJs.sortUndefined.map(v => UndefinedPriority.fromJs(v.asInstanceOf[UndefinedPriorityJs]))

    /** WARNING: This mutates the object in-place. */
    def setSortUndefined(sortUndefined: js.UndefOr[UndefinedPriority]): Single[T, A, TM, CM] =
      Single { toJs.sortUndefined = sortUndefined.map(_.toJs); toJs }

    lazy val sortingFn: js.UndefOr[BuiltInSorting | SortingFn[T, TM]] =
      toJs.sortingFn.map(v =>
        js.typeOf(v) match
          case "string" => BuiltInSorting.fromJs(v.asInstanceOf[String])
          case fn       =>
            (rowA: Row[T, TM], rowB: Row[T, TM], colId: ColumnId) =>
              fn.asInstanceOf[raw.buildLibFeaturesRowSortingMod.SortingFn[T]](
                rowA.toJs,
                rowB.toJs,
                colId.value
              ).toInt
      )

    /** WARNING: This mutates the object in-place. */
    def setSortingFn(
      sortingFn: js.UndefOr[BuiltInSorting | SortingFn[T, TM]]
    ): Single[T, A, TM, CM] =
      Single {
        toJs.sortingFn = sortingFn match
          case builtIn: BuiltInSorting => builtIn.toJs
          case fn                      =>
            (rowA, rowB, colId) =>
              fn.asInstanceOf[SortingFn[T, TM]](Row(rowA), Row(rowB), ColumnId(colId)).toDouble
        toJs
      }

    /** WARNING: This mutates the object in-place. */
    def sortableBuiltIn(builtIn: BuiltInSorting): Single[T, A, TM, CM] =
      this.setSortingFn(builtIn).setEnableSorting(true)

    /** WARNING: This mutates the object in-place. */
    def sortableWith[B](f: (A, A) => Int): Single[T, A, TM, CM] =
      val sbfn: SortingFn[T, TM] = (r1, r2, col) => f(r1.getValue[A](col), r2.getValue[A](col))
      this.setSortingFn(sbfn).setEnableSorting(true)

    /** WARNING: This mutates the object in-place. */
    def sortableBy[B](f: A => B)(using ordering: Ordering[B]): ColumnDef[T, A, TM, CM] =
      sortableWith((a1, a2) => ordering.compare(f(a1), f(a2)))

    /** WARNING: This mutates the object in-place. */
    def sortable(using Ordering[A]) = sortableBy(identity)

  end Single

  object Single:
    type NoMeta[T, A]             = Single[T, A, Nothing, Nothing]
    type WithTableMeta[T, A, TM]  = Single[T, A, TM, Nothing]
    type WithColumnMeta[T, A, CM] = Single[T, A, Nothing, CM]

    def apply[T, A, TM, CM](
      id:              ColumnId,
      accessor:        js.UndefOr[T => A] = js.undefined,
      header:          js.UndefOr[String | (HeaderContext[T, A, TM, CM] => VdomNode)] = js.undefined,
      cell:            js.UndefOr[CellContext[T, A, TM, CM] => VdomNode] = js.undefined,
      footer:          js.UndefOr[HeaderContext[T, A, TM, CM] => VdomNode] = js.undefined,
      meta:            js.UndefOr[CM] = js.undefined,
      // Column Sizing
      enableResizing:  js.UndefOr[Boolean] = js.undefined,
      size:            js.UndefOr[SizePx] = js.undefined,
      minSize:         js.UndefOr[SizePx] = js.undefined,
      maxSize:         js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:    js.UndefOr[Boolean] = js.undefined,
      // Sorting
      enableSorting:   js.UndefOr[Boolean] = js.undefined,
      enableMultiSort: js.UndefOr[Boolean] = js.undefined,
      invertSorting:   js.UndefOr[Boolean] = js.undefined,
      sortDescFirst:   js.UndefOr[Boolean] = js.undefined,
      sortUndefined:   js.UndefOr[UndefinedPriority] = js.undefined,
      sortingFn:       js.UndefOr[BuiltInSorting | SortingFn[T, TM]] = js.undefined
    ): Single[T, A, TM, CM] = { // TODO Check where TM is used???!?!?!?
      val p: ColumnDefJs[T, A, TM, CM] = new js.Object().asInstanceOf[ColumnDefJs[T, A, TM, CM]]
      p.id = id.value
      Single[T, A, TM, CM](p)
        .applyOrNot(accessor, _.setAccessor(_))
        .applyOrNot(header, _.setHeader(_))
        .applyOrNot(footer, _.setFooter(_))
        .applyOrNot(cell, _.setCell(_))
        .applyOrNot(meta, _.setMeta(_))
        .applyOrNot(enableResizing, _.setEnableResizing(_))
        .applyOrNot(size, _.setSize(_))
        .applyOrNot(minSize, _.setMinSize(_))
        .applyOrNot(maxSize, _.setMaxSize(_))
        .applyOrNot(enableHiding, _.setEnableHiding(_))
        .applyOrNot(enableSorting, _.setEnableSorting(_))
        .applyOrNot(enableMultiSort, _.setEnableMultiSort(_))
        .applyOrNot(invertSorting, _.setInvertSorting(_))
        .applyOrNot(sortDescFirst, _.setSortDescFirst(_))
        .applyOrNot(sortUndefined, _.setSortUndefined(_))
        .applyOrNot(sortingFn, _.setSortingFn(_))
    }
  end Single

  case class Group[T, TM, CM](private[table] val toJs: ColumnDefJs[T, Nothing, TM, CM])
      extends ColumnDef[T, Nothing, TM, CM]:
    lazy val id: ColumnId = ColumnId(toJs.id)

    /** WARNING: This mutates the object in-place. */
    def setId(id: ColumnId): Group[T, TM, CM] = Group { toJs.id = id.value; toJs }

    lazy val columns: List[ColumnDef[T, Any, TM, Any]] =
      toJs.columns
        .map(_.toList)
        .toOption
        .orEmpty
        .map(ColumnDef.fromJs(_).asInstanceOf[ColumnDef[T, Any, TM, Any]])

    /** WARNING: This mutates the object in-place. */
    def withColumns(columns: List[ColumnDef[T, Any, TM, Any]]): Group[T, TM, CM] =
      Group { toJs.columns = columns.map(_.toJs).toJSArray; toJs }

    lazy val header: js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM] => VdomNode)] =
      toJs.header.map: v =>
        js.typeOf(v) match
          case "string" => v.asInstanceOf[String]
          case renderFn =>
            (headerContext: HeaderContext[T, Nothing, TM, CM]) =>
              VdomNode:
                renderFn
                  .asInstanceOf[js.Function1[
                    raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing],
                    Node
                  ]](headerContext.toJs)

    /** WARNING: This mutates the object in-place. */
    def setHeader(
      header: js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM] => VdomNode)]
    ): Group[T, TM, CM] = Group {
      toJs.header = header.map:
        _ match
          case s: String                                                 => s
          case renderFn: (HeaderContext[T, Nothing, TM, CM] => VdomNode) =>
            (headerContextJs: raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing]) =>
              renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    lazy val footer: js.UndefOr[HeaderContext[T, Nothing, TM, CM] => VdomNode] =
      toJs.footer.map: renderFn =>
        (headerContext: HeaderContext[T, Nothing, TM, CM]) =>
          VdomNode:
            renderFn
              .asInstanceOf[js.Function1[
                raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing],
                Node
              ]](headerContext.toJs)

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: js.UndefOr[HeaderContext[T, Nothing, TM, CM] => VdomNode]
    ): Group[T, TM, CM] =
      Group {
        toJs.footer = footer.map: renderFn =>
          headerContextJs => renderFn(HeaderContext(headerContextJs)).rawNode
        toJs
      }

    lazy val meta: js.UndefOr[CM] = toJs.meta

    /** WARNING: This mutates the object in-place. */
    def setMeta(meta: CM): Group[T, TM, CM] = Group { toJs.meta = meta; toJs }

    // Column Sizing
    lazy val enableResizing: js.UndefOr[Boolean] = toJs.enableResizing

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: js.UndefOr[Boolean]): Group[T, TM, CM] =
      Group { toJs.enableResizing = enableResizing; toJs }

    lazy val size: js.UndefOr[SizePx] = toJs.size.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: js.UndefOr[SizePx]): Group[T, TM, CM] =
      Group { toJs.size = size.map(_.value); toJs }

    lazy val minSize: js.UndefOr[SizePx] = toJs.minSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: js.UndefOr[SizePx]): Group[T, TM, CM] =
      Group { toJs.minSize = minSize.map(_.value); toJs }

    lazy val maxSize: js.UndefOr[SizePx] = toJs.maxSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: js.UndefOr[SizePx]): Group[T, TM, CM] =
      Group { toJs.maxSize = maxSize.map(_.value); toJs }

    // Column Visibility
    lazy val enableHiding: js.UndefOr[Boolean] = toJs.enableHiding

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: js.UndefOr[Boolean]): Group[T, TM, CM] =
      Group { toJs.enableHiding = enableHiding; toJs }

  end Group

  object Group:
    type NoMeta[T]             = Group[T, Nothing, Nothing]
    type WithTableMeta[T, TM]  = Group[T, TM, Nothing]
    type WithColumnMeta[T, CM] = Group[T, Nothing, CM]

    def apply[T, TM, CM](
      id:             ColumnId,
      header:         js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM] => VdomNode)] = js.undefined,
      columns:        List[ColumnDef[T, ?, TM, ?]],
      footer:         js.UndefOr[HeaderContext[T, Nothing, TM, CM] => VdomNode] = js.undefined,
      meta:           js.UndefOr[CM] = js.undefined,
      // Column Sizing
      enableResizing: js.UndefOr[Boolean] = js.undefined,
      size:           js.UndefOr[SizePx] = js.undefined,
      minSize:        js.UndefOr[SizePx] = js.undefined,
      maxSize:        js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:   js.UndefOr[Boolean] = js.undefined
    ): Group[T, TM, CM] = {
      val p: ColumnDefJs[T, Nothing, TM, CM] =
        new js.Object().asInstanceOf[ColumnDefJs[T, Nothing, TM, CM]]
      p.id = id.value
      p.columns = columns.map(_.toJs).toJSArray
      Group[T, TM, CM](p)
        .applyOrNot(header, _.setHeader(_))
        .applyOrNot(meta, _.setMeta(_))
        .applyOrNot(enableResizing, _.setEnableResizing(_))
        .applyOrNot(size, _.setSize(_))
        .applyOrNot(minSize, _.setMinSize(_))
        .applyOrNot(maxSize, _.setMaxSize(_))
        .applyOrNot(enableHiding, _.setEnableHiding(_))
    }
  end Group

  private[table] def fromJs[T, A, TM, CM](
    colDef: ColumnDefJs[T, A, TM, CM]
  ): ColumnDef[T, A, TM, CM] =
    if (colDef.columns.isDefined)
      Group(colDef.asInstanceOf[ColumnDefJs[T, Nothing, TM, CM]])
        .asInstanceOf[ColumnDef[T, A, TM, CM]]
    else
      Single(colDef)

  def apply[T]: Applied[T, Nothing] =
    new Applied[T, Nothing]

  object WithTableMeta:
    def apply[T, TM]: Applied[T, TM] =
      new Applied[T, TM]

  class Applied[T, TM]:
    def apply[A](
      id:              ColumnId,
      accessor:        js.UndefOr[T => A] = js.undefined,
      header:          js.UndefOr[String | (HeaderContext[T, A, TM, Nothing] => VdomNode)] = js.undefined,
      cell:            js.UndefOr[CellContext[T, A, TM, Nothing] => VdomNode] = js.undefined,
      footer:          js.UndefOr[HeaderContext[T, A, TM, Nothing] => VdomNode] = js.undefined,
      // Column Sizing
      enableResizing:  js.UndefOr[Boolean] = js.undefined,
      size:            js.UndefOr[SizePx] = js.undefined,
      minSize:         js.UndefOr[SizePx] = js.undefined,
      maxSize:         js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:    js.UndefOr[Boolean] = js.undefined,
      // Sorting
      enableSorting:   js.UndefOr[Boolean] = js.undefined,
      enableMultiSort: js.UndefOr[Boolean] = js.undefined,
      invertSorting:   js.UndefOr[Boolean] = js.undefined,
      sortDescFirst:   js.UndefOr[Boolean] = js.undefined,
      sortUndefined:   js.UndefOr[UndefinedPriority] = js.undefined,
      sortingFn:       js.UndefOr[BuiltInSorting | SortingFn[T, TM]] = js.undefined
    ): Single[T, A, TM, Nothing] =
      Single(
        id,
        accessor,
        header,
        cell,
        footer,
        js.undefined,
        // Column Sizing
        enableResizing,
        size,
        minSize,
        maxSize,
        // Column Visibility
        enableHiding,
        // Sorting
        enableSorting,
        enableMultiSort,
        invertSorting,
        sortDescFirst,
        sortUndefined,
        sortingFn
      )

    def group(
      id:             ColumnId,
      header:         js.UndefOr[String | (HeaderContext[T, Nothing, TM, Nothing] => VdomNode)] =
        js.undefined,
      columns:        List[ColumnDef[T, ?, TM, ?]],
      footer:         js.UndefOr[HeaderContext[T, Nothing, TM, Nothing] => VdomNode] = js.undefined,
      // Column Sizing
      enableResizing: js.UndefOr[Boolean] = js.undefined,
      size:           js.UndefOr[SizePx] = js.undefined,
      minSize:        js.UndefOr[SizePx] = js.undefined,
      maxSize:        js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:   js.UndefOr[Boolean] = js.undefined
    ): Group[T, TM, Nothing] =
      Group(
        id,
        header,
        columns,
        footer,
        js.undefined,
        enableResizing,
        size,
        minSize,
        maxSize,
        enableHiding
      )

    def WithColumnMeta[CM]: AppliedWithColumnMeta[T, TM, CM] =
      new AppliedWithColumnMeta[T, TM, CM]
  end Applied

  object Applied:
    type NoMeta[T] = Applied[T, Nothing]

  class AppliedWithColumnMeta[T, TM, CM]:
    def apply[A](
      id:              ColumnId,
      accessor:        js.UndefOr[T => A] = js.undefined,
      header:          js.UndefOr[String | (HeaderContext[T, A, TM, CM] => VdomNode)] = js.undefined,
      cell:            js.UndefOr[CellContext[T, A, TM, CM] => VdomNode] = js.undefined,
      footer:          js.UndefOr[HeaderContext[T, A, TM, CM] => VdomNode] = js.undefined,
      meta:            js.UndefOr[CM] = js.undefined,
      // Column Sizing
      enableResizing:  js.UndefOr[Boolean] = js.undefined,
      size:            js.UndefOr[SizePx] = js.undefined,
      minSize:         js.UndefOr[SizePx] = js.undefined,
      maxSize:         js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:    js.UndefOr[Boolean] = js.undefined,
      // Sorting
      enableSorting:   js.UndefOr[Boolean] = js.undefined,
      enableMultiSort: js.UndefOr[Boolean] = js.undefined,
      invertSorting:   js.UndefOr[Boolean] = js.undefined,
      sortDescFirst:   js.UndefOr[Boolean] = js.undefined,
      sortUndefined:   js.UndefOr[UndefinedPriority] = js.undefined,
      sortingFn:       js.UndefOr[BuiltInSorting | SortingFn[T, TM]] = js.undefined
    ): Single[T, A, TM, CM] =
      Single(
        id,
        accessor,
        header,
        cell,
        footer,
        meta,
        // Column Sizing
        enableResizing,
        size,
        minSize,
        maxSize,
        // Column Visibility
        enableHiding,
        // Sorting
        enableSorting,
        enableMultiSort,
        invertSorting,
        sortDescFirst,
        sortUndefined,
        sortingFn
      )

    def group[TM](
      id:             ColumnId,
      header:         js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM] => VdomNode)] = js.undefined,
      columns:        List[ColumnDef[T, Any, TM, Any]],
      footer:         js.UndefOr[HeaderContext[T, Nothing, TM, CM] => VdomNode] = js.undefined,
      meta:           js.UndefOr[CM] = js.undefined,
      // Column Sizing
      enableResizing: js.UndefOr[Boolean] = js.undefined,
      size:           js.UndefOr[SizePx] = js.undefined,
      minSize:        js.UndefOr[SizePx] = js.undefined,
      maxSize:        js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:   js.UndefOr[Boolean] = js.undefined
    ): Group[T, TM, CM] =
      Group(id, header, columns, footer, meta, enableResizing, size, minSize, maxSize, enableHiding)
  end AppliedWithColumnMeta

end ColumnDef
