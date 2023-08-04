// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.clipboard

import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import lucuma.react.common.TestUtils

class CopyToClipboardSuite extends munit.FunSuite with TestUtils {
  test("render") {
    val table =
      CopyToClipboard(text = "text")(<<.div("Copy"))
    val html  =
      """<div><div>Copy</div></div>""".stripMargin.replaceAll("[\n\r]", "")
    assertRender(table, html)
  }
}
