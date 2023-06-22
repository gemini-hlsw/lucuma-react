// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.VdomNode
import lucuma.react.SizePx
import lucuma.react.table.facade.*
import lucuma.typed.{tanstackTableCore => raw}

import scalajs.js
import scalajs.js.JSConverters.*

sealed trait ColumnDef[T, A]:
  def id: ColumnId
  def header: js.UndefOr[String | (raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode)]
  def footer: js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode]
  def meta: js.UndefOr[Any]

  // Column Sizing
  def enableResizing: js.UndefOr[Boolean]
  def size: js.UndefOr[SizePx]
  def minSize: js.UndefOr[SizePx]
  def maxSize: js.UndefOr[SizePx]

  // Column Visibility
  def enableHiding: js.UndefOr[Boolean]

  private[table] def toJs: ColumnDefJs[T, A]

object ColumnDef:
  // Should we have a Display case??

  case class Single[T, A](private[table] val toJs: ColumnDefJs[T, A]) extends ColumnDef[T, A]:
    lazy val id: ColumnId = ColumnId(toJs.id)

    /** WARNING: This mutates the object in-place. */
    def setId(id: ColumnId): Single[T, A] = Single { toJs.id = id.value; toJs }

    lazy val accessor: js.UndefOr[T => A] =
      toJs.accessorFn.map(identity) // This makes implicit conversions kick-in

    /** WARNING: This mutates the object in-place. */
    def setAccessor(accessor: js.UndefOr[T => A]): Single[T, A] = Single {
      toJs.accessorFn = accessor.map(identity); toJs
    }

    lazy val header
      : js.UndefOr[String | (raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode)] =
      toJs.header.map(v =>
        js.typeOf(v) match
          case "string" => v.asInstanceOf[String]
          case fn       =>
            fn.asInstanceOf[js.Function1[raw.buildLibCoreHeadersMod.HeaderContext[T, A], Node]]
              .andThen(VdomNode(_))
      )

    /** WARNING: This mutates the object in-place. */
    def setHeader(
      header: js.UndefOr[String | (raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode)]
    ): Single[T, A] = Single {
      toJs.header = header match
        case s: String                                                        => s
        case fn: (raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode) =>
          fn.andThen(_.rawNode)
        case _                                                                => ()
      toJs
    }

    lazy val cell: js.UndefOr[raw.buildLibCoreCellMod.CellContext[T, A] => VdomNode] =
      toJs.cell.map(_.andThen(VdomNode(_)))

    /** WARNING: This mutates the object in-place. */
    def setCell(
      cell: js.UndefOr[raw.buildLibCoreCellMod.CellContext[T, A] => VdomNode]
    ): Single[T, A] =
      Single { toJs.cell = cell.map(_.andThen(_.rawNode)); toJs }

    lazy val footer: js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode] =
      toJs.footer.map(_.andThen(VdomNode(_)))

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode]
    ): Single[T, A] =
      Single { toJs.footer = footer.map(_.andThen(_.rawNode)); toJs }

    lazy val meta: js.UndefOr[Any] = toJs.meta

    /** WARNING: This mutates the object in-place. */
    def setMeta(meta: Any): Single[T, A] = Single { toJs.meta = meta; toJs }

    // Column Sizing
    lazy val enableResizing: js.UndefOr[Boolean] = toJs.enableResizing

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: js.UndefOr[Boolean]): Single[T, A] =
      Single { toJs.enableResizing = enableResizing; toJs }

    lazy val size: js.UndefOr[SizePx] = toJs.size.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: js.UndefOr[SizePx]): Single[T, A] =
      Single { toJs.size = size.map(_.value); toJs }

    lazy val minSize: js.UndefOr[SizePx] = toJs.minSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: js.UndefOr[SizePx]): Single[T, A] =
      Single { toJs.minSize = minSize.map(_.value); toJs }

    lazy val maxSize: js.UndefOr[SizePx] = toJs.maxSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: js.UndefOr[SizePx]): Single[T, A] =
      Single { toJs.maxSize = maxSize.map(_.value); toJs }

    // Column Visibility
    lazy val enableHiding: js.UndefOr[Boolean] = toJs.enableHiding

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: js.UndefOr[Boolean]): Single[T, A] =
      Single { toJs.enableHiding = enableHiding; toJs }

    // Sorting
    lazy val enableSorting: js.UndefOr[Boolean] = toJs.enableSorting

    /** WARNING: This mutates the object in-place. */
    def setEnableSorting(enableSorting: js.UndefOr[Boolean]): Single[T, A] =
      Single { toJs.enableSorting = enableSorting; toJs }

    lazy val enableMultiSort: js.UndefOr[Boolean] = toJs.enableMultiSort

    /** WARNING: This mutates the object in-place. */
    def setEnableMultiSort(enableMultiSort: js.UndefOr[Boolean]): Single[T, A] =
      Single { toJs.enableMultiSort = enableMultiSort; toJs }

    lazy val invertSorting: js.UndefOr[Boolean] = toJs.invertSorting

    /** WARNING: This mutates the object in-place. */
    def setInvertSorting(invertSorting: js.UndefOr[Boolean]): Single[T, A] =
      Single { toJs.invertSorting = invertSorting; toJs }

    lazy val sortDescFirst: js.UndefOr[Boolean] = toJs.sortDescFirst

    /** WARNING: This mutates the object in-place. */
    def setSortDescFirst(sortDescFirst: js.UndefOr[Boolean]): Single[T, A] =
      Single { toJs.sortDescFirst = sortDescFirst; toJs }

    lazy val sortUndefined: js.UndefOr[UndefinedPriority] = // Baffingly, we need this cast.
      toJs.sortUndefined.map(v => UndefinedPriority.fromJs(v.asInstanceOf[UndefinedPriorityJs]))

    /** WARNING: This mutates the object in-place. */
    def setSortUndefined(sortUndefined: js.UndefOr[UndefinedPriority]): Single[T, A] =
      Single { toJs.sortUndefined = sortUndefined.map(_.toJs); toJs }

    lazy val sortingFn: js.UndefOr[BuiltInSorting | SortingFn[T]] =
      toJs.sortingFn.map(v =>
        js.typeOf(v) match
          case "string" => BuiltInSorting.fromJs(v.asInstanceOf[String])
          case fn       =>
            (rowA: Row[T], rowB: Row[T], colId: ColumnId) =>
              fn.asInstanceOf[raw.buildLibFeaturesSortingMod.SortingFn[T]](rowA.toJs,
                                                                           rowB.toJs,
                                                                           colId.value
              ).toInt
      )

    /** WARNING: This mutates the object in-place. */
    def setSortingFn(sortingFn: js.UndefOr[BuiltInSorting | SortingFn[T]]): Single[T, A] = Single {
      toJs.sortingFn = sortingFn match
        case builtIn: BuiltInSorting => builtIn.toJs
        case fn                      =>
          (rowA, rowB, colId) =>
            fn.asInstanceOf[SortingFn[T]](Row(rowA), Row(rowB), ColumnId(colId)).toDouble
      toJs
    }

    /** WARNING: This mutates the object in-place. */
    def sortableBuiltIn(builtIn: BuiltInSorting): Single[T, A] =
      this.setSortingFn(builtIn).setEnableSorting(true)

    /** WARNING: This mutates the object in-place. */
    def sortableWith[B](f: (A, A) => Int): Single[T, A] =
      val sbfn: SortingFn[T] = (r1, r2, col) => f(r1.getValue[A](col), r2.getValue[A](col))
      this.setSortingFn(sbfn).setEnableSorting(true)

    /** WARNING: This mutates the object in-place. */
    def sortableBy[B](f: A => B)(using ordering: Ordering[B]): ColumnDef[T, A] =
      sortableWith((a1, a2) => ordering.compare(f(a1), f(a2)))

    /** WARNING: This mutates the object in-place. */
    def sortable(using Ordering[A]) = sortableBy(identity)

  end Single

  object Single:
    def apply[T, A](
      id:              ColumnId,
      accessor:        js.UndefOr[T => A] = js.undefined,
      header:          js.UndefOr[String | (raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode)] =
        js.undefined,
      cell:            js.UndefOr[raw.buildLibCoreCellMod.CellContext[T, A] => VdomNode] = js.undefined,
      footer:          js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode] = js.undefined,
      meta:            js.UndefOr[Any] = js.undefined,
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
      sortingFn:       js.UndefOr[BuiltInSorting | SortingFn[T]] = js.undefined
    ): Single[T, A] = {
      val p: ColumnDefJs[T, A] = new js.Object().asInstanceOf[ColumnDefJs[T, A]]
      p.id = id.value
      Single(p)
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

  case class Group[T](private[table] val toJs: ColumnDefJs[T, Nothing])
      extends ColumnDef[T, Nothing]:
    lazy val id: ColumnId = ColumnId(toJs.id)

    /** WARNING: This mutates the object in-place. */
    def setId(id: ColumnId): Group[T] = Group { toJs.id = id.value; toJs }

    lazy val columns: List[ColumnDef[T, Any]] =
      toJs.columns.map(_.toList).toOption.orEmpty.map(ColumnDef.fromJs[T, Any])

    /** WARNING: This mutates the object in-place. */
    def withColumns(columns: List[ColumnDef[T, Any]]): Group[T] =
      Group { toJs.columns = columns.map(_.toJs).toJSArray; toJs }

    lazy val header
      : js.UndefOr[String | (raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode)] =
      toJs.header.map(v =>
        js.typeOf(v) match
          case "string" => v.asInstanceOf[String]
          case fn       =>
            fn.asInstanceOf[js.Function1[raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing],
                                         Node
            ]].andThen(VdomNode(_))
      )

    /** WARNING: This mutates the object in-place. */
    def setHeader(
      header: js.UndefOr[
        String | (raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode)
      ]
    ): Group[T] = Group {
      toJs.header = header match
        case s: String                                                              => s
        case fn: (raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode) =>
          fn.andThen(_.rawNode)
        case _                                                                      => ()
      toJs
    }

    lazy val footer: js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode] =
      toJs.footer.map(_.andThen(VdomNode(_)))

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode]
    ): Group[T] =
      Group { toJs.footer = footer.map(_.andThen(_.rawNode)); toJs }

    lazy val meta: js.UndefOr[Any] = toJs.meta

    /** WARNING: This mutates the object in-place. */
    def setMeta(meta: Any): Group[T] = Group { toJs.meta = meta; toJs }

    // Column Sizing
    lazy val enableResizing: js.UndefOr[Boolean] = toJs.enableResizing

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: js.UndefOr[Boolean]): Group[T] =
      Group { toJs.enableResizing = enableResizing; toJs }

    lazy val size: js.UndefOr[SizePx] = toJs.size.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: js.UndefOr[SizePx]): Group[T] =
      Group { toJs.size = size.map(_.value); toJs }

    lazy val minSize: js.UndefOr[SizePx] = toJs.minSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: js.UndefOr[SizePx]): Group[T] =
      Group { toJs.minSize = minSize.map(_.value); toJs }

    lazy val maxSize: js.UndefOr[SizePx] = toJs.maxSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: js.UndefOr[SizePx]): Group[T] =
      Group { toJs.maxSize = maxSize.map(_.value); toJs }

    // Column Visibility
    lazy val enableHiding: js.UndefOr[Boolean] = toJs.enableHiding

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: js.UndefOr[Boolean]): Group[T] =
      Group { toJs.enableHiding = enableHiding; toJs }

  end Group

  object Group:
    def apply[T](
      id:             ColumnId,
      header:         js.UndefOr[
        String | (raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode)
      ] = js.undefined,
      columns:        List[ColumnDef[T, Any]],
      footer:         js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode] =
        js.undefined,
      meta:           js.UndefOr[Any] = js.undefined,
      // Column Sizing
      enableResizing: js.UndefOr[Boolean] = js.undefined,
      size:           js.UndefOr[SizePx] = js.undefined,
      minSize:        js.UndefOr[SizePx] = js.undefined,
      maxSize:        js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:   js.UndefOr[Boolean] = js.undefined
    ): Group[T] = {
      val p: ColumnDefJs[T, Nothing] = new js.Object().asInstanceOf[ColumnDefJs[T, Nothing]]
      p.id = id.value
      p.columns = columns.map(_.toJs).toJSArray
      Group(p)
        .applyOrNot(header, _.setHeader(_))
        .applyOrNot(meta, _.setMeta(_))
        .applyOrNot(enableResizing, _.setEnableResizing(_))
        .applyOrNot(size, _.setSize(_))
        .applyOrNot(minSize, _.setMinSize(_))
        .applyOrNot(maxSize, _.setMaxSize(_))
        .applyOrNot(enableHiding, _.setEnableHiding(_))
    }
  end Group

  private[table] def fromJs[T, A](colDef: ColumnDefJs[T, A]): ColumnDef[T, A] =
    if (colDef.columns.isDefined)
      Group(colDef.asInstanceOf[ColumnDefJs[T, Nothing]]).asInstanceOf[ColumnDef[T, A]]
    else
      Single(colDef)

  def apply[T]: Applied[T] =
    new Applied[T]

  class Applied[T]:
    def apply[A](
      id:              ColumnId,
      accessor:        js.UndefOr[T => A] = js.undefined,
      header:          js.UndefOr[String | (raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode)] =
        js.undefined,
      cell:            js.UndefOr[raw.buildLibCoreCellMod.CellContext[T, A] => VdomNode] = js.undefined,
      footer:          js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, A] => VdomNode] = js.undefined,
      meta:            js.UndefOr[Any] = js.undefined,
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
      sortingFn:       js.UndefOr[BuiltInSorting | SortingFn[T]] = js.undefined
    ): Single[T, A] =
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

    def group(
      id:             ColumnId,
      header:         js.UndefOr[
        String | (raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode)
      ] = js.undefined,
      columns:        List[ColumnDef[T, Any]],
      footer:         js.UndefOr[raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing] => VdomNode] =
        js.undefined,
      meta:           js.UndefOr[Any] = js.undefined,
      // Column Sizing
      enableResizing: js.UndefOr[Boolean] = js.undefined,
      size:           js.UndefOr[SizePx] = js.undefined,
      minSize:        js.UndefOr[SizePx] = js.undefined,
      maxSize:        js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:   js.UndefOr[Boolean] = js.undefined
    ): Group[T] =
      Group(id, header, columns, footer, meta, enableResizing, size, minSize, maxSize, enableHiding)
  end Applied

end ColumnDef
