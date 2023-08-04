// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.circularprogressbar

import lucuma.react.common.TestUtils

class CircularProgressbarSuite extends munit.FunSuite with TestUtils {
  test("render") {
    val progressbar = CircularProgressbar(0.5)
    val html        =
      """|<svg class="CircularProgressbar " viewBox="0 0 100 100" data-test-id="CircularProgressbar"><path class="CircularProgressbar-trail" style="stroke-dasharray:289.02652413026095px 289.02652413026095px;stroke-dashoffset:0px" d="
         |      M 50,50
         |      m 0,-46
         |      a 46,46 0 1 1 0,92
         |      a 46,46 0 1 1 0,-92
         |    " stroke-width="8" fill-opacity="0"></path><path class="CircularProgressbar-path" style="stroke-dasharray:289.02652413026095px 289.02652413026095px;stroke-dashoffset:287.58139150960966px" d="
         |      M 50,50
         |      m 0,-46
         |      a 46,46 0 1 1 0,92
         |      a 46,46 0 1 1 0,-92
         |    " stroke-width="8" fill-opacity="0"></path></svg>""".stripMargin
    assertRender(progressbar.render, html)
  }
}
