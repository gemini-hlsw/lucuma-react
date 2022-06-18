// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.modal

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class ModalContent(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  image:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  scrolling:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ModalContent.ModalContentProps, ModalContent] {
  override protected def cprops                     = ModalContent.props(this)
  override protected val component                  = ModalContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ModalContent {
  @js.native
  @JSImport("semantic-ui-react", "ModalContent")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ModalContentProps extends js.Object {
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

    /** A modal can contain image content. */
    var image: MyUndefOr[Boolean] = js.native

    /** A modal can use the entire size of the screen. */
    var scrolling: MyUndefOr[Boolean] = js.native
  }

  def props(q: ModalContent): ModalContentProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.image, q.scrolling)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    image:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    scrolling: MyUndefOr[Boolean] = MyUndefOr.undefined
  ): ModalContentProps = {
    val p = as.toJsObject[ModalContentProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    image.foreach(v => p.image = v)
    scrolling.foreach(v => p.scrolling = v)
    p
  }

  private val component =
    JsFnComponent[ModalContentProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ModalContent =
    new ModalContent(modifiers = modifiers)
}
