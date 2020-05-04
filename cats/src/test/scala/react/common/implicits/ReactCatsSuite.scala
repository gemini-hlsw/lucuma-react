package react.common.implicits

import cats.tests.CatsSuite
import cats.kernel.laws.discipline._
import japgolly.scalajs.react.raw.JsNumber
import org.scalacheck.Arbitrary._
import react.common._
import react.common.arb._

final class ImplicitsSpec extends CatsSuite {
  checkAll("Eq[JsNumber]", EqTests[JsNumber].eqv)
}

final class StyleSpec     extends CatsSuite {
  checkAll("Eq[Style]", EqTests[Style].eqv)
  checkAll("Eq[Css]", EqTests[Css].eqv)
  checkAll("Eq[Size]", EqTests[Size].eqv)
  checkAll("Monoid[Css]", MonoidTests[Css].monoid)
  checkAll("Monoid[Style]", MonoidTests[Style].monoid)
}
