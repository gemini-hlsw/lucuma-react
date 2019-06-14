package react.common

import react.common.syntax._
import react.common.implicits._
import react.common.style._
import cats.implicits._
import scala.scalajs.js
import utest._

object CommonTests extends TestSuite with TestUtils {

  val tests = Tests {
    'merge - {
      val a: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
      val b            = js.Object()
      val m            = merge(a, b)
      assert(a === m)
    }
    'merge1 - {
      val a: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
      val b: js.Object = js.Dynamic.literal(c = 15)
      val c: js.Object = js.Dynamic.literal(c = 15, foo = 42, bar = "foobar")
      val m            = merge(a, b)
      assert(c === m)
    }
    'merge1 - {
      val a: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
      val b: js.Object = js.Dynamic.literal(foo = 15)
      val c: js.Object = js.Dynamic.literal(foo = 15, bar = "foobar")
      val m            = merge(a, b)
      assert(c === m)
    }
    'style - {
      val a: js.Object = js.Dynamic.literal(height = 42, background = "black")
      val b: js.Object = Style(Map("height" -> 42, "background" -> "black")).toJsObject
      assert(a === b)
    }
  }
}
