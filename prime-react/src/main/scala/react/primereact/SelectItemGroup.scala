// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.typed.primereact.selectitemSelectitemMod.{SelectItem => CSelectItem}
import react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

case class SelectItemGroup[A](
  label:   VdomNode,
  options: List[SelectItem[A]]
)

object SelectItemGroup {

  // We're using the index of the options for the CSelectValue so comparisons can
  // be made via Eq rather than let javascript do the comparisons.
  extension [A](groups: List[SelectItemGroup[A]])
    def toOptionsWithIndex: List[(SelectItem[A], Int)] =
      groups.map(_.options).flatten.zipWithIndex
}

// We can't pass `List[SelectItem[A]] | List[SelectItemGroup[A]]` as a parameter to MultiSelects
// and DropDowns because they can't be distinguished at runtime via pattern matching. So, the
// group list is wrapped in this class. For backwards compatibility and because it is by far the
// most common case, `List[SelectItem[A]]` is left unwrapped.
// Another option would be to have different components for grouped and ungrouped, but that would
// lead to a lot of additional components.
case class SelectItemGroups[A](groups: List[SelectItemGroup[A]]):
  val optionsWithIndex   = groups.map(_.options).flatten.zipWithIndex
  val raw: js.Array[Any] =
    val (idx, objList) = groups.foldLeft((0, js.Array[Any]()))((acc, sig) =>
      val (idx, l)            = acc
      val (nextIdx, children) = rawOptionsSegment(sig.options, idx)
      val obj                 = js.Dynamic.literal(
        "label"    -> sig.label.rawNode.asInstanceOf[js.Any],
        "children" -> children
      )
      (nextIdx, l :+ obj)
    )
    objList

  private def rawOptionsSegment(
    options:  List[SelectItem[A]],
    startIdx: Int
  ): (Int, js.Array[CSelectItem]) =
    val (nextIdx, list) = options.foldLeft((startIdx, js.Array[CSelectItem]())) {
      case ((idx, l), si) =>
        (idx + 1, l :+ si.raw(idx))
    }
    (nextIdx, list)
