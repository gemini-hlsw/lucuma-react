// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

object all
    extends UsePopupMenuRef.HooksApiExt
    with UseToastRef.HooksApiExt
    with UseOverlayPanelRef.HooksApiExt
    with UseMessagesRef.HooksApiExt:
  export UsePopupMenuRef.usePopupMenuRef, UseToastRef.useToastRef,
    UseOverlayPanelRef.useOverlayPanelRef, UseMessagesRef.useMessagesRef
