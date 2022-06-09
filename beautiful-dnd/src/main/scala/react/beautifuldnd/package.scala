// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

import scala.scalajs.js
import scala.scalajs.js.|
import japgolly.scalajs.react.{ facade => Raw }
import japgolly.scalajs.react.vdom.VdomBuilder
import japgolly.scalajs.react.util.JsUtil

package object beautifuldnd {
  type Id          = String
  type DraggableId = Id
  type DroppableId = Id
  type TypeId      = Id

  type Announce = String => Unit

  type MovementMode = "FLUID" | "SNAP"
  object MovementMode {
    val Fluid = "FLUID"
    val Snap  = "SNAP"
  }

  type DropReason = "DROP" | "CANCEL"
  object DropReason {
    val Drop   = "DROP"
    val Cancel = "CANCEL"
  }

  type DroppableMode = "standard" | "virtual"
  object DroppableMode {
    val Standard = "standard"
    val Virtual  = "virtual"
  }

  type Direction = "horizontal" | "vertical"
  object Direction {
    val Horizontal = "horizontal"
    val Vertical   = "vertical"
  }

  implicit class OrNullOps[A](a: A | Null) {
    def toOption: Option[A] =
      if (a == null)
        None
      else
        Some(a.asInstanceOf[A])
  }

  type SensorAPI = js.Object

  type Sensor = SensorAPI => Unit
}
