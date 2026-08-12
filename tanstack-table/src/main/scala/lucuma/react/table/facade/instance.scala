// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import lucuma.typed.tanstackReactTable.distUseLegacyTableMod.LegacyFeatures
import lucuma.typed.tanstackTableCore.*

import scala.scalajs.js

import distCoreCellsCoreCellsFeatureDottypesMod.*
import distCoreColumnsCoreColumnsFeatureDottypesMod.*
import distCoreHeadersCoreHeadersFeatureDottypesMod.*
import distCoreRowModelsCoreRowModelsFeatureDottypesMod.*
import distCoreRowsCoreRowsFeatureDottypesMod.*
import distCoreTableCoreTablesFeatureDottypesMod.*
import distFeaturesCellSelectionCellSelectionFeatureDottypesMod.*
import distFeaturesCellSpanningCellSpanningFeatureDottypesMod.*
import distFeaturesColumnFacetingColumnFacetingFeatureDottypesMod.*
import distFeaturesColumnFilteringColumnFilteringFeatureDottypesMod.*
import distFeaturesColumnGroupingColumnGroupingFeatureDottypesMod.*
import distFeaturesColumnOrderingColumnOrderingFeatureDottypesMod.*
import distFeaturesColumnPinningColumnPinningFeatureDottypesMod.*
import distFeaturesColumnResizingColumnResizingFeatureDottypesMod.*
import distFeaturesColumnSizingColumnSizingFeatureDottypesMod.*
import distFeaturesColumnVisibilityColumnVisibilityFeatureDottypesMod.*
import distFeaturesGlobalFilteringGlobalFilteringFeatureDottypesMod.*
import distFeaturesRowExpandingRowExpandingFeatureDottypesMod.*
import distFeaturesRowPaginationRowPaginationFeatureDottypesMod.*
import distFeaturesRowPinningRowPinningFeatureDottypesMod.*
import distFeaturesRowSelectionRowSelectionFeatureDottypesMod.*
import distFeaturesRowSortingRowSortingFeatureDottypesMod.*

/**
 * Aggregated `@js.native` instance traits for TanStack Table v9.
 *
 * v9 computes each entity's instance API with a type-level `ExtractFeatureMapTypes[TFeatures, …]`
 * that ScalablyTyped reduces to memberless `js.Any`, so the public `Table`/`Column`/`Row`/`Cell`/
 * `Header` types expose only core members. To recover the full instance API the facade relies on,
 * we declare one `@js.native trait` per entity that extends the generated core trait plus the
 * relevant zero/parametric feature mixin traits, all pinned to `LegacyFeatures` (the full feature
 * set registered by `useLegacyTable`). The runtime objects created by v9 conform to these
 * intersections, so the casts are sound.
 */
object instance:
  private type LF = LegacyFeatures

  @js.native
  trait Column[T, V]
      extends js.Object
      with ColumnColumn[LF, T, V]
      with ColumnColumnVisibility
      with ColumnRowSorting[LF, T]
      with ColumnColumnFiltering[LF, T]
      with ColumnColumnPinning
      with ColumnColumnSizing
      with ColumnColumnResizing
      with ColumnColumnGrouping
      with ColumnColumnOrdering
      with ColumnColumnFaceting[LF, T]
      with ColumnGlobalFiltering
      with ColumnIndexes

  @js.native
  trait Row[T]
      extends js.Object
      with RowRow[LF, T]
      with RowRowExpanding
      with RowRowPinning
      with RowRowSelection
      with RowColumnGrouping
      with RowColumnPinning[LF, T]
      with RowColumnVisibility[LF, T]
      with RowColumnFiltering[LF, T]

  @js.native
  trait Cell[T, V]
      extends js.Object
      with CellCell[LF, T, V]
      with CellCellSelection
      with CellCellSpanning
      with CellColumnGrouping:
    // v8 grouping-cell concept; removed from v9 core but present on legacy instances.
    def getIsAggregated(): Boolean = js.native

  @js.native
  trait Table[T]
      extends js.Object
      with TableTable[LF, T]
      with TableColumns[LF, T]
      with TableRows[LF, T]
      with TableHeaders[LF, T]
      with TableColumnVisibility[LF, T]
      with TableColumnPinning[LF, T]
      with TableColumnSizing
      with TableColumnResizing
      with TableColumnFiltering
      with TableColumnGrouping[LF, T]
      with TableColumnOrdering[LF, T]
      with TableColumnFaceting[LF, T]
      with TableRowSorting[LF, T]
      with TableRowSelection[LF, T]
      with TableRowExpanding[LF, T]
      with TableRowPinning[LF, T]
      with TableRowPagination[LF, T]
      with TableGlobalFiltering[LF, T]
      with TableCellSelection[LF, T]
      with TableCellSpanning[LF, T]
      with TableRowModelsCore[LF, T]
      with TableRowModelsExpanded[LF, T]
      with TableRowModelsFaceted[LF, T]
      with TableRowModelsFiltered[LF, T]
      with TableRowModelsGrouped[LF, T]
      with TableRowModelsPaginated[LF, T]
      with TableRowModelsSorted[LF, T]:
    // Legacy v8-style state access (v9 removed these in favour of table.store/state).
    def getState(): lucuma.typed.tanstackTableCore.distTypesTableStateMod.TableState[LF] = js.native
    def setState(stateOrUpdater: js.Any): Unit                                           = js.native

  @js.native
  trait Header[T, V]
      extends js.Object
      with HeaderHeader[LF, T, V]
      with HeaderColumnSizing
      with HeaderColumnResizing

  @js.native
  trait HeaderGroup[T] extends js.Object with HeaderGroupHeader[LF, T, Any]

  // RowModel keeps its generated type (no feature extraction issue): alias through.
  type RowModel[T] = distCoreRowModelsCoreRowModelsFeatureDottypesMod.RowModel[LF, T]

  // --- Table state (public TableState reduces to memberless js.Any via ExtractFeatureMapTypes) ---
  @js.native
  trait TableState
      extends js.Object
      with TableStateColumnVisibility
      with TableStateColumnOrdering
      with TableStateColumnPinning
      with TableStateRowPinning
      with TableStateRowSorting
      with TableStateRowExpanding
      with TableStateColumnSizing
      with TableStateColumnResizing
      with TableStateRowSelection
      with TableStateColumnFiltering
      with TableStateGlobalFiltering:
    // v9 state objects expose plain fields; these mutating setters mirror the v8 builder API the
    // facade relies on (they assign + return self). Read access comes from the TableState* mixins.
    def setColumnVisibility(
      v: distFeaturesColumnVisibilityColumnVisibilityFeatureDottypesMod.ColumnVisibilityState
    ): this.type                                                                                  = js.native
    def setColumnOrder(
      v: distFeaturesColumnOrderingColumnOrderingFeatureDottypesMod.ColumnOrderState
    ): this.type                                                                                  = js.native
    def setColumnPinning(
      v: distFeaturesColumnPinningColumnPinningFeatureDottypesMod.ColumnPinningState
    ): this.type                                                                                  = js.native
    def setRowPinning(
      v: distFeaturesRowPinningRowPinningFeatureDottypesMod.RowPinningState
    ): this.type                                                                                  = js.native
    def setSorting(v: distFeaturesRowSortingRowSortingFeatureDottypesMod.SortingState): this.type =
      js.native
    def setExpanded(
      v: distFeaturesRowExpandingRowExpandingFeatureDottypesMod.ExpandedState
    ): this.type                                                                                  = js.native
    def setColumnSizing(
      v: distFeaturesColumnSizingColumnSizingFeatureDottypesMod.ColumnSizingState
    ): this.type                                                                                  = js.native
    def setColumnResizing(
      v: distFeaturesColumnResizingColumnResizingFeatureDottypesMod.columnResizingState
    ): this.type                                                                                  = js.native
    def setRowSelection(
      v: distFeaturesRowSelectionRowSelectionFeatureDottypesMod.RowSelectionState
    ): this.type                                                                                  = js.native
    def setColumnFilters(
      v: distFeaturesColumnFilteringColumnFilteringFeatureDottypesMod.ColumnFiltersState
    ): this.type                                                                                  = js.native
    def setGlobalFilter(v: js.Any): this.type                                                     = js.native

end instance
