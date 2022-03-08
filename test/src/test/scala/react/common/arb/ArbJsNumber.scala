// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common.arb

import japgolly.scalajs.react.facade.JsNumber
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalacheck.Cogen

trait ArbJsNumber {
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
        case _         => sys.error("Unsupported type")
      }
    }
}

object ArbJsNumber extends ArbJsNumber
