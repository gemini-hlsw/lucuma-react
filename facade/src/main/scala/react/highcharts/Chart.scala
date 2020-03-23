package react.highcharts

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import org.scalajs.dom.html
import typings.highcharts.TypeofHighcharts
import typings.highcharts.mod.HTMLDOMElement
import typings.highcharts.mod.Options

@js.native
@JSImport("highcharts", JSImport.Default)
private object Highcharts extends TypeofHighcharts

object Chart {
 
  class Backend($: BackendScope[Options, Unit]) {
    private val containerRef = Ref[html.Element]

    def render(props: Options) = 
      <.div.withRef(containerRef)

    def init(props: Options): Callback =
      containerRef.foreach{element =>
        Highcharts.chart(element.asInstanceOf[HTMLDOMElement], props)
        ()
      }
  }

  private val component =
    ScalaComponent
      .builder[Options]("Chart")
      .renderBackend[Backend]
      .componentDidMount($ => $.backend.init($.props))
      .build

  def apply(props: Options) = component(props)
}
