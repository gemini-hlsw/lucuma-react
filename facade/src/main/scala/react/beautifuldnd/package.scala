package react

import scala.scalajs.js
import scala.scalajs.js.|
import japgolly.scalajs.react.{raw => Raw}
import japgolly.scalajs.react.vdom.Builder
import japgolly.scalajs.react.internal.JsUtil

package object beautifuldnd {
  type Id = String
  type DraggableId = Id
  type DroppableId = Id
  type TypeId = Id

  type Announce = String => Unit

  type MovementMode = "FLUID" | "SNAP"

  type DropReason = "DROP" | "CANCEL"

  implicit class OrNullOps[A](a: A | Null) {
    def toOption: Option[A] = 
      if( a == null)
        None
      else
        Some(a.asInstanceOf[A])
  }

  implicit class BuilderOps(b: Builder) {
    def addAttrsObject(o: js.Object): Unit =
      for ((k, v) <- JsUtil.objectIterator(o)) b.addAttr(k, v)

    def addRefFn[A](refFn: Raw.React.RefFn[A]): Unit =
      b.addAttr("ref", refFn)
  }
}