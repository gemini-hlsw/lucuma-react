// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import react.fa.FAIcon
import react.fa.FontAwesomeIcon
import react.fa.IconLibrary

import scala.annotation.nowarn
import scala.scalajs.js
import scala.scalajs.js.annotation._

@nowarn
object Icons {
  @js.native
  @JSImport("@fortawesome/pro-solid-svg-icons", "faDiceOne")
  val faDiceOne: FAIcon = js.native

  @js.native
  @JSImport("@fortawesome/pro-solid-svg-icons", "faDiceTwo")
  val faDiceTwo: FAIcon = js.native

  @js.native
  @JSImport("@fortawesome/pro-solid-svg-icons", "faPlus")
  val faPlus: FAIcon = js.native

  @js.native
  @JSImport("@fortawesome/pro-solid-svg-icons", "faMinus")
  val faMinus: FAIcon = js.native

  IconLibrary.add(faDiceOne, faDiceTwo, faPlus, faMinus)

  val DiceOne = FontAwesomeIcon(faDiceOne)
  val DiceTwo = FontAwesomeIcon(faDiceTwo)
  val Plus    = FontAwesomeIcon(faPlus)
  val Minus   = FontAwesomeIcon(faMinus)
}
