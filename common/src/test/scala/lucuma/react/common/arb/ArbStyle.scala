// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common.arb

import lucuma.react.common.*
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.*
import org.scalacheck.Cogen
import org.scalacheck.Gen

trait ArbStyle:
  given Arbitrary[String | Int] = Arbitrary:
    Gen.oneOf(Gen.posNum[Int], Gen.negNum[Int], Gen.alphaNumStr)

  given Cogen[String | Int] =
    Cogen[Either[String, Int]].contramap: x =>
      (x: Any) match
        case a: String => Left(a)
        case a: Int    => Right(a)
        case _         => sys.error("Unsupported type")

  given Arbitrary[Style] = Arbitrary:
    arbitrary[Map[String, String | Int]].map(m => Style(m))

  given Cogen[Style] =
    Cogen[List[(String, String | Int)]].contramap(_.value.toList)

  given Arbitrary[Css] = Arbitrary:
    for cs <- Gen.listOf(Gen.alphaLowerStr)
    yield Css(cs)

  given Cogen[Css] =
    Cogen[String].contramap(_.htmlClass)

object ArbStyle extends ArbStyle
