// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
 * Native binding to the `@tanstack/react-table` **root** export for the full v9 API.
 *
 * We bind the root namespace directly (rather than the generated `distUseTableMod` /
 * `distFeaturesStockFeaturesMod` / `distCoreRowModelsCreate*Mod` modules) because those generated
 * modules use `@tanstack/react-table/dist/<x>` / `@tanstack/table-core/dist/<x>` `@JSImport` paths
 * that Node's `exports` enforcement and Vite reject — see `tanstack-v9-followups.md` §4. All these
 * symbols are named exports on the package root.
 */
@js.native
@JSImport("@tanstack/react-table", JSImport.Namespace)
object ReactTableRaw extends js.Object:
  def useTable(options: js.Any): js.Any                                                 = js.native
  val stockFeatures: js.Any                                                             = js.native
  def tableFeatures(slots: js.Any): js.Any                                              = js.native
  def createSortedRowModel(): js.Any                                                    = js.native
  def createFilteredRowModel(): js.Any                                                  = js.native
  def createExpandedRowModel(): js.Any                                                  = js.native
  def createGroupedRowModel(): js.Any                                                   = js.native
  def createPaginatedRowModel(): js.Any                                                 = js.native
  def createFacetedRowModel(): js.Any                                                   = js.native
  def createFacetedUniqueValues(): js.Any                                               = js.native
  def createFacetedMinMaxValues(): js.Any                                               = js.native
