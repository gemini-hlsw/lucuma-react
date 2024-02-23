// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.resizeDetector

import japgolly.scalajs.react.*
import lucuma.react.common.given
import lucuma.react.resizeDetector.ResizeDetector.*
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.*

@js.native
trait UseResizeDetectorReturnJS extends DimensionsJS {
  val ref: facade.React.RefHandle[html.Element]
}

sealed trait UseResizeDetectorReturn extends Dimensions {
  val ref: Ref.Simple[html.Element]

  def isReady: Boolean = width.isDefined && height.isDefined

  override def toString(): String = s"Resize($width, $height)"
}

object UseResizeDetectorReturn {
  def fromJS(r: UseResizeDetectorReturnJS): UseResizeDetectorReturn = new UseResizeDetectorReturn {
    val height = r.height.toOption.map(_.toInt)
    val width  = r.width.toOption.map(_.toInt)
    val ref    =
      Ref.fromJs(r.ref)
  }

  implicit val reusabilityUseResizeDetectorReturn: Reusability[UseResizeDetectorReturn] =
    Reusability.by(x => (x.height, x.width, x.ref))
}

@js.native
protected trait UseResizeDetectorProps extends Props {
  var targetRef: facade.React.RefFn[html.Element]
}

object UseResizeDetectorProps {

  def apply(
    onResize:        js.UndefOr[(Int, Int) => Callback] = js.undefined,
    handleHeight:    js.UndefOr[Boolean] = js.undefined,
    handleWidth:     js.UndefOr[Boolean] = js.undefined,
    skipOnMount:     js.UndefOr[Boolean] = js.undefined,
    refreshMode:     js.UndefOr[RefreshMode] = js.undefined,
    refreshRate:     js.UndefOr[Int] = js.undefined,
    refreshOptions:  js.UndefOr[RefreshOptions] = js.undefined,
    observerOptions: js.UndefOr[ObserverOptions] = js.undefined
  ): UseResizeDetectorProps = {
    val p = (new js.Object).asInstanceOf[UseResizeDetectorProps]
    onResize.foreach(v =>
      p.onResize = { (x, y) =>
        if (x.isDefined && y.isDefined) v(x.get.toInt, y.get.toInt).runNow()
      }: js.Function2[js.UndefOr[Double], js.UndefOr[Double], Unit]
    )
    handleHeight.foreach(v => p.handleHeight = v)
    handleWidth.foreach(v => p.handleWidth = v)
    skipOnMount.foreach(v => p.skipOnMount = v)
    refreshMode.toJs.foreach(v => p.refreshMode = v)
    refreshRate.foreach(v => p.refreshRate = v)
    refreshOptions.foreach(v => p.refreshOptions = v)
    observerOptions.foreach(v => p.observerOptions = v)
    p
  }
}

object HooksApiExt {

  object mod {
    @js.native
    @JSImport("react-resize-detector", "useResizeDetector")
    def baseUseResizeDetector: js.Function1[
      js.UndefOr[UseResizeDetectorProps],
      UseResizeDetectorReturnJS
    ] = js.native

    inline def useResizeDetector(
      props: js.UndefOr[UseResizeDetectorProps] = js.undefined
    ): UseResizeDetectorReturn =
      UseResizeDetectorReturn.fromJS(baseUseResizeDetector(props))
  }

  val hook =
    CustomHook[UseResizeDetectorProps]
      .buildReturning { pos =>
        mod.useResizeDetector(pos)
      }

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

object hooks extends HooksApiExt
