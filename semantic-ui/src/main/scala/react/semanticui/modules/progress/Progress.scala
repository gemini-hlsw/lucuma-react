// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.progress

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.JsNumber
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.elements.label.Label
import react.semanticui.sizes._
import react.semanticui.{raw => suiraw}

import scala.scalajs.js

import js.annotation._
import js.|

final case class Progress(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  attached:               js.UndefOr[String] = js.undefined,
  autoSuccess:            js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  error:                  js.UndefOr[Boolean] = js.undefined,
  indicating:             js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  percent:                js.UndefOr[JsNumber | String] = js.undefined,
  precision:              js.UndefOr[JsNumber] = js.undefined,
  progress:               js.UndefOr[Boolean | String] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  success:                js.UndefOr[Boolean] = js.undefined,
  total:                  js.UndefOr[JsNumber | String] = js.undefined,
  value:                  js.UndefOr[JsNumber | String] = js.undefined,
  warning:                js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Progress.ProgressProps, Progress] {
  override protected def cprops                     = Progress.props(this)
  override protected val component                  = Progress.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Progress {
  type Event = (ReactMouseEvent, js.Object) => Callback

  @js.native
  @JSImport("semantic-ui-react", "Progress")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ProgressProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** A progress bar can show activity. */
    var active: js.UndefOr[Boolean] = js.native

    /** A progress bar can attach to and show the progress of an element (i.e. Card or Segment). */
    var attached: js.UndefOr[String] = js.native

    /** Whether success state should automatically trigger when progress completes. */
    var autoSuccess: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A progress bar can have different colors. */
    var color: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A progress bar be disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** A progress bar can show a error state. */
    var error: js.UndefOr[Boolean] = js.native

    /** An indicating progress bar visually indicates the current level of progress of a task. */
    var indicating: js.UndefOr[Boolean] = js.native

    /** A progress bar can have its colors inverted. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** Can be set to either to display progress as percent or ratio. */
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** Current percent complete. */
    var percent: js.UndefOr[JsNumber | String] = js.native

    /** Decimal point precision for calculated progress. */
    var precision: js.UndefOr[JsNumber] = js.native

    /** A progress bar can contain a text value indicating current progress. */
    var progress: js.UndefOr[Boolean | String] = js.native

    /** A progress bar can vary in size. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** A progress bar can show a success state. */
    var success: js.UndefOr[Boolean] = js.native

    /**
     * For use with value. Together, these will calculate the percent. Mutually excludes percent.
     */
    var total: js.UndefOr[JsNumber | String] = js.native

    /**
     * For use with total. Together, these will calculate the percent. Mutually excludes percent.
     */
    var value: js.UndefOr[JsNumber | String] = js.native

    /** A progress bar can show a warning state. */
    var warning: js.UndefOr[Boolean] = js.native
  }

  def props(q: Progress): ProgressProps =
    rawprops(
      q.as,
      q.active,
      q.attached,
      q.autoSuccess,
      q.className,
      q.clazz,
      q.color,
      q.content,
      q.error,
      q.indicating,
      q.inverted,
      q.label,
      q.percent,
      q.precision,
      q.progress,
      q.size,
      q.success,
      q.total,
      q.value,
      q.warning
    )

  def rawprops(
    as:          js.UndefOr[AsC] = js.undefined,
    active:      js.UndefOr[Boolean] = js.undefined,
    attached:    js.UndefOr[String] = js.undefined,
    autoSuccess: js.UndefOr[Boolean] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    color:       js.UndefOr[SemanticColor] = js.undefined,
    content:     js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    error:       js.UndefOr[Boolean] = js.undefined,
    indicating:  js.UndefOr[Boolean] = js.undefined,
    inverted:    js.UndefOr[Boolean] = js.undefined,
    label:       js.UndefOr[ShorthandS[Label]] = js.undefined,
    percent:     js.UndefOr[JsNumber | String] = js.undefined,
    precision:   js.UndefOr[JsNumber] = js.undefined,
    progress:    js.UndefOr[Boolean | String] = js.undefined,
    size:        js.UndefOr[SemanticSize] = js.undefined,
    success:     js.UndefOr[Boolean] = js.undefined,
    total:       js.UndefOr[JsNumber | String] = js.undefined,
    value:       js.UndefOr[JsNumber | String] = js.undefined,
    warning:     js.UndefOr[Boolean] = js.undefined
  ): ProgressProps = {
    val p = as.toJsObject[ProgressProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    active.foreach(v => p.active = v)
    attached.foreach(v => p.attached = v)
    autoSuccess.foreach(v => p.autoSuccess = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    content.toJs.foreachUnchecked(v => p.content = v)
    error.foreach(v => p.error = v)
    indicating.foreach(v => p.indicating = v)
    inverted.foreach(v => p.inverted = v)
    CompToPropsS(label).toJs.foreachUnchecked(v => p.label = v)
    percent.foreachUnchecked(v => p.percent = v)
    precision.foreachUnchecked(v => p.precision = v)
    progress.foreachUnchecked(v => p.progress = v)
    size.toJs.foreach(v => p.size = v)
    success.foreach(v => p.success = v)
    total.foreachUnchecked(v => p.total = v)
    value.foreachUnchecked(v => p.value = v)
    warning.foreach(v => p.warning = v)
    p
  }

  private val component =
    JsComponent[ProgressProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Progress =
    Progress(modifiers = modifiers)
}
