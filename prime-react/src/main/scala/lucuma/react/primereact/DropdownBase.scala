// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.Eq
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Dropdown as CDropdown
import lucuma.typed.primereact.dropdownDropdownMod.DropdownChangeEvent
import lucuma.typed.primereact.tooltipTooltipoptionsMod.TooltipOptions as CTooltipOptions

import scalajs.js

// TODO: Support grouped dropdowns. See MultiSelect for implementation.
private[primereact] trait DropdownBase {
  protected type AA
  protected type GG[_]
  implicit val eqAA: Eq[AA]

  val value: GG[AA]
  val options: List[SelectItem[AA]]
  val id: js.UndefOr[String]
  val clazz: js.UndefOr[Css]
  val panelClass: js.UndefOr[Css]
  val showClear: js.UndefOr[Boolean]
  val filter: js.UndefOr[Boolean]
  val showFilterClear: js.UndefOr[Boolean]
  val placeholder: js.UndefOr[String]
  val disabled: js.UndefOr[Boolean]
  val dropdownIcon: js.UndefOr[String]
  val tooltip: js.UndefOr[String]
  val tooltipOptions: js.UndefOr[TooltipOptions]
  val onChange: js.UndefOr[GG[AA] => Callback]
  val onChangeE: js.UndefOr[(GG[AA], ReactEvent) => Callback] // called after onChange
  val modifiers: Seq[TagMod]

  protected def getter: js.UndefOr[Int]
  protected def finder(i: Any): GG[AA]

  protected val optionsWithIndex: List[(SelectItem[AA], Int)] = options.zipWithIndex
}

object DropdownBase {
  private[primereact] val component = ScalaFnComponent[DropdownBase] { props =>
    val changeHandler: DropdownChangeEvent => Callback =
      parms =>
        val a = props.finder(parms.value)
        props.onChange.toOption.map(_(a)).getOrElse(Callback.empty) >>
          props.onChangeE.toOption.map(_(a, parms.originalEvent.get)).getOrElse(Callback.empty)

    CDropdown
      .value(props.getter)
      .options(props.optionsWithIndex.raw)
      .optionValue("value")
      .onChange(changeHandler)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.panelClass, (c, p) => c.panelClassName(p.htmlClass))
      .applyOrNot(props.showClear, _.showClear(_))
      .applyOrNot(props.filter, _.filter(_))
      .applyOrNot(props.showFilterClear, _.showFilterClear(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.dropdownIcon, _.dropdownIcon(_))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions,
                  (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions])
      )(
        props.modifiers.toTagMod
      )
  }
}
