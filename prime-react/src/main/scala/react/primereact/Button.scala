// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.primereact.PrimeStyles
import reactST.primereact.components.{Button => CButton}
import reactST.primereact.primereactStrings.button
import reactST.primereact.primereactStrings.reset
import reactST.primereact.primereactStrings.submit

import scalajs.js
import scalajs.js.JSConverters.*

final case class Button(
  className: js.UndefOr[String] = js.undefined,
  clazz:     js.UndefOr[Css] = js.undefined,
  onClick:   Callback = Callback.empty,
  size:      Button.Size = Button.Size.Normal,
  tpe:       Button.Type = Button.Type.Button,
  severity:  Button.Severity = Button.Severity.Primary,
  outlined:  Boolean = false,
  raised:    Boolean = false,
  rounded:   Boolean = false,
  text:      Boolean = false
) extends ReactFnPropsWithChildren[Button](Button.component)

object Button {
  enum Size(val cls: Css):
    case Small  extends Size(PrimeStyles.ButtonSmall)
    case Normal extends Size(PrimeStyles.ButtonNormal)
    case Large  extends Size(PrimeStyles.ButtonLarge)

  enum Severity(val cls: Css):
    case Primary   extends Severity(PrimeStyles.ButtonPrimary)
    case Secondary extends Severity(PrimeStyles.ButtonSecondary)
    case Success   extends Severity(PrimeStyles.ButtonSuccess)
    case Info      extends Severity(PrimeStyles.ButtonInfo)
    case Warning   extends Severity(PrimeStyles.ButtonWarning)
    case Help      extends Severity(PrimeStyles.ButtonHelp)
    case Danger    extends Severity(PrimeStyles.ButtonDanger)

  enum Type(val value: submit | reset | button):
    case Button extends Type(button)
    case Submit extends Type(submit)
    case Reset  extends Type(reset)

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
            props.text.cssOrEmpty(PrimeStyles.ButtonText)

        CButton
          .onClick(_ => props.onClick)
          .`type`(props.tpe.value)
          .applyOrNot((props.className, fullCss).cssToJs, _.className(_))(children)
      }
}
