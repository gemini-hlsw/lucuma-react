// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.derived.*
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.*
import react.common.*
import react.fa.FontAwesomeIcon
import react.primereact.PrimeStyles
import reactST.primereact.components.{ToggleButton => CToggleButton}
import reactST.primereact.tooltipoptionsMod.{TooltipOptions => CTooltipOptions}

import scalajs.js
import scalajs.js.JSConverters.*

case class ToggleButton(
  id:             js.UndefOr[String] = js.undefined,
  onIcon:         js.UndefOr[FontAwesomeIcon] = js.undefined,
  offIcon:        js.UndefOr[FontAwesomeIcon] = js.undefined,
  onLabel:        js.UndefOr[String] = js.undefined,
  offLabel:       js.UndefOr[String] = js.undefined,
  iconPos:        Button.IconPosition = Button.IconPosition.Left,
  checked:        js.UndefOr[Boolean] = js.undefined, // default: false
  tabIndex:       js.UndefOr[Int] = js.undefined,     // default: 0
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  onChange:       js.UndefOr[Boolean => Callback] = js.undefined,
  onChangeE:      js.UndefOr[(ReactEventFrom[Element], Boolean) => Callback] = js.undefined,
  size:           Button.Size = Button.Size.Normal,
  severity:       Button.Severity = Button.Severity.Primary,
  outlined:       Boolean = false,
  raised:         Boolean = false,
  rounded:        Boolean = false,
  text:           Boolean = false
) extends ReactFnPropsWithChildren(ToggleButton.component)

object ToggleButton:
  private val component =
    ScalaFnComponent
      .withHooks[ToggleButton]
      .withPropsChildren
      .render { (props, children) =>
        val fullCss =
          props.clazz.toOption.orEmpty |+|
            props.size.cls |+|
            props.severity.cls |+|
            Option.when(props.outlined)(PrimeStyles.ButtonOutlined).orEmpty |+|
            Option.when(props.raised)(PrimeStyles.ButtonRaised).orEmpty |+|
            Option.when(props.rounded)(PrimeStyles.ButtonRounded).orEmpty |+|
            Option.when(props.text)(PrimeStyles.ButtonText).orEmpty |+|
            props.iconPos.buttonCls

        val onIconWithClass =
          props.onIcon.map(_.clazz(PrimeStyles.ButtonIcon |+| props.iconPos.iconCls))

        val offIconWithClass =
          props.offIcon.map(_.clazz(PrimeStyles.ButtonIcon |+| props.iconPos.iconCls))

        CToggleButton
          .applyOrNot(props.id, _.id(_))
          .applyOrNot(onIconWithClass, (c, p) => c.onIcon(p.raw))
          .applyOrNot(offIconWithClass, (c, p) => c.offIcon(p.raw))
          .applyOrNot(props.onLabel, _.onLabel(_))
          .applyOrNot(props.offLabel, _.offLabel(_))
          .applyOrNot(props.checked, _.checked(_))
          .applyOrNot(props.tabIndex, _.tabIndex(_))
          .applyOrNot(props.tooltip, _.tooltip(_))
          .applyOrNot(
            props.tooltipOptions,
            (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions])
          )
          .onChange(e =>
            props.onChange.map(_(e.value)).toOption.getOrEmpty >>
              props.onChangeE.map(_(e.originalEvent, e.value)).toOption.getOrEmpty
          )
          .applyOrNot(fullCss, (c, p) => c.className(p.htmlClass))
      }
