// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common.implicits

import cats.kernel.laws.discipline._
import lucuma.react.common._
import lucuma.react.common.arb._
import munit._
import org.scalacheck.Arbitrary._

class StyleSuite extends DisciplineSuite {
  checkAll("Eq[Style]", EqTests[Style].eqv)
  checkAll("Order[Css]", OrderTests[Css].eqv)
  checkAll("Eq[Size]", EqTests[Size].eqv)
  checkAll("Monoid[Css]", MonoidTests[Css].monoid)
  checkAll("Monoid[Style]", MonoidTests[Style].monoid)
}
