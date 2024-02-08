// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.resizeDetector

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.{*, given}
import org.scalajs.dom.html

import scalajs.js

object ResizeDetector {

  @js.native
  trait DimensionsJS extends js.Object {
    val height: js.UndefOr[Double]
    val width: js.UndefOr[Double]
  }

  @js.native
  trait RenderPropsJS extends DimensionsJS {
    val targetRef: facade.React.RefFn[html.Element]
  }

  trait Dimensions {
    val height: Option[Int]
    val width: Option[Int]
  }

  object Dimensions {
    implicit val dimensionsReuse: Reusability[Dimensions] = Reusability.by(d => (d.height, d.width))
  }

  protected type RenderJS = js.Function1[RenderPropsJS, facade.React.Node | Null]

  object RefreshMode:
    implicit val enumValue: EnumValue[RefreshMode] = EnumValue.toLowerCaseString

  enum RefreshMode:
    case Throttle
    case Debounce

  object ObserveBox:
    implicit val enumValue: EnumValue[ObserveBox] = EnumValue.instance(_ match {
      case Content            => "content-box"
      case Border             => "border-box"
      case DevicePixelContent => "device-pixel-content-box"
    })

  enum ObserveBox:
    case Content
    case Border
    case DevicePixelContent

  @js.native
  trait RefreshOptions extends js.Object {
    var leading: js.UndefOr[Boolean]
    var trailing: js.UndefOr[Boolean]
  }
  object RefreshOptions {
    def apply(
      leading:  js.UndefOr[Boolean] = js.undefined,
      trailing: js.UndefOr[Boolean] = js.undefined
    ): RefreshOptions = {
      val p = (new js.Object).asInstanceOf[RefreshOptions]
      leading.foreach(v => p.leading = v)
      trailing.foreach(v => p.trailing = v)
      p
    }
  }

  @js.native
  trait ObserverOptions extends js.Object {
    var box: js.UndefOr[String]
  }
  object ObserverOptions {
    def apply(
      box: js.UndefOr[ObserveBox] = js.undefined
    ): ObserverOptions = {
      val p = (new js.Object).asInstanceOf[ObserverOptions]
      box.toJs.foreach(v => p.box = v)
      p
    }
  }

  @js.native
  trait Props extends js.Object {
    var children: RenderJS
    var onResize: js.UndefOr[js.Function2[js.UndefOr[Double], js.UndefOr[Double], Unit]]
    var handleHeight: js.UndefOr[Boolean]
    var handleWidth: js.UndefOr[Boolean]
    var skipOnMount: js.UndefOr[Boolean]
    var refreshMode: js.UndefOr[String]
    var refreshRate: js.UndefOr[Int]
    var refreshOptions: js.UndefOr[RefreshOptions]
    var observerOptions: js.UndefOr[ObserverOptions]
  }

}
