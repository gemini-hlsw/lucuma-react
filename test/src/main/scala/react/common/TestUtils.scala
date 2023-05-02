// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common

import japgolly.scalajs.react.ReactDOMServer
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.TopNode
import japgolly.scalajs.react.vdom.html_<^._
import munit.Assertions._
import org.scalajs.dom.Element

trait TestUtils {

  def assertRender(e: VdomElement, expected: String, scrub: String = ""): Unit =
    assertRender(e.rawElement, expected, scrub)

  def assertRender(e: React.Element, expected: String, scrub: String): Unit = {
    val rendered: String = ReactDOMServer.raw.renderToStaticMarkup(e)
    assertEquals(rendered.replaceAll(scrub, "").trim.replaceAll("\n", ""),
                 expected.replaceAll(scrub, "").trim.replaceAll("\n", "")
    )
  }

  def assertRender(e: React.Node, expected: String): Unit =
    assertRenderNode(Some(e), expected)

  def assertRenderNode[N <: TopNode](e: Option[React.Node], expected: String): Unit =
    e.map(x => HtmlTag("div").apply(VdomNode(x))) match {
      case Some(e) => assertRender(e.rawElement, expected)
      case _       => assert(false)
    }

  def assertRender[N <: TopNode](e: Option[TagOf[N]], expected: String): Unit =
    e match {
      case Some(e) => assertRender(e.rawElement, expected)
      case _       => assert(false)
    }

  def assertOuterHTML(node: Element, expect: String): Unit =
    assertEquals(scrubReactHtml(node.outerHTML), expect)

  private val reactRubbish =
    """\s+data-react\S*?\s*?=\s*?".*?"|<!--(?:.|[\r\n])*?-->""".r

  def scrubReactHtml(html: String): String =
    reactRubbish.replaceAllIn(html, "")
}

object TestUtils extends TestUtils
