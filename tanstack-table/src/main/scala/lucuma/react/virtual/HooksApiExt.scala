// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import japgolly.scalajs.react.*
import lucuma.react.virtual.facade.Virtualizer
import org.scalajs.dom.Element

object HooksApiExt:
  sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]):
    final def useVirtualizer[TScrollElement <: Element, TItemElement <: Element](
      options: VirtualOptions[TScrollElement, TItemElement]
    )(using
      step:    Step
    ): step.Next[Virtualizer[TScrollElement, TItemElement]] =
      useVirtualizerBy(_ => options)

    final def useVirtualizerBy[TScrollElement <: Element, TItemElement <: Element](
      options: Ctx => VirtualOptions[TScrollElement, TItemElement]
    )(using
      step:    Step
    ): step.Next[Virtualizer[TScrollElement, TItemElement]] =
      api.customBy(ctx => VirtualHook.useVirtualizerHook(options(ctx)))

  final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ) extends Primary[Ctx, Step](api):
    def useVirtualizerBy[TScrollElement <: Element, TItemElement <: Element](
      tableDefWithOptions: CtxFn[VirtualOptions[TScrollElement, TItemElement]]
    )(implicit
      step:                Step
    ): step.Next[Virtualizer[TScrollElement, TItemElement]] =
      super.useVirtualizerBy(step.squash(tableDefWithOptions)(_))

trait HooksApiExt:
  import HooksApiExt._

  implicit def hooksExtUseReactTable1[Ctx, Step <: HooksApi.AbstractStep](
    api: HooksApi.Primary[Ctx, Step]
  ): Primary[Ctx, Step] =
    new Primary(api)

  implicit def hooksExtUseReactTable2[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ): Secondary[Ctx, CtxFn, Step] =
    new Secondary(api)
