// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.beautifuldnd

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait ResponderProvided extends js.Object {
  val announce: Announce
}

@js.native
trait Combine extends js.Object {
  val draggableId: DraggableId
  val droppableId: DroppableId
}

@js.native
trait DraggableLocation extends js.Object {
  val droppableId: DroppableId
  // the position of the draggable within a droppable
  val index: Int
}

@js.native
trait DraggableRubric extends js.Object {
  val draggableId: DraggableId
  @JSName("type") val tpe: TypeId
  val source: DraggableLocation
}

@js.native
trait DragStart extends DraggableRubric {
  val mode: MovementMode
}

@js.native
trait DragUpdate extends DragStart {
  val destination: DraggableLocation | Null
  val combine: Combine | Null
}

@js.native
trait DropResult extends DragUpdate {
  val reason: DropReason
}

@js.native
trait BeforeCapture extends js.Object {
  val draggableId: DraggableId
  val mode: MovementMode
}
