package react.semanticui.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object HomeComponent {
  private val component =
    ScalaComponent
      .builder[Unit]("Home")
      .render { _ =>
        <.div(
          ^.cls := "ui container",
          <.div(
            ^.cls := "ui basic center aligned segment",
            <.img(
              ^.src := Logo.asInstanceOf[String],
              ^.cls := "ui small centered image"
            ),
            <.h1(
              ^.cls := "ui center aligned header",
              "ScalaJS Semantic UI React"
            )
          ),
          <.div(
            ^.cls := "ui basic padded segment",
            <.h2(^.cls := "ui dividing header", "Introduction"),
            <.p(
              "ScalaJS Semantic UI React is a facade for ",
              <.a(
                ^.href := "https://react.semantic-ui.com",
                "Semantic React UI"
              )
            )
          )
        )
      }
      .build

  def apply() = component()
}
