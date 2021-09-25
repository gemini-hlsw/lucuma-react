package react.common.arb

import scala.scalajs.js.|
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalacheck.Cogen
import react.common._

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
    arbitrary[Map[String, String | Int]].map(Style.apply)
  }
  implicit val cogenStyle: Cogen[Style]                =
    Cogen[List[(String, String | Int)]].contramap(_.styles.toList)

  implicit val arbGStyle: Arbitrary[Css]               = Arbitrary {
    for {
      cs <- Gen.listOf(Gen.alphaLowerStr)
    } yield Css(cs)
  }

  implicit val gStyleCogen: Cogen[Css]                 =
    Cogen[String].contramap(_.htmlClass)

}

object ArbStyle extends ArbStyle
