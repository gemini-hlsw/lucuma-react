package reactST.reactTable

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
  def apply[D](
    value_   : D,
    subRows_ : js.UndefOr[List[Expandable[D]]]
  ): Expandable[D] =
    new Expandable[D] {
      override val value   = value_
      override val subRows = subRows_.map(_.toJSArray)
    }

  def leaf[D](value_ : D): Expandable[D] = apply(value_, js.undefined)

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
