package react.semanticui.elements.segment

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.JsFnComponent
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui.raw._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class SegmentGroup(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  horizontal:             js.UndefOr[Boolean] = js.undefined,
  piled:                  js.UndefOr[Boolean] = js.undefined,
  raised:                 js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  stacked:                js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[SegmentGroup.SegmentGroupProps, SegmentGroup] {
  override protected def cprops                     = SegmentGroup.props(this)
  override protected val component                  = SegmentGroup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object SegmentGroup {
  @js.native
  @JSImport("semantic-ui-react", "Segment.Group")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait SegmentGroupProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit          = js.native
    var as: js.UndefOr[AsT]                           = js.native
    var children: js.UndefOr[React.Node]              = js.native
    var className: js.UndefOr[String]                 = js.native
    var color: js.UndefOr[suiraw.SemanticCOLORS]      = js.native
    var compact: js.UndefOr[Boolean]                  = js.native
    var content: js.UndefOr[SemanticShorthandContent] = js.native
    var horizontal: js.UndefOr[Boolean]               = js.native
    var piled: js.UndefOr[Boolean]                    = js.native
    var raised: js.UndefOr[Boolean]                   = js.native
    var size: js.UndefOr[suiraw.SemanticSIZES]        = js.native
    var stacked: js.UndefOr[Boolean]                  = js.native
  }

  def props(
    q: SegmentGroup
  ): SegmentGroupProps = {
    val p = q.as.toJsObject[SegmentGroupProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.compact.foreach(v => p.compact = v)
    q.content.toJs.foreach(v => p.content = v)
    q.horizontal.foreach(v => p.horizontal = v)
    q.piled.foreach(v => p.piled = v)
    q.raised.foreach(v => p.raised = v)
    q.size.toJs.foreach(v => p.size = v)
    q.stacked.foreach(v => p.stacked = v)
    p
  }

  private val component =
    JsFnComponent[SegmentGroupProps, Children.Varargs](RawComponent)

  val Default: SegmentGroup = SegmentGroup()

  val defaultProps: SegmentGroupProps = props(Default)

  def apply(modifiers: TagMod*): SegmentGroup =
    new SegmentGroup(modifiers = modifiers)
}
