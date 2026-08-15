// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.CustomHook
import lucuma.react.table.facade.*
import lucuma.react.table.facade.ReactTableRaw
import lucuma.react.table.facade.compat as raw
import lucuma.react.table.facade.instance

import scala.scalajs.js

object TableHook:
  // Full v9 API: `useTable` with a required `features` option. We keep the Scala `useReactTable`
  // public name; internally it calls `useTable` from `@tanstack/react-table` (root export) with a
  // default `features` object built from `stockFeatures` (all feature objects) plus every row-model
  // factory slot. This preserves the previous `useLegacyTable` behaviour (all features, sorting /
  // filtering / faceting / expanding / pagination all wired into getRowModel()) without the
  // deprecated shim or the `./legacy` subpath. `ReactTableRaw` binds the root namespace directly
  // to avoid the generated modules' broken `./dist/<x>` subpaths (see tanstack-v9-followups.md §4).
  private def defaultFeatures(): js.Any =
    js.Object.assign(
      js.Dynamic.literal(),
      ReactTableRaw.stockFeatures.asInstanceOf[js.Object],
      js.Dynamic.literal(
        sortedRowModel = ReactTableRaw.createSortedRowModel(),
        filteredRowModel = ReactTableRaw.createFilteredRowModel(),
        expandedRowModel = ReactTableRaw.createExpandedRowModel(),
        groupedRowModel = ReactTableRaw.createGroupedRowModel(),
        paginatedRowModel = ReactTableRaw.createPaginatedRowModel(),
        facetedRowModel = ReactTableRaw.createFacetedRowModel(),
        facetedUniqueValues = ReactTableRaw.createFacetedUniqueValues(),
        facetedMinMaxValues = ReactTableRaw.createFacetedMinMaxValues()
      )
    )

  // With `paginatedRowModel` always wired, v9's default pagination state ({pageIndex: 0,
  // pageSize: 10}) would silently truncate every table to its first 10 rows. Default to
  // pageSize Infinity (which v9 treats as "don't slice") unless the caller provided its own
  // pagination state. The public facade doesn't expose pagination yet, so today this always
  // applies; the guard future-proofs against it being added.
  private def ensureUnpaginatedByDefault(options: js.Dynamic): Unit =
    val statePagination        =
      !js.isUndefined(options.state) && !js.isUndefined(options.state.pagination)
    val initialStatePagination =
      !js.isUndefined(options.initialState) && !js.isUndefined(options.initialState.pagination)
    if !statePagination && !initialStatePagination then
      if js.isUndefined(options.initialState) then options.initialState = js.Dynamic.literal()
      options.initialState.pagination =
        js.Dynamic.literal(pageIndex = 0, pageSize = Double.PositiveInfinity)

  private def useReactTableJs[T, TM, CM, TF](
    options: TableOptionsJs[T, TM, CM, TF]
  ): raw.buildLibTypesMod.Table[T] =
    options.asInstanceOf[js.Dynamic].updateDynamic("features")(defaultFeatures())
    ensureUnpaginatedByDefault(options.asInstanceOf[js.Dynamic])
    ReactTableRaw.useTable(options).asInstanceOf[instance.Table[T]]

  def useReactTable[T, TM, CM, TF](
    options: TableOptions[T, TM, CM, TF]
  ): HookResult[Table[T, TM, CM, TF]] =
    for
      cols <- useMemo(options.columns)(_ => options.columnsJs)
      rows <- useMemo(options.data)(_ => options.dataJs)
    yield Table[T, TM, CM, TF](useReactTableJs(options.toJs(cols, rows)))

  def useTableHook[T, TM, CM, TF]: CustomHook[TableOptions[T, TM, CM, TF], Table[T, TM, CM, TF]] =
    CustomHook.fromHookResult(useReactTable(_))
