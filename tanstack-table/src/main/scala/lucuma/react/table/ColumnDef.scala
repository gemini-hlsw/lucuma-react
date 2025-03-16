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

/**
 * @tparam T
 *   The type of the row.
 * @tparam A
 *   The type of the column.
 * @tparam TM
 *   The type of the metadata for the table.
 * @tparam CM
 *   The type of the metadata for the column.
 * @tparam TF
 *   The type of the global filter.
 * @tparam CF
 *   The type of the column filter.
 * @tparam FM
 *   The type of the filter metadata (column specific).
 */
sealed trait ColumnDef[T, A, TM, CM, TF, CF, FM]:
  def id: ColumnId
  def header: js.UndefOr[String | (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode)]
  def footer: js.UndefOr[HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode]
  def meta: js.UndefOr[CM]

  // Column Sizing
  def enableResizing: js.UndefOr[Boolean]
  def size: js.UndefOr[SizePx]
  def minSize: js.UndefOr[SizePx]
  def maxSize: js.UndefOr[SizePx]

  // Column Visibility
  def enableHiding: js.UndefOr[Boolean]

  // Column Pinning
  def enablePinning: js.UndefOr[Boolean]

  // Column Filtering
  def filterFn: js.UndefOr[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]]
  def enableColumnFilter: js.UndefOr[Boolean]

  // Global Filtering
  def enableGlobalFilter: js.UndefOr[Boolean]

  type Type = Column[T, A, TM, CM, TF, CF, FM]

  private[table] def toJs: ColumnDefJs[T, A, CM]

object ColumnDef:
  type Simple[T, A]               = ColumnDef[T, A, Nothing, Nothing, Nothing, Nothing, Nothing]
  type WithTableMeta[T, A, TM]    = ColumnDef[T, A, TM, Nothing, Nothing, Nothing, Nothing]
  type WithColumnMeta[T, A, CM]   = ColumnDef[T, A, Nothing, CM, Nothing, Nothing, Nothing]
  type WithGlobalFilter[T, A, TF] = ColumnDef[T, A, Nothing, Nothing, TF, Nothing, Nothing]
  type WithColumnFilter[T, A, CF] = ColumnDef[T, A, Nothing, Nothing, Nothing, CF, Nothing]
  type WithFilterMeta[T, A, FM]   = ColumnDef[T, A, Nothing, Nothing, Nothing, Nothing, FM]

  /**
   * @tparam T
   *   The type of the row.
   * @tparam A
   *   The type of the column.
   * @tparam TM
   *   The type of the metadata for the table.
   * @tparam CM
   *   The type of the metadata for the column.
   * @tparam TF
   *   The type of the global filter.
   * @tparam CF
   *   The type of the column filter.
   * @tparam FM
   *   The type of the filter metadata (column specific).
   */
  case class Single[T, A, TM, CM, TF, CF, FM](
    private[table] val toJs: ColumnDefJs[T, A, CM]
  ) extends ColumnDef[T, A, TM, CM, TF, CF, FM]:
    type WithTableMeta[TM1]             = Single[T, A, TM1, CM, TF, CF, FM]
    type WithColumnMeta[CM1]            = Single[T, A, TM, CM1, TF, CF, FM]
    type WithGlobalFilter[TF1]          = Single[T, A, TM, CM, TF1, CF, FM]
    type WithColumnFilter[CF1]          = Single[T, A, TM, CM, TF, CF1, FM]
    type WithColumnFilterMeta[CF1, FM1] = Single[T, A, TM, CM, TF, CF1, FM1]

    lazy val id: ColumnId = ColumnId(toJs.id)

    /** WARNING: This mutates the object in-place. */
    def setId(id: ColumnId): Single[T, A, TM, CM, TF, CF, FM] = Single { toJs.id = id.value; toJs }

    lazy val accessor: js.UndefOr[T => A] =
      toJs.accessorFn.map(identity) // This makes implicit conversions kick-in

    /** WARNING: This mutates the object in-place. */
    def setAccessor(accessor: js.UndefOr[T => A]): Single[T, A, TM, CM, TF, CF, FM] = Single {
      toJs.accessorFn = accessor.map(identity); toJs
    }

    lazy val header: js.UndefOr[String | (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode)] =
      toJs.header.map: v =>
        js.typeOf(v) match
          case "string" => v.asInstanceOf[String]
          case renderFn =>
            (headerContext: HeaderContext[T, A, TM, CM, TF, CF, FM]) =>
              VdomNode:
                renderFn
                  .asInstanceOf[js.Function1[raw.buildLibCoreHeadersMod.HeaderContext[T, A], Node]](
                    headerContext.toJs
                  )

    /** WARNING: This mutates the object in-place. */
    def setHeader(
      header: js.UndefOr[String | (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode)]
    ): Single[T, A, TM, CM, TF, CF, FM] = Single {
      toJs.header = header.map:
        _ match
          case s: String                                                       => s
          case renderFn: (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode) =>
            (headerContextJs: raw.buildLibCoreHeadersMod.HeaderContext[T, A]) =>
              renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    lazy val cell: js.UndefOr[CellContext[T, A, TM, CM, TF, CF, FM] => VdomNode] =
      toJs.cell.map(renderFn => cellContext => VdomNode(renderFn(cellContext.toJs)))

    /** WARNING: This mutates the object in-place. */
    def setCell(
      cell: js.UndefOr[CellContext[T, A, TM, CM, TF, CF, FM] => VdomNode]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single {
        toJs.cell =
          cell.map(renderFn => cellContextJs => renderFn(CellContext(cellContextJs)).rawNode);
        toJs
      }

    lazy val footer: js.UndefOr[HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode] =
      toJs.footer.map: renderFn =>
        (headerContext: HeaderContext[T, A, TM, CM, TF, CF, FM]) =>
          VdomNode:
            renderFn
              .asInstanceOf[js.Function1[raw.buildLibCoreHeadersMod.HeaderContext[T, A], Node]](
                headerContext.toJs
              )

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: js.UndefOr[HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode]
    ): Single[T, A, TM, CM, TF, CF, FM] = Single {
      toJs.footer = footer.map: renderFn =>
        headerContextJs => renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    lazy val meta: js.UndefOr[CM] = toJs.meta

    /** WARNING: This mutates the object in-place. */
    def setMeta(meta: CM): Single[T, A, TM, CM, TF, CF, FM] = Single { toJs.meta = meta; toJs }

    // Column Sizing
    lazy val enableResizing: js.UndefOr[Boolean] = toJs.enableResizing

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: js.UndefOr[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableResizing = enableResizing; toJs }

    lazy val size: js.UndefOr[SizePx] = toJs.size.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: js.UndefOr[SizePx]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.size = size.map(_.value); toJs }

    lazy val minSize: js.UndefOr[SizePx] = toJs.minSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: js.UndefOr[SizePx]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.minSize = minSize.map(_.value); toJs }

    lazy val maxSize: js.UndefOr[SizePx] = toJs.maxSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: js.UndefOr[SizePx]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.maxSize = maxSize.map(_.value); toJs }

    // Column Visibility
    lazy val enableHiding: js.UndefOr[Boolean] = toJs.enableHiding

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: js.UndefOr[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableHiding = enableHiding; toJs }

    // Sorting
    lazy val enableSorting: Boolean = toJs.enableSorting.getOrElse(true)

    /** WARNING: This mutates the object in-place. */
    def setEnableSorting(enableSorting: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableSorting = enableSorting; toJs }

    lazy val enableMultiSort: js.UndefOr[Boolean] = toJs.enableMultiSort

    /** WARNING: This mutates the object in-place. */
    def setEnableMultiSort(enableMultiSort: js.UndefOr[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableMultiSort = enableMultiSort; toJs }

    lazy val invertSorting: js.UndefOr[Boolean] = toJs.invertSorting

    /** WARNING: This mutates the object in-place. */
    def setInvertSorting(invertSorting: js.UndefOr[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.invertSorting = invertSorting; toJs }

    lazy val sortDescFirst: js.UndefOr[Boolean] = toJs.sortDescFirst

    /** WARNING: This mutates the object in-place. */
    def setSortDescFirst(sortDescFirst: js.UndefOr[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.sortDescFirst = sortDescFirst; toJs }

    lazy val sortUndefined: js.UndefOr[UndefinedPriority] = // Baffingly, we need this cast.
      toJs.sortUndefined.map(v => UndefinedPriority.fromJs(v.asInstanceOf[UndefinedPriorityJs]))

    /** WARNING: This mutates the object in-place. */
    def setSortUndefined(
      sortUndefined: js.UndefOr[UndefinedPriority]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.sortUndefined = sortUndefined.map(_.toJs); toJs }

    lazy val sortingFn: js.UndefOr[BuiltInSorting | SortingFn[T, TM, CM, TF]] =
      toJs.sortingFn.map(v =>
        js.typeOf(v) match
          case "string" => BuiltInSorting.fromJs(v.asInstanceOf[String])
          case fn       =>
            (rowA: Row[T, TM, CM, TF], rowB: Row[T, TM, CM, TF], colId: ColumnId) =>
              fn.asInstanceOf[raw.buildLibFeaturesRowSortingMod.SortingFn[T]](
                rowA.toJs,
                rowB.toJs,
                colId.value
              ).toInt
      )

    /** WARNING: This mutates the object in-place. */
    def setSortingFn(
      sortingFn: js.UndefOr[BuiltInSorting | SortingFn[T, TM, CM, TF]]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single {
        toJs.sortingFn = sortingFn match
          case builtIn: BuiltInSorting => builtIn.toJs
          case fn                      =>
            (rowA, rowB, colId) =>
              fn.asInstanceOf[SortingFn[T, TM, CM, TF]](Row(rowA), Row(rowB), ColumnId(colId))
                .toDouble
        toJs
      }

    /** WARNING: This mutates the object in-place. */
    def sortableBuiltIn(builtIn: BuiltInSorting): Single[T, A, TM, CM, TF, CF, FM] =
      this.setSortingFn(builtIn).setEnableSorting(true)

    /** WARNING: This mutates the object in-place. */
    def sortableWith[B](f: (A, A) => Int): Single[T, A, TM, CM, TF, CF, FM] =
      val sbfn: SortingFn[T, TM, CM, TF] = (r1, r2, col) =>
        f(r1.getValue[A](col), r2.getValue[A](col))
      this.setSortingFn(sbfn).setEnableSorting(true)

    /** WARNING: This mutates the object in-place. */
    def sortableBy[B](f: A => B)(using ordering: Ordering[B]): Single[T, A, TM, CM, TF, CF, FM] =
      sortableWith((a1, a2) => ordering.compare(f(a1), f(a2)))

    /** WARNING: This mutates the object in-place. */
    def sortable(using Ordering[A]): Single[T, A, TM, CM, TF, CF, FM] = sortableBy(identity)

    // Column Pinning
    lazy val enablePinning: js.UndefOr[Boolean] = toJs.enablePinning

    /** WARNING: This mutates the object in-place. */
    def setEnablePinning(enablePinning: js.UndefOr[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enablePinning = enablePinning; toJs }

    // Column Filtering
    def filterFn: js.UndefOr[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]] =
      toJs.filterFn.map(v =>
        js.typeOf(v) match
          case "string" =>
            BuiltInFilter.fromJs(v.asInstanceOf[String]).asInstanceOf[BuiltInFilter[CF]]
          case fn       =>
            FilterFn.fromJs(fn.asInstanceOf[raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]])
      )

    /** WARNING: This mutates the object in-place. */
    def setFilterFn[F1, FM1](
      filterFn: js.UndefOr[
        BuiltInFilter[F1] | FilterFn[T, TM, CM, TF, F1, FM1] | FilterFn.Type[T, TM, CM, TF, F1, FM1]
      ]
    ): Single[T, A, TM, CM, TF, F1, FM1] =
      Single {
        toJs.filterFn = filterFn match
          case builtIn: BuiltInFilter[F1] => builtIn.toJs
          case ff @ FilterFn(_, _, _)     => ff.asInstanceOf[FilterFn[T, TM, CM, TF, F1, FM1]].toJs
          case fn                         =>
            FilterFn(fn.asInstanceOf[FilterFn.Type[T, TM, CM, TF, F1, FM1]], none, none).toJs
        toJs
      }

    def enableColumnFilter: js.UndefOr[Boolean] = toJs.enableColumnFilter

    /** WARNING: This mutates the object in-place. */
    def setEnableColumnFilter(
      enableColumnFilter: js.UndefOr[Boolean]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableColumnFilter = enableColumnFilter; toJs }

    // Global Filtering
    def enableGlobalFilter: js.UndefOr[Boolean] = toJs.enableGlobalFilter

    /** WARNING: This mutates the object in-place. */
    def setEnableGlobalFilter(
      enableGlobalFilter: js.UndefOr[Boolean]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableGlobalFilter = enableGlobalFilter; toJs }

  end Single

  object Single:
    type Simple[T, A]               = Single[T, A, Nothing, Nothing, Nothing, Nothing, Nothing]
    type WithTableMeta[T, A, TM]    = Single[T, A, TM, Nothing, Nothing, Nothing, Nothing]
    type WithColumnMeta[T, A, CM]   = Single[T, A, Nothing, CM, Nothing, Nothing, Nothing]
    type WithGlobalFilter[T, A, TF] = Single[T, A, Nothing, Nothing, TF, Nothing, Nothing]
    type WithColumnFilter[T, A, CF] = Single[T, A, Nothing, Nothing, Nothing, CF, Nothing]
    type WithFilterMeta[T, A, FM]   = Single[T, A, Nothing, Nothing, Nothing, Nothing, FM]

    def apply[T, A, TM, CM, TF, CF, FM](
      id:                 ColumnId,
      accessor:           js.UndefOr[T => A] = js.undefined,
      header:             js.UndefOr[String | (HeaderContext[T, A, TM, CM, TF, ?, ?] => VdomNode)] =
        js.undefined,
      cell:               js.UndefOr[CellContext[T, A, TM, CM, TF, ?, ?] => VdomNode] = js.undefined,
      footer:             js.UndefOr[HeaderContext[T, A, TM, CM, TF, ?, ?] => VdomNode] = js.undefined,
      meta:               js.UndefOr[CM] = js.undefined,
      // Column Sizing
      enableResizing:     js.UndefOr[Boolean] = js.undefined,
      size:               js.UndefOr[SizePx] = js.undefined,
      minSize:            js.UndefOr[SizePx] = js.undefined,
      maxSize:            js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:       js.UndefOr[Boolean] = js.undefined,
      // Sorting - We override the default of "true" to "false", so that ordering must be explicitly specified for each column.
      enableSorting:      Boolean = false,
      enableMultiSort:    js.UndefOr[Boolean] = js.undefined,
      invertSorting:      js.UndefOr[Boolean] = js.undefined,
      sortDescFirst:      js.UndefOr[Boolean] = js.undefined,
      sortUndefined:      js.UndefOr[UndefinedPriority] = js.undefined,
      sortingFn:          js.UndefOr[BuiltInSorting | SortingFn[T, TM, CM, TF]] = js.undefined,
      // Column Pinning
      enablePinning:      js.UndefOr[Boolean] = js.undefined,
      // Column Filtering
      filterFn:           js.UndefOr[
        BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM] | FilterFn.Type[T, TM, CM, TF, CF, FM]
      ] = js.undefined,
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined,
      // Global Filtering
      enableGlobalFilter: js.UndefOr[Boolean] = js.undefined
    ): Single[T, A, TM, CM, TF, CF, FM] = {
      val p: ColumnDefJs[T, A, CM] =
        new js.Object().asInstanceOf[ColumnDefJs[T, A, CM]]
      p.id = id.value
      Single[T, A, TM, CM, TF, CF, FM](p)
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
        .setEnableSorting(enableSorting)
        .applyOrNot(enableMultiSort, _.setEnableMultiSort(_))
        .applyOrNot(invertSorting, _.setInvertSorting(_))
        .applyOrNot(sortDescFirst, _.setSortDescFirst(_))
        .applyOrNot(sortUndefined, _.setSortUndefined(_))
        .applyOrNot(sortingFn, _.setSortingFn(_))
        .applyOrNot(enablePinning, _.setEnablePinning(_))
        .applyOrNot(filterFn, _.setFilterFn(_))
        .applyOrNot(enableColumnFilter, _.setEnableColumnFilter(_))
        .applyOrNot(enableGlobalFilter, _.setEnableGlobalFilter(_))
    }
  end Single

  /**
   * @tparam T
   *   The type of the row.
   * @tparam A
   *   The type of the column.
   * @tparam TM
   *   The type of the metadata for the table.
   * @tparam CM
   *   The type of the metadata for the column.
   * @tparam TF
   *   The type of the global filter.
   * @tparam CF
   *   The type of the column filter.
   * @tparam FM
   *   The type of the filter metadata (column specific).
   */
  case class Group[T, TM, CM, TF, CF, FM](
    private[table] val toJs: ColumnDefJs[T, Nothing, CM]
  ) extends ColumnDef[T, Nothing, TM, CM, TF, CF, FM]:
    type WithTableMeta[TM1]             = Group[T, TM1, CM, TF, CF, FM]
    type WithColumnMeta[CM1]            = Group[T, TM, CM1, TF, CF, FM]
    type WithGlobalFilter[TF1]          = Group[T, TM, CM, TF1, CF, FM]
    type WithColumnFilter[CF1]          = Group[T, TM, CM, TF, CF1, FM]
    type WithColumnFilterMeta[CF1, FM1] = Group[T, TM, CM, TF, CF1, FM1]

    lazy val id: ColumnId = ColumnId(toJs.id)

    /** WARNING: This mutates the object in-place. */
    def setId(id: ColumnId): Group[T, TM, CM, TF, CF, FM] = Group { toJs.id = id.value; toJs }

    lazy val columns: List[ColumnDef[T, Any, TM, CM, TF, Any, Any]] =
      toJs.columns
        .map(_.toList)
        .toOption
        .orEmpty
        .map(ColumnDef.fromJs(_).asInstanceOf[ColumnDef[T, Any, TM, CM, TF, Any, Any]])

    /** WARNING: This mutates the object in-place. */
    def withColumns(
      columns: List[ColumnDef[T, Any, TM, CM, TF, Any, Any]]
    ): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.columns = columns.map(_.toJs).toJSArray; toJs }

    lazy val header
      : js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode)] =
      toJs.header.map: v =>
        js.typeOf(v) match
          case "string" => v.asInstanceOf[String]
          case renderFn =>
            (headerContext: HeaderContext[T, Nothing, TM, CM, TF, CF, FM]) =>
              VdomNode:
                renderFn
                  .asInstanceOf[js.Function1[
                    raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing],
                    Node
                  ]](headerContext.toJs)

    /** WARNING: This mutates the object in-place. */
    def setHeader(
      header: js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode)]
    ): Group[T, TM, CM, TF, CF, FM] = Group {
      toJs.header = header.map:
        _ match
          case s: String                                                             => s
          case renderFn: (HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode) =>
            (headerContextJs: raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing]) =>
              renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    lazy val footer: js.UndefOr[HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode] =
      toJs.footer.map: renderFn =>
        (headerContext: HeaderContext[T, Nothing, TM, CM, TF, CF, FM]) =>
          VdomNode:
            renderFn
              .asInstanceOf[js.Function1[
                raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing],
                Node
              ]](headerContext.toJs)

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: js.UndefOr[HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode]
    ): Group[T, TM, CM, TF, CF, FM] =
      Group {
        toJs.footer = footer.map: renderFn =>
          headerContextJs => renderFn(HeaderContext(headerContextJs)).rawNode
        toJs
      }

    lazy val meta: js.UndefOr[CM] = toJs.meta

    /** WARNING: This mutates the object in-place. */
    def setMeta(meta: CM): Group[T, TM, CM, TF, CF, FM] = Group { toJs.meta = meta; toJs }

    // Column Sizing
    lazy val enableResizing: js.UndefOr[Boolean] = toJs.enableResizing

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: js.UndefOr[Boolean]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableResizing = enableResizing; toJs }

    lazy val size: js.UndefOr[SizePx] = toJs.size.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: js.UndefOr[SizePx]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.size = size.map(_.value); toJs }

    lazy val minSize: js.UndefOr[SizePx] = toJs.minSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: js.UndefOr[SizePx]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.minSize = minSize.map(_.value); toJs }

    lazy val maxSize: js.UndefOr[SizePx] = toJs.maxSize.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: js.UndefOr[SizePx]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.maxSize = maxSize.map(_.value); toJs }

    // Column Visibility
    lazy val enableHiding: js.UndefOr[Boolean] = toJs.enableHiding

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: js.UndefOr[Boolean]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableHiding = enableHiding; toJs }

    // Column Pinning
    lazy val enablePinning: js.UndefOr[Boolean] = toJs.enablePinning

    /** WARNING: This mutates the object in-place. */
    def setEnablePinning(enablePinning: js.UndefOr[Boolean]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enablePinning = enablePinning; toJs }

    // Column Filtering
    def filterFn: js.UndefOr[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]] =
      toJs.filterFn.map(v =>
        js.typeOf(v) match
          case "string" =>
            BuiltInFilter.fromJs(v.asInstanceOf[String]).asInstanceOf[BuiltInFilter[CF]]
          case fn       =>
            FilterFn.fromJs(fn.asInstanceOf[raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]])
      )

    /** WARNING: This mutates the object in-place. */
    def setFilterFn[F1, FM1](
      filterFn: js.UndefOr[BuiltInFilter[F1] | FilterFn[T, TM, CM, TF, F1, FM1]]
    ): Group[T, TM, CM, TF, F1, FM1] =
      Group {
        toJs.filterFn = filterFn match
          case builtIn: BuiltInFilter[F1] => builtIn.toJs
          case fn                         => fn.asInstanceOf[FilterFn[T, TM, CM, TF, F1, FM1]].toJs
        toJs
      }

    def enableColumnFilter: js.UndefOr[Boolean] = toJs.enableColumnFilter

    /** WARNING: This mutates the object in-place. */
    def setEnableColumnFilter(
      enableColumnFilter: js.UndefOr[Boolean]
    ): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableColumnFilter = enableColumnFilter; toJs }

    // Global Filtering
    def enableGlobalFilter: js.UndefOr[Boolean] = toJs.enableGlobalFilter

    /** WARNING: This mutates the object in-place. */
    def setEnableGlobalFilter(
      enableGlobalFilter: js.UndefOr[Boolean]
    ): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableGlobalFilter = enableGlobalFilter; toJs }

  end Group

  object Group:
    type Simple[T]               = Group[T, Nothing, Nothing, Nothing, Nothing, Nothing]
    type WithTableMeta[T, TM]    = Group[T, TM, Nothing, Nothing, Nothing, Nothing]
    type WithColumnMeta[T, CM]   = Group[T, Nothing, CM, Nothing, Nothing, Nothing]
    type WithGlobalFilter[T, TF] = Group[T, Nothing, Nothing, TF, Nothing, Nothing]
    type WithColumnFilter[T, CF] = Group[T, Nothing, Nothing, Nothing, CF, Nothing]
    type WithFilterMeta[T, FM]   = Group[T, Nothing, Nothing, Nothing, Nothing, FM]

    def apply[T, TM, CM, TF, CF, FM](
      id:                 ColumnId,
      header:             js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM, TF, ?, ?] => VdomNode)] =
        js.undefined,
      columns:            List[ColumnDef[T, ?, TM, CM, TF, ?, ?]],
      footer:             js.UndefOr[HeaderContext[T, Nothing, TM, CM, TF, ?, ?] => VdomNode] = js.undefined,
      meta:               js.UndefOr[CM] = js.undefined,
      // Column Sizing
      enableResizing:     js.UndefOr[Boolean] = js.undefined,
      size:               js.UndefOr[SizePx] = js.undefined,
      minSize:            js.UndefOr[SizePx] = js.undefined,
      maxSize:            js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:       js.UndefOr[Boolean] = js.undefined,
      // Column Pinning
      enablePinning:      js.UndefOr[Boolean] = js.undefined,
      // Column Filtering
      filterFn:           js.UndefOr[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]] = js.undefined,
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined,
      // Global Filtering
      enableGlobalFilter: js.UndefOr[Boolean] = js.undefined
    ): Group[T, TM, CM, TF, CF, FM] = {
      val p: ColumnDefJs[T, Nothing, CM] =
        new js.Object().asInstanceOf[ColumnDefJs[T, Nothing, CM]]
      p.id = id.value
      p.columns = columns.map(_.toJs).toJSArray
      Group[T, TM, CM, TF, CF, FM](p)
        .applyOrNot(header, _.setHeader(_))
        .applyOrNot(meta, _.setMeta(_))
        .applyOrNot(enableResizing, _.setEnableResizing(_))
        .applyOrNot(size, _.setSize(_))
        .applyOrNot(minSize, _.setMinSize(_))
        .applyOrNot(maxSize, _.setMaxSize(_))
        .applyOrNot(enableHiding, _.setEnableHiding(_))
        .applyOrNot(enablePinning, _.setEnablePinning(_))
        .applyOrNot(filterFn, _.setFilterFn(_))
        .applyOrNot(enableColumnFilter, _.setEnableColumnFilter(_))
        .applyOrNot(enableGlobalFilter, _.setEnableGlobalFilter(_))
    }
  end Group

  private[table] def fromJs[T, A, TM, CM, TF, CF, FM](
    colDef: ColumnDefJs[T, A, CM]
  ): ColumnDef[T, A, TM, CM, TF, CF, FM] =
    if (colDef.columns.isDefined)
      Group(colDef.asInstanceOf[ColumnDefJs[T, Nothing, CM]])
        .asInstanceOf[ColumnDef[T, A, TM, CM, TF, CF, FM]]
    else
      Single(colDef)

  def apply[T]: Applied[T, Nothing, Nothing, Nothing] =
    new Applied[T, Nothing, Nothing, Nothing]

  class Applied[T, TM, CM, TF]:
    type Type       = ColumnDef[T, ?, TM, CM, TF, ?, ?]
    type TypeFor[A] = Single[T, A, TM, CM, TF, ?, ?]

    type ColType       = Column[T, ?, TM, CM, TF, ?, ?]
    type ColTypeFor[A] = Column[T, A, TM, CM, TF, ?, ?]

    type WithTableMeta[TM1]    = Applied[T, TM1, CM, TF]
    type WithColumnMeta[CM1]   = Applied[T, TM, CM1, TF]
    type WithGlobalFilter[TF1] = Applied[T, TM, CM, TF1]

    def WithTableMeta[TM1]: WithTableMeta[TM1] =
      new Applied[T, TM1, CM, TF]

    def WithColumnMeta[CM1]: WithColumnMeta[CM1] =
      new Applied[T, TM, CM1, TF]

    def WithGlobalFilter[TF1]: WithGlobalFilter[TF1] =
      new Applied[T, TM, CM, TF1]

    def apply[A, CF, FM](
      id:                 ColumnId,
      accessor:           js.UndefOr[T => A] = js.undefined,
      header:             js.UndefOr[String | (HeaderContext[T, A, TM, CM, TF, ?, ?] => VdomNode)] =
        js.undefined,
      cell:               js.UndefOr[CellContext[T, A, TM, CM, TF, ?, ?] => VdomNode] = js.undefined,
      footer:             js.UndefOr[HeaderContext[T, A, TM, CM, TF, ?, ?] => VdomNode] = js.undefined,
      // Column Sizing
      enableResizing:     js.UndefOr[Boolean] = js.undefined,
      size:               js.UndefOr[SizePx] = js.undefined,
      minSize:            js.UndefOr[SizePx] = js.undefined,
      maxSize:            js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:       js.UndefOr[Boolean] = js.undefined,
      // Sorting - We override the default of "true" to "false", so that ordering must be explicitly specified for each column.
      enableSorting:      Boolean = false,
      enableMultiSort:    js.UndefOr[Boolean] = js.undefined,
      invertSorting:      js.UndefOr[Boolean] = js.undefined,
      sortDescFirst:      js.UndefOr[Boolean] = js.undefined,
      sortUndefined:      js.UndefOr[UndefinedPriority] = js.undefined,
      sortingFn:          js.UndefOr[BuiltInSorting | SortingFn[T, TM, CM, TF]] = js.undefined,
      // Column Pinning
      enablePinning:      js.UndefOr[Boolean] = js.undefined,
      // Column Filtering
      filterFn:           js.UndefOr[
        BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM] | FilterFn.Type[T, TM, CM, TF, CF, FM]
      ] = js.undefined,
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined
    ): Single[T, A, TM, CM, TF, CF, FM] =
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
        sortingFn,
        // Column Pinning
        enablePinning,
        // Column Filtering
        filterFn,
        enableColumnFilter
      )

    def group[CF, FM](
      id:                 ColumnId,
      header:             js.UndefOr[String | (HeaderContext[T, Nothing, TM, CM, TF, ?, ?] => VdomNode)] =
        js.undefined,
      columns:            List[ColumnDef[T, ?, TM, CM, TF, ?, ?]],
      footer:             js.UndefOr[HeaderContext[T, Nothing, TM, CM, TF, ?, ?] => VdomNode] = js.undefined,
      // Column Sizing
      enableResizing:     js.UndefOr[Boolean] = js.undefined,
      size:               js.UndefOr[SizePx] = js.undefined,
      minSize:            js.UndefOr[SizePx] = js.undefined,
      maxSize:            js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:       js.UndefOr[Boolean] = js.undefined,
      // Column Pinning
      enablePinning:      js.UndefOr[Boolean] = js.undefined,
      // Column Filtering
      filterFn:           js.UndefOr[FilterFn[T, TM, CM, TF, CF, FM]] = js.undefined,
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined
    ): Group[T, TM, CM, TF, CF, FM] =
      Group(
        id,
        header,
        columns,
        footer,
        js.undefined,
        // Column Sizing
        enableResizing,
        size,
        minSize,
        maxSize,
        // Column Visibility
        enableHiding,
        // Column Pinning
        enablePinning,
        // Column Filtering
        filterFn,
        enableColumnFilter
      )

  end Applied
end ColumnDef
