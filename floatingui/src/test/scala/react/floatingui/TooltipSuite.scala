// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.floatingui

import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import react.common.TestUtils
import react.common.fnProps2Component
import react.floatingui.syntax._

class TooltipSuite extends munit.FunSuite with TestUtils {
  test("render") {
    val tooltip: TagMod = Tooltip(<<.span("tool"), <<.span("tip"))
    val html            = """<div><span>tool</span></div>""".stripMargin
    assertRender(<<.div(tooltip), html)
  }
}