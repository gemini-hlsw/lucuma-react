package react.semanticui.elements.label

import scala.scalajs.js
import js.annotation._
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class LabelGroup(
  as:                     js.UndefOr[AsC] = js.undefined,
  circular:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  content:                js.UndefOr[Seq[VdomNode]] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  tag:                    js.UndefOr[Boolean] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Labels can share shapes. */
    var circular: js.UndefOr[Boolean] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Label group can share colors together. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[js.Array[suiraw.SemanticShorthandContent]] = js.native

    /** Label group can share sizes together. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** Label group can share tag formatting. */
    var tag: js.UndefOr[Boolean] = js.native
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
    as:        js.UndefOr[AsC] = js.undefined,
    circular:  js.UndefOr[Boolean] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    color:     js.UndefOr[SemanticColor] = js.undefined,
    content:   js.UndefOr[Seq[VdomNode]] = js.undefined,
    size:      js.UndefOr[SemanticSize] = js.undefined,
    tag:       js.UndefOr[Boolean] = js.undefined
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
