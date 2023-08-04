// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.{Element => HTMLElement}
import lucuma.react.common.EnumValue

import scala.scalajs.js

package object draggable {
  type Bounds = DraggableBounds | String | FalseBounds.type
}

package draggable {
  sealed trait Axis

  object Axis {
    implicit val enumValue: EnumValue[Axis] = EnumValue.toLowerCaseString

    case object Both extends Axis
    case object X    extends Axis
    case object Y    extends Axis
    case object None extends Axis
  }

  case class Grid(x: Double, y: Double) {
    val value: js.Array[Double] = js.Array(x, y)
  }

  @js.native
  trait ControlPosition extends js.Object {
    var x: js.UndefOr[Double]
    var y: js.UndefOr[Double]
  }

  object ControlPosition {
    def apply(
      x: js.UndefOr[Double] = js.undefined,
      y: js.UndefOr[Double] = js.undefined
    ): ControlPosition = {
      val p = (new js.Object).asInstanceOf[ControlPosition]
      p.x = x
      p.y = y
      p
    }
  }

  @js.native
  trait DraggableBounds extends js.Object {
    var left: Double
    var right: Double
    var top: Double
    var bottom: Double
  }

  object DraggableBounds {
    def apply(left: Double, right: Double, top: Double, bottom: Double): DraggableBounds = {
      val p = (new js.Object).asInstanceOf[DraggableBounds]
      p.left = left
      p.right = right
      p.top = top
      p.bottom = bottom
      p
    }
  }

  @js.native
  trait DraggableData extends js.Object {
    var node: HTMLElement = js.native
    var x: Double         = js.native
    var y: Double         = js.native
    var deltaX: Double    = js.native
    var deltaY: Double    = js.native
    var lastX: Double     = js.native
    var lastY: Double     = js.native
  }

  @js.native
  trait PositionOffsetControlPosition extends js.Object {
    var x: js.UndefOr[String | Double]
    var y: js.UndefOr[String | Double]
  }

  object PositionOffsetControlPosition {
    def apply(
      x: js.UndefOr[String | Double] = js.undefined,
      y: js.UndefOr[String | Double] = js.undefined
    ): PositionOffsetControlPosition = {
      val p = (new js.Object).asInstanceOf[PositionOffsetControlPosition]
      p.x = x
      p.y = y
      p
    }
  }

  case object FalseBounds

  /**
   * Raw facades, shouldn't be exposed to final users
   */
  private[draggable] object raw {
    type RawOnMouseDown = js.Function1[MouseEvent, Unit]

    type RawDraggableEventHandler =
      js.Function2[MouseEvent, DraggableData, Unit]
  }
}
