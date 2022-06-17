package react.semanticui.elements.segment

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.JsFnComponent
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.raw._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class SegmentInline(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[SegmentInline.SegmentInlineProps, SegmentInline] {
  override protected def cprops                     = SegmentInline.props(this)
  override protected val component                  = SegmentInline.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object SegmentInline {
  @js.native
  @JSImport("semantic-ui-react", "Segment.Inline")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait SegmentInlineProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit          = js.native
    var as: js.UndefOr[AsT]                           = js.native
    var children: js.UndefOr[React.Node]              = js.native
    var className: js.UndefOr[String]                 = js.native
    var content: js.UndefOr[SemanticShorthandContent] = js.native
  }

  def props(
    q: SegmentInline
  ): SegmentInlineProps = {
    val p = q.as.toJsObject[SegmentInlineProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsFnComponent[SegmentInlineProps, Children.Varargs](RawComponent)

  val Default: SegmentInline = SegmentInline()

  val defaultProps: SegmentInlineProps = props(Default)

  def apply(modifiers: TagMod*): SegmentInline =
    new SegmentInline(modifiers = modifiers)
}
