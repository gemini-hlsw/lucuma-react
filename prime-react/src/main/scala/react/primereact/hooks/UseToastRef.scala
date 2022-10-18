// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact.hooks

import japgolly.scalajs.react.*
import react.primereact.Toast
import react.primereact.ToastRef

object UseToastRef {

  val hook = CustomHook[Unit]
    .useRefToJsComponentWithMountedFacade[Toast.ToastProps, Null, Toast.Facade]
    .buildReturning((_, ref) => ToastRef(ref))

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
