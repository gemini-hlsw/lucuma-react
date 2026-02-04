// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scala.scalajs.js.annotation.JSImport

import scalajs.js

@JSImport("@atlaskit/pragmatic-drag-and-drop/element/adapter", JSImport.Namespace)
@js.native
object ElementAdapterRaw extends js.Object:
  def draggable[S, T](args:             DraggableArgs[S, T]): js.Function0[Unit]  = js.native
  def dropTargetForElements[S, T](args: DropTargetArgs[S, T]): js.Function0[Unit] = js.native
  def monitorForElements[S, T](args:    MonitorArgs[S, T]): js.Function0[Unit]    = js.native
