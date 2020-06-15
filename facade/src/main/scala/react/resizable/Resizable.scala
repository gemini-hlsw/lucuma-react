package react.resizable

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.JSConverters._
import js.annotation.JSImport
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.ReactEvent
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomElement
import react.common._
import react.draggable.Draggable.{ Props => DraggableProps }
import react.draggable.Axis

final case class Resizable(
  axis:                   js.UndefOr[Axis] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  draggableOpts:          js.UndefOr[DraggableProps] = js.undefined,
  height:                 JsNumber,
  handle:                 js.UndefOr[VdomElement | ResizeHandleAxis => VdomElement] = js.undefined,
  handleSize:             js.UndefOr[JsNumberTuple] = js.undefined,
  lockAspectRatio:        js.UndefOr[Boolean] = js.undefined,
  minConstraints:         js.UndefOr[JsNumberTuple] = js.undefined,
  maxConstraints:         js.UndefOr[JsNumberTuple] = js.undefined,
  onResizeStop:           js.UndefOr[Resizable.OnResize] = js.undefined,
  onResizeStart:          js.UndefOr[Resizable.OnResize] = js.undefined,
  onResize:               js.UndefOr[Resizable.OnResize] = js.undefined,
  resizeHandles:          js.UndefOr[List[ResizeHandleAxis]] = js.undefined,
  transformScale:         js.UndefOr[JsNumber] = js.undefined,
  width:                  JsNumber,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Resizable.Props, Resizable] {
  override protected def cprops    = Resizable.props(this)
  override protected val component = Resizable.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Resizable {
  @js.native
  @JSImport("react-resizable", "ResizableBox")
  object RawComponent extends js.Object

  type RawOnResize = (ReactEvent, ResizeCallbackData) => Unit
  type OnResize    = (ReactEvent, ResizeCallbackData) => Callback
  type Handle      = React.Element |(String => React.Element)

  @js.native
  trait Props extends js.Object {
    /*
     *  Restricts resizing to a particular axis (default: 'both')
     * 'both' - allows resizing by width or height
     * 'x' - only allows the width to be changed
     * 'y' - only allows the height to be changed
     * 'none' - disables resizing altogether
     * */
    var axis: js.UndefOr[String]                       = js.native
    var className: js.UndefOr[String]                  = js.native
    /*
     * Require that one and only one child be present.
     * */
    /*
     * These will be passed wholesale to react-draggable's DraggableCore
     * */
    var draggableOpts: js.UndefOr[DraggableProps]      = js.native
    /*
     * Initial height
     * */
    var height: JsNumber                               = js.native
    /*
     * Customize cursor resize handle
     * */
    var handle: js.UndefOr[Handle]                     = js.native
    /*
     * If you change this, be sure to update your css
     * */
    var handleSize: js.UndefOr[js.Array[JsNumber]]     = js.native
    var lockAspectRatio: js.UndefOr[Boolean]           = js.native
    /*
     * Max X & Y measure
     * */
    var maxConstraints: js.UndefOr[js.Array[JsNumber]] = js.native
    /*
     * Min X & Y measure
     * */
    var minConstraints: js.UndefOr[js.Array[JsNumber]] = js.native
    /*
     * Called on stop resize event
     * */
    var onResizeStop: js.UndefOr[RawOnResize]          = js.native
    /*
     * Called on start resize event
     * */
    var onResizeStart: js.UndefOr[RawOnResize]         = js.native
    /*
     * Called on resize event
     * */
    var onResize: js.UndefOr[RawOnResize]              = js.native
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
    var resizeHandles: js.UndefOr[js.Array[String]]    = js.native
    var transformScale: js.UndefOr[JsNumber]           = js.native
    /*
     * Initial width
     */
    var width: JsNumber                                = js.native
  }

  def props(q: Resizable): Props =
    rawprops(
      q.axis,
      q.className,
      q.clazz,
      q.draggableOpts,
      q.height,
      q.handle,
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
    className:       js.UndefOr[String] = js.undefined,
    clazz:           js.UndefOr[Css] = js.undefined,
    draggableOpts:   js.UndefOr[DraggableProps] = js.undefined,
    height:          JsNumber,
    handle:          js.UndefOr[VdomElement | ResizeHandleAxis => VdomElement],
    handleSize:      js.UndefOr[JsNumberTuple] = js.undefined,
    lockAspectRatio: js.UndefOr[Boolean] = js.undefined,
    minConstraints:  js.UndefOr[JsNumberTuple] = js.undefined,
    maxConstraints:  js.UndefOr[JsNumberTuple] = js.undefined,
    onResizeStop:    js.UndefOr[OnResize] = js.undefined,
    onResizeStart:   js.UndefOr[OnResize] = js.undefined,
    onResize:        js.UndefOr[OnResize] = js.undefined,
    resizeHandles:   js.UndefOr[List[ResizeHandleAxis]] = js.undefined,
    transformScale:  js.UndefOr[JsNumber] = js.undefined,
    width:           JsNumber
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    axis.foreach(v => p.axis = v.toJs)
    (className, clazz).toJs.foreach(v => p.className = v)
    draggableOpts.foreach(v => p.draggableOpts = v)
    p.height = height
    handle.foreach(v =>
      (v: Any) match {
        case v: VdomElement =>
          p.handle = v.rawElement
        case v              =>
          val f = v.asInstanceOf[ResizeHandleAxis => VdomElement]
          p.handle = (s: String) => f(ResizeHandleAxis.fromString(s)).rawElement
      }
    )
    handleSize.foreach(x => p.handleSize = js.Array(x._1, x._2))
    lockAspectRatio.foreach(v => p.lockAspectRatio = v)
    minConstraints.foreach(x => p.minConstraints = js.Array(x._1, x._2))
    maxConstraints.foreach(x => p.maxConstraints = js.Array(x._1, x._2))
    onResizeStop.foreach(cb =>
      p.onResizeStop = (e: ReactEvent, d: ResizeCallbackData) => cb(e, d).runNow()
    )
    onResizeStart.foreach(cb =>
      p.onResizeStart = (e: ReactEvent, d: ResizeCallbackData) => cb(e, d).runNow()
    )
    onResize.foreach(cb => p.onResize = (e: ReactEvent, d: ResizeCallbackData) => cb(e, d).runNow())
    resizeHandles.foreach(v => p.resizeHandles = v.map(_.toJs).toJSArray)
    transformScale.foreach(v => p.transformScale = v)
    p.width = width
    p
  }

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  // def apply(modifiers: TagMod*): Resizable = Resizable(modifiers = modifiers)
}
