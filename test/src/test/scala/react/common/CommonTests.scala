package react.common

import react.common.implicits._
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
    'cssMix - {
      val className: js.UndefOr[String] = "header"
      val clz                           = Css("abc")
      val clz2                          = Css(List("c1", "c2"))
      val clazz: js.UndefOr[Css]        = clz |+| clz2
      assert((className, clazz).toJs === "header abc c1 c2")
    }
    'cssMix1 - {
      val className: js.UndefOr[String] = js.undefined
      val clazz: js.UndefOr[Css]        = js.undefined
      assert(!(className, clazz).toJs.isDefined)
    }
    'cssMix2 - {
      val className: js.UndefOr[String] = "header"
      val clazz: js.UndefOr[Css]        = js.undefined
      assert((className, clazz).toJs === "header")
    }
    'cssMix3 - {
      val className: js.UndefOr[String] = "header"
      val clz                           = Css("abc")
      val clz2                          = Css(List("c1", "c2"))
      assert((className, (clz |+| clz2): js.UndefOr[Css]).toJs === "header abc c1 c2")
    }
  }
}
