// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.highcharts

import japgolly.scalajs.react.test._
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.document
import reactST.highcharts.mod.Options
import reactST.highcharts.mod.charts
import reactST.highcharts.mod.{^ => HighCharts, _}
import utest._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.annotation.JSImport

object MainTest extends TestSuite {
  val tests = Tests {
    test("render") {
      val chart = Chart(Highcharts.defaultOptions)
      ReactTestUtils.withRenderedIntoDocument(chart.toUnmounted) { m =>
        val html           = m.outerHtmlScrubbed()
        val normalizedHTML =
          html
            .replaceAll(" id=\"highcharts-\\w{7}-\\d-?\"", "")
            .replaceAll("Created with Highcharts \\d+\\.\\d+\\.\\d+",
                        "Created with Highcharts x.y.z"
            )
        assert(
          normalizedHTML == """<div data-highcharts-chart="0" style="overflow: hidden;"><div style="position: relative; overflow: hidden; width: 600px; height: 400px; text-align: left; line-height: normal; z-index: 0; user-select: none; outline: none;" dir="ltr" class="highcharts-container "><svg version="1.1" class="highcharts-root" style="font-family: &quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif; font-size: 12px;" xmlns="http://www.w3.org/2000/svg" width="600" height="400" viewBox="0 0 600 400" role="img" aria-label="Chart title"><desc>Created with Highcharts x.y.z</desc><defs><clipPath><rect x="0" y="0" width="580" height="375" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="600" height="400" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="580" height="375"></rect><rect fill="none" class="highcharts-plot-border" data-z-index="1" x="10" y="10" width="580" height="375"></rect><g class="highcharts-series-group" data-z-index="3"></g><text x="300" text-anchor="middle" class="highcharts-title" data-z-index="4" style="color: rgb(51, 51, 51); font-size: 18px; fill: #333333;" y="24">Chart title</text><text x="300" text-anchor="middle" class="highcharts-subtitle" data-z-index="4" style="color: rgb(102, 102, 102); fill: #666666;" y="10"></text><text x="10" text-anchor="start" class="highcharts-caption" data-z-index="4" style="color: rgb(102, 102, 102); fill: #666666;" y="397"></text><g class="highcharts-legend highcharts-no-tooltip" data-z-index="7" visibility="hidden"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="8" height="8"></rect><g data-z-index="1"><g></g></g></g><text x="590" class="highcharts-credits" text-anchor="end" data-z-index="8" style="cursor: pointer; color: rgb(153, 153, 153); font-size: 9px; fill: #999999;" y="395">Highcharts.com</text></svg></div></div>"""
        )
      }

    }
  }
}
