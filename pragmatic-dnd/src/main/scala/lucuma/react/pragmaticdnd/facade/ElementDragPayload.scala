// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import org.scalajs.dom.HTMLElement

import scalajs.js

@js.native
trait ElementDragPayload[S] extends js.Object {
  var element: HTMLElement;
  var dragHandle: js.UndefOr[HTMLElement];
  var data: S;
}
