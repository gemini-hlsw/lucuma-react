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
  def header: Option[String | (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode)]
  def footer: Option[HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode]
  def meta: Option[CM]

  // Column Sizing
  def enableResizing: Option[Boolean]
  def size: Option[SizePx]
  def minSize: Option[SizePx]
  def maxSize: Option[SizePx]

  // Column Visibility
  def enableHiding: Option[Boolean]

  // Column Pinning
  def enablePinning: Option[Boolean]

  // Column Filtering
  def enableColumnFilter: Option[Boolean]
  def filterFn: Option[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]]

  // Global Filtering
  def enableGlobalFilter: Option[Boolean]

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

    lazy val accessor: Option[T => A] =
      toJs.accessorFn.toOption.map(identity) // This makes implicit conversions kick-in

    /** WARNING: This mutates the object in-place. */
    def setAccessor(accessor: Option[T => A]): Single[T, A, TM, CM, TF, CF, FM] = Single {
      toJs.accessorFn = accessor.orUndefined.map(identity); toJs
    }

    /** WARNING: This mutates the object in-place. */
    inline def withAccessor(accessor: T => A): Single[T, A, TM, CM, TF, CF, FM] =
      setAccessor(accessor.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutAccessor: Single[T, A, TM, CM, TF, CF, FM] =
      setAccessor(none)

    lazy val header: Option[String | (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode)] =
      toJs.header.toOption.map: v =>
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
      header: Option[String | (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode)]
    ): Single[T, A, TM, CM, TF, CF, FM] = Single {
      toJs.header = header.orUndefined.map:
        _ match
          case s: String                                                       => s
          case renderFn: (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode) =>
            (headerContextJs: raw.buildLibCoreHeadersMod.HeaderContext[T, A]) =>
              renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    /** WARNING: This mutates the object in-place. */
    inline def withHeader(
      header: String | (HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode)
    ): Single[T, A, TM, CM, TF, CF, FM] =
      setHeader(header.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutHeader: Single[T, A, TM, CM, TF, CF, FM] =
      setHeader(none)

    lazy val cell: Option[CellContext[T, A, TM, CM, TF, CF, FM] => VdomNode] =
      toJs.cell.toOption.map(renderFn => cellContext => VdomNode(renderFn(cellContext.toJs)))

    /** WARNING: This mutates the object in-place. */
    def setCell(
      cell: Option[CellContext[T, A, TM, CM, TF, CF, FM] => VdomNode]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single {
        toJs.cell = cell.orUndefined.map: renderFn =>
          cellContextJs => renderFn(CellContext(cellContextJs)).rawNode
        toJs
      }

    /** WARNING: This mutates the object in-place. */
    inline def withCell(
      cell: CellContext[T, A, TM, CM, TF, CF, FM] => VdomNode
    ): Single[T, A, TM, CM, TF, CF, FM] =
      setCell(cell.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutCell: Single[T, A, TM, CM, TF, CF, FM] =
      setCell(none)

    lazy val footer: Option[HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode] =
      toJs.footer.toOption.map: renderFn =>
        (headerContext: HeaderContext[T, A, TM, CM, TF, CF, FM]) =>
          VdomNode:
            renderFn
              .asInstanceOf[js.Function1[raw.buildLibCoreHeadersMod.HeaderContext[T, A], Node]](
                headerContext.toJs
              )

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: Option[HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode]
    ): Single[T, A, TM, CM, TF, CF, FM] = Single {
      toJs.footer = footer.orUndefined.map: renderFn =>
        headerContextJs => renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    /** WARNING: This mutates the object in-place. */
    inline def withFooter(
      footer: HeaderContext[T, A, TM, CM, TF, CF, FM] => VdomNode
    ): Single[T, A, TM, CM, TF, CF, FM] =
      setFooter(footer.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutFooter: Single[T, A, TM, CM, TF, CF, FM] =
      setFooter(none)

    lazy val meta: Option[CM] = toJs.meta.toOption

    /** WARNING: This mutates the object in-place. */
    def setMeta[CM1](meta: Option[CM1]): Single[T, A, TM, CM1, TF, CF, FM] =
      Single {
        val toJs1 = toJs.asInstanceOf[ColumnDefJs[T, A, CM1]]
        toJs1.meta = meta.orUndefined
        toJs1
      }

    /** WARNING: This mutates the object in-place. */
    inline def withMeta[CM1](meta: CM1): Single[T, A, TM, CM1, TF, CF, FM] =
      setMeta(meta.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutMeta: Single[T, A, TM, Nothing, TF, CF, FM] =
      setMeta(none)

    // Column Sizing
    lazy val enableResizing: Option[Boolean] = toJs.enableResizing.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: Option[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableResizing = enableResizing.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableResizing(enableResizing: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      setEnableResizing(enableResizing.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableResizing: Single[T, A, TM, CM, TF, CF, FM] =
      setEnableResizing(none)

    lazy val size: Option[SizePx] = toJs.size.toOption.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: Option[SizePx]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.size = size.orUndefined.map(_.value); toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withSize(size: SizePx): Single[T, A, TM, CM, TF, CF, FM] =
      setSize(size.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutSize: Single[T, A, TM, CM, TF, CF, FM] =
      setSize(none)

    lazy val minSize: Option[SizePx] = toJs.minSize.toOption.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: Option[SizePx]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.minSize = minSize.orUndefined.map(_.value); toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withMinSize(minSize: SizePx): Single[T, A, TM, CM, TF, CF, FM] =
      setMinSize(minSize.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutMinSize: Single[T, A, TM, CM, TF, CF, FM] =
      setMinSize(none)

    lazy val maxSize: Option[SizePx] = toJs.maxSize.toOption.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: Option[SizePx]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.maxSize = maxSize.orUndefined.map(_.value); toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withMaxSize(maxSize: SizePx): Single[T, A, TM, CM, TF, CF, FM] =
      setMaxSize(maxSize.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutMaxSize: Single[T, A, TM, CM, TF, CF, FM] =
      setMaxSize(none)

    // Column Visibility
    lazy val enableHiding: Option[Boolean] = toJs.enableHiding.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: Option[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableHiding = enableHiding.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableHiding(enableHiding: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      setEnableHiding(enableHiding.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableHiding: Single[T, A, TM, CM, TF, CF, FM] =
      setEnableHiding(none)

    // Sorting
    lazy val enableSorting: Option[Boolean] = toJs.enableSorting.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableSorting(enableSorting: Option[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableSorting = enableSorting.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableSorting(enableSorting: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      setEnableSorting(enableSorting.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableSorting: Single[T, A, TM, CM, TF, CF, FM] =
      setEnableSorting(none)

    lazy val enableMultiSort: Option[Boolean] = toJs.enableMultiSort.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableMultiSort(enableMultiSort: Option[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableMultiSort = enableMultiSort.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableMultiSort(enableMultiSort: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      setEnableMultiSort(enableMultiSort.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableMultiSort: Single[T, A, TM, CM, TF, CF, FM] =
      setEnableMultiSort(none)

    lazy val invertSorting: Option[Boolean] = toJs.invertSorting.toOption

    /** WARNING: This mutates the object in-place. */
    def setInvertSorting(invertSorting: Option[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.invertSorting = invertSorting.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withInvertSorting(invertSorting: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      setInvertSorting(invertSorting.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutInvertSorting: Single[T, A, TM, CM, TF, CF, FM] =
      setInvertSorting(none)

    lazy val sortDescFirst: Option[Boolean] = toJs.sortDescFirst.toOption

    /** WARNING: This mutates the object in-place. */
    def setSortDescFirst(sortDescFirst: Option[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.sortDescFirst = sortDescFirst.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withSortDescFirst(sortDescFirst: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      setSortDescFirst(sortDescFirst.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutSortDescFirst: Single[T, A, TM, CM, TF, CF, FM] =
      setSortDescFirst(none)

    lazy val sortUndefined: Option[UndefinedPriority] = // Baffingly, we need this cast.
      toJs.sortUndefined.toOption.map(v =>
        UndefinedPriority.fromJs(v.asInstanceOf[UndefinedPriorityJs])
      )

    /** WARNING: This mutates the object in-place. */
    def setSortUndefined(
      sortUndefined: Option[UndefinedPriority]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.sortUndefined = sortUndefined.orUndefined.map(_.toJs); toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withSortUndefined(
      sortUndefined: UndefinedPriority
    ): Single[T, A, TM, CM, TF, CF, FM] =
      setSortUndefined(sortUndefined.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutSortUndefined: Single[T, A, TM, CM, TF, CF, FM] =
      setSortUndefined(none)

    lazy val sortingFn: Option[BuiltInSorting | SortingFn[T, TM, CM, TF]] =
      toJs.sortingFn.toOption.map(v =>
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
      sortingFn: Option[BuiltInSorting | SortingFn[T, TM, CM, TF]]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single {
        toJs.sortingFn = sortingFn.orUndefined.map:
          case builtIn: BuiltInSorting => builtIn.toJs
          case fn                      =>
            (rowA: raw.buildLibTypesMod.Row[T], rowB: raw.buildLibTypesMod.Row[T], colId: String) =>
              fn.asInstanceOf[SortingFn[T, TM, CM, TF]](Row(rowA), Row(rowB), ColumnId(colId))
                .toDouble
        toJs
      }

    /** WARNING: This mutates the object in-place. */
    inline def withSortingFn(
      sortingFn: BuiltInSorting | SortingFn[T, TM, CM, TF]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      setSortingFn(sortingFn.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutSortingFn: Single[T, A, TM, CM, TF, CF, FM] =
      setSortingFn(none)

    /** WARNING: This mutates the object in-place. */
    def sortableBuiltIn(builtIn: BuiltInSorting): Single[T, A, TM, CM, TF, CF, FM] =
      this.setSortingFn(builtIn.some)

    /** WARNING: This mutates the object in-place. */
    def sortableWith[B](f: (A, A) => Int): Single[T, A, TM, CM, TF, CF, FM] =
      val sbfn: SortingFn[T, TM, CM, TF] = (r1, r2, col) =>
        f(r1.getValue[A](col), r2.getValue[A](col))
      this.setSortingFn(sbfn.some)

    /** WARNING: This mutates the object in-place. */
    def sortableBy[B](f: A => B)(using ordering: Ordering[B]): Single[T, A, TM, CM, TF, CF, FM] =
      sortableWith((a1, a2) => ordering.compare(f(a1), f(a2)))

    /** WARNING: This mutates the object in-place. */
    def sortable(using Ordering[A]): Single[T, A, TM, CM, TF, CF, FM] = sortableBy(identity)

    // Column Pinning
    lazy val enablePinning: Option[Boolean] = toJs.enablePinning.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnablePinning(enablePinning: Option[Boolean]): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enablePinning = enablePinning.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnablePinning(enablePinning: Boolean): Single[T, A, TM, CM, TF, CF, FM] =
      setEnablePinning(enablePinning.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnablePinning: Single[T, A, TM, CM, TF, CF, FM] =
      setEnablePinning(none)

    // Column Filtering
    lazy val filterFn: Option[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]] =
      toJs.filterFn.toOption.map(v =>
        js.typeOf(v) match
          case "string" =>
            BuiltInFilter.fromJs(v.asInstanceOf[String]).asInstanceOf[BuiltInFilter[CF]]
          case fn       =>
            FilterFn.fromJs(fn.asInstanceOf[raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]])
      )

    /** WARNING: This mutates the object in-place. */
    def setFilterFn[F1, FM1](
      filterFn: Option[
        BuiltInFilter[F1] | FilterFn[T, TM, CM, TF, F1, FM1] | FilterFn.Type[T, TM, CM, TF, F1, FM1]
      ]
    ): Single[T, A, TM, CM, TF, F1, FM1] =
      Single {
        toJs.filterFn = filterFn.orUndefined.map:
          case builtIn: BuiltInFilter[F1] => builtIn.toJs
          case ff @ FilterFn(_, _, _)     => ff.asInstanceOf[FilterFn[T, TM, CM, TF, F1, FM1]].toJs
          case fn                         =>
            FilterFn(fn.asInstanceOf[FilterFn.Type[T, TM, CM, TF, F1, FM1]], none, none).toJs
        toJs
      }

    /** WARNING: This mutates the object in-place. */
    inline def withFilterFn[F1, FM1](
      filterFn: BuiltInFilter[F1] | FilterFn[T, TM, CM, TF, F1, FM1] |
        FilterFn.Type[T, TM, CM, TF, F1, FM1]
    ): Single[T, A, TM, CM, TF, F1, FM1] =
      setFilterFn(filterFn.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutFilterFn[F1, FM1]: Single[T, A, TM, CM, TF, F1, FM1] =
      setFilterFn[F1, FM1](none)

    lazy val enableColumnFilter: Option[Boolean] = toJs.enableColumnFilter.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableColumnFilter(
      enableColumnFilter: Option[Boolean]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableColumnFilter = enableColumnFilter.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableColumnFilter(
      enableColumnFilter: Boolean
    ): Single[T, A, TM, CM, TF, CF, FM] =
      setEnableColumnFilter(enableColumnFilter.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableColumnFilter: Single[T, A, TM, CM, TF, CF, FM] =
      setEnableColumnFilter(none)

    // Global Filtering
    lazy val enableGlobalFilter: Option[Boolean] = toJs.enableGlobalFilter.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableGlobalFilter(
      enableGlobalFilter: Option[Boolean]
    ): Single[T, A, TM, CM, TF, CF, FM] =
      Single { toJs.enableGlobalFilter = enableGlobalFilter.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableGlobalFilter(
      enableGlobalFilter: Boolean
    ): Single[T, A, TM, CM, TF, CF, FM] =
      setEnableGlobalFilter(enableGlobalFilter.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableGlobalFilter: Single[T, A, TM, CM, TF, CF, FM] =
      setEnableGlobalFilter(none)

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
      header:             js.UndefOr[String | (HeaderContext[T, A, TM, ?, TF, ?, ?] => VdomNode)] =
        js.undefined,
      cell:               js.UndefOr[CellContext[T, A, TM, ?, TF, ?, ?] => VdomNode] = js.undefined,
      footer:             js.UndefOr[HeaderContext[T, A, TM, ?, TF, ?, ?] => VdomNode] = js.undefined,
      meta:               js.UndefOr[CM] = js.undefined,
      // Column Sizing
      enableResizing:     js.UndefOr[Boolean] = js.undefined,
      size:               js.UndefOr[SizePx] = js.undefined,
      minSize:            js.UndefOr[SizePx] = js.undefined,
      maxSize:            js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:       js.UndefOr[Boolean] = js.undefined,
      enableSorting:      js.UndefOr[Boolean] = js.undefined,
      enableMultiSort:    js.UndefOr[Boolean] = js.undefined,
      invertSorting:      js.UndefOr[Boolean] = js.undefined,
      sortDescFirst:      js.UndefOr[Boolean] = js.undefined,
      sortUndefined:      js.UndefOr[UndefinedPriority] = js.undefined,
      sortingFn:          js.UndefOr[BuiltInSorting | SortingFn[T, TM, CM, TF]] = js.undefined,
      // Column Pinning
      enablePinning:      js.UndefOr[Boolean] = js.undefined,
      // Column Filtering
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined,
      filterFn:           js.UndefOr[
        BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM] | FilterFn.Type[T, TM, CM, TF, CF, FM]
      ] = js.undefined,
      // Global Filtering
      enableGlobalFilter: js.UndefOr[Boolean] = js.undefined
    ): Single[T, A, TM, CM, TF, CF, FM] = {
      val autoEnableResizing: Boolean =
        !enableResizing.contains(false) && (
          enableResizing.contains(true) ||
            minSize.isDefined ||
            maxSize.isDefined
        )

      val autoEnableSorting: Boolean =
        !enableSorting.contains(false) && (
          enableSorting.contains(true) ||
            enableMultiSort.contains(true) ||
            invertSorting.isDefined ||
            sortDescFirst.isDefined ||
            sortUndefined.isDefined ||
            sortingFn.isDefined
        )

      val autoEnableColumnFilter: Boolean =
        !enableColumnFilter.contains(false) && (
          enableColumnFilter.contains(true) ||
            filterFn.isDefined
        )

      val p: ColumnDefJs[T, A, CM] =
        new js.Object().asInstanceOf[ColumnDefJs[T, A, CM]]
      p.id = id.value

      Single[T, A, TM, CM, TF, CF, FM](p)
        .applyOrNot(accessor, _.withAccessor(_))
        .applyOrNot(header, _.withHeader(_))
        .applyOrNot(footer, _.withFooter(_))
        .applyOrNot(cell, _.withCell(_))
        .applyOrNot(meta, _.withMeta(_))
        .applyOrNot(enableResizing, _.withEnableResizing(_))
        .applyWhen(autoEnableResizing, _.withEnableResizing(true))
        .applyOrNot(size, _.withSize(_))
        .applyOrNot(minSize, _.withMinSize(_))
        .applyOrNot(maxSize, _.withMaxSize(_))
        .applyOrNot(enableHiding, _.withEnableHiding(_))
        .applyOrNot(enableSorting, _.withEnableSorting(_))
        .applyWhen(autoEnableSorting, _.withEnableSorting(true))
        .applyOrNot(enableMultiSort, _.withEnableMultiSort(_))
        .applyOrNot(invertSorting, _.withInvertSorting(_))
        .applyOrNot(sortDescFirst, _.withSortDescFirst(_))
        .applyOrNot(sortUndefined, _.withSortUndefined(_))
        .applyOrNot(sortingFn, _.withSortingFn(_))
        .applyOrNot(enablePinning, _.withEnablePinning(_))
        .applyOrNot(enableColumnFilter, _.withEnableColumnFilter(_))
        .applyWhen(autoEnableColumnFilter, _.withEnableColumnFilter(true))
        .applyOrNot(filterFn, _.withFilterFn(_))
        .applyOrNot(enableGlobalFilter, _.withEnableGlobalFilter(_))
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

    lazy val header: Option[String | (HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode)] =
      toJs.header.toOption.map: v =>
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
      header: Option[String | (HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode)]
    ): Group[T, TM, CM, TF, CF, FM] = Group {
      toJs.header = header.orUndefined.map:
        case s: String                                                             => s
        case renderFn: (HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode) =>
          (headerContextJs: raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing]) =>
            renderFn(HeaderContext(headerContextJs)).rawNode
      toJs
    }

    /** WARNING: This mutates the object in-place. */
    inline def withHeader(
      header: String | (HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode)
    ): Group[T, TM, CM, TF, CF, FM] =
      setHeader(header.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutHeader: Group[T, TM, CM, TF, CF, FM] =
      setHeader(none)

    lazy val footer: Option[HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode] =
      toJs.footer.toOption.map: renderFn =>
        (headerContext: HeaderContext[T, Nothing, TM, CM, TF, CF, FM]) =>
          VdomNode:
            renderFn
              .asInstanceOf[js.Function1[
                raw.buildLibCoreHeadersMod.HeaderContext[T, Nothing],
                Node
              ]](headerContext.toJs)

    /** WARNING: This mutates the object in-place. */
    def setFooter(
      footer: Option[HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode]
    ): Group[T, TM, CM, TF, CF, FM] =
      Group {
        toJs.footer = footer.orUndefined.map: renderFn =>
          headerContextJs => renderFn(HeaderContext(headerContextJs)).rawNode
        toJs
      }

    /** WARNING: This mutates the object in-place. */
    inline def withFooter(
      footer: HeaderContext[T, Nothing, TM, CM, TF, CF, FM] => VdomNode
    ): Group[T, TM, CM, TF, CF, FM] =
      setFooter(footer.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutFooter: Group[T, TM, CM, TF, CF, FM] =
      setFooter(none)

    lazy val meta: Option[CM] = toJs.meta.toOption

    /** WARNING: This mutates the object in-place. */
    def setMeta(meta: Option[CM]): Group[T, TM, CM, TF, CF, FM] = Group {
      toJs.meta = meta.orUndefined; toJs
    }

    /** WARNING: This mutates the object in-place. */
    inline def withMeta(meta: CM): Group[T, TM, CM, TF, CF, FM] =
      setMeta(meta.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutMeta: Group[T, TM, CM, TF, CF, FM] =
      setMeta(none)

    // Column Sizing
    lazy val enableResizing: Option[Boolean] = toJs.enableResizing.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableResizing(enableResizing: Option[Boolean]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableResizing = enableResizing.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableResizing(enableResizing: Boolean): Group[T, TM, CM, TF, CF, FM] =
      setEnableResizing(enableResizing.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableResizing: Group[T, TM, CM, TF, CF, FM] =
      setEnableResizing(none)

    lazy val size: Option[SizePx] = toJs.size.toOption.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setSize(size: Option[SizePx]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.size = size.orUndefined.map(_.value); toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withSize(size: SizePx): Group[T, TM, CM, TF, CF, FM] =
      setSize(size.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutSize: Group[T, TM, CM, TF, CF, FM] =
      setSize(none)

    lazy val minSize: Option[SizePx] = toJs.minSize.toOption.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMinSize(minSize: Option[SizePx]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.minSize = minSize.orUndefined.map(_.value); toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withMinSize(minSize: SizePx): Group[T, TM, CM, TF, CF, FM] =
      setMinSize(minSize.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutMinSize: Group[T, TM, CM, TF, CF, FM] =
      setMinSize(none)

    lazy val maxSize: Option[SizePx] = toJs.maxSize.toOption.map(SizePx(_))

    /** WARNING: This mutates the object in-place. */
    def setMaxSize(maxSize: Option[SizePx]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.maxSize = maxSize.orUndefined.map(_.value); toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withMaxSize(maxSize: SizePx): Group[T, TM, CM, TF, CF, FM] =
      setMaxSize(maxSize.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutMaxSize: Group[T, TM, CM, TF, CF, FM] =
      setMaxSize(none)

    // Column Visibility
    lazy val enableHiding: Option[Boolean] = toJs.enableHiding.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableHiding(enableHiding: Option[Boolean]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableHiding = enableHiding.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableHiding(enableHiding: Boolean): Group[T, TM, CM, TF, CF, FM] =
      setEnableHiding(enableHiding.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableHiding: Group[T, TM, CM, TF, CF, FM] =
      setEnableHiding(none)

    // Column Pinning
    lazy val enablePinning: Option[Boolean] = toJs.enablePinning.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnablePinning(enablePinning: Option[Boolean]): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enablePinning = enablePinning.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnablePinning(enablePinning: Boolean): Group[T, TM, CM, TF, CF, FM] =
      setEnablePinning(enablePinning.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnablePinning: Group[T, TM, CM, TF, CF, FM] =
      setEnablePinning(none)

    // Column Filtering

    lazy val enableColumnFilter: Option[Boolean] = toJs.enableColumnFilter.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableColumnFilter(
      enableColumnFilter: Option[Boolean]
    ): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableColumnFilter = enableColumnFilter.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableColumnFilter(
      enableColumnFilter: Boolean
    ): Group[T, TM, CM, TF, CF, FM] =
      setEnableColumnFilter(enableColumnFilter.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableColumnFilter: Group[T, TM, CM, TF, CF, FM]             =
      setEnableColumnFilter(none)
    lazy val filterFn: Option[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]] =
      toJs.filterFn.toOption.map: v =>
        js.typeOf(v) match
          case "string" =>
            BuiltInFilter.fromJs(v.asInstanceOf[String]).asInstanceOf[BuiltInFilter[CF]]
          case fn       =>
            FilterFn.fromJs(fn.asInstanceOf[raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]])

    /** WARNING: This mutates the object in-place. */
    def setFilterFn[F1, FM1](
      filterFn: Option[BuiltInFilter[F1] | FilterFn[T, TM, CM, TF, F1, FM1]]
    ): Group[T, TM, CM, TF, F1, FM1] =
      Group {
        toJs.filterFn = filterFn.orUndefined.map:
          case builtIn: BuiltInFilter[F1] => builtIn.toJs
          case fn                         => fn.asInstanceOf[FilterFn[T, TM, CM, TF, F1, FM1]].toJs
        toJs
      }

    /** WARNING: This mutates the object in-place. */
    inline def withFilterFn[F1, FM1](
      filterFn: BuiltInFilter[F1] | FilterFn[T, TM, CM, TF, F1, FM1]
    ): Group[T, TM, CM, TF, F1, FM1] =
      setFilterFn(filterFn.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutFilterFn[F1, FM1]: Group[T, TM, CM, TF, F1, FM1] =
      setFilterFn[F1, FM1](none)

    // Global Filtering
    lazy val enableGlobalFilter: Option[Boolean] = toJs.enableGlobalFilter.toOption

    /** WARNING: This mutates the object in-place. */
    def setEnableGlobalFilter(
      enableGlobalFilter: Option[Boolean]
    ): Group[T, TM, CM, TF, CF, FM] =
      Group { toJs.enableGlobalFilter = enableGlobalFilter.orUndefined; toJs }

    /** WARNING: This mutates the object in-place. */
    inline def withEnableGlobalFilter(
      enableGlobalFilter: Boolean
    ): Group[T, TM, CM, TF, CF, FM] =
      setEnableGlobalFilter(enableGlobalFilter.some)

    /** WARNING: This mutates the object in-place. */
    inline def withoutEnableGlobalFilter: Group[T, TM, CM, TF, CF, FM] =
      setEnableGlobalFilter(none)

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
      header:             js.UndefOr[String | (HeaderContext[T, Nothing, TM, ?, TF, ?, ?] => VdomNode)] =
        js.undefined,
      columns:            List[ColumnDef[T, ?, TM, CM, TF, ?, ?]],
      footer:             js.UndefOr[HeaderContext[T, Nothing, TM, ?, TF, ?, ?] => VdomNode] = js.undefined,
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
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined,
      filterFn:           js.UndefOr[BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM]] = js.undefined,
      // Global Filtering
      enableGlobalFilter: js.UndefOr[Boolean] = js.undefined
    ): Group[T, TM, CM, TF, CF, FM] = {
      val autoEnableResizing: Boolean =
        !enableResizing.contains(false) && (
          enableResizing.contains(true) ||
            minSize.isDefined ||
            maxSize.isDefined
        )

      val autoEnableColumnFilter: Boolean =
        !enableColumnFilter.contains(false) && (
          enableColumnFilter.contains(true) ||
            filterFn.isDefined
        )

      val p: ColumnDefJs[T, Nothing, CM] =
        new js.Object().asInstanceOf[ColumnDefJs[T, Nothing, CM]]
      p.id = id.value
      p.columns = columns.map(_.toJs).toJSArray
      Group[T, TM, CM, TF, CF, FM](p)
        .applyOrNot(header, _.withHeader(_))
        .applyOrNot(meta, _.withMeta(_))
        .applyOrNot(enableResizing, _.withEnableResizing(_))
        .applyWhen(autoEnableResizing, _.withEnableResizing(true))
        .applyOrNot(size, _.withSize(_))
        .applyOrNot(minSize, _.withMinSize(_))
        .applyOrNot(maxSize, _.withMaxSize(_))
        .applyOrNot(enableHiding, _.withEnableHiding(_))
        .applyOrNot(enablePinning, _.withEnablePinning(_))
        .applyOrNot(enableColumnFilter, _.withEnableColumnFilter(_))
        .applyWhen(autoEnableColumnFilter, _.withEnableColumnFilter(true))
        .applyOrNot(filterFn, _.withFilterFn(_))
        .applyOrNot(enableGlobalFilter, _.withEnableGlobalFilter(_))
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
    type Type                   = ColumnDef[T, ?, TM, CM, TF, Any, Any]
    type TypeFor[A]             = Single[T, A, TM, CM, TF, Any, Any]
    type FullTypeFor[A, CM, FM] = Single[T, A, TM, CM, TF, CM, FM]

    type ColType                   = Column[T, ?, TM, CM, TF, Any, Any]
    type ColTypeFor[A]             = Column[T, A, TM, CM, TF, Any, Any]
    type ColFullTypeFor[A, CM, FM] = Column[T, A, TM, CM, TF, CM, FM]

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
      header:             js.UndefOr[String | (HeaderContext[T, A, TM, ?, TF, ?, ?] => VdomNode)] =
        js.undefined,
      cell:               js.UndefOr[CellContext[T, A, TM, ?, TF, ?, ?] => VdomNode] = js.undefined,
      footer:             js.UndefOr[HeaderContext[T, A, TM, ?, TF, ?, ?] => VdomNode] = js.undefined,
      // Column Sizing
      enableResizing:     js.UndefOr[Boolean] = js.undefined,
      size:               js.UndefOr[SizePx] = js.undefined,
      minSize:            js.UndefOr[SizePx] = js.undefined,
      maxSize:            js.UndefOr[SizePx] = js.undefined,
      // Column Visibility
      enableHiding:       js.UndefOr[Boolean] = js.undefined,
      enableSorting:      js.UndefOr[Boolean] = js.undefined,
      enableMultiSort:    js.UndefOr[Boolean] = js.undefined,
      invertSorting:      js.UndefOr[Boolean] = js.undefined,
      sortDescFirst:      js.UndefOr[Boolean] = js.undefined,
      sortUndefined:      js.UndefOr[UndefinedPriority] = js.undefined,
      sortingFn:          js.UndefOr[BuiltInSorting | SortingFn[T, TM, CM, TF]] = js.undefined,
      // Column Pinning
      enablePinning:      js.UndefOr[Boolean] = js.undefined,
      // Column Filtering
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined,
      filterFn:           js.UndefOr[
        BuiltInFilter[CF] | FilterFn[T, TM, CM, TF, CF, FM] | FilterFn.Type[T, TM, CM, TF, CF, FM]
      ] = js.undefined,
      // Global Filtering
      enableGlobalFilter: js.UndefOr[Boolean] = js.undefined
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
        enableColumnFilter,
        filterFn,
        // Global Filtering
        enableGlobalFilter = enableGlobalFilter
      )

    def group[CF, FM](
      id:                 ColumnId,
      header:             js.UndefOr[String | (HeaderContext[T, Nothing, TM, ?, TF, ?, ?] => VdomNode)] =
        js.undefined,
      columns:            List[ColumnDef[T, ?, TM, CM, TF, ?, ?]],
      footer:             js.UndefOr[HeaderContext[T, Nothing, TM, ?, TF, ?, ?] => VdomNode] = js.undefined,
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
      enableColumnFilter: js.UndefOr[Boolean] = js.undefined,
      filterFn:           js.UndefOr[FilterFn[T, TM, CM, TF, CF, FM]] = js.undefined,
      // Global Filtering
      enableGlobalFilter: js.UndefOr[Boolean] = js.undefined
    ): Group[T, TM, CM, TF, CF, FM] =
      Group(
        id,
        header,
        columns,
        footer,
        meta,
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
        enableColumnFilter,
        filterFn,
        // Global Filtering
        enableGlobalFilter
      )

  end Applied
end ColumnDef
