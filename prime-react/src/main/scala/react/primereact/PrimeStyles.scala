// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import react.common._

trait PrimeStyles {

  // wrappers for primereact classes
  val Invalid: Css = Css("p-invalid")

  // Input groups
  val InputGroup: Css      = Css("p-inputgroup")
  val InputGroupAddon: Css = Css("p-inputgroup-addon")

  // Button
  val ButtonSmall: Css      = Css("p-button-sm")
  val ButtonNormal: Css     = Css.Empty
  val ButtonLarge: Css      = Css("p-button-lg")
  val ButtonIcon: Css       = Css("p-button-icon")
  val ButtonIconLeft: Css   = Css("p-button-icon-left")
  val ButtonIconRight: Css  = Css("p-button-icon-right")
  val ButtonIconTop: Css    = Css("p-button-icon-top")
  val ButtonIconBottom: Css = Css("p-button-icon-bottom")
  val ButtonVertical: Css   = Css("p-button-vertical")

  val ButtonPrimary: Css   = Css.Empty
  val ButtonSecondary: Css = Css("p-button-secondary")
  val ButtonSuccess: Css   = Css("p-button-success")
  val ButtonInfo: Css      = Css("p-button-info")
  val ButtonWarning: Css   = Css("p-button-warning")
  val ButtonHelp: Css      = Css("p-button-help")
  val ButtonDanger: Css    = Css("p-button-danger")

  val ButtonOutlined: Css = Css("p-button-outlined")
  val ButtonRaised: Css   = Css("p-button-raised")
  val ButtonRounded: Css  = Css("p-button-rounded")
  val ButtonText: Css     = Css("p-button-text")

  // Tag
  val TagIcon: Css = Css("p-tag-icon")
}

object PrimeStyles extends PrimeStyles
