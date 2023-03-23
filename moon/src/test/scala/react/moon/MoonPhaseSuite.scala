// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.moon

import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import react.common.TestUtils

class MoonPhaseSuite extends munit.FunSuite with TestUtils {
  test("render") {
    val moonPhase = MoonPhase()
    val html      =
      """<div size="80" class="sc-beqWaB gtNfkn"><div size="80" class="sc-gueYoa iSPddj"><div size="80" class="sc-hLseeU hEPNBz"></div></div><div size="80" style="background:black;transform:translateX(-50%) rotateY(1.0053096491487339rad)" class="sc-dmqHEX gkJfYM"></div><div size="80" class="sc-gueYoa gqtjSe"><div size="80" class="sc-hLseeU hEPNBz"></div></div></div>""".stripMargin
    assertRender(moonPhase.render, html)
  }
}
