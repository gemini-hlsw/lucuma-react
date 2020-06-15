package react

import scala.scalajs.js
import japgolly.scalajs.react.raw.JsNumber
import org.scalajs.dom.html.{ Element => HTMLElement }
import react.common.EnumValue

package object resizable {
  type JsNumberTuple = (JsNumber, JsNumber)
}

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
    var width: JsNumber
    var height: JsNumber
  }

  @js.native
  trait ResizeCallbackData extends js.Object {
    var node: HTMLElement
    var size: ResizeSize
    var handle: ResizeHandleAxis
  }

}
