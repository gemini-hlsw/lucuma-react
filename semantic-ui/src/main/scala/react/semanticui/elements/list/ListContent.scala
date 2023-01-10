// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.list

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

import scala.scalajs.js

import js.JSConverters._
import js.annotation._

final case class ListContent(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  description:            js.UndefOr[ShorthandS[ListDescription]] = js.undefined,
  content:                js.UndefOr[Seq[VdomNode]] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  header:                 js.UndefOr[ListHeader] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ListContent.ListContentProps, ListContent] {
  override protected def cprops    = ListContent.props(this)
  override protected val component = ListContent.component
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
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[js.Array[suiraw.SemanticShorthandContent]] = js.native

    /** Shorthand for ListDescription. */
    var description
      : js.UndefOr[suiraw.SemanticShorthandItemS[ListDescription.ListDescriptionProps]] = js.native

    /** An list content can be floated left or right. */
    var floated: js.UndefOr[suiraw.SemanticFLOATS] = js.native

    /** Shorthand for ListHeader. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[ListHeader.ListHeaderProps]] = js.native

    /** An element inside a list can be vertically aligned. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

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
    as:            js.UndefOr[AsC] = js.undefined,
    className:     js.UndefOr[String] = js.undefined,
    clazz:         js.UndefOr[Css] = js.undefined,
    content:       js.UndefOr[Seq[VdomNode]] = js.undefined,
    description:   js.UndefOr[ShorthandS[ListDescription]] = js.undefined,
    floated:       js.UndefOr[SemanticFloat] = js.undefined,
    header:        js.UndefOr[ListHeader] = js.undefined,
    verticalAlign: js.UndefOr[SemanticVerticalAlignment] = js.undefined
  ): ListContentProps = {
    val p = as.toJsObject[ListContentProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    content.map(_.map(_.rawNode).toJSArray).foreach(v => p.content = v)
    CompFnToPropsS(description).toJs.foreachUnchecked(v => p.description = v)
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
