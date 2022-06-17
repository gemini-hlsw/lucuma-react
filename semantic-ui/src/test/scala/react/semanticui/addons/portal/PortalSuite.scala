package react.semanticui.addons.portal

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.render._

class PortalSuite extends munit.FunSuite {
  test("render") {
    val portal = Portal(open = true)(<.div("Abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      portal.renderIntoDOM(mountNode)
      assertEquals(mountNode.outerHTML, """<div></div>""")
    }
  }
}
