// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.label

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.raw._

import scala.scalajs.js

import js.annotation._

final case class LabelDetail(
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[LabelDetail.LabelDetailProps, LabelDetail] {
  override protected def cprops    = LabelDetail.props(this)
  override protected val component = LabelDetail.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object LabelDetail {
  @js.native
  @JSImport("semantic-ui-react", "LabelDetail")
  object RawComponent extends js.Object

  @js.native
  trait LabelDetailProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native
    var as: js.UndefOr[AsT]                           = js.native
    var children: js.UndefOr[React.Node]              = js.native
    var className: js.UndefOr[String]                 = js.native
    var content: js.UndefOr[SemanticShorthandContent] = js.native
  }

  def props(q: LabelDetail): LabelDetailProps = {
    val p = q.as.toJsObject[LabelDetailProps]
    q.as.toJs.foreachUnchecked(v => p.as = v)
    (q.className, q.clazz).cssToJs.foreach(v => p.className = v)
    q.content.toJs.foreachUnchecked(v => p.content = v)
    p
  }

  private val component =
    JsComponent[LabelDetailProps, Children.Varargs, Null](RawComponent)
}
