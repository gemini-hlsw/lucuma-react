package react.semanticui.elements.segment

import scala.scalajs.js
import scala.scalajs.js.|
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

final case class Segment(
  as:                     js.UndefOr[AsC] = js.undefined,
  attached:               js.UndefOr[SegmentAttached] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  circular:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  clearing:               js.UndefOr[Boolean] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  padded:                 js.UndefOr[String] = js.undefined,
  placeholder:            js.UndefOr[Boolean] = js.undefined,
  piled:                  js.UndefOr[Boolean] = js.undefined,
  raised:                 js.UndefOr[Boolean] = js.undefined,
  secondary:              js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  stacked:                js.UndefOr[Boolean] = js.undefined,
  tertiary:               js.UndefOr[Boolean] = js.undefined,
  textAlign:              js.UndefOr[SemanticTextAlignment] = js.undefined,
  vertical:               js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Segment.SegmentProps, Segment] {
  override protected def cprops                     = Segment.props(this)
  override protected val component                  = Segment.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Segment {
  @js.native
  @JSImport("semantic-ui-react", "Segment")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait SegmentProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit                 = js.native
    var as: js.UndefOr[AsT]                                  = js.native
    var attached: js.UndefOr[Boolean | String]               = js.native
    var basic: js.UndefOr[Boolean]                           = js.native
    var children: js.UndefOr[React.Node]                     = js.native
    var circular: js.UndefOr[Boolean]                        = js.native
    var className: js.UndefOr[String]                        = js.native
    var clearing: js.UndefOr[Boolean]                        = js.native
    var color: js.UndefOr[suiraw.SemanticCOLORS]             = js.native
    var compact: js.UndefOr[Boolean]                         = js.native
    var content: js.UndefOr[SemanticShorthandContent]        = js.native
    var disabled: js.UndefOr[Boolean]                        = js.native
    var floated: js.UndefOr[suiraw.SemanticFLOATS]           = js.native
    var inverted: js.UndefOr[Boolean]                        = js.native
    var loading: js.UndefOr[Boolean]                         = js.native
    var padded: js.UndefOr[String]                           = js.native
    var placeholder: js.UndefOr[Boolean]                     = js.native
    var piled: js.UndefOr[Boolean]                           = js.native
    var raised: js.UndefOr[Boolean]                          = js.native
    var secondary: js.UndefOr[Boolean]                       = js.native
    var size: js.UndefOr[suiraw.SemanticSIZES]               = js.native
    var stacked: js.UndefOr[Boolean]                         = js.native
    var tertiary: js.UndefOr[Boolean]                        = js.native
    var textAlign: js.UndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native
    var vertical: js.UndefOr[Boolean]                        = js.native
  }

  def props(
    q: Segment
  ): SegmentProps = {
    val p = q.as.toJsObject[SegmentProps]
    q.as.toJs.foreach(v => p.as = v)
    q.attached.toJs.foreach(v => p.attached = v)
    q.basic.foreach(v => p.basic = v)
    q.circular.foreach(v => p.circular = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.clearing.foreach(v => p.clearing = v)
    q.color.toJs.foreach(v => p.color = v)
    q.compact.foreach(v => p.compact = v)
    q.content.toJs.foreach(v => p.content = v)
    q.disabled.foreach(v => p.disabled = v)
    q.floated.toJs.foreach(v => p.floated = v)
    q.inverted.foreach(v => p.inverted = v)
    q.loading.foreach(v => p.loading = v)
    q.padded.foreach(v => p.padded = v)
    q.placeholder.foreach(v => p.placeholder = v)
    q.piled.foreach(v => p.piled = v)
    q.raised.foreach(v => p.raised = v)
    q.secondary.foreach(v => p.secondary = v)
    q.size.toJs.foreach(v => p.size = v)
    q.stacked.foreach(v => p.stacked = v)
    q.tertiary.foreach(v => p.tertiary = v)
    q.textAlign.toJs.foreach(v => p.textAlign = v)
    q.vertical.foreach(v => p.vertical = v)
    p
  }

  private val component =
    JsFnComponent[SegmentProps, Children.Varargs](RawComponent)

  val Default: Segment = Segment()

  val defaultProps: SegmentProps = props(Default)

  def apply(modifiers: TagMod*): Segment =
    new Segment(modifiers = modifiers)
}
