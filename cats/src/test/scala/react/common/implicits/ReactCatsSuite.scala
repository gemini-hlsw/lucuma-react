package react.common.implicits

import cats.tests.CatsSuite
import cats.kernel.laws.discipline._
import japgolly.scalajs.react.raw.JsNumber
import scala.scalajs.js.|
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalacheck.Cogen
import react.common.style._

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

final class StyleSpec extends CatsSuite {

  implicit val arbStyleMember: Arbitrary[String | Int] = Arbitrary {
    Gen.oneOf(Gen.posNum[Int], Gen.negNum[Int], Gen.alphaNumStr)
  }
  implicit val cogenStyleMember: Cogen[String | Int] =
    Cogen[Either[String, Int]].contramap { x =>
      (x: Any) match {
        case a: String => Left(a)
        case a: Int    => Right(a)
      }
    }
  implicit val arbStyle: Arbitrary[Style] = Arbitrary {
    arbitrary[Map[String, String | Int]].map(Style.apply)
  }
  implicit val cogenStyle: Cogen[Style] = Cogen[List[(String, String | Int)]].contramap(_.styles.toList)

  implicit val arbGStyle: Arbitrary[Css] = Arbitrary {
    for {
        cs <- Gen.listOf(Gen.alphaLowerStr)
    } yield Css(cs)
  }

  implicit val gStyleCogen: Cogen[Css] =
    Cogen[String].contramap(_.htmlClass)

  checkAll("Eq[Style]", EqTests[Style].eqv)
  checkAll("Eq[Css]", EqTests[Css].eqv)
  checkAll("Monoid[Css]", MonoidTests[Css].monoid)
}
