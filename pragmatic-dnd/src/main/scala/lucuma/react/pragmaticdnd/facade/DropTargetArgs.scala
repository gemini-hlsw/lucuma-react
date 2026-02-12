// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import japgolly.scalajs.react.callback.*
import org.scalajs.dom.HTMLElement

import scalajs.js

@js.native
trait DropTargetArgs[S, T] extends DropTargetEvents[S, T] {

  /**
   * The `element` that you want to attach drop target behaviour to. The `element` is the unique
   * _key_ for a drop target
   */
  var element: HTMLElement

  /**
   * t A function that returns `data` you want to attach to the drop target. `getData()` is called
   * _repeatedly_ while the user is dragging over the drop target in order to power addons
   */
  var getData: js.UndefOr[js.Function1[DropTargetGetFeedbackArgs[S], T]]

  /**
   * Used to conditionally block dropping. By default a drop target can be dropped on.
   *
   * Return `false` if you want to block a drop.
   *
   * Blocking dropping on a drop target will not block dropping on child or parent drop targets. If
   * you want child or parent drop targets to block dropping, then they will need to return `false`
   * from their `canDrop()`
   *
   * `canDrop()` is called _repeatedly_ while a drop target is being dragged over to allow you to
   * dynamically change your mind as to whether a drop target can be dropped on.
   */
  var canDrop: js.UndefOr[js.Function1[DropTargetGetFeedbackArgs[S], Boolean]]

  /**
   * Optionally provide a _drop effect_ to be applied when this drop target is the innermost drop
   * target being dragged over.
   */
  // var getDropEffect
  //   : js.UndefOr[js.Function1[DropTargetGetFeedbackArgs[D], DropTargetAllowedDropEffect]]

  /**
   * Return `true` if you want your drop target to hold onto selection after the user is no longer
   * dragging over this drop target. Stickiness defaults to `false` _For more details about the
   * stickiness algorithm please refer to the docs_
   */
  var getIsSticky: js.UndefOr[js.Function1[DropTargetGetFeedbackArgs[S], Boolean]]
}

object DropTargetArgs {
  def apply[S, T](
    element:               HTMLElement,
    getData:               js.UndefOr[DropTargetGetFeedbackArgs[S] => T] = js.undefined,
    canDrop:               js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
    getIsSticky:           js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
    onGenerateDragPreview: js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDragStart:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDrag:                js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDropTargetChange:    js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDrop:                js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDragEnter:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDragLeave:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined
  ): DropTargetArgs[S, T] = {
    val p = (new js.Object).asInstanceOf[DropTargetArgs[S, T]]
    p.element = element
    p.applyOrNot(getData, (p, f) => p.getData = args => f(args))
      .applyOrNot(canDrop, (p, f) => p.canDrop = args => f(args))
      .applyOrNot(getIsSticky, (p, f) => p.getIsSticky = args => f(args))
      .applyOrNot(
        onGenerateDragPreview,
        (p, f) => p.onGenerateDragPreview = args => f(args).runNow()
      )
      .applyOrNot(onDragStart, (p, f) => p.onDragStart = args => f(args).runNow())
      .applyOrNot(onDrag, (p, f) => p.onDrag = args => f(args).runNow())
      .applyOrNot(onDropTargetChange, (p, f) => p.onDropTargetChange = args => f(args).runNow())
      .applyOrNot(onDrop, (p, f) => p.onDrop = args => f(args).runNow())
      .applyOrNot(onDragEnter, (p, f) => p.onDragEnter = args => f(args).runNow())
      .applyOrNot(onDragLeave, (p, f) => p.onDragLeave = args => f(args).runNow())
  }
}
