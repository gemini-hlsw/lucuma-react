package react.common.implicits

import cats.tests.CatsSuite
import cats.kernel.laws.discipline._
import japgolly.scalajs.react.raw.JsNumber
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalacheck.Cogen

final class ImplicitsSpec extends CatsSuite {
  implicit val arbJsNumber: Arbitrary[JsNumber] = Arbitrary {
    Gen.oneOf(
      Gen.posNum[Byte],
      Gen.negNum[Byte],
      Gen.posNum[Short],
      Gen.negNum[Short],
      Gen.posNum[Int],
      Gen.negNum[Int],
      Gen.posNum[Float],
      Gen.negNum[Float],
      Gen.posNum[Double],
      Gen.negNum[Double]
    )
  }
  implicit val cogenJsNumber: Cogen[JsNumber] =
    Cogen[Either[Byte, Either[Short, Either[Int, Either[Float, Double]]]]].contramap { x =>
      (x: Any) match {
        case b: Byte   => Left(b)
        case b: Short  => Right(Left(b))
        case b: Int    => Right(Right(Left(b)))
        case b: Float  => Right(Right(Right(Left(b))))
        case b: Double => Right(Right(Right(Right(b))))
      }

    }

  checkAll("Eq[JsNumber]", EqTests[JsNumber].eqv)
}
