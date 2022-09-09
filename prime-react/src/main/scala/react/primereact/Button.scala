// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Button => CButton}
import reactST.primereact.primereactStrings.button
import reactST.primereact.primereactStrings.reset
import reactST.primereact.primereactStrings.submit

import scalajs.js
import scalajs.js.JSConverters.*

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

  def apply(
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    onClick:   Callback = Callback.empty,
    size:      Size = Size.Normal,
    tpe:       Type = Type.Button,
    severity:  Severity = Severity.Primary,
    padding:   Padding = Padding.Normal
  ): CButton.Builder = {
    val fullCss = clazz.toOption.orEmpty |+| size.cls |+| severity.cls |+| padding.cls
    CButton
      .onClick(_ => onClick)
      .`type`(tpe.value)
      .applyOrNot((className, fullCss).toJs, _.className(_))
  }
}
