// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import japgolly.scalajs.react.facade.React.Node
import reactST.tanstackTableCore.mod.ColumnMeta
import reactST.{tanstackTableCore => raw}

import scalajs.js

trait ColumnDefJS[T, A] extends js.Object:
  var id: String
  var header: js.UndefOr[String | js.Function1[raw.mod.HeaderContext[T, A], Node]]
  var accessorFn: js.UndefOr[js.Function1[T, A]]
  var cell: js.UndefOr[js.Function1[raw.mod.CellContext[T, A], Node]]
  var columns: js.UndefOr[js.Array[ColumnDefJS[T, ?]]]
  var footer: js.UndefOr[js.Function1[raw.mod.HeaderContext[T, A], Node]]
  var meta: js.UndefOr[Any]

  // Column Sizing
  var enableResizing: js.UndefOr[Boolean] = js.undefined
  var size: js.UndefOr[Int]               = js.undefined
  var minSize: js.UndefOr[Int]            = js.undefined
  var maxSize: js.UndefOr[Int]            = js.undefined

  // Column Visibility
  var enableHiding: js.UndefOr[Boolean] = js.undefined

  // Sorting
  var enableMultiSort: js.UndefOr[Boolean]                 = js.undefined
  var enableSorting: js.UndefOr[Boolean]                   = js.undefined
  var invertSorting: js.UndefOr[Boolean]                   = js.undefined
  var sortDescFirst: js.UndefOr[Boolean]                   = js.undefined
  // var sortUndefined: js.UndefOr[false | -1 | 1]   = js.undefined
  var sortingFn: js.UndefOr[String | raw.mod.SortingFn[T]] = js.undefined
