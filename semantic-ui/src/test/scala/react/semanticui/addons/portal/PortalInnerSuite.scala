// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.portal

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.render._

class PortalInnerSuite extends munit.FunSuite {
  test("render") {
    val portal = PortalInner(<.div("Abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      portal.renderIntoDOM(mountNode)
      assertEquals(mountNode.outerHTML, """<div></div>""")
    }
  }
}
