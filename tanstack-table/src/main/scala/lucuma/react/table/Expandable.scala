// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.ReactExtensions._
import japgolly.scalajs.react.Reusability

import scala.annotation.tailrec
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.UndefOr

import scalajs.js

trait Expandable[D] extends js.Object {
  val value: D
  val subRows: js.UndefOr[js.Array[Expandable[D]]]
}

object Expandable {
  def apply[D](value_ : D): Expandable[D] =
    new Expandable[D] {
      override val value   = value_
      override val subRows = js.undefined
    }

  implicit class ExpandableOps[D](val expandable: Expandable[D]) extends AnyVal {
    def withSubRows(subRows: List[Expandable[D]]): Expandable[D] = {
      expandable.asInstanceOf[js.Dynamic].subRows = subRows.toJSArray
      expandable
    }
  }

  implicit def reuseExpandable[D: Reusability]: Reusability[Expandable[D]] =
    Reusability { (a, b) =>
      val subRowReuse = Reusability.byIterator[Iterable, Expandable[D]](reuseExpandable)

      a.value ~=~ b.value &&
      a.subRows.exists(subRowsA =>
        b.subRows.exists(subRowsB =>
          subRowReuse.test(subRowsA: Iterable[Expandable[D]], subRowsB: Iterable[Expandable[D]])
        )
      )
    }
}
