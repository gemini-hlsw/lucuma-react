// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.header

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._

import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class Header(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  attached:               MyUndefOr[HeaderAttached] = MyUndefOr.undefined,
  block:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  dividing:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  image:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  sub:                    MyUndefOr[Boolean] = MyUndefOr.undefined,
  subheader:              MyUndefOr[HeaderSubheader] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Header.HeaderProps, Header] {
  override protected def cprops                     = Header.props(this)
  override protected val component                  = Header.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Header {
  @js.native
  @JSImport("semantic-ui-react", "Header")
  private[semanticui] object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait HeaderProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Attach header  to other content, like a segment. */
    var attached: MyUndefOr[Boolean | String] = js.native

    /** Format header to appear inside a content block. */
    var block: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Color of the header. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Show that the header is inactive. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Divide header from the content below it. */
    var dividing: MyUndefOr[Boolean] = js.native

    /** Header can sit to the left or right of other content. */
    var floated: MyUndefOr[suiraw.SemanticFLOATS] = js.native

    /** Add an icon by icon name or pass an Icon. */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native

    /** Add an image by img src or pass an Image. */
    var image: MyUndefOr[Boolean] = js.native

    /** Inverts the color of the header for dark backgrounds. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** Content headings are sized with em and are based on the font-size of their container. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /** Headers may be formatted to label smaller or de-emphasized content. */
    var sub: MyUndefOr[Boolean] = js.native

    /** Shorthand for Header.Subheader. */
    var subheader: MyUndefOr[suiraw.SemanticShorthandItemS[HeaderSubheader.HeaderSubheaderProps]] =
      js.native

    /** Align header content. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native
  }

  def props(q: Header): HeaderProps =
    rawprops(
      as = q.as,
      attached = q.attached,
      block = q.block,
      className = q.className,
      clazz = q.clazz,
      color = q.color,
      content = q.content,
      disabled = q.disabled,
      dividing = q.dividing,
      floated = q.floated,
      icon = q.icon,
      image = q.image,
      inverted = q.inverted,
      size = q.size,
      sub = q.sub,
      subheader = q.subheader,
      textAlign = q.textAlign
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    attached:  MyUndefOr[HeaderAttached] = MyUndefOr.undefined,
    block:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    color:     MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    disabled:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    dividing:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    floated:   MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
    icon:      MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
    image:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    inverted:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:      MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    sub:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    subheader: MyUndefOr[HeaderSubheader] = MyUndefOr.undefined,
    textAlign: MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined
  ): HeaderProps = {
    val p = as.toJsObject[HeaderProps]
    as.toJs.foreach(v => p.as = v)
    attached.toJs.foreach(v => p.attached = v)
    block.foreach(v => p.block = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    content.toJs.foreach(v => p.content = v)
    disabled.foreach(v => p.disabled = v)
    dividing.foreach(v => p.dividing = v)
    floated.toJs.foreach(v => p.floated = v)
    icon.toJs.foreach(v => p.icon = v)
    image.foreach(v => p.image = v)
    inverted.foreach(v => p.inverted = v)
    size.toJs.foreach(v => p.size = v)
    sub.foreach(v => p.sub = v)
    subheader.map(_.props).foreach(v => p.subheader = v)
    textAlign.toJs.foreach(v => p.textAlign = v)
    p
  }

  private val component =
    JsFnComponent[HeaderProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): Header =
    Header(modifiers = modifiers)
}
