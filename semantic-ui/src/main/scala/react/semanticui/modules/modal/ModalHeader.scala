// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.modal

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

import scala.scalajs.js

import js.annotation._

final case class ModalHeader(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ModalHeader.ModalHeaderProps, ModalHeader] {
  override protected def cprops                     = ModalHeader.props(this)
  override protected val component                  = ModalHeader.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ModalHeader {
  @js.native
  @JSImport("semantic-ui-react", "ModalHeader")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ModalHeaderProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native
  }

  def props(q: ModalHeader): ModalHeaderProps =
    rawprops(q.as, q.className, q.clazz, q.content)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined
  ): ModalHeaderProps = {
    val p = as.toJsObject[ModalHeaderProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    content.toJs.foreachUnchecked(v => p.content = v)
    p
  }

  private val component =
    JsFnComponent[ModalHeaderProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ModalHeader =
    new ModalHeader(modifiers = modifiers)
}
