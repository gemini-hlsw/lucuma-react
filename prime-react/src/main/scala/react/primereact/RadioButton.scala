// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.Id
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{RadioButton => CRadioButton}

import scalajs.js

case class RadioButton[A](
  value:          A,
  id:             js.UndefOr[String] = js.undefined,
  inputId:        js.UndefOr[String] = js.undefined, // id of the input element
  name:           js.UndefOr[String] = js.undefined, // Name of the radio button
  disabled:       js.UndefOr[Boolean] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  checked:        js.UndefOr[Boolean] = js.undefined,
  required:       js.UndefOr[Boolean] = js.undefined,
  onChange:       js.UndefOr[(A, Boolean) => Callback] = js.undefined,
  modifiers:      Seq[TagMod] = Seq.empty
)(using val eqAA: Eq[A])
    extends ReactFnProps[RadioButtonBase](RadioButtonBase.component)
    with RadioButtonBase {
  type AA    = A
  type GG[X] = Id[X]

  protected def valueFinder(i: Any): GG[AA] = i.asInstanceOf[A]

  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)
}

private[primereact] trait RadioButtonBase {
  protected type AA
  protected type GG[_]
  implicit val eqAA: Eq[AA]

  val value: GG[AA]
  val id: js.UndefOr[String]
  val inputId: js.UndefOr[String]
  val name: js.UndefOr[String]
  val disabled: js.UndefOr[Boolean]
  val clazz: js.UndefOr[Css]
  val checked: js.UndefOr[Boolean]
  val required: js.UndefOr[Boolean]
  val onChange: js.UndefOr[(GG[AA], Boolean) => Callback]
  val modifiers: Seq[TagMod]

  // used by onChange
  protected def valueFinder(i: Any): GG[AA]

}

object RadioButtonBase {
  private[primereact] val component = ScalaFnComponent[RadioButtonBase] { props =>
    CRadioButton
      .value(props.value)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.inputId, _.inputId(_))
      .applyOrNot(props.name, _.name(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.checked, _.checked(_))
      .applyOrNot(props.required, _.required(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(
        props.onChange,
        (c, p) => c.onChange(scp => p(props.valueFinder(scp.value), scp.checked))
      )(
        props.modifiers.toTagMod
      )
  }
}
