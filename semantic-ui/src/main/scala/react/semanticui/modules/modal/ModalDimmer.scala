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

final case class ModalDimmer(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  blurring:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  scrolling:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ModalDimmer.ModalDimmerProps, ModalDimmer] {
  override protected def cprops                     = ModalDimmer.props(this)
  override protected val component                  = ModalDimmer.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ModalDimmer {
  @js.native
  @JSImport("semantic-ui-react", "ModalDimmer")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ModalDimmerProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A dimmer can be blurred. */
    var blurring: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A dimmer can center its contents in the viewport. */
    var centered: MyUndefOr[Boolean] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A dimmer can be inverted. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** The node where the modal should mount. Defaults to document.body. */
    // mountNode?: any

    /** A dimmer can make body scrollable. */
    var scrolling: MyUndefOr[Boolean] = js.native
  }

  def props(q: ModalDimmer): ModalDimmerProps =
    rawprops(q.as, q.blurring, q.className, q.clazz, q.centered, q.content, q.inverted, q.scrolling)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    blurring:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    centered:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    inverted:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    scrolling: MyUndefOr[Boolean] = MyUndefOr.undefined
  ): ModalDimmerProps = {
    val p = as.toJsObject[ModalDimmerProps]
    as.toJs.foreach(v => p.as = v)
    blurring.foreach(v => p.blurring = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    centered.foreach(v => p.centered = v)
    content.toJs.foreach(v => p.content = v)
    inverted.foreach(v => p.inverted = v)
    scrolling.foreach(v => p.scrolling = v)
    p
  }

  private val component =
    JsFnComponent[ModalDimmerProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ModalDimmer =
    new ModalDimmer(modifiers = modifiers)
}
