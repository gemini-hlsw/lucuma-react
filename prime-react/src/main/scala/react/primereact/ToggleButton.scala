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
import reactST.primereact.primereactStrings.left
import reactST.primereact.primereactStrings.right
import reactST.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}

import scalajs.js
import scalajs.js.JSConverters.*

case class ToggleButton(
  id:             js.UndefOr[String] = js.undefined,
  onIcon:         js.UndefOr[FontAwesomeIcon | String] = js.undefined,
  offIcon:        js.UndefOr[FontAwesomeIcon | String] = js.undefined,
  onLabel:        js.UndefOr[String] = js.undefined,
  offLabel:       js.UndefOr[String] = js.undefined,
  iconPos:        ToggleButton.IconPosition = ToggleButton.IconPosition.Left,
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
  text:           Boolean = false,
  modifiers:      Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren(ToggleButton.component):
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)

object ToggleButton:
  enum IconPosition(val iconCls: Css, val buttonCls: Css, val stringIconPos: left | right)
      derives Eq:
    case Left  extends IconPosition(PrimeStyles.ButtonIconLeft, Css.Empty, left)
    case Right extends IconPosition(PrimeStyles.ButtonIconRight, Css.Empty, right)

  private val component =
    ScalaFnComponent
      .withHooks[ToggleButton]
      .withPropsChildren
      .render { (props, children) =>
        val fullCss =
          props.clazz.toOption.orEmpty |+|
            props.size.cls |+|
            props.severity.cls |+|
            PrimeStyles.ButtonOutlined.when_(props.outlined) |+|
            PrimeStyles.ButtonRaised.when_(props.raised) |+|
            PrimeStyles.ButtonRounded.when_(props.rounded) |+|
            PrimeStyles.ButtonText.when_(props.text) |+|
            props.iconPos.buttonCls

        val onIconWithClass =
          props.onIcon.map(_.toPrimeWithClass(PrimeStyles.ButtonIcon |+| props.iconPos.iconCls))

        val offIconWithClass =
          props.offIcon.map(_.toPrimeWithClass(PrimeStyles.ButtonIcon |+| props.iconPos.iconCls))

        CToggleButton
          .iconPos(props.iconPos.stringIconPos)
          .applyOrNot(props.id, _.id(_))
          .applyOrNot(onIconWithClass, _.onIcon(_))
          .applyOrNot(offIconWithClass, _.offIcon(_))
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
          .applyOrNot(fullCss, (c, p) => c.className(p.htmlClass))(
            props.modifiers.toTagMod
          )
      }
