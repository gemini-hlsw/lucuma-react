package react.semanticui.modules.accordion

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Accordion(
  as:                     js.UndefOr[AsC] = js.undefined,
  activeIndex:            js.UndefOr[Double | js.Array[Double]] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  defaultActiveIndex:     js.UndefOr[Int | Seq[Int]] = js.undefined,
  exclusive:              js.UndefOr[Boolean] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  styled:                 js.UndefOr[Boolean] = js.undefined,
  onTitleClickE:          js.UndefOr[AccordionTitle.OnClick] = js.undefined,
  onTitleClick:           js.UndefOr[Callback] = js.undefined,
  panels:                 js.UndefOr[Seq[AccordionPanel]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Accordion.AccordionProps, Accordion] {
  override protected def cprops                     = Accordion.props(this)
  override protected val component                  = Accordion.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Accordion {
  @js.native
  @JSImport("semantic-ui-react", "Accordion")
  object RawComponent extends js.Object

  @js.native
  trait AccordionProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Index of the currently active panel. */
    var activeIndex: js.UndefOr[Double | js.Array[Double]] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Initial activeIndex value. */
    var defaultActiveIndex: js.UndefOr[Double | js.Array[Double]] = js.native

    /** Only allow one panel open at a time. */
    var exclusive: js.UndefOr[Boolean] = js.native

    /** Format to take up the width of its container . */
    var fluid: js.UndefOr[Boolean] = js.native

    /** Format for dark backgrounds. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** Adds some basic styling to accordion panels. */
    var styled: js.UndefOr[Boolean] = js.native

    /**
     * Called when a panel title is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {AccordionTitleProps}
     *   data - All item props.
     */
    var onTitleClick: js.UndefOr[AccordionTitle.RawOnClick]

    /** Shorthand array of props for Accordion. */
    var panels: js.UndefOr[js.Array[AccordionPanel.AccordionPanelProps]] =
      js.native
  }

  def props(q: Accordion): AccordionProps = {
    val p = q.as.toJsObject[AccordionProps]
    q.as.toJs.foreach(v => p.as = v)
    q.activeIndex.foreach(v => p.activeIndex = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.defaultActiveIndex
      .map[Double | js.Array[Double]] { x =>
        (x: Any) match {
          case p: Int => p
          case p      => p.asInstanceOf[Seq[Double]].toJSArray
        }
      }
      .foreach(v => p.defaultActiveIndex = v)
    q.exclusive.foreach(v => p.exclusive = v)
    q.fluid.foreach(v => p.fluid = v)
    q.inverted.foreach(v => p.inverted = v)
    q.styled.foreach(v => p.styled = v)
    (q.onTitleClickE, q.onTitleClick).toJs.foreach(v => p.onTitleClick = v)
    q.panels.map(_.map(_.props).toJSArray).foreach(v => p.panels = v)
    p
  }

  private val component = JsComponent[AccordionProps, Children.None, Null](RawComponent)

  def apply(ps: AccordionPanel*): Accordion =
    new Accordion(panels = ps)
}
