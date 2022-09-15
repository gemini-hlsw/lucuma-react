// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import react.common._

trait PrimeStyles {
  val Compact: Css     = Css("compact")
  val VeryCompact: Css = Css("very-compact")

  // wrappers for primereact classes
  val Invalid: Css = Css("p-invalid")

  // Input groups
  val InputGroup: Css      = Css("p-inputgroup")
  val InputGroupAddon: Css = Css("p-inputgroup-addon")

  // Button
  val ButtonSmall: Css  = Css("p-button-sm")
  val ButtonNormal: Css = Css.Empty
  val ButtonLarge: Css  = Css("p-button-lg")

  val ButtonPrimary: Css   = Css.Empty
  val ButtonSecondary: Css = Css("p-button-secondary")
  val ButtonSuccess: Css   = Css("p-button-success")
  val ButtonInfo: Css      = Css("p-button-info")
  val ButtonWarning: Css   = Css("p-button-warning")
  val ButtonHelp: Css      = Css("p-button-help")
  val ButtonDanger: Css    = Css("p-button-danger")
}

object PrimeStyles extends PrimeStyles
