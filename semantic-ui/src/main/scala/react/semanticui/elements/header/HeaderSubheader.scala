// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.header

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class HeaderSubheader(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[HeaderSubheader.HeaderSubheaderProps, HeaderSubheader] {
  override protected def cprops                     = HeaderSubheader.props(this)
  override protected val component                  = HeaderSubheader.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object HeaderSubheader {
  @js.native
  @JSImport("semantic-ui-react", "HeaderSubheader")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait HeaderSubheaderProps extends js.Object {
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

  def props(
    q: HeaderSubheader
  ): HeaderSubheaderProps = {
    val p = q.as.toJsObject[HeaderSubheaderProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsFnComponent[HeaderSubheaderProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): HeaderSubheader =
    new HeaderSubheader(modifiers = modifiers)
}
