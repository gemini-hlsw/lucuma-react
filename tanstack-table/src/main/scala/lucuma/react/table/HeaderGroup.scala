// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

/**
 * @tparam T
 *   The type of the row.
 * @tparam TM
 *   The type of the metadata for the table.
 * @tparam CM
 *   The type of the metadata for the column.
 * @tparam TF
 *   The type of the global filter.
 */
case class HeaderGroup[T, TM, CM, TF] private[table] (
  private[table] val toJs: raw.buildLibTypesMod.HeaderGroup[T]
):
  lazy val depth: Int                                          = toJs.depth.toInt
  lazy val headers: List[Header[T, Any, TM, CM, TF, Any, Any]] = toJs.headers.toList.map(Header(_))
  lazy val id: HeaderGroupId                                   = HeaderGroupId(toJs.id)
