// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.label

import scala.scalajs.js
import js.annotation._
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class LabelGroup(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  circular:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  content:                MyUndefOr[Seq[VdomNode]] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  tag:                    MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[LabelGroup.LabelGroupProps, LabelGroup] {
  override protected def cprops                     = LabelGroup.props(this)
  override protected val component                  = LabelGroup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object LabelGroup {
  @js.native
  @JSImport("semantic-ui-react", "LabelGroup")
  object RawComponent extends js.Object

  @js.native
  trait LabelGroupProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Labels can share shapes. */
    var circular: MyUndefOr[Boolean] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Label group can share colors together. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[js.Array[suiraw.SemanticShorthandContent]] = js.native

    /** Label group can share sizes together. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /** Label group can share tag formatting. */
    var tag: MyUndefOr[Boolean] = js.native
  }

  def props(q: LabelGroup): LabelGroupProps =
    rawprops(
      q.as,
      q.circular,
      q.className,
      q.clazz,
      q.color,
      q.content,
      q.size,
      q.tag
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    circular:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    color:     MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    content:   MyUndefOr[Seq[VdomNode]] = MyUndefOr.undefined,
    size:      MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    tag:       MyUndefOr[Boolean] = MyUndefOr.undefined
  ): LabelGroupProps = {
    val p = as.toJsObject[LabelGroupProps]
    as.toJs.foreach(v => p.as = v)
    circular.foreach(v => p.circular = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    content.map(_.map(_.rawNode).toJSArray).foreach(v => p.content = v)
    size.toJs.foreach(v => p.size = v)
    tag.foreach(v => p.tag = v)
    p
  }

  private val component =
    JsComponent[LabelGroupProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): LabelGroup =
    new LabelGroup(modifiers = modifiers)
}
