// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.CustomHook
import lucuma.react.table.facade.*
import lucuma.react.table.facade.compat as raw
import lucuma.react.table.facade.instance
import lucuma.typed.tanstackReactTable.distLegacyMod
import lucuma.typed.tanstackReactTable.distUseLegacyTableMod.LegacyTableOptions

import scala.scalajs.js

object TableHook:
  // v9 renamed `useReactTable` to `useTable` (with a required `features` option) and provides a
  // v8-style `useLegacyTable` shim. We keep the Scala `useReactTable` public name but route through
  // the legacy shim so existing TableOptions (v8-shaped, no `features`) keep working.
  private def useReactTableJs[T, TM, CM, TF](
    options: TableOptionsJs[T, TM, CM, TF]
  ): raw.buildLibTypesMod.Table[T] =
    distLegacyMod
      .useLegacyTable[T](options.asInstanceOf[LegacyTableOptions[T]])
      .asInstanceOf[instance.Table[T]]

  def useReactTable[T, TM, CM, TF](
    options: TableOptions[T, TM, CM, TF]
  ): HookResult[Table[T, TM, CM, TF]] =
    for
      cols <- useMemo(options.columns)(_ => options.columnsJs)
      rows <- useMemo(options.data)(_ => options.dataJs)
    yield Table[T, TM, CM, TF](useReactTableJs(options.toJs(cols, rows)))

  def useTableHook[T, TM, CM, TF]: CustomHook[TableOptions[T, TM, CM, TF], Table[T, TM, CM, TF]] =
    CustomHook.fromHookResult(useReactTable(_))
