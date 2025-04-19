// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.floatingui

import japgolly.scalajs.react.*
import japgolly.scalajs.react.feature.ReactFragment
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.ReactFnProps
import lucuma.react.common.Style
import lucuma.react.floatingui.hooks.*
import org.scalajs.dom

import scala.scalajs.js

/**
 * Tooltip base on floating ui see: https://floating-ui.com/docs/react-dom
 */
case class Tooltip(
  trigger:   VdomTag,
  tooltip:   VdomNode,
  placement: Placement = Placement.Top,
  open:      Boolean = false
) extends ReactFnProps(Tooltip.component)

object Tooltip {
  private type Props = Tooltip

  private val component =
    ScalaFnComponent
      .withHooks[Props]
      .useStateBy(_.open) // isOpen
      .useRefToVdom[dom.HTMLElement] // arrow
      .useFloatingBy { (props, open, arrow) =>
        UseFloatingProps(
          placement = props.placement,
          open = open.value,
          onOpenChange = open.setState,
          middleware = List(
            middleware.flip(),
            middleware.shift(ShiftOptions(padding = 5)),
            middleware.offset(4),
            middleware.arrow(ArrowElement(arrow.raw))
          )
        )
      }
      .useInteractionsBy { (_, _, _, h) =>
        List(middleware.useHover(h.context))
      }
      .render { (props, open, arrow, floating, _) =>
        val display: Map[String, String | Int] =
          if (open.value) Map.empty[String, String | Int]
          else Map[String, String | Int]("display" -> "none")

        val style = (floating.x.toOption, floating.y.toOption) match {
          case (Some(x), Some(y)) =>
            Style(
              Map(
                "position" -> floating.strategy,
                "left"     -> s"${x}px",
                "top"      -> s"${y}px"
              ) ++ display
            )
          case _                  =>
            Style(
              Map(
                "position" -> floating.strategy,
                "left"     -> "0",
                "top"      -> "0"
              ) ++ display
            )
        }

        val arrowOpt                                 = floating.middlewareData.arrow.toOption
        val arrowStyleMap: Map[String, String | Int] =
          (arrowOpt.flatMap(_.x.toOption), arrowOpt.flatMap(_.y.toOption)) match {
            case (Some(x), Some(y)) =>
              Map("left" -> s"${x}px", "top" -> s"${y}px", "right" -> "", "bottom" -> "")
            case (Some(x), None)    =>
              Map("left" -> s"${x}px", "top" -> s"", "right" -> "", "bottom" -> "")
            case (None, Some(y))    =>
              Map("left" -> "", "top" -> s"${y}px", "right" -> "", "bottom" -> "")
            case _                  => Map.empty
          }

        val arrowShift  = "-4px"
        val arrowBorder = "var(--tooltip-border-width)"

        val placementStyle: Map[String, String | Int] =
          Placement.fromString(floating.placement) match {
            case Some(Placement.Top) | Some(Placement.TopStart) | Some(Placement.TopEnd)          =>
              Map("bottom"            -> arrowShift,
                  "borderRightWidth"  -> arrowBorder,
                  "borderBottomWidth" -> arrowBorder
              )
            case Some(Placement.Bottom) | Some(Placement.BottomStart) | Some(Placement.BottomEnd) =>
              Map("top"             -> arrowShift,
                  "borderLeftWidth" -> arrowBorder,
                  "borderTopWidth"  -> arrowBorder
              )
            case Some(Placement.Left) | Some(Placement.LeftStart) | Some(Placement.LeftEnd)       =>
              Map("right"            -> arrowShift,
                  "borderRightWidth" -> arrowBorder,
                  "borderTopWidth"   -> arrowBorder
              )
            case Some(Placement.Right) | Some(Placement.RightStart) | Some(Placement.RightEnd)    =>
              Map("left"              -> arrowShift,
                  "borderLeftWidth"   -> arrowBorder,
                  "borderBottomWidth" -> arrowBorder
              )
            case _                                                                                => Map.empty
          }

        val arrowStyle =
          arrowOpt.fold(Style(display))(_ => Style(display ++ arrowStyleMap ++ placementStyle))
        ReactFragment(
          props.trigger(^.untypedRef(floating.refs.setReference)),
          if (open.value)
            <.div(
              ^.untypedRef(floating.refs.setFloating),
              ^.cls   := "tooltip",
              ^.style := style.toJsObject,
              props.tooltip,
              <.div(^.cls := "arrow", ^.untypedRef := arrow, ^.style := arrowStyle.toJsObject)
            )
          else
            EmptyVdom
        )
      }
}
