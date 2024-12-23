// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.resizeDetector

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TopNode
import lucuma.react.resizeDetector.ResizeDetector.*
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.ResizeObserver
import org.scalajs.dom.ResizeObserverEntry

import scala.scalajs.js
import scala.scalajs.js.annotation.*

object ResizeDetector {
  object raw {
    type UseResizeObserverCallback = js.Function2[ResizeObserverEntry, ResizeObserver, Unit]

    trait UseResizeObserverOptions extends js.Object

    @js.native
    @JSImport("@react-hook/resize-observer", JSImport.Default)
    val useResizeObserver: js.Function2[
      facade.React.RefHandle[TopNode | Null],
      UseResizeObserverCallback,
      ResizeObserver
    ] = js.native
  }

  val jsHook = HookResult.fromFunction(raw.useResizeObserver)

  def useResizeDetector(props: UseResizeDetectorProps): HookResult[UseResizeDetectorReturn] =
    for
      ref  <- useRefToVdom[HTMLElement]
      size <- useState(UseResizeDetectorReturn(none, none, ref))
      _    <- jsHook(
                ref.raw,
                (entry, _) =>
                  val height = entry.contentRect.height.toInt
                  val width  = entry.contentRect.width.toInt

                  (
                    size.setState(UseResizeDetectorReturn(height.some, width.some, ref)) >>
                      props.onResize.map(_(height, width)).toOption.getOrEmpty
                  ).runNow()
              )
    yield size.value

  val hook = CustomHook.fromHookResult(useResizeDetector)
}

object HooksApiExt {
  sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

    final def useResizeDetector(props: UseResizeDetectorProps = UseResizeDetectorProps())(implicit
      step: Step
    ): step.Next[UseResizeDetectorReturn] =
      useResizeDetectorBy(_ => props)

    final def useResizeDetectorBy(props: Ctx => UseResizeDetectorProps)(implicit
      step: Step
    ): step.Next[UseResizeDetectorReturn] =
      api.customBy(ctx => hook(props(ctx)))
  }

  final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ) extends Primary[Ctx, Step](api) {

    def useResizeDetectorBy(pos: CtxFn[UseResizeDetectorProps])(implicit
      step: Step
    ): step.Next[UseResizeDetectorReturn] =
      useResizeDetectorBy(step.squash(pos)(_))
  }
}

trait HooksApiExt {
  import HooksApiExt.*

  implicit def hooksExtFloating1[Ctx, Step <: HooksApi.AbstractStep](
    api: HooksApi.Primary[Ctx, Step]
  ): Primary[Ctx, Step] =
    new Primary(api)

  implicit def hooksExtFloating2[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ): Secondary[Ctx, CtxFn, Step] =
    new Secondary(api)
}

object hooks extends HooksApiExt:
  export ResizeDetector.useResizeDetector
