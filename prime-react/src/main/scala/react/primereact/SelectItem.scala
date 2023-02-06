// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.syntax.all.*
import japgolly.scalajs.react.*
import react.common.*
import lucuma.typed.primereact.selectitemSelectitemMod.SelectItemOptionsType
import lucuma.typed.primereact.selectitemSelectitemMod.{SelectItem => CSelectItem}

import scalajs.js
import scalajs.js.JSConverters.*

// Note: icon in CSelectItem seems to do nothing. You need to use templates.
case class SelectItem[A: Eq](
  value:     A,
  label:     js.UndefOr[String] = js.undefined,
  disabled:  js.UndefOr[Boolean] = js.undefined,
  className: js.UndefOr[String] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined
):
  def raw(index: Int): CSelectItem =
    val csi: CSelectItem = CSelectItem().setValue(index)
    label.foreach(v => csi.setLabel(v))
    disabled.foreach(v => csi.setDisabled(v))
    clazz.foreach(v => csi.setClassName(v.htmlClass))
    csi

object SelectItem {
  def fromTupleList[A: Eq](list: List[(A, String)]) =
    list.map((a, l) => SelectItem(value = a, label = l))

  // We're using the index of the options for the CSelectValue so comparisons can
  // be made via Eq rather than let javascript do the comparisons.
  extension [A: Eq](options: List[(SelectItem[A], Int)])
    def indexOfOption(a: A): Option[Int] = options.find(_._1.value === a).map(_._2)
    def findByIndexOption(index: Int): Option[A]                       =
      findSelectItemByIndexOption(index).map(_.value)
    def findSelectItemByIndexOption(index: Int): Option[SelectItem[A]] =
      options.find(_._2 === index).map(_._1)
    def raw: SelectItemOptionsType                                     =
      options.map((si, idx) => si.raw(idx)).toJSArray
}
