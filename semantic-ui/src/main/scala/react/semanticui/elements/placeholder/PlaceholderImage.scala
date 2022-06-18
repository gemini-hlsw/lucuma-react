// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.placeholder

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class PlaceholderImage(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  square:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  rectangular:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPA[
      PlaceholderImage.PlaceholderImageProps,
      PlaceholderImage
    ] {
  override protected def cprops                     = PlaceholderImage.props(this)
  override protected val component                  = PlaceholderImage.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object PlaceholderImage {
  @js.native
  @JSImport("semantic-ui-react", "PlaceholderImage")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PlaceholderImageProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** An image can modify size correctly with responsive styles. */
    var square: MyUndefOr[Boolean] = js.native

    /** An image can modify size correctly with responsive styles. */
    var rectangular: MyUndefOr[Boolean] = js.native
  }

  def props(
    q: PlaceholderImage
  ): PlaceholderImageProps = {
    val p = q.as.toJsObject[PlaceholderImageProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.square.foreach(v => p.square = v)
    q.rectangular.foreach(v => p.rectangular = v)
    p
  }

  private val component =
    JsFnComponent[PlaceholderImageProps, Children.None](RawComponent)

  val Default: PlaceholderImage = PlaceholderImage()

  val defaultProps: PlaceholderImageProps = props(Default)

  def apply(modifiers: TagMod*): PlaceholderImage =
    new PlaceholderImage(modifiers = modifiers)
}
