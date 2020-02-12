package react

import scala.scalajs.js.|

package object beautifuldnd {
  type Id = String
  type DraggableId = Id
  type DroppableId = Id
  type TypeId = Id

  type Announce = String => Unit

  type MovementMode = "FLUID" | "SNAP"

  type DropReason = "DROP" | "CANCEL"
}