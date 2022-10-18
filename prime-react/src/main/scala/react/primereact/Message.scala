// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.derived.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.primereactStrings.error
import reactST.primereact.primereactStrings.info
import reactST.primereact.primereactStrings.success
import reactST.primereact.primereactStrings.warn

import scalajs.js

// used by Messages and Toasts
@js.native
trait Message extends js.Object {
  var id: js.UndefOr[String]               = js.native
  var severity: js.UndefOr[String]         = js.native
  var summary: js.UndefOr[String]          = js.native
  var detail: js.UndefOr[String]           = js.native
  var content: js.UndefOr[Node]            = js.native
  var className: js.UndefOr[String]        = js.native
  var contentClassName: js.UndefOr[String] = js.native
  var closable: js.UndefOr[Boolean]        = js.native
  var sticky: js.UndefOr[Boolean]          = js.native
  var life: js.UndefOr[Int]                = js.native
}

object Message {
  def apply(
    id:           js.UndefOr[String] = js.undefined,
    severity:     Message.Severity = Severity.Info,    // Without a value is mostly transparent.
    summary:      js.UndefOr[String] = js.undefined,
    detail:       js.UndefOr[String] = js.undefined,
    content:      js.UndefOr[VdomNode] = js.undefined, // instead of summary and details
    clazz:        js.UndefOr[Css] = js.undefined,
    contentClass: js.UndefOr[Css] = js.undefined,
    closable:     js.UndefOr[Boolean] = js.undefined,  // default: true
    sticky:       js.UndefOr[Boolean] = js.undefined,  // default: false?
    life:         js.UndefOr[Int] = js.undefined       // in milliseconds. default: 3000
  ): Message = {
    val m = (new js.Object).asInstanceOf[Message]
    id.foreach(v => m.id = v)
    m.severity = severity.value
    summary.foreach(v => m.summary = v)
    detail.foreach(v => m.detail = v)
    content.foreach(v => m.content = v.rawNode)
    clazz.foreach(v => m.className = v.htmlClass)
    contentClass.foreach(v => m.contentClassName = v.htmlClass)
    closable.foreach(v => m.closable = v)
    sticky.foreach(v => m.sticky = v)
    life.foreach(v => m.life = v)
    m
  }

  enum Severity(val value: String) derives Eq:
    case Error   extends Severity("error")
    case Info    extends Severity("info")
    case Success extends Severity("success")
    case Warn    extends Severity("warn")
}
