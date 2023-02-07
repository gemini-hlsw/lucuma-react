// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.CustomHook
import lucuma.react.table.facade.*
import lucuma.typed.tanstackReactTable.mod.useReactTable
import lucuma.typed.{tanstackTableCore => raw}

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.JSImport

object TableHook:
  @JSImport("@tanstack/react-table", "useReactTable")
  @js.native
  private def useReactTableJs[T](options: TableOptionsJs[T]): raw.buildLibTypesMod.Table[T] =
    js.native

  def useTableHook[T] =
    CustomHook[TableOptions[T]]
      .useMemoBy(_.columns)(input => _ => input.columnsJs)
      .useMemoBy(_.input.data)(ctx => _ => ctx.input.dataJs)
      .buildReturning { (options, cols, rows) =>
        Table(useReactTableJs[T](options.toJs(cols, rows)))
      }
