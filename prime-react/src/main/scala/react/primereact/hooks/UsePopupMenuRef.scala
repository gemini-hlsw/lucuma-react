// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact.hooks

import japgolly.scalajs.react.*
import react.primereact.PopupMenu
import react.primereact.PopupMenuRef

object UsePopupMenuRef {

  val hook = CustomHook[Unit]
    .useRefToJsComponentWithMountedFacade[PopupMenu.PopupMenuProps, Null, PopupMenu.Facade]
    .buildReturning((_, ref) => PopupMenuRef(ref))

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
