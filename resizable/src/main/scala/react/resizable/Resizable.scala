// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.resizable

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.ReactEvent
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.draggable.Axis
import react.draggable.Draggable.{Props => DraggableProps}

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.|

import js.annotation.JSImport

final case class Resizable(
  axis:                   js.UndefOr[Axis] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  content:                VdomNode,
  clazz:                  js.UndefOr[Css] = js.undefined,
  draggableOpts:          js.UndefOr[DraggableProps] = js.undefined,
  height:                 Double,
  // TODO This needs some work
  handle:                 js.UndefOr[Resizable.RawReactElement] = js.undefined,
  handleFn:               js.UndefOr[ResizeHandleAxis => Resizable.RawReactElement] = js.undefined,
  handleSize:             js.UndefOr[(Int, Int)] = js.undefined,
  lockAspectRatio:        js.UndefOr[Boolean] = js.undefined,
  minConstraints:         js.UndefOr[(Int, Int)] = js.undefined,
  maxConstraints:         js.UndefOr[(Int, Int)] = js.undefined,
  onResizeStop:           js.UndefOr[Resizable.OnResize] = js.undefined,
  onResizeStart:          js.UndefOr[Resizable.OnResize] = js.undefined,
  onResize:               js.UndefOr[Resizable.OnResize] = js.undefined,
  resizeHandles:          js.UndefOr[List[ResizeHandleAxis]] = js.undefined,
  transformScale:         js.UndefOr[Double] = js.undefined,
  width:                  Double,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Resizable.Props, Resizable] {
  override protected def cprops                     = Resizable.props(this)
  override protected val component                  = Resizable.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Resizable {
  @js.native
  @JSImport("react-resizable", "Resizable")
  object RawComponent extends js.Object

  type RawReactElement = React.Element

  type RawOnResize = js.Function2[ReactEvent, ResizeCallbackData, Unit]
  type OnResize    = (ReactEvent, ResizeCallbackData) => Callback
  type HandleFn    = js.Function1[String, RawReactElement]
  type Handle      = RawReactElement | HandleFn

  @js.native
  trait Props extends js.Object {
    /*
     *  Restricts resizing to a particular axis (default: 'both')
     * 'both' - allows resizing by width or height
     * 'x' - only allows the width to be changed
     * 'y' - only allows the height to be changed
     * 'none' - disables resizing altogether
     * */
    var axis: js.UndefOr[String]                     = js.native
    var className: js.UndefOr[String]                = js.native
    /*
     * Require that one and only one child be present.
     * */
    var children: js.UndefOr[React.Node]             = js.native
    /*
     * Require that one and only one child be present.
     * */
    /*
     * These will be passed wholesale to react-draggable's DraggableCore
     * */
    var draggableOpts: js.UndefOr[DraggableProps]    = js.native
    /*
     * Initial height
     * */
    var height: Double                               = js.native
    /*
     * Customize cursor resize handle
     * */
    var handle: js.UndefOr[Handle]                   = js.native
    /*
     * If you change this, be sure to update your css
     * */
    var handleSize: js.UndefOr[js.Array[Double]]     = js.native
    var lockAspectRatio: js.UndefOr[Boolean]         = js.native
    /*
     * Max X & Y measure
     * */
    var maxConstraints: js.UndefOr[js.Array[Double]] = js.native
    /*
     * Min X & Y measure
     * */
    var minConstraints: js.UndefOr[js.Array[Double]] = js.native
    /*
     * Called on stop resize event
     * */
    var onResizeStop: js.UndefOr[RawOnResize]        = js.native
    /*
     * Called on start resize event
     * */
    var onResizeStart: js.UndefOr[RawOnResize]       = js.native
    /*
     * Called on resize event
     * */
    var onResize: js.UndefOr[RawOnResize]            = js.native
    /*
     * Defines which resize handles should be rendered (default: 'se')
     * 's' - South handle (bottom-center)
     * 'w' - West handle (left-center)
     * 'e' - East handle (right-center)
     * 'n' - North handle (top-center)
     * 'sw' - Southwest handle (bottom-left)
     * 'nw' - Northwest handle (top-left)
     * 'se' - Southeast handle (bottom-right)
     * 'ne' - Northeast handle (top-center)
     * */
    var resizeHandles: js.UndefOr[js.Array[String]]  = js.native
    var transformScale: js.UndefOr[Double]           = js.native
    /*
     * Initial width
     */
    var width: Double                                = js.native
  }

  def props(q: Resizable): Props =
    rawprops(
      q.axis,
      q.content,
      q.className,
      q.clazz,
      q.draggableOpts,
      q.height,
      q.handle,
      q.handleFn,
      q.handleSize,
      q.lockAspectRatio,
      q.minConstraints,
      q.maxConstraints,
      q.onResizeStop,
      q.onResizeStart,
      q.onResize,
      q.resizeHandles,
      q.transformScale,
      q.width
    )
  def rawprops(
    axis:            js.UndefOr[Axis] = js.undefined,
    content:         VdomNode,
    className:       js.UndefOr[String] = js.undefined,
    clazz:           js.UndefOr[Css] = js.undefined,
    draggableOpts:   js.UndefOr[DraggableProps] = js.undefined,
    height:          Double,
    handle:          js.UndefOr[RawReactElement] = js.undefined,
    handleFn:        js.UndefOr[ResizeHandleAxis => RawReactElement] = js.undefined,
    handleSize:      js.UndefOr[(Int, Int)] = js.undefined,
    lockAspectRatio: js.UndefOr[Boolean] = js.undefined,
    minConstraints:  js.UndefOr[(Int, Int)] = js.undefined,
    maxConstraints:  js.UndefOr[(Int, Int)] = js.undefined,
    onResizeStop:    js.UndefOr[OnResize] = js.undefined,
    onResizeStart:   js.UndefOr[OnResize] = js.undefined,
    onResize:        js.UndefOr[OnResize] = js.undefined,
    resizeHandles:   js.UndefOr[List[ResizeHandleAxis]] = js.undefined,
    transformScale:  js.UndefOr[Double] = js.undefined,
    width:           Double
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    axis.foreach((v: Axis) => p.axis = v.toJs)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    p.children = content.rawNode
    draggableOpts.foreach(v => p.draggableOpts = v)
    p.height = height
    handle.foreach(v => p.handle = v)
    handleFn.foreach(v => p.handle = ((s: String) => v(ResizeHandleAxis.fromString(s))): HandleFn)
    handleSize.foreach(x => p.handleSize = js.Array(x._1.toDouble, x._2.toDouble))
    lockAspectRatio.foreach(v => p.lockAspectRatio = v)
    minConstraints.foreach(x => p.minConstraints = js.Array(x._1.toDouble, x._2.toDouble))
    maxConstraints.foreach(x => p.maxConstraints = js.Array(x._1.toDouble, x._2.toDouble))
    onResizeStop.foreach(cb =>
      p.onResizeStop = ((e: ReactEvent, d: ResizeCallbackData) => cb(e, d).runNow()): RawOnResize
    )
    onResizeStart.foreach(cb =>
      p.onResizeStart = ((e: ReactEvent, d: ResizeCallbackData) => cb(e, d).runNow()): RawOnResize
    )
    onResize.foreach(cb =>
      p.onResize = ((e: ReactEvent, d: ResizeCallbackData) => cb(e, d).runNow()): RawOnResize
    )
    resizeHandles.foreach(v => p.resizeHandles = v.map(_.toJs).toJSArray)
    transformScale.foreach(v => p.transformScale = v)
    p.width = width
    p
  }

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

}
