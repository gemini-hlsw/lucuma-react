// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import react.common.style.Css
import reactST.{tanstackTableCore => raw}
import reactST.{tanstackVirtualCore => rawVirtual}

import scalajs.js

trait HTMLTableProps[T]:
  val table: raw.mod.Table[T]
  val tableClass: Css
  val rowClassFn: (Int, T) => Css

trait HTMLVirtualizedTableProps[T]:
  val table: raw.mod.Table[T]
  val estimateSize: Int => Int
  // Table options
  val containerClass: Css
  val tableClass: Css
  val rowClassFn: (Int, T) => Css
  // Virtual options
  val overscan: js.UndefOr[Int]
  val getItemKey: js.UndefOr[Int => rawVirtual.mod.Key]
