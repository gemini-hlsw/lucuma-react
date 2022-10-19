// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.textarea

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.TagMod
import org.scalajs.dom
import react.common._
import react.semanticui._

import scala.scalajs.js

import js.annotation._
import js.|

final case class TextArea(
  as:                     js.UndefOr[AsC] = js.undefined,
  onChangeE:              js.UndefOr[TextArea.Event] = js.undefined,
  onChange:               js.UndefOr[Callback] = js.undefined,
  onInputE:               js.UndefOr[TextArea.Event] = js.undefined,
  onInput:                js.UndefOr[Callback] = js.undefined,
  rows:                   js.UndefOr[Int | String] = js.undefined,
  value:                  js.UndefOr[String | Double] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[TextArea.TextAreaProps, TextArea] {
  override protected def cprops    = TextArea.props(this)
  override protected val component = TextArea.component
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
    var as: js.UndefOr[AsT] = js.native

    /**
     * Called on change.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onChange: js.UndefOr[RawEvent] = js.native

    /**
     * Called on input.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onInput: js.UndefOr[RawEvent] = js.native

    /** Indicates row count for a TextArea. */
    var rows: js.UndefOr[Int | String] = js.native

    /** The value of the textarea. */
    var value: js.UndefOr[Double | String] = js.native

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
    as:        js.UndefOr[AsC] = js.undefined,
    onChangeE: js.UndefOr[TextArea.Event] = js.undefined,
    onChange:  js.UndefOr[Callback] = js.undefined,
    onInputE:  js.UndefOr[TextArea.Event] = js.undefined,
    onInput:   js.UndefOr[Callback] = js.undefined,
    rows:      js.UndefOr[Int | String] = js.undefined,
    value:     js.UndefOr[String | Double] = js.undefined
  ): TextAreaProps = {
    val p = as.toJsObject[TextAreaProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    (onChangeE, onChange).toJs.foreach(v => p.onChange = v)
    (onInputE, onInput).toJs.foreach(v => p.onInput = v)
    rows.foreachUnchecked(v => p.rows = v)
    value.foreachUnchecked(v => p.value = v)
    p
  }

  private val component =
    JsComponent[TextAreaProps, Children.None, Null](RawComponent)

  val Default: TextArea = TextArea()

  val defaultProps: TextAreaProps = props(Default)

  def apply(modifiers: TagMod*): TextArea =
    new TextArea(modifiers = modifiers)
}
