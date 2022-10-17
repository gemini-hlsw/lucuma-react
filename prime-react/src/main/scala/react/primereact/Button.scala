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
import reactST.primereact.components.{Button => CButton}
import reactST.primereact.primereactStrings.button
import reactST.primereact.primereactStrings.reset
import reactST.primereact.primereactStrings.submit
import reactST.primereact.primereactStrings.top

import scalajs.js
import scalajs.js.JSConverters.*

case class Button(
  label:     js.UndefOr[String] = js.undefined,
  icon:      js.UndefOr[FontAwesomeIcon] = js.undefined,
  iconPos:   Button.IconPosition = Button.IconPosition.Left,
  clazz:     js.UndefOr[Css] = js.undefined,
  onClick:   Callback = Callback.empty,
  onClickE:  ReactMouseEventFrom[HTMLButtonElement & Element] => Callback = _ => Callback.empty,
  size:      Button.Size = Button.Size.Normal,
  tpe:       Button.Type = Button.Type.Button,
  severity:  Button.Severity = Button.Severity.Primary,
  outlined:  Boolean = false,
  raised:    Boolean = false,
  rounded:   Boolean = false,
  text:      Boolean = false,
  modifiers: Seq[TagMod] = Seq.empty
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

  enum IconPosition(val iconCls: Css, val buttonCls: Css) derives Eq:
    case Left   extends IconPosition(PrimeStyles.ButtonIconLeft, Css.Empty)
    case Right  extends IconPosition(PrimeStyles.ButtonIconRight, Css.Empty)
    case Top    extends IconPosition(PrimeStyles.ButtonIconTop, PrimeStyles.ButtonVertical)
    case Bottom extends IconPosition(PrimeStyles.ButtonIconBottom, PrimeStyles.ButtonVertical)

  private val component =
    ScalaFnComponent
      .withHooks[Button]
      .withPropsChildren
      .render { (props, children) =>
        extension (b: Boolean) def cssOrEmpty(css: Css): Css = if b then css else Css.Empty

        val fullCss =
          props.clazz.toOption.orEmpty |+|
            props.size.cls |+|
            props.severity.cls |+|
            props.outlined.cssOrEmpty(PrimeStyles.ButtonOutlined) |+|
            props.raised.cssOrEmpty(PrimeStyles.ButtonRaised) |+|
            props.rounded.cssOrEmpty(PrimeStyles.ButtonRounded) |+|
            props.text.cssOrEmpty(PrimeStyles.ButtonText) |+|
            props.iconPos.buttonCls

        val iconWithClass =
          props.icon.map(_.clazz(PrimeStyles.ButtonIcon |+| props.iconPos.iconCls))

        CButton
          .onClick(e => props.onClick >> props.onClickE(e))
          .`type`(props.tpe.value)
          .applyOrNot(props.label, _.label(_))
          .applyOrNot(iconWithClass, (c, p) => c.icon(p.raw))
          .applyOrNot(fullCss, (c, p) => c.className(p.htmlClass))(
            props.modifiers.toTagMod,
            children
          )
      }
}
