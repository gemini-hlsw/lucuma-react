// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.image

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import react.semanticui.verticalalignment._
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui.elements.label.Label
import react.semanticui.modules.dimmer.Dimmer
import japgolly.scalajs.react.vdom.VdomNode

final case class Image(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  avatar:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  bordered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  circular:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  dimmer:                 MyUndefOr[Dimmer] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean | String] = MyUndefOr.undefined,
  hidden:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  href:                   MyUndefOr[String] = MyUndefOr.undefined,
  inline:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  rounded:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  spaced:                 MyUndefOr[ImageSpaced] = MyUndefOr.undefined,
  src:                    MyUndefOr[String] = MyUndefOr.undefined,
  ui:                     MyUndefOr[Boolean] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  wrapped:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Image.ImageProps, Image] {
  override protected def cprops                     = Image.props(this)
  override protected val component                  = Image.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Image {
  @js.native
  @JSImport("semantic-ui-react", "Image")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ImageProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** An image may be formatted to appear inline with text as an avatar. */
    var avatar: MyUndefOr[Boolean] = js.native

    /** An image may include a border to emphasize the edges of white or transparent content. */
    var bordered: MyUndefOr[Boolean] = js.native

    /** An image can appear centered in a content block. */
    var centered: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** An image may appear circular. */
    var circular: MyUndefOr[Boolean] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** An image can show that it is disabled and cannot be selected. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Shorthand for Dimmer. */
    var dimmer: MyUndefOr[Dimmer.DimmerProps] = js.native

    /** An image can sit to the left or right of other content. */
    var floated: MyUndefOr[suiraw.SemanticFLOATS] = js.native

    /** An image can take up the width of its container. */
    var fluid: MyUndefOr[Boolean | String] = js.native

    /** An image can be hidden. */
    var hidden: MyUndefOr[Boolean] = js.native

    /** Renders the Image as an <a> tag with this href. */
    var href: MyUndefOr[String] = js.native

    /** An image may appear inline. */
    var inline: MyUndefOr[Boolean] = js.native

    /** Shorthand for Label. */
    var label: MyUndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]]

    /** An image may appear rounded. */
    var rounded: MyUndefOr[Boolean] = js.native

    /** An image may appear at different sizes. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /**
     * An image can specify that it needs an additional spacing to separate it from nearby content.
     */
    var spaced: MyUndefOr[Boolean | String] = js.native

    var src: MyUndefOr[String] = js.native

    /** Whether or not to add the ui className. */
    var ui: MyUndefOr[Boolean] = js.native

    /** An image can specify its vertical alignment. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

    /** An image can render wrapped in a `div.ui.image` as alternative HTML markup. */
    var wrapped: MyUndefOr[Boolean] = js.native
  }

  def props(
    q: Image
  ): ImageProps = {
    val p = q.as.toJsObject[ImageProps]
    q.as.toJs.foreach(v => p.as = v)
    q.avatar.foreach(v => p.avatar = v)
    q.bordered.foreach(v => p.bordered = v)
    q.centered.foreach(v => p.centered = v)
    q.circular.foreach(v => p.circular = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.map(_.rawNode).foreach(v => p.content = v)
    q.disabled.foreach(v => p.disabled = v)
    q.dimmer.map(_.props).foreach(v => p.dimmer = v)
    q.floated.toJs.foreach(v => p.floated = v)
    q.fluid.foreach(v => p.fluid = v)
    q.hidden.foreach(v => p.hidden = v)
    q.href.foreach(v => p.href = v)
    q.inline.foreach(v => p.inline = v)
    q.label.toJs.foreach(v => p.label = v)
    q.rounded.foreach(v => p.rounded = v)
    q.size.toJs.foreach(v => p.size = v)
    q.spaced.toJs.foreach(v => p.spaced = v)
    q.src.foreach(v => p.src = v)
    q.ui.foreach(v => p.ui = v)
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    q.wrapped.foreach(v => p.wrapped = v)
    p
  }

  private val component =
    JsFnComponent[ImageProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): Image =
    new Image(modifiers = modifiers)

  val Default: Image = Image()

  val defaultProps: ImageProps = props(Default)
}
