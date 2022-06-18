// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.container

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class Container(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  text:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Container.ContainerProps, Container] {
  override protected def cprops                     = Container.props(this)
  override protected val component                  = Container.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Container {
  @js.native
  @JSImport("semantic-ui-react", "Container")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ContainerProps extends js.Object {
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

    /** Container has no maximum width. */
    var fluid: MyUndefOr[Boolean] = js.native

    /** Reduce maximum width to more naturally accommodate text. */
    var text: MyUndefOr[Boolean] = js.native

    /** Align container text. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native
  }

  def props(
    q: Container
  ): ContainerProps = {
    val p = q.as.toJsObject[ContainerProps]
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.fluid.foreach(v => p.fluid = v)
    q.text.foreach(v => p.text = v)
    q.textAlign.toJs.foreach(v => p.textAlign = v)
    p
  }

  private val component =
    JsFnComponent[ContainerProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): Container =
    new Container(modifiers = modifiers)

  val Default: Container = Container()

  val defaultProps: ContainerProps = props(Default)
}
