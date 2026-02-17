// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import org.scalajs.dom.HTMLElement

import scalajs.js

/**
 * Information about a drop target
 */
@js.native
trait DropTargetRecord[T] extends js.Object {

  /**
   * The element the drop target is attached to
   */
  var element: HTMLElement

  // using 'symbol' allows us to create uniquely typed keys / values
  /**
   * Data associated with the drop target
   *
   * (Collected by `getData()`)
   */
  var data: Data[T]

  /**
   * The drop effect for the drop target
   *
   * (Collected by `getDropEffect()`)
   */
  // var dropEffect: DropTargetAllowedDropEffect

  /**
   * Whether or not the drop target is active due to _stickiness_
   */
  var isActiveDueToStickiness: Boolean
}

object DropTargetRecord:
  def apply[T](
    element:                 HTMLElement,
    data:                    Data[T],
    // dropEffect:              DropTargetAllowedDropEffect,
    isActiveDueToStickiness: Boolean
  ): DropTargetRecord[T] =
    val p = (new js.Object).asInstanceOf[DropTargetRecord[T]]
    p.element = element
    p.data = data
    // p.dropEffect = dropEffect
    p.isActiveDueToStickiness = isActiveDueToStickiness
    p
