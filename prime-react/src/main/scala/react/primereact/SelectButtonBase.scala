// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{SelectButton => CSelectButton}
import reactST.primereact.selectitemMod.{SelectItem => CSelectItem}

import scalajs.js
import scalajs.js.JSConverters.*

private[primereact] trait SelectButtonBase {
  protected type AA
  protected type GG[_]
  implicit val eqAA: Eq[AA]

  val value: GG[AA]
  val options: List[SelectItem[AA]]
  val id: js.UndefOr[String]
  val multiple: js.UndefOr[Boolean]     // default: false
  val disabled: js.UndefOr[Boolean]
  val unselectable: js.UndefOr[Boolean] // default: true
  val itemTemplate: js.UndefOr[SelectItem[AA] => VdomNode]
  val className: js.UndefOr[String]
  val clazz: js.UndefOr[Css]
  val onChange: js.UndefOr[GG[AA] => Callback]

  protected def getter: Any
  // used by onChange
  protected def valueFinder(i: Any): GG[AA]

  protected val optionsWithIndex: List[(SelectItem[AA], Int)] = options.zipWithIndex

  // called by itemTemplate, so should always find a value here.
  protected def selectItemFinder(i: Any): SelectItem[AA] =
    optionsWithIndex.findSelectItemByIndexOption(i.asInstanceOf[Int]).getOrElse(options(0))
}

object SelectButtonBase {
  private[primereact] val component = ScalaFnComponent[SelectButtonBase] { props =>
    CSelectButton
      .value(props.getter)
      .options(props.optionsWithIndex.raw)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.multiple, _.multiple(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.unselectable, _.unselectable(_))
      .applyOrNot(
        props.itemTemplate,
        (c, p) =>
          c.itemTemplate(raw =>
            p(props.selectItemFinder(raw.asInstanceOf[CSelectItem].value)).rawNode
          )
      )
      .applyOrNot((props.className, props.clazz).toJs, _.className(_))
      .applyOrNot(props.onChange, (c, p) => c.onChange(e => p(props.valueFinder(e.value))))
  }
}
