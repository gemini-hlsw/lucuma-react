// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.markdown

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.*
import lucuma.react.common.Css
import lucuma.react.common.GenericFnComponentPA
import org.http4s.Uri

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.*

sealed trait RemarkPlugin extends js.Object

object RemarkPlugin {

  @js.native
  @JSImport("remark-gfm", JSImport.Default)
  object RemarkGFM extends js.Object with RemarkPlugin

  @js.native
  @JSImport("remark-math", JSImport.Default)
  object RemarkMath extends js.Object with RemarkPlugin

}

sealed trait RehypePlugin extends js.Object

object RehypePlugin {
  @js.native
  @JSImport("rehype-katex", JSImport.Default)
  object RehypeKatex extends js.Object with RehypePlugin

  @js.native
  trait ExternalLinksOptions extends js.Object {
    var target: js.UndefOr[String]
    var rel: js.UndefOr[js.Array[String]]
  }

  object ExternalLinksOptions {
    def apply(
      target: js.UndefOr[String] = js.undefined,
      rel:    js.UndefOr[List[String]] = js.undefined
    ): ExternalLinksOptions = {
      val p = (new js.Object).asInstanceOf[ExternalLinksOptions]
      target.map(v => p.target = v)
      rel.map(v => p.rel = v.toJSArray)
      p
    }
  }

  @js.native
  @JSImport("rehype-external-links", JSImport.Default)
  object RehypeExternalLinks extends js.Object with RehypePlugin {
    def apply(args: ExternalLinksOptions): RehypePlugin = js.native
  }
}

case class ReactMarkdown(
  content:                js.UndefOr[String],
  clazz:                  js.UndefOr[Css] = js.undefined,
  urlTransform:           js.UndefOr[Uri => Uri] = js.undefined,
  remarkPlugins:          js.UndefOr[List[RemarkPlugin]] = js.undefined,
  rehypePlugins:          js.UndefOr[List[RehypePlugin]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPA[ReactMarkdown.Props, ReactMarkdown] {
  override protected def cprops    = ReactMarkdown.props(this)
  override protected val component = ReactMarkdown.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ReactMarkdown {

  @js.native
  @JSImport("react-markdown", JSImport.Default)
  object ReactMarkdown extends js.Function1[js.Any, js.Any] {
    def apply(arg: js.Any): js.Any = js.native
  }

  @js.native
  trait Props extends js.Object {
    var children: js.UndefOr[String]
    var className: js.UndefOr[String]
    var urlTransform: js.UndefOr[js.Function1[String, String]]
    var remarkPlugins: js.UndefOr[js.Array[js.Object]]
    var rehypePlugins: js.UndefOr[js.Array[js.Object]]
  }

  protected def props(p: ReactMarkdown): Props =
    rawprops(p.content, p.clazz, p.urlTransform, p.remarkPlugins, p.rehypePlugins)

  protected def rawprops(
    content:       js.UndefOr[String],
    clazz:         js.UndefOr[Css],
    urlTransform:  js.UndefOr[Uri => Uri],
    remarkPlugins: js.UndefOr[List[RemarkPlugin]],
    rehypePlugins: js.UndefOr[List[RehypePlugin]]
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    content.foreach(v => p.children = v)
    clazz.foreach(v => p.className = v.htmlClass)
    urlTransform.foreach(v =>
      p.urlTransform =
        ((u: String) => v(Uri.unsafeFromString(u)).toString()): js.Function1[String, String]
    )
    remarkPlugins.foreach(l => p.remarkPlugins = l.map(_.asInstanceOf[js.Object]).toJSArray)
    rehypePlugins.foreach(l => p.rehypePlugins = l.map(_.asInstanceOf[js.Object]).toJSArray)
    p
  }

  private val component = JsFnComponent[Props, Children.None](ReactMarkdown)
}
