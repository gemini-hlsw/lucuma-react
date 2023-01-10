// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.radio

import japgolly.scalajs.react.test._
import react.common.syntax.render._

class RadioSuite extends munit.FunSuite {
  test("pusher") {
    val check = Radio()
    ReactTestUtils.withNewBodyElement { mountNode =>
      check.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui fitted radio checkbox"><input class="hidden" readonly="" tabindex="0" type="radio" value=""><label></label></div>"""
      )
    }
  }
}
