package react.semanticui.modules.accordion

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }

final case class AccordionPanel(
  active:        js.UndefOr[Boolean] = js.undefined,
  content:       js.UndefOr[ShorthandS[AccordionContent]] = js.undefined,
  index:         js.UndefOr[Double | String] = js.undefined,
  onTitleClickE: js.UndefOr[AccordionTitle.OnClick] = js.undefined,
  onTitleClick:  js.UndefOr[Callback] = js.undefined,
  title:         js.UndefOr[ShorthandS[AccordionTitle]] = js.undefined
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
    var active: js.UndefOr[Boolean] = js.native

    /** Shorthand for Accordion.Content. */
    var content: js.UndefOr[suiraw.SemanticShorthandItemS[AccordionContent.AccordionContentProps]] =
      js.native

    /** A panel index. */
    var index: js.UndefOr[Double | String] = js.native

    /**
     * Called when a panel title is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {AccordionTitleProps}
     *   data - All item props.
     */
    var onTitleClick: js.UndefOr[AccordionTitle.RawOnClick]

    /** A shorthand for Accordion.Title. */
    var title: js.UndefOr[suiraw.SemanticShorthandItemS[AccordionTitle.AccordionTitleProps]] =
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
