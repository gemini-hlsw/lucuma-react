// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.all._
import react.semanticui.colors._
import react.semanticui.elements.icon._
import react.semanticui.elements.label.Label
import react.semanticui.elements.label.LabelDetail

object LabelsComponent {
  private val IconNewspaper                                              = Icon(name = "newspaper")
  private val IconDelete                                                 = Icon(name = "delete")
  def section(title: String, description: String)(items: TagMod): TagMod =
    <.div(
      ^.cls := "equal width row",
      <.div(
        ^.cls := "ui padded one column grid",
        <.div(
          ^.cls := "column",
          <.h2(
            ^.cls := "ui header",
            title
          ),
          <.p(description)
        ),
        <.div(
          ^.cls := "center aligned column",
          <.div(
            ^.cls := "ui doubling five column grid",
            items
          )
        )
      )
    )

  private val component =
    ScalaComponent
      .builder[Unit]("Labels")
      .render { _ =>
        <.div(
          ^.cls := "ui grid",
          <.div(
            ^.cls := "equal width row",
            <.div(
              ^.cls := "column",
              <.h1(
                ^.cls := "ui header",
                "Labels",
                <.div(
                  ^.cls := "sub header",
                  "A label displays content classification."
                )
              )
            )
          ),
          section("Samples", "A label") {
            <.div(
              ^.cls := "column docs-icon-set-column",
              new Label().apply(IconNewspaper, "23")
            )
          },
          section("Image", "As a link and an image") {
            List(
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(as = <.a, image = true)(
                  <.img(^.src := WebpackResources.AdaAvatar.resource),
                  "Ade"
                )
              ),
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(as = "a", image = true, color = Blue)(
                  <.img(^.src := WebpackResources.AdaAvatar.resource),
                  "Ade",
                  LabelDetail("Friend")
                )
              ),
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(as = "a", image = true)(
                  <.img(^.src := WebpackResources.AdaAvatar.resource),
                  "Ade",
                  IconDelete
                )
              )
            ).toTagMod
          },
          section("Tag", "A label can be formated as a tag") {
            List(
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(as = "a", tag = true).apply("New")
              ),
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(color = Red, as = "a", tag = true).apply("Upcoming")
              ),
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(color = Teal, as = "a", tag = true).apply("Featured")
              )
            ).toTagMod
          },
          section("Callback", "A label can have a callback") {
            List(
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(as = "a",
                      onClickE = (e: ReactMouseEvent, p: Label.LabelProps) =>
                        Callback.log(s"${e.screenX} $p")
                ).apply("Callback")
              )
            ).toTagMod
          },
          section("Icon", "A label can have an icon") {
            List(
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(as = "a").apply(IconNewspaper, "Icon")
              ),
              <.div(
                ^.cls := "column docs-icon-set-column",
                Label(as = "a").apply("Icon2", IconDelete.color(Red))
              )
            ).toTagMod
          }
        )
      }
      .build

  def apply() = component()
}
