// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.popup

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class PopupContent(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[PopupContent.PopupContentProps, PopupContent] {
  override protected def cprops                     = PopupContent.props(this)
  override protected val component                  = PopupContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object PopupContent {
  @js.native
  @JSImport("semantic-ui-react", "PopupContent")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PopupContentProps extends js.Object {
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
  }

  def props(q: PopupContent): PopupContentProps =
    rawprops(q.as, q.className, q.clazz, q.content)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined
  ): PopupContentProps = {
    val p = as.toJsObject[PopupContentProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsFnComponent[PopupContentProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): PopupContent =
    new PopupContent(modifiers = modifiers)
}
