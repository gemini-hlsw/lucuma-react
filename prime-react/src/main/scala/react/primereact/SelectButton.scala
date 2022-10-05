// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.Id
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

case class SelectButton[A](
  value:          A,
  options:        List[SelectItem[A]],
  id:             js.UndefOr[String] = js.undefined,
  disabled:       js.UndefOr[Boolean] = js.undefined,
  itemTemplate:   js.UndefOr[SelectItem[A] => VdomNode] = js.undefined,
  className:      js.UndefOr[String] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  onChange:       js.UndefOr[A => Callback] = js.undefined
)(using val eqAA: Eq[A])
    extends ReactFnProps[SelectButtonBase](SelectButtonBase.component)
    with SelectButtonBase {
  type AA    = A
  type GG[X] = Id[X]

  override def getter: js.UndefOr[Int] = optionsWithIndex.indexOfOption(value).orUndefined
  override def valueFinder(i: Any): A  = selectItemFinder(i).value

  override val multiple: js.UndefOr[Boolean]     = false
  override val unselectable: js.UndefOr[Boolean] = false
}
