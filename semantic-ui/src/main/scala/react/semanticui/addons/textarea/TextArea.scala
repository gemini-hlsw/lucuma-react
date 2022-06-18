// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.textarea

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.TagMod
import react.common._
import react.semanticui._
import org.scalajs.dom

final case class TextArea(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
  onChange:               MyUndefOr[Callback] = MyUndefOr.undefined,
  onInputE:               MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
  onInput:                MyUndefOr[Callback] = MyUndefOr.undefined,
  rows:                   MyUndefOr[Int | String] = MyUndefOr.undefined,
  value:                  MyUndefOr[String | Double] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[TextArea.TextAreaProps, TextArea] {
  override protected def cprops                     = TextArea.props(this)
  override protected val component                  = TextArea.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TextArea {
  final type ReactChangeEvent = ReactEventFrom[dom.Node]
  type Event                  = (ReactChangeEvent, TextAreaProps) => Callback
  type RawEvent               = js.Function2[ReactChangeEvent, TextAreaProps, Unit]

  @js.native
  @JSImport("semantic-ui-react", "TextArea")
  object RawComponent extends js.Object

  @js.native
  trait TextAreaProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /**
     * Called on change.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onChange: MyUndefOr[RawEvent] = MyUndefOr.undefined

    /**
     * Called on input.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onInput: MyUndefOr[RawEvent] = MyUndefOr.undefined

    /** Indicates row count for a TextArea. */
    var rows: MyUndefOr[Double | String] = MyUndefOr.undefined

    /** The value of the textarea. */
    var value: MyUndefOr[Double | String] = MyUndefOr.undefined

  }

  def props(q: TextArea): TextAreaProps =
    rawprops(
      q.as,
      q.onChangeE,
      q.onChange,
      q.onInputE,
      q.onInput,
      q.rows,
      q.value
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    onChangeE: MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
    onChange:  MyUndefOr[Callback] = MyUndefOr.undefined,
    onInputE:  MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
    onInput:   MyUndefOr[Callback] = MyUndefOr.undefined,
    rows:      MyUndefOr[Int | String] = MyUndefOr.undefined,
    value:     MyUndefOr[String | Double] = MyUndefOr.undefined
  ): TextAreaProps = {
    val p = as.toJsObject[TextAreaProps]
    as.toJs.foreach(v => p.as = v)
    (onChangeE, onChange).toJs.foreach(v => p.onChange = v)
    (onInputE, onInput).toJs.foreach(v => p.onInput = v)
    rows.foreach(v => p.rows = v)
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[TextAreaProps, Children.None, Null](RawComponent)

  val Default: TextArea = TextArea()

  val defaultProps: TextAreaProps = props(Default)

  def apply(modifiers: TagMod*): TextArea =
    new TextArea(modifiers = modifiers)
}
