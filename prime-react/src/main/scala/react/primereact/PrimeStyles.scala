// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.syntax.all._
import react.common._

object PrimeStyles {
  // These are not prime react specific. Perhaps should be moved to wherever our
  // "higher level" components live. (Components that use Views and Enumerations)
  val Compact: Css     = Css("compact")
  val VeryCompact: Css = Css("very-compact")

  val FormColumn: Css            = Css("form-column")
  val FormColumnCompact: Css     = FormColumn |+| Compact
  val FormColumnVeryCompact: Css = FormColumn |+| VeryCompact

  val FormField: Css      = Css("form-field")
  val FormFieldLabel: Css = Css("form-field-label")

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
