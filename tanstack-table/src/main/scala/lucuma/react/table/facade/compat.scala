// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import lucuma.typed.tanstackReactTable.distUseLegacyTableMod.LegacyFeatures

/**
 * Compatibility shim that re-exports the TanStack Table **v9** generated types under the v8-style
 * `buildLib*Mod` module names that the hand-maintained facade was written against.
 *
 * v9 reorganised `@tanstack/table-core`'s source tree under `dist/`, so ScalablyTyped now emits
 * modules named after those paths (e.g. `distTypesTableMod`) instead of the old `buildLibTypesMod`.
 * Rather than rewrite every `raw.buildLibFooMod.X` reference across ~30 files, this object provides
 * stable short aliases. All v9 core types gained a leading `TFeatures` type parameter; we pin it to
 * `LegacyFeatures` (the full feature set registered by `useLegacyTable`), which restores the v8
 * single-generic `[TData]` shape the facade assumes.
 *
 * This is the migration path endorsed by the TanStack v9 guide: keep the v8-style options/instance
 * API via `useLegacyTable` while running on v9 under the hood. The full v9 `features`-based API can
 * be adopted later without churning this facade's public `[T, TM, CM, TF]` surface.
 */
object compat:

  private type LF = LegacyFeatures

  // --- Core types (was buildLibTypesMod) ---
  // Instance entities point at the aggregated @js.native traits in `instance` so that v9's
  // feature-injected methods are accessible (the public generated types reduce to js.Any).
  object buildLibTypesMod:
    type Table[T]          = instance.Table[T]
    type Row[T]            = instance.Row[T]
    type Column[T, V]      = instance.Column[T, V]
    type Cell[T, V]        = instance.Cell[T, V]
    type Header[T, V]      = instance.Header[T, V]
    type HeaderGroup[T]    = instance.HeaderGroup[T]
    type RowModel[T]       = instance.RowModel[T]
    type TableState        = instance.TableState
    type OnChangeFn[T]     = lucuma.typed.tanstackTableCore.distTypesTypeUtilsMod.OnChangeFn[T]
    type Updater[T]        = lucuma.typed.tanstackTableCore.distTypesTypeUtilsMod.Updater[T]
    type InitialTableState = instance.TableState
    val InitialTableState: lucuma.typed.tanstackTableCore.anon.PartialTableStateAll.type =
      lucuma.typed.tanstackTableCore.anon.PartialTableStateAll

  // --- Core contexts (was buildLibCoreHeadersMod / buildLibCoreCellMod) ---
  object buildLibCoreHeadersMod:
    type HeaderContext[T, V] =
      lucuma.typed.tanstackTableCore.distCoreHeadersCoreHeadersFeatureDottypesMod.HeaderContext[LF,
                                                                                                T,
                                                                                                V
      ]

  object buildLibCoreCellMod:
    type CellContext[T, V] =
      lucuma.typed.tanstackTableCore.distCoreCellsCoreCellsFeatureDottypesMod.CellContext[LF, T, V]

  // --- Column Sizing / Resizing (was buildLibFeaturesColumnSizingMod) ---
  private val resizingMod =
    lucuma.typed.tanstackTableCore.distFeaturesColumnResizingColumnResizingFeatureDottypesMod
  private val sizingMod   =
    lucuma.typed.tanstackTableCore.distFeaturesColumnSizingColumnSizingFeatureDottypesMod

  object buildLibFeaturesColumnSizingMod:
    type ColumnSizingState     = sizingMod.ColumnSizingState
    // v9 split sizing from resizing; the "info" state is now the resizing state.
    type ColumnSizingInfoState = resizingMod.columnResizingState
    val ColumnSizingInfoState: resizingMod.columnResizingState.type =
      resizingMod.columnResizingState
    type ColumnResizeMode = resizingMod.ColumnResizeMode
    val ColumnResizeMode: resizingMod.ColumnResizeMode.type = resizingMod.ColumnResizeMode

  // --- Column Filtering (was buildLibFeaturesColumnFilteringMod) ---
  private val filteringMod =
    lucuma.typed.tanstackTableCore.distFeaturesColumnFilteringColumnFilteringFeatureDottypesMod
  object buildLibFeaturesColumnFilteringMod:
    type ColumnFiltersState = filteringMod.ColumnFiltersState
    type FilterFn[T]        = filteringMod.FilterFn[LF, T]
    type ColumnFilter       = filteringMod.ColumnFilter
    val ColumnFilter: filteringMod.ColumnFilter.type = filteringMod.ColumnFilter
    type FilterMeta = filteringMod.FilterMeta

  // --- Row Sorting (was buildLibFeaturesRowSortingMod) ---
  private val sortingMod =
    lucuma.typed.tanstackTableCore.distFeaturesRowSortingRowSortingFeatureDottypesMod
  object buildLibFeaturesRowSortingMod:
    type ColumnSort = sortingMod.ColumnSort
    val ColumnSort: sortingMod.ColumnSort.type = sortingMod.ColumnSort
    type SortingState = sortingMod.SortingState
    type SortingFn[T] = sortingMod.SortFn[LF, T]

  // --- Column Pinning (was buildLibFeaturesColumnPinningMod) ---
  private val colPinningMod =
    lucuma.typed.tanstackTableCore.distFeaturesColumnPinningColumnPinningFeatureDottypesMod
  object buildLibFeaturesColumnPinningMod:
    type ColumnPinningState = colPinningMod.ColumnPinningState
    val ColumnPinningState: colPinningMod.ColumnPinningState.type = colPinningMod.ColumnPinningState
    type ColumnPinningPosition = colPinningMod.ColumnPinningPosition
    val ColumnPinningPosition: colPinningMod.ColumnPinningPosition.type =
      colPinningMod.ColumnPinningPosition

  // --- Row Selection (was buildLibFeaturesRowSelectionMod) ---
  object buildLibFeaturesRowSelectionMod:
    type RowSelectionState =
      lucuma.typed.tanstackTableCore.distFeaturesRowSelectionRowSelectionFeatureDottypesMod.RowSelectionState

  // --- Column Visibility (was buildLibFeaturesColumnVisibilityMod) ---
  object buildLibFeaturesColumnVisibilityMod:
    type VisibilityState =
      lucuma.typed.tanstackTableCore.distFeaturesColumnVisibilityColumnVisibilityFeatureDottypesMod.ColumnVisibilityState

  // --- Row Pinning (was buildLibFeaturesRowPinningMod) ---
  private val rowPinningMod =
    lucuma.typed.tanstackTableCore.distFeaturesRowPinningRowPinningFeatureDottypesMod
  object buildLibFeaturesRowPinningMod:
    type RowPinningState = rowPinningMod.RowPinningState
    val RowPinningState: rowPinningMod.RowPinningState.type = rowPinningMod.RowPinningState
    type RowPinningPosition = rowPinningMod.RowPinningPosition
    val RowPinningPosition: rowPinningMod.RowPinningPosition.type =
      rowPinningMod.RowPinningPosition

  // --- Row Expanding (was buildLibFeaturesRowExpandingMod) ---
  object buildLibFeaturesRowExpandingMod:
    type ExpandedState =
      lucuma.typed.tanstackTableCore.distFeaturesRowExpandingRowExpandingFeatureDottypesMod.ExpandedState

  // --- Column Ordering (was buildLibFeaturesColumnOrderingMod) ---
  object buildLibFeaturesColumnOrderingMod:
    type ColumnOrderState =
      lucuma.typed.tanstackTableCore.distFeaturesColumnOrderingColumnOrderingFeatureDottypesMod.ColumnOrderState

  // --- String / boolean literal unions (unchanged surface) ---
  val tanstackTableCoreStrings: lucuma.typed.tanstackTableCore.tanstackTableCoreStrings.type   =
    lucuma.typed.tanstackTableCore.tanstackTableCoreStrings
  val tanstackTableCoreBooleans: lucuma.typed.tanstackTableCore.tanstackTableCoreBooleans.type =
    lucuma.typed.tanstackTableCore.tanstackTableCoreBooleans

  // --- anonymous helper types (was raw.anon) ---
  object anon:
    type PartialTableState = lucuma.typed.tanstackTableCore.anon.PartialTableStateAll
    val PartialTableState: lucuma.typed.tanstackTableCore.anon.PartialTableStateAll.type =
      lucuma.typed.tanstackTableCore.anon.PartialTableStateAll

end compat
