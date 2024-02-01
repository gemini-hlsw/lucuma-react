// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import cats.syntax.all.*
import lucuma.react.common.givens.given

import scala.scalajs.js

class CommonSuite extends munit.FunSuite with TestUtils:
  test("merge"):
    val a: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
    val b            = js.Object()
    val m            = merge(a, b)
    assert(a === m)

  test("merge1"):
    val a: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
    val b: js.Object = js.Dynamic.literal(c = 15)
    val c: js.Object = js.Dynamic.literal(c = 15, foo = 42, bar = "foobar")
    val m            = merge(a, b)
    assert(c === m)

  test("merge1"):
    val a: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
    val b: js.Object = js.Dynamic.literal(foo = 15)
    val c: js.Object = js.Dynamic.literal(foo = 15, bar = "foobar")
    val m            = merge(a, b)
    assert(c === m)

  test("style"):
    val a: js.Object = js.Dynamic.literal(height = 42, background = "black")
    val b: js.Object = Style(Map("height" -> 42, "background" -> "black")).toJsObject
    assert(a === b)

  test("cssMix"):
    val className: js.UndefOr[String] = "header"
    val clz                           = Css("abc")
    val clz2                          = Css(List("c1", "c2"))
    val clazz: js.UndefOr[Css]        = clz |+| clz2
    assert((className, clazz).cssToJs === "header abc c1 c2")

  test("cssMix1"):
    val className: js.UndefOr[String] = js.undefined
    val clazz: js.UndefOr[Css]        = js.undefined
    assert(!(className, clazz).cssToJs.isDefined)

  test("cssMix2"):
    val className: js.UndefOr[String] = "header"
    val clazz: js.UndefOr[Css]        = js.undefined
    assert((className, clazz).cssToJs === "header")

  test("cssMix3"):
    val className: js.UndefOr[String] = "header"
    val clz                           = Css("abc")
    val clz2                          = Css(List("c1", "c2"))
    assert((className, (clz |+| clz2): js.UndefOr[Css]).cssToJs === "header abc c1 c2")
