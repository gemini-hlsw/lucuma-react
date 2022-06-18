// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dimmer

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class DimmerDimmable(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  blurring:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  dimmed:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[DimmerDimmable.DimmerDimmableProps, DimmerDimmable] {
  override protected def cprops                     = DimmerDimmable.props(this)
  override protected val component                  = DimmerDimmable.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DimmerDimmable {
  @js.native
  @JSImport("semantic-ui-react", "DimmerDimmable")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait DimmerDimmableProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A dimmable element can blur its contents. */
    var blurring: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Controls whether or not the dim is displayed. */
    var dimmed: MyUndefOr[Boolean] = js.native
  }

  def props(q: DimmerDimmable): DimmerDimmableProps =
    rawprops(q.as, q.blurring, q.className, q.clazz, q.content, q.dimmed)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    blurring:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[VdomNode] = MyUndefOr.undefined,
    dimmed:    MyUndefOr[Boolean] = MyUndefOr.undefined
  ): DimmerDimmableProps = {
    val p = as.toJsObject[DimmerDimmableProps]
    as.toJs.foreach(v => p.as = v)
    blurring.foreach(v => p.blurring = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    dimmed.foreach(v => p.dimmed = v)
    p
  }

  private val component =
    JsFnComponent[DimmerDimmableProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): DimmerDimmable =
    new DimmerDimmable(modifiers = modifiers)
}
