// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.CustomHook
import lucuma.react.table.facade.*
import lucuma.react.table.facade.compat as raw
import lucuma.react.table.facade.instance

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object TableHook:
  // v9 renamed `useReactTable` to `useTable` (with a required `features` option) and provides a
  // v8-style `useLegacyTable` shim. We keep the Scala `useReactTable` public name but route through
  // the legacy shim so existing TableOptions (v8-shaped, no `features`) keep working.
  //
  // NOTE: we bind `useLegacyTable` directly via the `./legacy` subpath export rather than the
  // generated `distLegacyMod`, because that module's `@JSImport` uses `./dist/legacy`, which Node's
  // `exports` enforcement rejects (`@tanstack/react-table` only exports `./legacy`).
  @JSImport("@tanstack/react-table/legacy", "useLegacyTable")
  @js.native
  private def useLegacyTableJs[T](options: js.Any): js.Any = js.native

  private def useReactTableJs[T, TM, CM, TF](
    options: TableOptionsJs[T, TM, CM, TF]
  ): raw.buildLibTypesMod.Table[T] =
    useLegacyTableJs[T](options).asInstanceOf[instance.Table[T]]

  def useReactTable[T, TM, CM, TF](
    options: TableOptions[T, TM, CM, TF]
  ): HookResult[Table[T, TM, CM, TF]] =
    for
      cols <- useMemo(options.columns)(_ => options.columnsJs)
      rows <- useMemo(options.data)(_ => options.dataJs)
    yield Table[T, TM, CM, TF](useReactTableJs(options.toJs(cols, rows)))

  def useTableHook[T, TM, CM, TF]: CustomHook[TableOptions[T, TM, CM, TF], Table[T, TM, CM, TF]] =
    CustomHook.fromHookResult(useReactTable(_))
