// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import japgolly.scalajs.react.*
import lucuma.react.common.*

import scalajs.js
import scalajs.js.annotation.JSImport

opaque type CssSize = String
object CssSize:
  def apply(value: String): CssSize           = value
  extension (s:    CssSize) def value: String = s
  // For some reason, we seem to need this. The compiler won't convert automatically at call sites.
  given Conversion[CssSize, js.UndefOr[CssSize]] with
    def apply(s: CssSize): js.UndefOr[CssSize] = s

opaque type LineType = String
object LineType:
  val Terminal: LineType        = "terminal"
  val NoTerminal: LineType      = "no-terminal"
  val TerminalNoBleed: LineType = "terminal-no-bleed"

opaque type LineAppearance = String
object LineAppearance:
  val Default: LineAppearance = "default"
  val Warning: LineAppearance = "warning"

final case class ListItemDropIndicator(
  instruction: Instruction,
  gap:         js.UndefOr[CssSize] = js.undefined,
  lineType:    js.UndefOr[LineType] = js.undefined
) extends GenericFnComponentP[ListItemDropIndicator.Props]:
  override protected def cprops = ListItemDropIndicator.props(this)
  override def render           = ListItemDropIndicator.component(cprops)

object ListItemDropIndicator:
  @js.native
  @JSImport("@atlaskit/pragmatic-drag-and-drop-react-drop-indicator/list-item", "DropIndicator")
  object RawListItemDropIndicator extends js.Object

  @js.native
  trait Props extends js.Object:
    var instruction: Instruction
    var lineGap: js.UndefOr[CssSize]
    var lineType: js.UndefOr[LineType]

  private def props(p: ListItemDropIndicator): Props =
    val r = (new js.Object).asInstanceOf[Props]
    r.instruction = p.instruction
    p.gap.foreach(v => r.lineGap = v)
    p.lineType.foreach(v => r.lineType = v)
    r

  val component = JsFnComponent[Props, Children.None](RawListItemDropIndicator)

final case class BoxDropIndicator(
  edge:       Edge,
  gap:        js.UndefOr[CssSize] = js.undefined,
  appearance: js.UndefOr[LineAppearance] = js.undefined,
  lineType:   js.UndefOr[LineType] = js.undefined,
  indent:     js.UndefOr[CssSize] = js.undefined
) extends GenericFnComponentP[BoxDropIndicator.Props]:
  override protected def cprops = BoxDropIndicator.props(this)
  override def render           = BoxDropIndicator.component(cprops)

object BoxDropIndicator:
  @js.native
  @JSImport("@atlaskit/pragmatic-drag-and-drop-react-drop-indicator/box", "DropIndicator")
  object RawBoxDropIndicator extends js.Object

  @js.native
  trait Props extends js.Object:
    var edge: Edge
    var gap: js.UndefOr[CssSize]
    var appearance: js.UndefOr[LineAppearance]
    var `type`: js.UndefOr[LineType]
    var indent: js.UndefOr[CssSize]

  private def props(p: BoxDropIndicator): Props =
    val r = (new js.Object).asInstanceOf[Props]
    r.edge = p.edge
    p.gap.foreach(v => r.gap = v)
    p.appearance.foreach(v => r.appearance = v)
    p.lineType.foreach(v => r.`type` = v)
    p.indent.foreach(v => r.indent = v)
    r

  val component = JsFnComponent[Props, Children.None](RawBoxDropIndicator)
