package react.common

import cats.implicits._
import react.common.implicits._
import utest._

object StyleTests extends TestSuite with TestUtils {

  val tests = Tests {
    'style - {
      val a: Style = Style(Map("height" -> 42, "background" -> "black"))
      val b: Style = Style(Map("height" -> 42, "background" -> "black"))
      val c: Style = Style(Map("height" -> 42, "background" -> "black"))
      assert((a |+| b) === c)
    }
    'extract - {
      val a: Style = Style(Map("height" -> 42, "background" -> "black"))
      assert(a.extract[Int]("height") == 42.some)
      assert(a.extract[String]("height").isEmpty)
      assert(a.extract[String]("background") == "black".some)
    }
    'remove - {
      val a: Style = Style(Map("height" -> 42, "background" -> "black"))
      assert(a.remove("height").extract[String]("height").isEmpty)
    }
  }
}
