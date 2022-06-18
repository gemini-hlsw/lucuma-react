// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.loader

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Loader(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  indeterminate:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  inline:                 MyUndefOr[LoaderInline] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Loader.LoaderProps, Loader] {
  override protected def cprops                     = Loader.props(this)
  override protected val component                  = Loader.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Loader {
  @js.native
  @JSImport("semantic-ui-react", "Loader")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait LoaderProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A loader can be active or visible. */
    var active: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A loader can be disabled or hidden. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** A loader can show it's unsure of how long a task will take. */
    var indeterminate: MyUndefOr[Boolean] = js.native

    /** Loaders can appear inline with content. */
    var inline: MyUndefOr[Boolean | String] = js.native

    /** Loaders can have their colors inverted. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** Loaders can have different sizes. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native
  }

  def props(
    q: Loader
  ): LoaderProps = {
    val p = q.as.toJsObject[LoaderProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.disabled.foreach(v => p.disabled = v)
    q.indeterminate.foreach(v => p.indeterminate = v)
    q.inline.toJs.foreach(v => p.inline = v)
    q.inverted.foreach(v => p.inverted = v)
    q.size.toJs.foreach(v => p.size = v)
    p
  }

  private val component =
    JsFnComponent[LoaderProps, Children.Varargs](RawComponent)

  val Default: Loader = Loader()

  val defaultProps: LoaderProps = props(Default)

  def apply(modifiers: TagMod*): Loader =
    new Loader(modifiers = modifiers)
}
