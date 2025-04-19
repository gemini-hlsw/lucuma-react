// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

enum SortDirection:
  case Ascending, Descending

object SortDirection:
  def fromDescending(b: Boolean) = if (b) SortDirection.Descending else SortDirection.Ascending

  extension (d: SortDirection)
    def toDescending: Boolean = d match
      case Ascending  => false
      case Descending => true
