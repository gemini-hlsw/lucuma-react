// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.highcharts

import japgolly.scalajs.react.Reusable
import japgolly.scalajs.react.test.*
import utest.*

class ChartTest extends TestSuite {
  val tests = Tests {
    test("render") {
      val chart = Chart(Reusable.always(Highcharts.defaultOptions))
      ReactTestUtils2.withRendered(chart.toUnmounted) { m =>
        val html           = m.outerHTML()
        val normalizedHTML =
          html
            .replaceAll(" id=\"highcharts-\\w{7}-\\d-?\"", "")
            .replaceAll(
              "Created with Highcharts \\d+\\.\\d+\\.\\d+",
              "Created with Highcharts x.y.z"
            )
        assert(
          normalizedHTML == """<div data-highcharts-chart="0" style="overflow: hidden;"><div style="position: relative; overflow: hidden; width: 600px; height: 400px; text-align: left; line-height: normal; z-index: 0; user-select: none; outline: none;" dir="ltr" class="highcharts-container "><svg version="1.1" class="highcharts-root" style="font-family: Helvetica, Arial, sans-serif; font-size: 1rem;" xmlns="http://www.w3.org/2000/svg" width="600" height="400" viewBox="0 0 600 400" role="img" aria-label="Chart title"><desc>Created with Highcharts x.y.z</desc><defs><filter id="highcharts-drop-shadow-0"><feDropShadow dx="1" dy="1" flood-color="#000000" flood-opacity="0.75" stdDeviation="2.5"></feDropShadow></filter></defs><rect fill="#ffffff" class="highcharts-background" filter="none" x="0" y="0" width="600" height="400" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="10" width="580" height="375" filter="none"></rect><rect fill="none" class="highcharts-plot-border" data-z-index="1" stroke="#cccccc" stroke-width="0" x="10" y="10" width="580" height="375"></rect><g class="highcharts-series-group" data-z-index="3" filter="none"></g><text x="300" text-anchor="middle" class="highcharts-title" data-z-index="4" style="font-size: 1.2em; color: rgb(51, 51, 51); font-weight: bold; fill: #333333;" y="10">Chart title</text><text x="300" text-anchor="middle" class="highcharts-subtitle" data-z-index="4" style="color: rgb(102, 102, 102); font-size: 0.8em; fill: #666666;" y="10"></text><text x="10" text-anchor="start" class="highcharts-caption" data-z-index="4" style="color: rgb(102, 102, 102); font-size: 0.8em; fill: #666666;" y="387"></text><g class="highcharts-legend highcharts-no-tooltip" data-z-index="7" visibility="hidden"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" stroke="#999999" stroke-width="0" filter="none" x="0" y="0" width="8" height="8"></rect><g data-z-index="1"><g></g></g></g><text x="590" class="highcharts-credits" text-anchor="end" data-z-index="8" style="cursor: pointer; color: rgb(153, 153, 153); font-size: 0.6em; fill: #999999;" y="395">Highcharts.com</text></svg></div></div>"""
        )
      }

    }
  }
}
