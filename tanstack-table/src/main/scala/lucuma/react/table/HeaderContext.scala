// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

case class HeaderContext[T, A, TM, CM, F, FM] private[table] (
  private[table] val toJs: raw.buildLibCoreHeadersMod.HeaderContext[T, A]
):
  lazy val column: Column[T, A, TM, CM, F, FM] = Column(toJs.column)
  lazy val header: Header[T, A, TM, CM, F, FM] = Header(toJs.header)
  lazy val table: Table[T, TM]                 = Table(toJs.table)
