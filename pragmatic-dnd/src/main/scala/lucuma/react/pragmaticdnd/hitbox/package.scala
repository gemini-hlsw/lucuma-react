// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.hitbox

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

enum Edge(val toJs: String):
  case Top    extends Edge("top")
  case Right  extends Edge("right")
  case Bottom extends Edge("bottom")
  case Left   extends Edge("left")

@js.native
@JSImport("@atlaskit/pragmatic-drag-and-drop-hitbox/closest-edge", JSImport.Default)
object ClosestEdgeRaw extends js.Object:
  def attachClosestEdge(
    rect:  js.Object,
    point: js.Object
  ): Edge = js.native

  def extractClosestEdge(edge: Edge): String = js.native

object ClosestEdge extends js.Object
