// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.*
import lucuma.react.primereact.Messages
import lucuma.react.primereact.MessagesRef

object UseMessagesRef {
  val useMessagesRef: HookResult[MessagesRef] =
    useRefToJsComponentWithMountedFacade[Messages.MessagesProps, Null, Messages.Facade].map:
      MessagesRef(_)

  private val hook: CustomHook[Unit, MessagesRef] =
    CustomHook.fromHookResult(useMessagesRef)

  object HooksApiExt {
    sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

      final def useMessagesRef(implicit step: Step): step.Next[MessagesRef] =
        api.custom(hook)
    }
  }

  trait HooksApiExt {
    import HooksApiExt._

    implicit def hooksExtMessagesRefHook[Ctx, Step <: HooksApi.AbstractStep](
      api: HooksApi.Primary[Ctx, Step]
    ): Primary[Ctx, Step] =
      new Primary(api)
  }

  object implicits extends HooksApiExt
}
