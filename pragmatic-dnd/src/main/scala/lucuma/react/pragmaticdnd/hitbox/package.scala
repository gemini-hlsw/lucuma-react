// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.hitbox

import lucuma.react.pragmaticdnd.facade.Data
import lucuma.react.pragmaticdnd.facade.Input
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import scalajs.js.JSConverters.*

type EdgeRaw = String

// enum Edge(val toJs: EdgeRaw):
//   case Top    extends Edge("top")
//   case Right  extends Edge("right")
//   case Bottom extends Edge("bottom")
//   case Left   extends Edge("left")

opaque type Edge = String
object Edge:
  val Top: Edge    = "top"
  val Right: Edge  = "right"
  val Bottom: Edge = "bottom"
  val Left: Edge   = "left"

  val Vertical: List[Edge]   = List(Top, Bottom)
  val Horizontal: List[Edge] = List(Right, Left)

@js.native
trait AttachClosestEdgeArgs extends js.Object:
  var element: Element
  var input: Input
  var allowedEdges: js.Array[Edge]

object AttachClosestEdgeArgs:
  def apply(element: Element, input: Input, allowedEdges: List[Edge]): AttachClosestEdgeArgs =
    val p = (new js.Object).asInstanceOf[AttachClosestEdgeArgs]
    p.element = element
    p.input = input
    p.allowedEdges = allowedEdges.toJSArray
    p

// @js.native
// trait DataWithClosestEdge[T] extends js.Object:
//   var data: T
//   var closestEdge: Edge
// trait ClosestEdge extends js.Object:
//   val closestEdge: Edge

@js.native
@JSImport("@atlaskit/pragmatic-drag-and-drop-hitbox/closest-edge", JSImport.Namespace)
object ClosestEdgeRaw extends js.Object:
  def attachClosestEdge[T](
    data: Data[T],
    args: AttachClosestEdgeArgs
  ): Data[T] = js.native

  def extractClosestEdge[T](data: Data[T]): js.UndefOr[Edge] = js.native

// object ClosestEdge extends js.Object
