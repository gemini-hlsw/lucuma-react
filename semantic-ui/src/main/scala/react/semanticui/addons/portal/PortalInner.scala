// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.portal

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
// import japgolly.scalajs.react.Ref
import japgolly.scalajs.react.facade.React
import react.common._
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui.MyUndefOr

final case class PortalInner(
  // innerRef:             MyUndefOr[Ref[html.Element]]                     = MyUndefOr.undefined,
  onMountE:               MyUndefOr[PortalInner.OnMount] = MyUndefOr.undefined,
  onMount:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onUnmountE:             MyUndefOr[PortalInner.OnUnmount] = MyUndefOr.undefined,
  onUnmount:              MyUndefOr[Callback] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[PortalInner.PortalInnerProps, PortalInner] {
  override protected def cprops                     = PortalInner.props(this)
  override protected val component                  = PortalInner.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object PortalInner {
  type RawOnMount   = js.Function2[Any, PortalInner.PortalInnerProps, Unit]
  type OnMount      = (Any, PortalInner.PortalInnerProps) => Callback
  type RawOnUnmount = js.Function2[Any, PortalInner.PortalInnerProps, Unit]
  type OnUnmount    = (Any, PortalInner.PortalInnerProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "PortalInner")
  object RawComponent extends js.Object

  @js.native
  trait PortalInnerProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Called with a ref to the inner node. */
    // var innerRef: MyUndefOr[Ref] = js.native

    /** The node where the portal should mount. */
    // var mountNode?: any

    /**
     * Called when the PortalInner is mounted on the DOM.
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onMount: MyUndefOr[RawOnMount] = js.native

    /**
     * Called when the PortalInner is unmounted from the DOM.
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onUnmount: MyUndefOr[RawOnUnmount]
  }

  def props(q: PortalInner): PortalInnerProps =
    rawprops(
      // q.innerRef,
      q.onMountE,
      q.onMount,
      q.onUnmountE,
      q.onUnmount
    )

  def rawprops(
    // innerRef:             MyUndefOr[Ref]                     = MyUndefOr.undefined,
    onMountE:   MyUndefOr[OnMount] = MyUndefOr.undefined,
    onMount:    MyUndefOr[Callback] = MyUndefOr.undefined,
    onUnmountE: MyUndefOr[OnUnmount] = MyUndefOr.undefined,
    onUnmount:  MyUndefOr[Callback] = MyUndefOr.undefined
  ): PortalInnerProps = {
    val p = (new js.Object).asInstanceOf[PortalInnerProps]
    // p.innerRef = innerRef
    (onMountE, onMount).toJs.foreach(v => p.onMount = v)
    (onUnmountE, onUnmount).toJs.foreach(v => p.onUnmount = v)
    p
  }

  private val component =
    JsComponent[PortalInnerProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): PortalInner =
    new PortalInner(modifiers = modifiers)
}
