// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

case class HeaderGroup[T, TM] private[table] (
  private[table] val toJs: raw.buildLibTypesMod.HeaderGroup[T]
):
  lazy val depth: Int                                       = toJs.depth.toInt
  lazy val headers: List[Header[T, Any, TM, Any, Any, Any]] = toJs.headers.toList.map(Header(_))
  lazy val id: HeaderGroupId                                = HeaderGroupId(toJs.id)
