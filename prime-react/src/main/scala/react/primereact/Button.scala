// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.derived.*
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.typed.primereact.components.{Button => CButton}
import lucuma.typed.primereact.primereactStrings.bottom
import lucuma.typed.primereact.primereactStrings.button
import lucuma.typed.primereact.primereactStrings.left
import lucuma.typed.primereact.primereactStrings.reset
import lucuma.typed.primereact.primereactStrings.right
import lucuma.typed.primereact.primereactStrings.submit
import lucuma.typed.primereact.primereactStrings.top
import lucuma.typed.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}
import org.scalajs.dom.*
import react.common.*

import scalajs.js

case class Button(
  label:          js.UndefOr[String] = js.undefined,
  icon:           js.UndefOr[Icon] = js.undefined,
  iconPos:        Button.IconPosition = Button.IconPosition.Left,
  badge:          js.UndefOr[String] = js.undefined,
  badgeClass:     js.UndefOr[Css] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  disabled:       js.UndefOr[Boolean] = js.undefined,
  loading:        js.UndefOr[Boolean] = js.undefined,
  loadingIcon:    js.UndefOr[Icon] = js.undefined,
  onClick:        Callback = Callback.empty,
  onClickE:       ReactMouseEventFrom[HTMLButtonElement & Element] => Callback = _ => Callback.empty,
  size:           Button.Size = Button.Size.Normal,
  tpe:            Button.Type = Button.Type.Button,
  severity:       Button.Severity = Button.Severity.Primary,
  outlined:       Boolean = false,
  raised:         Boolean = false,
  rounded:        Boolean = false,
  text:           Boolean = false,
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  modifiers:      Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren[Button](Button.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  // You can add content to the button as children without this method, but if you want
  // to add event handlers or other attributes, you'll need to use the modifiers.
  def withMods(mods: TagMod*) = addModifiers(mods)
}

object Button {
  enum Size(val cls: Css) derives Eq:
    case Small  extends Size(PrimeStyles.ButtonSmall)
    case Normal extends Size(PrimeStyles.ButtonNormal)
    case Large  extends Size(PrimeStyles.ButtonLarge)

  enum Severity(val cls: Css) derives Eq:
    case Primary   extends Severity(PrimeStyles.ButtonPrimary)
    case Secondary extends Severity(PrimeStyles.ButtonSecondary)
    case Success   extends Severity(PrimeStyles.ButtonSuccess)
    case Info      extends Severity(PrimeStyles.ButtonInfo)
    case Warning   extends Severity(PrimeStyles.ButtonWarning)
    case Help      extends Severity(PrimeStyles.ButtonHelp)
    case Danger    extends Severity(PrimeStyles.ButtonDanger)

  enum Type(val value: submit | reset | button) derives Eq:
    case Button extends Type(button)
    case Submit extends Type(submit)
    case Reset  extends Type(reset)

  enum IconPosition(
    val iconCls:       Css,
    val buttonCls:     Css,
    val stringIconPos: left | right | top | bottom
  ) derives Eq:
    case Left  extends IconPosition(PrimeStyles.ButtonIconLeft, Css.Empty, left)
    case Right extends IconPosition(PrimeStyles.ButtonIconRight, Css.Empty, right)
    case Top   extends IconPosition(PrimeStyles.ButtonIconTop, PrimeStyles.ButtonVertical, top)
    case Bottom
        extends IconPosition(PrimeStyles.ButtonIconBottom, PrimeStyles.ButtonVertical, bottom)

  private val component =
    ScalaFnComponent
      .withHooks[Button]
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

        val iconWithClass =
          props.icon.map(_.toPrimeWithClass(PrimeStyles.ButtonIcon |+| props.iconPos.iconCls))

        CButton
          .onClick(e => props.onClick >> props.onClickE(e))
          .`type`(props.tpe.value)
          .iconPos(props.iconPos.stringIconPos)
          .applyOrNot(props.label, _.label(_))
          .applyOrNot(iconWithClass, _.icon(_))
          .applyOrNot(fullCss, (c, p) => c.className(p.htmlClass))
          .applyOrNot(props.tooltip, _.tooltip(_))
          .applyOrNot(props.disabled, _.disabled(_))
          .applyOrNot(props.loading, _.loading(_))
          .applyOrNot(props.loadingIcon, (c, p) => c.loadingIcon(p.toPrime))
          .applyOrNot(props.badge, _.badge(_))
          .applyOrNot(props.badgeClass, (c, p) => c.badgeClassName(p.htmlClass))
          .applyOrNot(
            props.tooltipOptions,
            (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions])
          )(
            props.modifiers.toTagMod,
            children
          )
      }
}
