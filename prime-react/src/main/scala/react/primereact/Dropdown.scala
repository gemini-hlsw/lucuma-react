// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.Id
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

case class Dropdown[A](
  value:           A,
  options:         List[SelectItem[A]],
  id:              js.UndefOr[String] = js.undefined,
  clazz:           js.UndefOr[Css] = js.undefined,
  panelClass:      js.UndefOr[Css] = js.undefined,
  filter:          js.UndefOr[Boolean] = js.undefined,
  showFilterClear: js.UndefOr[Boolean] = js.undefined,
  placeholder:     js.UndefOr[String] = js.undefined,
  disabled:        js.UndefOr[Boolean] = js.undefined,
  dropdownIcon:    js.UndefOr[String] = js.undefined,
  tooltip:         js.UndefOr[String] = js.undefined,
  tooltipOptions:  js.UndefOr[TooltipOptions] = js.undefined,
  onChange:        js.UndefOr[A => Callback] = js.undefined,
  onChangeE:       js.UndefOr[(A, ReactEvent) => Callback] = js.undefined, // called after onChange
  modifiers:       Seq[TagMod] = Seq.empty
)(using val eqAA: Eq[A])
    extends ReactFnProps[DropdownBase](DropdownBase.component)
    with DropdownBase {
  type AA    = A
  type GG[X] = Id[X]

  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)

  override def getter: js.UndefOr[Int] = optionsWithIndex.indexOfOption(value).orUndefined
  override def finder(i: Any): A       =
    optionsWithIndex
      .findByIndexOption(i.asInstanceOf[Int])
      .getOrElse(options(0).value) // called by onChange, so should exist

  override val showClear: js.UndefOr[Boolean] = false
}
