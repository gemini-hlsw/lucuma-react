// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

enum FilterResult(val toBoolean: Boolean):
  case Pass extends FilterResult(true)
  case Fail extends FilterResult(false)

object FilterResult:
  def fromBoolean(b: Boolean): FilterResult =
    if b then Pass else Fail
