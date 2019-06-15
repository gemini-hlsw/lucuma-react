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
  }
}
