// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.fa.FontAwesomeIcon
import reactST.primereact.components.{Message => CMessage}
import reactST.primereact.primereactStrings.error
import reactST.primereact.primereactStrings.info
import reactST.primereact.primereactStrings.success
import reactST.primereact.primereactStrings.warn

import scalajs.js
import scalajs.js.JSConverters.*

case class Message(
  id:        js.UndefOr[String] = js.undefined,
  severity:  js.UndefOr[Message.Severity] = js.undefined,
  text:      js.UndefOr[String] = js.undefined,
  content:   js.UndefOr[VdomNode] = js.undefined,
  icon:      js.UndefOr[FontAwesomeIcon] = js.undefined, // default: severity icon
  clazz:     js.UndefOr[Css] = js.undefined,
  modifiers: Seq[TagMod] = Seq.empty
) extends ReactFnProps(Message.component):
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods: TagMod*)              = addModifiers(mods)

object Message:
  enum Severity(val value: error | info | success | warn):
    case Error   extends Severity(error)
    case Info    extends Severity(info)
    case Success extends Severity(success)
    case Warning extends Severity(warn)

  private val component = ScalaFnComponent[Message] { props =>
    CMessage
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.severity, (c, p) => c.severity(p.value))
      .applyOrNot(props.text, (c, p) => c.text(p.rawNode))
      .applyOrNot(props.content, (c, p) => c.content(p.rawNode))
      .applyOrNot(props.icon, (c, p) => c.icon(p.raw))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))(
        props.modifiers.toTagMod
      )
  }
