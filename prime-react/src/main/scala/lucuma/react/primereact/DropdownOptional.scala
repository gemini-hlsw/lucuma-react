// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.Eq
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

case class DropdownOptional[A](
  value:                Option[A],
  options:              List[SelectItem[A]],
  id:                   js.UndefOr[String] = js.undefined,
  clazz:                js.UndefOr[Css] = js.undefined,
  panelClass:           js.UndefOr[Css] = js.undefined,
  showClear:            js.UndefOr[Boolean] = js.undefined,
  filter:               js.UndefOr[Boolean] = js.undefined,
  showFilterClear:      js.UndefOr[Boolean] = js.undefined,
  placeholder:          js.UndefOr[String] = js.undefined,
  disabled:             js.UndefOr[Boolean] = js.undefined,
  loading:              js.UndefOr[Boolean] = js.undefined,
  dropdownIcon:         js.UndefOr[String] = js.undefined,
  tooltip:              js.UndefOr[String] = js.undefined,
  tooltipOptions:       js.UndefOr[TooltipOptions] = js.undefined,
  itemTemplate:         js.UndefOr[SelectItem[A] => VdomNode] = js.undefined,
  valueTemplate:        js.UndefOr[SelectItem[A] => VdomNode] = js.undefined,
  emptyMessage:         js.UndefOr[VdomNode] = js.undefined,
  emptyMessageTemplate: js.UndefOr[VdomNode] = js.undefined,
  onChange:             js.UndefOr[Option[A] => Callback] = js.undefined,
  onChangeE:            js.UndefOr[(Option[A], ReactEvent) => Callback] =
    js.undefined, // called after onChange
  modifiers:            Seq[TagMod] = Seq.empty
)(using val eqAA: Eq[A])
    extends ReactFnProps[DropdownBase](DropdownBase.component)
    with DropdownBase {
  type AA    = A
  type GG[X] = Option[X]

  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)

  override def getter: js.UndefOr[Int]   =
    value.flatMap(v => optionsWithIndex.indexOfOption(v)).orUndefined
  override def finder(i: Any): Option[A] =
    if (js.isUndefined(i)) none
    else
      optionsWithIndex.findByIndexOption(i.asInstanceOf[Int])
}
