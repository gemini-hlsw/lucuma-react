package react.semanticui.elements.placeholder

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class PlaceholderImageSuite extends munit.FunSuite {
  test("render") {
    val paragraph = PlaceholderImage()
    ReactTestUtils.withNewBodyElement { mountNode =>
      paragraph.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="image"></div>""")
    }
  }
}
