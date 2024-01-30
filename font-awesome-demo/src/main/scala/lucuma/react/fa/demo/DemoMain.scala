// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.fa.TextLayer
import lucuma.react.fa.*
import org.scalajs.dom

import scalajs.js
import js.annotation.*

@js.native
@JSImport("@fortawesome/free-solid-svg-icons", "faCircle")
val faCircle: FAIcon = js.native

@JSExportTopLevel("Demo")
object DemoMain {

  @JSExport
  def main(): Unit = {

    // This is tedious but lets us do proper tree-shaking
    FontAwesome.library.add(
      faCircle
    )

    val App =
      ScalaFnComponent[Unit]: _ =>
        <.div(
          <.h1("FontAwesome Icons"),
          <.p(
            "Icon:",
            FontAwesomeIcon(faCircle, bounce = true)
          ),
          <.p(
            "Layered icon:",
            LayeredIcon(fixedWidth = true, size = IconSize.X3, spin = true)(
              FontAwesomeIcon(faCircle),
              TextLayer("H", inverse = true, size = IconSize.XS)(
                ^.fontWeight.lighter, // Demo styles
                ^.aria.atomic := false // Demo attributes
              ),
              CounterLayer(27)
            )
          )
        )

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    ReactDOMClient.createRoot(container).render(App())

  }
}
