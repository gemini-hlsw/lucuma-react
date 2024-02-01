// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.floatingui

import japgolly.scalajs.react.vdom.html_<^.{< => <<, *}
import lucuma.react.common.TestUtils

import scala.language.implicitConversions // This shouldn't be necessary, but it is.

class TooltipSuite extends munit.FunSuite with TestUtils {
  test("render") {
    val tooltip: TagMod = Tooltip(<<.span("tool"), <<.span("tip"))
    val html            = """<div><span>tool</span></div>""".stripMargin
    assertRender(<<.div(tooltip), html)
  }
}
