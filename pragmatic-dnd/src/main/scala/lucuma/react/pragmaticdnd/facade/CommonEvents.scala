// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait CommonEvents[Payload] extends js.Object {

  /**
   * Drag is about to start. Make changes you want to see in the drag preview
   *
   * _Drag previews are not generated for external drag sources (eg files)_
   */
  var onGenerateDragPreview: js.Function1[Payload, Unit] // & js.Object {
  // /**
  //  * Allows you to use the native `setDragImage` function if you want Although, we recommend using
  //  * alternative techniques (see element adapter docs)
  //  */
  // var nativeSetDragImage: js.UndefOr[js.Function2[HTMLElement, Double, Double, Unit]]
  // }

  /**
   * A drag operation has started. You can make changes to the DOM and those changes won't be
   * reflected in your _drag preview_
   */
  var onDragStart: js.Function1[Payload, Unit]

  /**
   * A throttled update of where the the user is currently dragging. Useful if you want to create a
   * high fidelity experience such as drawing.
   */
  var onDrag: js.Function1[Payload, Unit]

  /**
   * The `onDropTargetChange` event fires when the `dropTarget` hierarchy changes during a drag.
   */
  var onDropTargetChange: js.Function1[Payload, Unit]

  /**
   * The `onDrop` event occurs when a user has finished a drag and drop operation. The `onDrop`
   * event will fire when the drag operation finishes, regardless of how the drag operation finished
   * (eg due to an explicit drop, the drag being canceled, recovering from an error and so on). On
   * the web platform we cannot distinguish between dropping on no drop targets and an explicit
   * cancel, so we do not publish any information about _how_ the drag ended, only that it ended.
   *
   * The `location.current` property will accurately contain the final drop targets.
   */
  var onDrop: js.Function1[Payload, Unit]
}
