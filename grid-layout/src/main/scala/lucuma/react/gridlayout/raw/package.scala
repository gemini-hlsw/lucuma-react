// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.gridlayout

import org.scalajs.dom.Event
import org.scalajs.dom.html.Element as HTMLElement

import scala.scalajs.js

import js.annotation.JSImport

package object raw {

  type RawLayoutChange =
    js.Function1[Layout, Unit]

  // react-grid-layout v2 EventCallback:
  // (layout, oldItem|null, newItem|null, placeholder|null, event: Event, element?: HTMLElement)
  type RawItemCallback =
    js.Function6[Layout, LayoutItem, LayoutItem, LayoutItem, Event, js.UndefOr[HTMLElement], Unit]

  // v2 onDrop: (layout, item|undefined, e: Event)
  type DropCallback =
    js.Function3[Layout, js.UndefOr[LayoutItem], Event, Unit]

  type BreakpointChangeCallback =
    js.Function2[Breakpoint, Int, Unit]

  type LayoutChangeCallback =
    js.Function2[Layout, js.Object, Unit]

  // v2 onWidthChange: (containerWidth, margin: [n, n], cols, containerPadding: [n, n] | null)
  type WidthChangeCallback =
    js.Function4[Int, js.Array[Int], Int, js.Array[Int], Unit]

  type Layout = js.Array[LayoutItem]

  type Breakpoint = String
}

package raw {
  // Compaction strategy
  @js.native
  trait Compactor extends js.Object

  // CSS positioning strategy (opaque to Scala).
  @js.native
  trait PositionStrategy extends js.Object

  // Helpers and predefined strategies/compactors live in the framework-agnostic core entry point.
  @js.native
  @JSImport("react-grid-layout/core", JSImport.Namespace)
  object Core extends js.Object {
    def getCompactor(
      compactType:      js.UndefOr[String],
      allowOverlap:     js.UndefOr[Boolean],
      preventCollision: js.UndefOr[Boolean]
    ): Compactor = js.native

    def createScaledStrategy(scale: Double): PositionStrategy = js.native

    val transformStrategy: PositionStrategy = js.native
    val absoluteStrategy: PositionStrategy  = js.native

    // Breakpoint detection moved here in v2 (was Responsive.utils in v1).
    def getBreakpointFromWidth(breakpoints: js.Dictionary[Int], width: Double): String = js.native
  }

  // { cols, rowHeight, margin, containerPadding, maxRows } — all optional (Partial<GridConfig>).
  class GridConfig(
    var cols:             js.UndefOr[Int] = js.undefined,
    var rowHeight:        js.UndefOr[Int] = js.undefined,
    var margin:           js.UndefOr[js.Array[Double]] = js.undefined,
    var containerPadding: js.UndefOr[js.Array[Double]] = js.undefined,
    var maxRows:          js.UndefOr[Int] = js.undefined
  ) extends js.Object

  // { enabled, bounded, handle, cancel, threshold } — Partial<DragConfig>.
  class DragConfig(
    var enabled:   js.UndefOr[Boolean] = js.undefined,
    var bounded:   js.UndefOr[Boolean] = js.undefined,
    var handle:    js.UndefOr[String] = js.undefined,
    var cancel:    js.UndefOr[String] = js.undefined,
    var threshold: js.UndefOr[Int] = js.undefined
  ) extends js.Object

  // { enabled, handles } — Partial<ResizeConfig>.
  class ResizeConfig(
    var enabled: js.UndefOr[Boolean] = js.undefined,
    var handles: js.UndefOr[js.Array[String]] = js.undefined
  ) extends js.Object

  // defaultItem only needs { w, h }.
  class DropDefaultItem(
    var w: Int,
    var h: Int
  ) extends js.Object

  // { enabled, defaultItem } — Partial<DropConfig>.
  class DropConfig(
    var enabled:     js.UndefOr[Boolean] = js.undefined,
    var defaultItem: js.UndefOr[DropDefaultItem] = js.undefined
  ) extends js.Object

  class LayoutItem(
    val w:             Int,
    val h:             Int,
    val x:             Int,
    val y:             Int,
    val i:             String,
    val minW:          js.UndefOr[Int] = js.undefined,
    val minH:          js.UndefOr[Int] = js.undefined,
    val maxW:          js.UndefOr[Int] = js.undefined,
    val maxH:          js.UndefOr[Int] = js.undefined,
    val static:        js.UndefOr[Boolean] = js.undefined,
    val isDraggable:   js.UndefOr[Boolean] = js.undefined,
    val isResizable:   js.UndefOr[Boolean] = js.undefined,
    val resizeHandles: js.UndefOr[js.Array[String]] = js.undefined,
    val isBounded:     js.UndefOr[Boolean] = js.undefined
  ) extends js.Object

}
