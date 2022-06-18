// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.list

import scala.scalajs.js
import js.JSConverters._
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class ListContent(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  description:            MyUndefOr[ShorthandS[ListDescription]] = MyUndefOr.undefined,
  content:                MyUndefOr[Seq[VdomNode]] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  header:                 MyUndefOr[ListHeader] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ListContent.ListContentProps, ListContent] {
  override protected def cprops                     = ListContent.props(this)
  override protected val component                  = ListContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ListContent {
  @js.native
  @JSImport("semantic-ui-react", "ListContent")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ListContentProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[js.Array[suiraw.SemanticShorthandContent]] = js.native

    /** Shorthand for ListDescription. */
    var description
      : MyUndefOr[suiraw.SemanticShorthandItemS[ListDescription.ListDescriptionProps]] = js.native

    /** An list content can be floated left or right. */
    var floated: MyUndefOr[suiraw.SemanticFLOATS] = js.native

    /** Shorthand for ListHeader. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[ListHeader.ListHeaderProps]] = js.native

    /** An element inside a list can be vertically aligned. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

  }

  def props(
    q: ListContent
  ): ListContentProps =
    rawprops(q.as,
             q.className,
             q.clazz,
             q.content,
             q.description,
             q.floated,
             q.header,
             q.verticalAlign
    )

  def rawprops(
    as:            MyUndefOr[AsC] = MyUndefOr.undefined,
    className:     MyUndefOr[String] = MyUndefOr.undefined,
    clazz:         MyUndefOr[Css] = MyUndefOr.undefined,
    content:       MyUndefOr[Seq[VdomNode]] = MyUndefOr.undefined,
    description:   MyUndefOr[ShorthandS[ListDescription]] = MyUndefOr.undefined,
    floated:       MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
    header:        MyUndefOr[ListHeader] = MyUndefOr.undefined,
    verticalAlign: MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined
  ): ListContentProps = {
    val p = as.toJsObject[ListContentProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.map(_.map(_.rawNode).toJSArray).foreach(v => p.content = v)
    description.toJs.foreach(v => p.description = v)
    floated.toJs.foreach(v => p.floated = v)
    header.map(_.props).foreach(v => p.header = v)
    verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsFnComponent[ListContentProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ListContent =
    new ListContent(modifiers = modifiers)
}
