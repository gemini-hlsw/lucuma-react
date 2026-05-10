// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import lucuma.react.pragmaticdnd.facade.Data
import lucuma.react.pragmaticdnd.facade.Input
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import scalajs.js.JSConverters.*

opaque type Edge = String
object Edge:
  val Top: Edge    = "top"
  val Right: Edge  = "right"
  val Bottom: Edge = "bottom"
  val Left: Edge   = "left"

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

@js.native
@JSImport("@atlaskit/pragmatic-drag-and-drop-hitbox/closest-edge", JSImport.Namespace)
object ClosestEdgeRaw extends js.Object:
  def attachClosestEdge[T](
    data: Data[T],
    args: AttachClosestEdgeArgs
  ): Data[T] = js.native

  def extractClosestEdge[T](data: Data[T]): Edge | Null = js.native

opaque type Operation = String
object Operation:
  val ReorderBefore: Operation = "reorder-before"
  val ReorderAfter: Operation  = "reorder-after"
  val Combine: Operation       = "combine"

opaque type Availability = String
object Availability:
  val Available: Availability    = "available"
  val NotAvailable: Availability = "not-available"
  val Blocked: Availability      = "blocked"

opaque type Axis = String
object Axis:
  val Vertical: Axis   = "vertical"
  val Horizontal: Axis = "horizontal"

@js.native
trait Instruction extends js.Object:
  val operation: Operation = js.native
  val blocked: Boolean     = js.native
  val axis: Axis           = js.native

@js.native
trait AttachInstructionArgs extends js.Object:
  var operations: js.Dictionary[Availability]
  var element: Element
  var input: Input
  var axis: Axis

opaque type Operations = Map[Operation, Availability]
object Operations:
  def apply(
    reorderBefore: Availability = Availability.NotAvailable,
    reorderAfter:  Availability = Availability.NotAvailable,
    combine:       Availability = Availability.NotAvailable
  ): Operations =
    Map(
      Operation.ReorderBefore -> reorderBefore,
      Operation.ReorderAfter  -> reorderAfter,
      Operation.Combine       -> combine
    )

  def available(operations: Operation*): Operations =
    operations.map(_ -> Availability.Available).toMap

  val Reorder: Operations                = available(Operation.ReorderBefore, Operation.ReorderAfter)
  val Combine: Operations                = available(Operation.Combine)
  val All: Operations                    =
    available(Operation.ReorderBefore, Operation.ReorderAfter, Operation.Combine)
  val ReorderOnExpandedGroup: Operations = available(Operation.ReorderBefore, Operation.Combine)

object AttachInstructionArgs:
  def apply(
    operations: Operations,
    element:    Element,
    input:      Input,
    axis:       Axis = Axis.Vertical
  ): AttachInstructionArgs =
    val p = (new js.Object).asInstanceOf[AttachInstructionArgs]
    p.operations = operations.asInstanceOf[Map[String, Availability]].toJSDictionary
    p.element = element
    p.input = input
    p.axis = axis
    p

@js.native
@JSImport("@atlaskit/pragmatic-drag-and-drop-hitbox/list-item", JSImport.Namespace)
object ListItemRaw extends js.Object:
  def attachInstruction[T](
    data: Data[T],
    args: AttachInstructionArgs
  ): Data[T] = js.native

  def extractInstruction[T](data: Data[T]): Instruction | Null = js.native
