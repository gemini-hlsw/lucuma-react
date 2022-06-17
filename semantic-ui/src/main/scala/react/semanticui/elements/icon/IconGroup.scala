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
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
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
    var children: js.UndefOr[React.Node] = js.native
    var content: js.UndefOr[React.Node]  = js.native
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
