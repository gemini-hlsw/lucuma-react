// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.icon

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class IconGroup(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[IconGroup.IconGroupProps, IconGroup] {
  override protected def cprops                     = IconGroup.props(this)
  override protected val component                  = IconGroup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object IconGroup {
  @js.native
  @JSImport("semantic-ui-react", "Icon.Group")
  object RawComponent extends js.Object

  @js.native
  trait IconGroupProps extends Icon.IconProps {
    var children: MyUndefOr[React.Node] = js.native
    var content: MyUndefOr[React.Node]  = js.native
  }

  def props(
    q: IconGroup
  ): IconGroupProps = {
    val p = q.as.toJsObject[IconGroupProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.size.toJs.foreach(v => p.size = v)
    p
  }

  private val component =
    JsComponent[IconGroupProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): IconGroup =
    new IconGroup(modifiers = modifiers)
}
