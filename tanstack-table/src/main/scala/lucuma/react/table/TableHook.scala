// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.CustomHook
import lucuma.react.table.facade.*
import lucuma.typed.tanstackTableCore as raw

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object TableHook:
  @JSImport("@tanstack/react-table", "useReactTable")
  @js.native
  private def useReactTableJs[T, M](options: TableOptionsJs[T, M]): raw.buildLibTypesMod.Table[T] =
    js.native

  def useReactTable[T, M](options: TableOptions[T, M]): HookResult[Table[T, M]] =
    for
      cols <- useMemo(options.columns)(_ => options.columnsJs)
      rows <- useMemo(options.data)(_ => options.dataJs)
    yield Table[T, M](useReactTableJs(options.toJs(cols, rows)))

  def useTableHook[T, M]: CustomHook[TableOptions[T, M], Table[T, M]] =
    CustomHook.fromHookResult(useReactTable(_))
