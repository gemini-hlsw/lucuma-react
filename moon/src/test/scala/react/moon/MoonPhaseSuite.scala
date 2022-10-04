// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.moon

import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import react.common.TestUtils

class MoonPhaseSuite extends munit.FunSuite with TestUtils {
  test("render") {
    val progressbar = MoonPhase()
    val html        =
      """<div size="80" class="sc-bcXHqe diJwlr"><div size="80" class="sc-gswNZR rnezl"><div size="80" class="sc-hLBbgP GCBql"></div></div><div size="80" style="background:black;transform:translateX(-50%) rotateY(1.0053096491487339rad)" class="sc-dkrFOg lkPnHR"></div><div size="80" class="sc-gswNZR jUJDEM"><div size="80" class="sc-hLBbgP GCBql"></div></div></div>""".stripMargin
    assertRender(progressbar.render, html)
  }
}
