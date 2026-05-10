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

final case class ListItemDropIndicator(
  instruction: Instruction,
  lineGap:     js.UndefOr[CssSize] = js.undefined,
  lineType:    js.UndefOr[LineType] = js.undefined
) extends GenericFnComponentP[ListItemDropIndicator.Props]:
  override protected def cprops = ListItemDropIndicator.props(this)
  override def render           = ListItemDropIndicator.component(cprops)

object ListItemDropIndicator:
  @js.native
  @JSImport("@atlaskit/pragmatic-drag-and-drop-react-drop-indicator/list-item", "DropIndicator")
  object RawDropIndicator extends js.Object

  @js.native
  trait Props extends js.Object:
    var instruction: Instruction
    var lineGap: js.UndefOr[CssSize]
    var lineType: js.UndefOr[LineType]

  private def props(p: ListItemDropIndicator): Props =
    val r = (new js.Object).asInstanceOf[Props]
    r.instruction = p.instruction
    p.lineGap.foreach(v => r.lineGap = v)
    p.lineType.foreach(v => r.lineType = v)
    r

  val component = JsFnComponent[Props, Children.None](RawDropIndicator)
