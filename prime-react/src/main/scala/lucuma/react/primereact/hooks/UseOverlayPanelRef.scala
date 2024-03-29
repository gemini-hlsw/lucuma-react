// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.*
import lucuma.react.primereact.OverlayPanel
import lucuma.react.primereact.OverlayPanelRef

object UseOverlayPanelRef {

  val hook = CustomHook[Unit]
    .useRefToJsComponentWithMountedFacade[OverlayPanel.OverlayPanelProps, Null, OverlayPanel.Facade]
    .buildReturning((_, ref) => OverlayPanelRef(ref))

  object HooksApiExt {
    sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

      final def useOverlayPanelRef(implicit step: Step): step.Next[OverlayPanelRef] =
        api.custom(hook)
    }
  }

  trait HooksApiExt {
    import HooksApiExt._

    implicit def hooksExtOverlayPanelRefHook[Ctx, Step <: HooksApi.AbstractStep](
      api: HooksApi.Primary[Ctx, Step]
    ): Primary[Ctx, Step] =
      new Primary(api)
  }

  object implicits extends HooksApiExt
}
