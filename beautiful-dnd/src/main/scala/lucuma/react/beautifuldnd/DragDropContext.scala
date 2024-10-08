// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.beautifuldnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

// https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/drag-drop-context.md
// https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/guides/responders.md
object DragDropContext {
  @js.native
  @JSImport("@atlaskit/pragmatic-drag-and-drop-react-beautiful-dnd-migration", "DragDropContext")
  object RawComponent extends js.Object

  type OnDragEndResponder         = js.Function2[DropResult, ResponderProvided, Unit]
  type OnBeforeCaptureResponder   = js.Function1[BeforeCapture, Unit]
  type OnBeforeDragStartResponder = js.Function1[DragStart, Unit]
  type OnDragStartResponder       = js.Function2[DragStart, ResponderProvided, Unit]
  type OnDragUpdateResponder      = js.Function2[DragUpdate, ResponderProvided, Unit]

  @js.native
  trait Props extends js.Object {
    var onDragEnd: OnDragEndResponder
    var onBeforeCapture: js.UndefOr[OnBeforeCaptureResponder]
    var onBeforeDragStart: js.UndefOr[OnBeforeDragStartResponder]
    var onDragStart: js.UndefOr[OnDragStartResponder]
    var onDragUpdate: js.UndefOr[OnDragUpdateResponder]
    var liftInstruction: js.UndefOr[String]
    var nonce: js.UndefOr[String]
    var sensors: js.Array[Sensor]
    var enableDefaultSensors: js.UndefOr[Boolean]
  }
  object Props {
    def apply(
      onDragEnd:            (DropResult, ResponderProvided) => Callback,
      onBeforeCapture:      js.UndefOr[BeforeCapture => Callback] = js.undefined,
      onBeforeDragStart:    js.UndefOr[DragStart => Callback] = js.undefined,
      onDragStart:          js.UndefOr[(DragStart, ResponderProvided) => Callback] = js.undefined,
      onDragUpdate:         js.UndefOr[(DragUpdate, ResponderProvided) => Callback] = js.undefined,
      liftInstruction:      js.UndefOr[String] = js.undefined,
      nonce:                js.UndefOr[String] = js.undefined,
      sensors:              js.Array[Sensor] = js.Array(),
      enableDefaultSensors: js.UndefOr[Boolean] = js.undefined
    ): Props = {
      val p = (new js.Object).asInstanceOf[Props]
      p.onDragEnd = (dr, rp) => onDragEnd(dr, rp).runNow()
      onBeforeCapture.foreach(event =>
        p.onBeforeCapture = (bc => event(bc).runNow()): OnBeforeCaptureResponder
      )
      onBeforeDragStart.foreach(event =>
        p.onBeforeDragStart = (ds => event(ds).runNow()): OnBeforeDragStartResponder
      )
      onDragStart.foreach(event =>
        p.onDragStart = ((ds, rp) => event(ds, rp).runNow()): OnDragStartResponder
      )
      onDragUpdate.foreach(event =>
        p.onDragUpdate = ((du, rp) => event(du, rp).runNow()): OnDragUpdateResponder
      )
      p.liftInstruction = liftInstruction
      p.nonce = nonce
      p.sensors = sensors
      p.enableDefaultSensors = enableDefaultSensors
      p
    }
  }

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(
    onDragEnd:            (DropResult, ResponderProvided) => Callback,
    onBeforeCapture:      js.UndefOr[BeforeCapture => Callback] = js.undefined,
    onBeforeDragStart:    js.UndefOr[DragStart => Callback] = js.undefined,
    onDragStart:          js.UndefOr[(DragStart, ResponderProvided) => Callback] = js.undefined,
    onDragUpdate:         js.UndefOr[(DragUpdate, ResponderProvided) => Callback] = js.undefined,
    liftInstruction:      js.UndefOr[String] = js.undefined,
    nonce:                js.UndefOr[String] = js.undefined,
    sensors:              js.Array[Sensor] = js.Array(),
    enableDefaultSensors: js.UndefOr[Boolean] = js.undefined
  )(children: VdomNode*) =
    component(
      Props(
        onDragEnd,
        onBeforeCapture,
        onBeforeDragStart,
        onDragStart,
        onDragUpdate,
        liftInstruction,
        nonce,
        sensors,
        enableDefaultSensors
      )
    )(children*)
}
