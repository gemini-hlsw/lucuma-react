// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.segment

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.JsFnComponent
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui.raw._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Segment(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  attached:               MyUndefOr[SegmentAttached] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  circular:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  clearing:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  compact:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  padded:                 MyUndefOr[String] = MyUndefOr.undefined,
  placeholder:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  piled:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  raised:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  secondary:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  stacked:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  tertiary:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  vertical:               MyUndefOr[Boolean] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT]                                  = js.native
    var attached: MyUndefOr[Boolean | String]               = js.native
    var basic: MyUndefOr[Boolean]                           = js.native
    var children: MyUndefOr[React.Node]                     = js.native
    var circular: MyUndefOr[Boolean]                        = js.native
    var className: MyUndefOr[String]                        = js.native
    var clearing: MyUndefOr[Boolean]                        = js.native
    var color: MyUndefOr[suiraw.SemanticCOLORS]             = js.native
    var compact: MyUndefOr[Boolean]                         = js.native
    var content: MyUndefOr[SemanticShorthandContent]        = js.native
    var disabled: MyUndefOr[Boolean]                        = js.native
    var floated: MyUndefOr[suiraw.SemanticFLOATS]           = js.native
    var inverted: MyUndefOr[Boolean]                        = js.native
    var loading: MyUndefOr[Boolean]                         = js.native
    var padded: MyUndefOr[String]                           = js.native
    var placeholder: MyUndefOr[Boolean]                     = js.native
    var piled: MyUndefOr[Boolean]                           = js.native
    var raised: MyUndefOr[Boolean]                          = js.native
    var secondary: MyUndefOr[Boolean]                       = js.native
    var size: MyUndefOr[suiraw.SemanticSIZES]               = js.native
    var stacked: MyUndefOr[Boolean]                         = js.native
    var tertiary: MyUndefOr[Boolean]                        = js.native
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native
    var vertical: MyUndefOr[Boolean]                        = js.native
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
