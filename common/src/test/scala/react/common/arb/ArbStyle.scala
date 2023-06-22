// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common.arb

import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.*
import org.scalacheck.Cogen
import org.scalacheck.Gen
import react.common.*

trait ArbStyle {
  implicit val arbStyleMember: Arbitrary[String | Int] = Arbitrary {
    Gen.oneOf(Gen.posNum[Int], Gen.negNum[Int], Gen.alphaNumStr)
  }
  implicit val cogenStyleMember: Cogen[String | Int]   =
    Cogen[Either[String, Int]].contramap { x =>
      (x: Any) match {
        case a: String => Left(a)
        case a: Int    => Right(a)
        case _         => sys.error("Unsupported type")
      }
    }
  implicit val arbStyle: Arbitrary[Style]              = Arbitrary {
    arbitrary[Map[String, String | Int]].map(m => Style(m))
  }
  implicit val cogenStyle: Cogen[Style]                =
    Cogen[List[(String, String | Int)]].contramap(_.value.toList)

  implicit val arbGStyle: Arbitrary[Css] = Arbitrary {
    for {
      cs <- Gen.listOf(Gen.alphaLowerStr)
    } yield Css(cs)
  }

  implicit val gStyleCogen: Cogen[Css] =
    Cogen[String].contramap(_.htmlClass)

}

object ArbStyle extends ArbStyle
