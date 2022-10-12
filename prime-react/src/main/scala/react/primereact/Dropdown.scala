// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.Id
import cats.syntax.all._
import japgolly.scalajs.react.*
import react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

case class Dropdown[A](
  value:           A,
  options:         List[SelectItem[A]],
  id:              js.UndefOr[String] = js.undefined,
  clazz:           js.UndefOr[Css] = js.undefined,
  filter:          js.UndefOr[Boolean] = js.undefined,
  showFilterClear: js.UndefOr[Boolean] = js.undefined,
  placeholder:     js.UndefOr[String] = js.undefined,
  disabled:        js.UndefOr[Boolean] = js.undefined,
  dropdownIcon:    js.UndefOr[String] = js.undefined,
  onChange:        js.UndefOr[A => Callback] = js.undefined
)(using val eqAA:  Eq[A])
    extends ReactFnProps[DropdownBase](DropdownBase.component)
    with DropdownBase {
  type AA    = A
  type GG[X] = Id[X]

  override def getter: js.UndefOr[Int] = optionsWithIndex.indexOfOption(value).orUndefined
  override def finder(i: Any): A       =
    optionsWithIndex
      .findByIndexOption(i.asInstanceOf[Int])
      .getOrElse(options(0).value) // called by onChange, so should exist

  override val showClear: js.UndefOr[Boolean] = false
}
