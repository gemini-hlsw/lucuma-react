// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*

import scalajs.js
import scalajs.js.annotation.JSImport

// Note: Needed to hand-roll a facade here because I couldn't get a ScalaFnComponent to work as
// a child of a `Splitter`.
case class SplitterPanel(
  size:                   js.UndefOr[Double] = js.undefined, // size relative to 100%
  minSize:                js.UndefOr[Double] = js.undefined, // minimum size relative to 100%
  clazz:                  js.UndefOr[Css] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[SplitterPanel.SplitterPanelProps, SplitterPanel] {
  override protected def cprops                     = SplitterPanel.props(this)
  override protected val component                  = SplitterPanel.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def apply(mods: TagMod*)                          = addModifiers(mods)
}

object SplitterPanel {
  @js.native
  @JSImport("primereact/splitter/splitter.esm", "SplitterPanel")
  object RawSplitterPanel extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait SplitterPanelProps extends js.Object {
    var size: js.UndefOr[Double]      = js.native
    var minSize: js.UndefOr[Double]   = js.native
    var className: js.UndefOr[String] = js.native
  }

  def props(q: SplitterPanel): SplitterPanelProps = {
    val p = (new js.Object).asInstanceOf[SplitterPanelProps]
    q.size.foreach(v => p.size = v)
    q.minSize.foreach(v => p.minSize = v)
    q.clazz.foreach(v => p.className = v.htmlClass)
    p
  }

  private val component = JsFnComponent[SplitterPanelProps, Children.Varargs](RawSplitterPanel)
}
