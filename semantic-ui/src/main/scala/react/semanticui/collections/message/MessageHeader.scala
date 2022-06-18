// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.message

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class MessageHeader(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[MessageHeader.MessageHeaderProps, MessageHeader] {
  override protected def cprops                     = MessageHeader.props(this)
  override protected val component                  = MessageHeader.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object MessageHeader {
  @js.native
  @JSImport("semantic-ui-react", "MessageHeader")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait MessageHeaderProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = MyUndefOr.undefined

    /** Primary content. */
    var children: MyUndefOr[React.Node] = MyUndefOr.undefined

    /** Additional classes. */
    var className: MyUndefOr[String] = MyUndefOr.undefined

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] =
      MyUndefOr.undefined
  }

  def props(q: MessageHeader): MessageHeaderProps =
    rawprops(
      q.as,
      q.className,
      q.clazz,
      q.content
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined
  ): MessageHeaderProps = {
    val p = as.toJsObject[MessageHeaderProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsComponent[MessageHeaderProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): MessageHeader =
    new MessageHeader(modifiers = modifiers)
}
