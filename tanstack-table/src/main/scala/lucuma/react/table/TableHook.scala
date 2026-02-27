// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
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
  private def useReactTableJs[T, TM, CM, TF](
    options: TableOptionsJs[T, TM, CM, TF]
  ): raw.buildLibTypesMod.Table[T] =
    js.native

  def useReactTable[T, TM, CM, TF](
    options: TableOptions[T, TM, CM, TF]
  ): HookResult[Table[T, TM, CM, TF]] =
    for
      cols <- useMemo(options.columns)(_ => options.columnsJs)
      rows <- useMemo(options.data)(_ => options.dataJs)
    yield Table[T, TM, CM, TF](useReactTableJs(options.toJs(cols, rows)))

  def useTableHook[T, TM, CM, TF]: CustomHook[TableOptions[T, TM, CM, TF], Table[T, TM, CM, TF]] =
    CustomHook.fromHookResult(useReactTable(_))
