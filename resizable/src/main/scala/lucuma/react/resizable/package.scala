// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import lucuma.react.common.EnumValue
import org.scalajs.dom.html.{Element => HTMLElement}

import scala.scalajs.js

package object resizable {}

package resizable {

  sealed trait ResizeHandleAxis

  object ResizeHandleAxis {
    implicit val enumValue: EnumValue[ResizeHandleAxis] = EnumValue.instance {
      case South     => "s"
      case West      => "w"
      case East      => "e"
      case North     => "n"
      case SouthWest => "sw"
      case NorthWest => "nw"
      case SouthEast => "se"
      case NorthEast => "ne"
    }

    def fromString(s: String) =
      s match {
        case "s"  => South
        case "w"  => West
        case "e"  => East
        case "n"  => North
        case "sw" => SouthWest
        case "nw" => NorthWest
        case "se" => SouthEast
        case "ne" => NorthEast
        case x    => sys.error(s"Unkwon axis $x")
      }

    case object South     extends ResizeHandleAxis
    case object West      extends ResizeHandleAxis
    case object East      extends ResizeHandleAxis
    case object North     extends ResizeHandleAxis
    case object SouthWest extends ResizeHandleAxis
    case object NorthWest extends ResizeHandleAxis
    case object SouthEast extends ResizeHandleAxis
    case object NorthEast extends ResizeHandleAxis
  }

  @js.native
  trait ResizeSize extends js.Object {
    var width: Int
    var height: Int
  }

  @js.native
  trait ResizeCallbackData extends js.Object {
    var node: HTMLElement
    var size: ResizeSize
    var handle: ResizeHandleAxis
  }

}
