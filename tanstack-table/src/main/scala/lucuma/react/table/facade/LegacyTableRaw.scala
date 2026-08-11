// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
 * Native binding to the `@tanstack/react-table/legacy` subpath export (the v8-style row-model
 * marker factories used by `useLegacyTable`).
 *
 * We bind the `./legacy` subpath directly rather than the generated `distLegacyMod`, because that
 * module's `@JSImport` uses `./dist/legacy`, which Node's `exports` enforcement rejects
 * (`@tanstack/react-table` only exports `./legacy`). Under `useLegacyTable` all row models are
 * registered automatically, so these are effectively no-op markers; the return type is widened to
 * `js.Any` since the consuming `TableOptionsJS` fields are function-typed.
 */
@js.native
@JSImport("@tanstack/react-table/legacy", JSImport.Namespace)
object LegacyTableRaw extends js.Object:
  def getCoreRowModel[T](): js.Any         = js.native
  def getSortedRowModel[T](): js.Any       = js.native
  def getFilteredRowModel[T](): js.Any     = js.native
  def getExpandedRowModel[T](): js.Any     = js.native
  def getFacetedRowModel[T](): js.Any      = js.native
  def getFacetedUniqueValues[T](): js.Any  = js.native
  def getFacetedMinMaxValues[T](): js.Any  = js.native
