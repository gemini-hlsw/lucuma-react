package react.semanticui.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.elements.icon.IconFlip._
import react.semanticui.elements.icon.IconRotated._
import react.semanticui.elements.icon.IconCorner
import react.semanticui.elements.icon._
import react.semanticui.sizes._
import react.semanticui.colors._
import react.semanticui._

object IconsComponent {
  val IconMailOutline                = Icon(name = "mail outline")
  val IconSearch                     = Icon(name = "search")
  val IconFeed                       = Icon(name = "feed")
  val IconCloudUpload                = Icon(name = "cloud upload")
  val IconEdit                       = Icon(name = "edit")
  val IconDashboard                  = Icon(name = "dashboard")
  val IconNewspaper                  = Icon(name = "newspaper")
  val IconAreaChart                  = Icon(name = "area chart")
  val IconTwitter                    = Icon(name = "twitter")
  val IconAdd                        = Icon(name = "add")
  val IconCircleThin                 = Icon(name = "circle thin")
  val IconUser                       = Icon(name = "user")
  val AllSizes: List[SemanticSize]   = List(Mini, Tiny, Small, Medium, Large, Big, Huge, Massive)
  val AllColors: List[SemanticColor] =
    List(Red, Orange, Yellow, Olive, Green, Teal, Blue, Violet, Purple, Pink, Brown, Grey, Black)
  private val sampleIcons            = Map("mail outline" -> IconMailOutline, "search" -> IconSearch)

  def section(title: String, description: String)(icons: TagMod): TagMod =
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
            icons
          )
        )
      )
    )

  private val component =
    ScalaComponent
      .builder[Unit]("Home")
      .render { _ =>
        <.div(
          ^.cls := "ui grid",
          <.div(
            ^.cls := "equal width row",
            <.div(
              ^.cls := "column",
              <.h1(
                ^.cls := "ui header",
                "Icons",
                <.div(
                  ^.cls := "sub header",
                  "An icon is a glyph used to represent something else."
                )
              )
            )
          ),
          section("Samples", "An icon set contains an arbitrary number of glyphs.") {
            sampleIcons.map { case (name, icon) =>
              <.div(
                ^.cls := "column docs-icon-set-column",
                icon.size(Big),
                <.p(
                  ^.cls := "name",
                  name
                )
              )
            }.toTagMod
          },
          section("Sizes", "An icon can vary in size") {
            AllSizes.map { case s =>
              <.div(
                ^.cls := "column docs-icon-set-column",
                IconFeed.size(s),
                <.p(
                  ^.cls := "name",
                  s.toJs
                )
              )
            }.toTagMod
          },
          section("Flipped", "An icon can be flipped") {
            List(Horizontally, Vertically).map { case o =>
              <.div(
                ^.cls := "column docs-icon-set-column",
                IconCloudUpload.flipped(o),
                <.p(
                  ^.cls := "name",
                  o.toJs
                )
              )
            }.toTagMod
          },
          section("Colors", "Icons support colors") {
            AllColors.map { case o =>
              <.div(
                ^.cls := "column docs-icon-set-column",
                IconEdit.color(o),
                <.p(
                  ^.cls := "name",
                  o.toJs
                )
              )
            }.toTagMod
          },
          section("Rotated", "Icons can be rotated") {
            List(Clockwise, CounterClockwise).map { case o =>
              <.div(
                ^.cls := "column docs-icon-set-column",
                IconDashboard.rotated(o),
                <.p(
                  ^.cls := "name",
                  o.toJs
                )
              )
            }.toTagMod
          },
          section("Bordered", "Icons can have a border") {
            <.div(
              ^.cls := "column docs-icon-set-column",
              IconNewspaper.bordered(),
              <.p(
                ^.cls := "name",
                "bordered"
              )
            )
          },
          section("Circular", "Icons can be circular") {
            <.div(
              ^.cls := "column docs-icon-set-column",
              IconAreaChart.circular(),
              <.p(
                ^.cls := "name",
                "circular"
              )
            )
          },
          section("Icon Group", "Icons can be grouped") {
            <.div(
              ^.cls := "column docs-icon-set-column",
              IconGroup(size = Huge)(IconCircleThin.size(Big), IconUser),
              <.p(
                ^.cls := "name",
                "icon group"
              )
            )
          },
          section("Corner Icon", "An icon can be in a corner") {
            <.div(
              ^.cls := "column docs-icon-set-column",
              IconGroup(size = Large)(IconTwitter, IconAdd.corner(IconCorner.BottomRight)),
              <.p(
                ^.cls := "name",
                "icon group"
              )
            )
          }
        )
      }
      .build

  def apply() = component()
}
