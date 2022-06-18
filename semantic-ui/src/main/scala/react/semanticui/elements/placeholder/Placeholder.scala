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

final case class Placeholder(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Placeholder.PlaceholderProps, Placeholder] {
  override protected def cprops                     = Placeholder.props(this)
  override protected val component                  = Placeholder.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Placeholder {
  @js.native
  @JSImport("semantic-ui-react", "Placeholder")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PlaceholderProps extends js.Object {
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

    /** A fluid placeholder takes up the width of its container. */
    var fluid: MyUndefOr[Boolean] = js.native

    /** A placeholder can have their color inverted. */
    var inverted: MyUndefOr[Boolean] = js.native
  }

  def props(
    q: Placeholder
  ): PlaceholderProps = {
    val p = q.as.toJsObject[PlaceholderProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.fluid.foreach(v => p.fluid = v)
    q.inverted.foreach(v => p.inverted = v)
    p
  }

  private val component =
    JsFnComponent[PlaceholderProps, Children.Varargs](RawComponent)

  val Default: Placeholder = Placeholder()

  val defaultProps: PlaceholderProps = props(Default)

  def apply(modifiers: TagMod*): Placeholder =
    new Placeholder(modifiers = modifiers)
}
