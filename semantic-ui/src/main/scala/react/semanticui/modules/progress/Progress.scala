// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.progress

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import react.semanticui.sizes._
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui.elements.label.Label

final case class Progress(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  attached:               MyUndefOr[String] = MyUndefOr.undefined,
  autoSuccess:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  error:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  indicating:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  percent:                MyUndefOr[Double | String] = MyUndefOr.undefined,
  precision:              MyUndefOr[Double] = MyUndefOr.undefined,
  progress:               MyUndefOr[Boolean | String] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  success:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  total:                  MyUndefOr[Double | String] = MyUndefOr.undefined,
  value:                  MyUndefOr[Double | String] = MyUndefOr.undefined,
  warning:                MyUndefOr[Boolean] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = js.native

    /** A progress bar can show activity. */
    var active: MyUndefOr[Boolean] = js.native

    /** A progress bar can attach to and show the progress of an element (i.e. Card or Segment). */
    var attached: MyUndefOr[String] = js.native

    /** Whether success state should automatically trigger when progress completes. */
    var autoSuccess: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A progress bar can have different colors. */
    var color: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A progress bar be disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** A progress bar can show a error state. */
    var error: MyUndefOr[Boolean] = js.native

    /** An indicating progress bar visually indicates the current level of progress of a task. */
    var indicating: MyUndefOr[Boolean] = js.native

    /** A progress bar can have its colors inverted. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** Can be set to either to display progress as percent or ratio. */
    var label: MyUndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** Current percent complete. */
    var percent: MyUndefOr[Double | String] = js.native

    /** Decimal point precision for calculated progress. */
    var precision: MyUndefOr[Double] = js.native

    /** A progress bar can contain a text value indicating current progress. */
    var progress: MyUndefOr[Boolean | String] = js.native

    /** A progress bar can vary in size. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /** A progress bar can show a success state. */
    var success: MyUndefOr[Boolean] = js.native

    /**
     * For use with value. Together, these will calculate the percent. Mutually excludes percent.
     */
    var total: MyUndefOr[Double | String] = js.native

    /**
     * For use with total. Together, these will calculate the percent. Mutually excludes percent.
     */
    var value: MyUndefOr[Double | String] = js.native

    /** A progress bar can show a warning state. */
    var warning: MyUndefOr[Boolean] = js.native
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
    as:          MyUndefOr[AsC] = MyUndefOr.undefined,
    active:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    attached:    MyUndefOr[String] = MyUndefOr.undefined,
    autoSuccess: MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:   MyUndefOr[String] = MyUndefOr.undefined,
    clazz:       MyUndefOr[Css] = MyUndefOr.undefined,
    color:       MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    content:     MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    error:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    indicating:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    inverted:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:       MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
    percent:     MyUndefOr[Double | String] = MyUndefOr.undefined,
    precision:   MyUndefOr[Double] = MyUndefOr.undefined,
    progress:    MyUndefOr[Boolean | String] = MyUndefOr.undefined,
    size:        MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    success:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    total:       MyUndefOr[Double | String] = MyUndefOr.undefined,
    value:       MyUndefOr[Double | String] = MyUndefOr.undefined,
    warning:     MyUndefOr[Boolean] = MyUndefOr.undefined
  ): ProgressProps = {
    val p = as.toJsObject[ProgressProps]
    as.toJs.foreach(v => p.as = v)
    active.foreach(v => p.active = v)
    attached.foreach(v => p.attached = v)
    autoSuccess.foreach(v => p.autoSuccess = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    content.toJs.foreach(v => p.content = v)
    error.foreach(v => p.error = v)
    indicating.foreach(v => p.indicating = v)
    inverted.foreach(v => p.inverted = v)
    label.toJs.foreach(v => p.label = v)
    percent.foreach(v => p.percent = v)
    precision.foreach(v => p.precision = v)
    progress.foreach(v => p.progress = v)
    size.toJs.foreach(v => p.size = v)
    success.foreach(v => p.success = v)
    total.foreach(v => p.total = v)
    value.foreach(v => p.value = v)
    warning.foreach(v => p.warning = v)
    p
  }

  private val component =
    JsComponent[ProgressProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Progress =
    Progress(modifiers = modifiers)
}
