// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.*
import lucuma.react.primereact.PopupMenu
import lucuma.react.primereact.PopupMenuRef

object UsePopupMenuRef {
  val usePopupMenuRef: HookResult[PopupMenuRef] =
    useRefToJsComponentWithMountedFacade[PopupMenu.PopupMenuProps, Null, PopupMenu.Facade].map:
      PopupMenuRef(_)

  private val hook: CustomHook[Unit, PopupMenuRef] =
    CustomHook.fromHookResult(usePopupMenuRef)

  object HooksApiExt {
    sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

      final def usePopupMenuRef(implicit step: Step): step.Next[PopupMenuRef] =
        api.custom(hook)
    }
  }

  trait HooksApiExt {
    import HooksApiExt._

    implicit def hooksExtPopupMenuRefHook[Ctx, Step <: HooksApi.AbstractStep](
      api: HooksApi.Primary[Ctx, Step]
    ): Primary[Ctx, Step] =
      new Primary(api)
  }

  object implicits extends HooksApiExt
}
