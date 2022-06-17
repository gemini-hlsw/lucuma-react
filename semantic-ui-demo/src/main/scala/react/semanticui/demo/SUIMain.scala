package react.semanticui.demo

import org.scalajs.dom
import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import extra.router._

@JSExportTopLevel("SUIMain")
object SUiMain {
  @JSExport
  def main(): Unit = {
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    Router(BaseUrl.fromWindowOrigin / "scalajs-react-semantic-ui/", Routing.config)().renderIntoDOM(
      container
    )

    ()
  }
}

sealed trait ElementItem
case object IconsElement  extends ElementItem
case object LabelsElement extends ElementItem

sealed trait Page
case object HomePage                         extends Page
final case class ElementPage(e: ElementItem) extends Page

object Routing {
  val config: RouterConfig[Page] = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._

    (
      trimSlashes
        | staticRoute(root, HomePage) ~>
        render(HomeComponent.apply())
        | staticRoute("elements/icons", ElementPage(IconsElement)) ~>
        render(IconsComponent.apply())
        | staticRoute("elements/labels", ElementPage(LabelsElement)) ~>
        render(LabelsComponent.apply())
    ).notFound(redirectToPage(HomePage)(SetRouteVia.HistoryReplace))
      .renderWith(layout)
      .logToConsole
  }

  private def layout(c: RouterCtl[Page], r: Resolution[Page]) = Layout(Layout.Props(c, r))
}
