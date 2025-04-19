// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import cats.syntax.all.*
import lucuma.react.common.style.*
import lucuma.react.common.style.given

class StyleSuite extends munit.FunSuite with TestUtils:
  test("style"):
    val a: Style = Style(Map("height" -> 42, "background" -> "black"))
    val b: Style = Style(Map("height" -> 42, "background" -> "black"))
    val c: Style = Style(Map("height" -> 42, "background" -> "black"))
    assert((a |+| b) === c)

  test("extract"):
    val a: Style = Style(Map("height" -> 42, "background" -> "black"))
    assert(a.extract[Int]("height") == 42.some)
    assert(a.extract[String]("height").isEmpty)
    assert(a.extract[String]("background") == "black".some)

  test("remove"):
    val a: Style = Style(Map("height" -> 42, "background" -> "black"))
    assert(a.remove("height").extract[String]("height").isEmpty)

  test("when"):
    assert(Style(Map("height" -> 42, "background" -> "black")).when_(true).nonEmpty)
    assert(Style(Map("height" -> 42, "background" -> "black")).when_(false).isEmpty)
    assert(Css("selector").when_(true).nonEmpty)
    assert(Css("selector").when_(false).isEmpty)

  test("unless"):
    assert(Style(Map("height" -> 42, "background" -> "black")).unless_(true).isEmpty)
    assert(Style(Map("height" -> 42, "background" -> "black")).unless_(false).nonEmpty)
    assert(Css("selector").unless_(true).isEmpty)
    assert(Css("selector").unless_(false).nonEmpty)
    // With combinations
    assert((Css("selector").unless_(false) |+| Css("selector").when_(true)).nonEmpty)
