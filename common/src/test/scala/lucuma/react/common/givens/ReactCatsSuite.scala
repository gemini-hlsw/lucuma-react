// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common.givens

import cats.kernel.laws.discipline.*
import lucuma.react.common.*
import lucuma.react.common.arb.all.given
import munit.*
import org.scalacheck.Arbitrary.*

class StyleSuite extends DisciplineSuite:
  checkAll("Eq[Style]", EqTests[Style].eqv)
  checkAll("Order[Css]", OrderTests[Css].eqv)
  checkAll("Eq[Size]", EqTests[Size].eqv)
  checkAll("Monoid[Css]", MonoidTests[Css].monoid)
  checkAll("Monoid[Style]", MonoidTests[Style].monoid)
