// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.resizeDetector

import japgolly.scalajs.react._
import japgolly.scalajs.react.{facade => Raw}
import org.scalajs.dom.html
import react.common._
import react.resizeDetector.ResizeDetector._

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.|

@js.native
protected trait ReactResizeDetectorDimensions extends js.Object {
  val height: js.UndefOr[Double]
  val width: js.UndefOr[Double]
}

@js.native
trait UseResizeDetectorReturnJS extends ReactResizeDetectorDimensions {
  val ref: Raw.React.RefFn[html.Element]
}

sealed trait UseResizeDetectorReturn {
  val height: Int
  val width: Int
  val ref: Ref.Simple[html.Element]
}

object UseResizeDetectorReturn {
  def fromJS(r: UseResizeDetectorReturnJS): UseResizeDetectorReturn = new UseResizeDetectorReturn {
    val height = r.height.toOption match {
      case Some(s) =>
        (s: Any) match {
          case s: Byte   => s.toInt
          case s: Short  => s.toInt
          case s: Int    => s
          case s: Float  => s.toInt
          case s: Double => s.toInt
          case _         => 1
        }
      case _       => 1
    }
    val width  = r.width.toOption match {
      case Some(s) =>
        (s: Any) match {
          case s: Byte   => s.toInt
          case s: Short  => s.toInt
          case s: Int    => s
          case s: Float  => s.toInt
          case s: Double => s.toInt
          case _         => 1
        }
      case _       => 1
    }
    val ref    =
      Ref.fromJs(r.ref.asInstanceOf[facade.React.RefHandle[html.Element | Null]])
  }

  implicit val reusabilityUseResizeDetectorReturn: Reusability[UseResizeDetectorReturn] =
    Reusability.by(x => (x.height, x.width))
}

@js.native
protected trait UseResizeDetectorProps extends Props {
  var targetRef: Raw.React.RefFn[html.Element]
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
      p.onResize = { case (x: Double, y: Double) =>
        v(x.toInt, y.toInt).runNow()
      }: js.Function2[
        Double,
        Double,
        Unit
      ]
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
    @JSImport("react-resize-detector", JSImport.Namespace)
    @js.native
    private object base extends js.Object

    @scala.inline
    def useResizeDetector(): UseResizeDetectorReturn                              = UseResizeDetectorReturn.fromJS(
      base
        .asInstanceOf[js.Dynamic]
        .applyDynamic("useResizeDetector")()
        .asInstanceOf[UseResizeDetectorReturnJS]
    )
    @scala.inline
    def useResizeDetector(props: UseResizeDetectorProps): UseResizeDetectorReturn =
      UseResizeDetectorReturn.fromJS(
        base
          .asInstanceOf[js.Dynamic]
          .applyDynamic("useResizeDetector")(props.asInstanceOf[js.Any])
          .asInstanceOf[UseResizeDetectorReturnJS]
      )
  }

  val hook =
    CustomHook[UseResizeDetectorProps]
      .buildReturning { pos =>
        mod.useResizeDetector(pos)
      }

  sealed class Primary[Ctx, Step <: HooksApi.AbstractStep](api: HooksApi.Primary[Ctx, Step]) {

    final def useResizeDetector(pos: UseResizeDetectorProps)(implicit
      step:                          Step
    ): step.Next[UseResizeDetectorReturn] =
      useResizeDetectorBy(_ => pos)

    final def useResizeDetectorBy(pos: Ctx => UseResizeDetectorProps)(implicit
      step:                            Step
    ): step.Next[UseResizeDetectorReturn] =
      api.customBy(ctx => hook(pos(ctx)))
  }

  final class Secondary[Ctx, CtxFn[_], Step <: HooksApi.SubsequentStep[Ctx, CtxFn]](
    api: HooksApi.Secondary[Ctx, CtxFn, Step]
  ) extends Primary[Ctx, Step](api) {

    def useResizeDetectorBy(pos: CtxFn[UseResizeDetectorProps])(implicit
      step:                      Step
    ): step.Next[UseResizeDetectorReturn] =
      useResizeDetectorBy(step.squash(pos)(_))
  }
}

trait HooksApiExt {
  import HooksApiExt._

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
