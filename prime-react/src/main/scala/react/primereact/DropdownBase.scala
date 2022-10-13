// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.fa.FontAwesomeIcon
import reactST.primereact.components.{Dropdown => CDropdown}

import scalajs.js
import scalajs.js.JSConverters.*

// TODO: Grouped Dropdowns will require additional work and probably a different implementation.
private[primereact] trait DropdownBase {
  protected type AA
  protected type GG[_]
  implicit val eqAA: Eq[AA]

  val value: GG[AA]
  val options: List[SelectItem[AA]]
  val id: js.UndefOr[String]
  val clazz: js.UndefOr[Css]
  val showClear: js.UndefOr[Boolean]
  val filter: js.UndefOr[Boolean]
  val showFilterClear: js.UndefOr[Boolean]
  val placeholder: js.UndefOr[String]
  val disabled: js.UndefOr[Boolean]
  val dropdownIcon: js.UndefOr[String]
  val onChange: js.UndefOr[GG[AA] => Callback]
  val modifiers: Seq[TagMod]

  protected def getter: js.UndefOr[Int]
  protected def finder(i: Any): GG[AA]

  protected val optionsWithIndex: List[(SelectItem[AA], Int)] = options.zipWithIndex
}

object DropdownBase {
  private[primereact] val component = ScalaFnComponent[DropdownBase] { props =>
    CDropdown
      .value(props.getter)
      .options(props.optionsWithIndex.raw)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.showClear, _.showClear(_))
      .applyOrNot(props.filter, _.filter(_))
      .applyOrNot(props.showFilterClear, _.showFilterClear(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.dropdownIcon, _.dropdownIcon(_))
      .applyOrNot(props.onChange, (c, p) => c.onChange(e => p(props.finder(e.value))))(
        props.modifiers.toTagMod
      )
  }
}
