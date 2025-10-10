// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.SplitButton as CSplitButton
import lucuma.typed.primereact.tooltipTooltipoptionsMod.TooltipOptions as CTooltipOptions
import org.scalajs.dom.*

import scalajs.js
import scalajs.js.JSConverters.*

final case class SplitButton(
  model:           List[MenuItem],
  label:           js.UndefOr[String] = js.undefined,
  icon:            js.UndefOr[Icon] = js.undefined,
  id:              js.UndefOr[String] = js.undefined,
  dropdownIcon:    js.UndefOr[Icon] = js.undefined,
  clazz:           js.UndefOr[Css] = js.undefined,
  buttonClass:     js.UndefOr[Css] = js.undefined,
  menuButtonClass: js.UndefOr[Css] = js.undefined,
  disabled:        js.UndefOr[Boolean] = js.undefined,
  loading:         js.UndefOr[Boolean] = js.undefined,
  loadingIcon:     js.UndefOr[Icon] = js.undefined,
  onClick:         Callback = Callback.empty,
  onClickE:        ReactMouseEventFrom[HTMLElement & Element] => Callback = _ => Callback.empty,
  size:            Button.Size = Button.Size.Normal,
  severity:        Button.Severity = Button.Severity.Primary,
  outlined:        Boolean = false,
  raised:          Boolean = false,
  rounded:         Boolean = false,
  text:            Boolean = false,
  tooltip:         js.UndefOr[String] = js.undefined,
  tooltipOptions:  js.UndefOr[TooltipOptions] = js.undefined,
  modifiers:       Seq[TagMod] = Seq.empty
) extends ReactFnProps[SplitButton](SplitButton.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)
}

object SplitButton {

  private val component =
    ScalaFnComponent[SplitButton] { props =>
      val fullCss =
        props.clazz.toOption.orEmpty |+|
          props.size.cls |+|
          props.severity.cls |+|
          PrimeStyles.ButtonOutlined.when_(props.outlined) |+|
          PrimeStyles.ButtonRaised.when_(props.raised) |+|
          PrimeStyles.ButtonRounded.when_(props.rounded) |+|
          PrimeStyles.ButtonText.when_(props.text)

      val iconWithClass         =
        props.icon.map(_.toPrimeWithClass(PrimeStyles.ButtonIcon))
      val dropdownIconWithClass =
        props.dropdownIcon.map(_.toPrimeWithClass(PrimeStyles.ButtonIcon))

      CSplitButton
        .model(props.model.map(_.asInstanceOf[Any]).toJSArray)
        .onClick(e => props.onClick >> props.onClickE(e))
        .applyOrNot(props.label, _.label(_))
        .applyOrNot(props.id, _.id(_))
        .applyOrNot(iconWithClass, _.icon(_))
        .applyOrNot(dropdownIconWithClass, _.dropdownIcon(_))
        .applyOrNot(fullCss, (c, p) => c.className(p.htmlClass))
        .applyOrNot(props.buttonClass, (c, p) => c.buttonClassName(p.htmlClass))
        .applyOrNot(props.menuButtonClass, (c, p) => c.menuButtonClassName(p.htmlClass))
        .applyOrNot(props.tooltip, _.tooltip(_))
        .applyOrNot(props.disabled, _.disabled(_))
        .applyOrNot(props.loading, _.loading(_))
        .applyOrNot(props.loadingIcon, (c, p) => c.loadingIcon(p.toPrime))
        .applyOrNot(
          props.tooltipOptions,
          (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions])
        )(
          props.modifiers.toTagMod
        )
    }
}
