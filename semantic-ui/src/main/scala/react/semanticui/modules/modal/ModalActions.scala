// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.modal

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.elements.button.Button
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class ModalActions(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  actions:                MyUndefOr[Seq[ShorthandS[Button]]] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  onActionClickE:         MyUndefOr[ModalActions.OnActionClick] = MyUndefOr.undefined,
  onActionClick:          MyUndefOr[Callback] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[ModalActions.ModalActionsProps, ModalActions] {
  override protected def cprops                     = ModalActions.props(this)
  override protected val component                  = ModalActions.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ModalActions {
  type RawOnActionClick = js.Function2[ReactMouseEvent, Button.ButtonProps, Unit]
  type OnActionClick    = (ReactMouseEvent, Button.ButtonProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "ModalActions")
  object RawComponent extends js.Object

  @js.native
  trait ModalActionsProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Array of shorthand buttons. */
    var actions: MyUndefOr[suiraw.SemanticShorthandArray[Button.ButtonProps]] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /**
     * onClick handler for an action. Mutually exclusive with children.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All item props.
     */
    var onActionClick: MyUndefOr[RawOnActionClick] = js.native
  }

  def props(q: ModalActions): ModalActionsProps =
    rawprops(q.as, q.actions, q.className, q.clazz, q.content, q.onActionClickE, q.onActionClick)

  def rawprops(
    as:             MyUndefOr[AsC] = MyUndefOr.undefined,
    actions:        MyUndefOr[Seq[ShorthandS[Button]]] = MyUndefOr.undefined,
    className:      MyUndefOr[String] = MyUndefOr.undefined,
    clazz:          MyUndefOr[Css] = MyUndefOr.undefined,
    content:        MyUndefOr[VdomNode] = MyUndefOr.undefined,
    onActionClickE: MyUndefOr[OnActionClick] = MyUndefOr.undefined,
    onActionClick:  MyUndefOr[Callback] = MyUndefOr.undefined
  ): ModalActionsProps = {
    val p = as.toJsObject[ModalActionsProps]
    as.toJs.foreach(v => p.as = v)
    actions.toJs.foreach(v => p.actions = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    (onActionClickE, onActionClick).toJs.foreach(v => p.onActionClick = v)
    p
  }

  private val component =
    JsComponent[ModalActionsProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): ModalActions =
    new ModalActions(modifiers = modifiers)
}
