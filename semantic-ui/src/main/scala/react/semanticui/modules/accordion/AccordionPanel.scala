// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.accordion

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

final case class AccordionPanel(
  active:        MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:       MyUndefOr[ShorthandS[AccordionContent]] = MyUndefOr.undefined,
  index:         MyUndefOr[Double | String] = MyUndefOr.undefined,
  onTitleClickE: MyUndefOr[AccordionTitle.OnClick] = MyUndefOr.undefined,
  onTitleClick:  MyUndefOr[Callback] = MyUndefOr.undefined,
  title:         MyUndefOr[ShorthandS[AccordionTitle]] = MyUndefOr.undefined
) extends GenericComponentP[AccordionPanel.AccordionPanelProps] {
  override protected def cprops    = AccordionPanel.props(this)
  override protected val component = AccordionPanel.component
}

object AccordionPanel {
  @js.native
  @JSImport("semantic-ui-react", "AccordionPanel")
  object RawComponent extends js.Object

  @js.native
  trait AccordionPanelProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** Whether or not the title is in the open state. */
    var active: MyUndefOr[Boolean] = js.native

    /** Shorthand for Accordion.Content. */
    var content: MyUndefOr[suiraw.SemanticShorthandItemS[AccordionContent.AccordionContentProps]] =
      js.native

    /** A panel index. */
    var index: MyUndefOr[Double | String] = js.native

    /**
     * Called when a panel title is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {AccordionTitleProps}
     *   data - All item props.
     */
    var onTitleClick: MyUndefOr[AccordionTitle.RawOnClick]

    /** A shorthand for Accordion.Title. */
    var title: MyUndefOr[suiraw.SemanticShorthandItemS[AccordionTitle.AccordionTitleProps]] =
      js.native
  }

  def props(q: AccordionPanel): AccordionPanelProps = {
    val p = (new js.Object).asInstanceOf[AccordionPanelProps]
    q.active.foreach(v => p.active = v)
    q.content.toJs.foreach(v => p.content = v)
    q.index.foreach(v => p.index = v)
    (q.onTitleClickE, q.onTitleClick).toJs.foreach(v => p.onTitleClick = v)
    q.title.toJs.foreach(v => p.title = v)
    p
  }

  private val component = JsComponent[AccordionPanelProps, Children.None, Null](RawComponent)
}
