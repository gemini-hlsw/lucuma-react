// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.ReactExtensions.*
import japgolly.scalajs.react.Reusability

case class Expandable[D](value: D, subRows: Option[List[Expandable[D]]] = None)

object Expandable:
  def apply[D](value: D, subRows: List[Expandable[D]]): Expandable[D] =
    Expandable(value, subRows.some)

  given [D: Reusability]: Reusability[Expandable[D]] =
    Reusability { (a, b) =>
      val subRowReuse = Reusability.byIterator[Iterable, Expandable[D]]

      a.value ~=~ b.value &&
      a.subRows.exists(subRowsA =>
        b.subRows.exists(subRowsB =>
          subRowReuse.test(subRowsA: Iterable[Expandable[D]], subRowsB: Iterable[Expandable[D]])
        )
      )
    }
