// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.*
import lucuma.react.primereact.Toast
import lucuma.react.primereact.ToastRef

object UseToastRef {
  val useToastRef: HookResult[ToastRef] =
    useRefToJsComponentWithMountedFacade[Toast.ToastProps, Null, Toast.Facade].map:
      ToastRef(_)

  private val hook: CustomHook[Unit, ToastRef] =
    CustomHook.fromHookResult(useToastRef)

  object HooksApiExt {
    sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

      final def useToastRef(implicit step: Step): step.Next[ToastRef] =
        api.custom(hook)
    }
  }

  trait HooksApiExt {
    import HooksApiExt._

    implicit def hooksExtToastRefHook[Ctx, Step <: HooksApi.AbstractStep](
      api: HooksApi.Primary[Ctx, Step]
    ): Primary[Ctx, Step] =
      new Primary(api)
  }

  object implicits extends HooksApiExt
}
