package react.common.arb

import japgolly.scalajs.react.facade.JsNumber
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._
import org.scalacheck.Cogen
import react.common._

trait ArbSize {
  implicit val arbSize: Arbitrary[Size] = Arbitrary {
    for {
      w <- arbitrary[JsNumber](arbJsNumber)
      h <- arbitrary[JsNumber](arbJsNumber)
    } yield Size(w, h)
  }

  implicit val cogenSize: Cogen[Size]   =
    Cogen[(JsNumber, JsNumber)].contramap(x => (x.width, x.height))
}

object ArbSize extends ArbSize
