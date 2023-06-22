// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

case class SelectButtonMultiple[A](
  value:          List[A],
  options:        List[SelectItem[A]],
  id:             js.UndefOr[String] = js.undefined,
  disabled:       js.UndefOr[Boolean] = js.undefined,
  itemTemplate:   js.UndefOr[SelectItem[A] => VdomNode] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  onChange:       js.UndefOr[List[A] => Callback] = js.undefined,
  modifiers:      Seq[TagMod] = Seq.empty
)(using val eqAA: Eq[A])
    extends ReactFnProps[SelectButtonBase](SelectButtonBase.component)
    with SelectButtonBase {
  type AA    = A
  type GG[X] = List[X]

  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)

  override def getter: js.Array[Int]        =
    value.flatMap(a => optionsWithIndex.indexOfOption(a)).toJSArray
  override def valueFinder(i: Any): List[A] =
    i.asInstanceOf[js.Array[Int]].toList.flatMap(idx => optionsWithIndex.findByIndexOption(idx))

  override val multiple: js.UndefOr[Boolean]     = true
  override val unselectable: js.UndefOr[Boolean] = true
}
