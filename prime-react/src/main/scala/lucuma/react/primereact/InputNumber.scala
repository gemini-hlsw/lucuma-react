// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.InputNumber as CInputNumber
import lucuma.typed.primereact.inputnumberInputnumberMod.InputNumberValueChangeEvent
import lucuma.typed.primereact.primereactStrings.currency as cCurrency
import lucuma.typed.primereact.primereactStrings.decimal
import lucuma.typed.primereact.tooltipTooltipoptionsMod.TooltipOptions as CTooltipOptions

import scalajs.js

case class InputNumber(
  id:                String,
  clazz:             js.UndefOr[Css] = js.undefined,
  size:              js.UndefOr[Int] = js.undefined,
  value:             js.UndefOr[Double] = js.undefined,
  disabled:          js.UndefOr[Boolean] = js.undefined,
  placeholder:       js.UndefOr[String] = js.undefined,
  tooltip:           js.UndefOr[String] = js.undefined,
  tooltipOptions:    js.UndefOr[TooltipOptions] = js.undefined,
  locale:            js.UndefOr[String] = js.undefined,
  mode:              js.UndefOr[InputNumber.Mode] = js.undefined,
  prefix:            js.UndefOr[String] = js.undefined,
  suffix:            js.UndefOr[String] = js.undefined,
  min:               js.UndefOr[Double] = js.undefined,
  max:               js.UndefOr[Double] = js.undefined,
  minFractionDigits: js.UndefOr[Int] = js.undefined,
  maxFractionDigits: js.UndefOr[Int] = js.undefined,
  onFocus:           js.UndefOr[ReactFocusEventFromInput => Callback] = js.undefined,
  onBlur:            js.UndefOr[ReactFocusEventFromInput => Callback] = js.undefined,
  onValueChange:     js.UndefOr[InputNumberValueChangeEvent => Callback] = js.undefined,
  modifiers:         Seq[TagMod] = Seq.empty
) extends ReactFnProps(InputNumber.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
}

object InputNumber:
  private type Props = InputNumber

  enum Mode(val value: decimal | cCurrency):
    case Decimal extends Mode(decimal)
    case Currency(currency: String, currencyDisplay: js.UndefOr[CurrencyDisplay] = js.undefined)
        extends Mode(cCurrency)

  enum CurrencyDisplay(val value: String):
    case Symbol extends CurrencyDisplay("symbol")
    case Code   extends CurrencyDisplay("code")
    case Name   extends CurrencyDisplay("name")

  val component = ScalaFnComponent[Props]: props =>
    CInputNumber
      .id(props.id)
      .applyOrNot(props.value, _.value(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.size, _.size(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions, (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions]))
      .applyOrNot(props.locale, _.locale(_))
      .applyOrNot(
        props.mode,
        {
          case (c, p @ Mode.Decimal)                     => c.mode(p.value)
          case (c, p @ Mode.Currency(currency, display)) =>
            c.mode(p.value)
              .currency(currency)
              .applyOrNot(display.map(_.value), _.currencyDisplay(_))
        }
      )
      .applyOrNot(props.prefix, _.prefix(_))
      .applyOrNot(props.suffix, _.suffix(_))
      .applyOrNot(props.min, _.min(_))
      .applyOrNot(props.max, _.max(_))
      .applyOrNot(props.minFractionDigits, (c, p) => c.minFractionDigits(p.toDouble))
      .applyOrNot(props.maxFractionDigits, (c, p) => c.maxFractionDigits(p.toDouble))
      .applyOrNot(props.onFocus, _.onFocus(_))
      .applyOrNot(props.onBlur, _.onBlur(_))
      .applyOrNot(props.onValueChange, _.onValueChange(_))(
        props.modifiers.toTagMod
      )

extension (e: InputNumberValueChangeEvent)
  def valueOption: Option[Double] = e.value.toOption.flatMap(Option(_)).map(_.nn)
