// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.common.style.Css
import reactST.{tanstackTableCore => raw}

import javax.swing.text.html.HTML

import scalajs.js

final case class HTMLTable[T](
  table:      raw.mod.Table[T],
  tableClass: Css = Css.Empty,
  rowClassFn: (Int, T) => Css = (_, _: T) => Css.Empty
) extends ReactFnProps(HTMLTable.component)
    with HTMLTableProps[T]

final case class HTMLVirtualizedTable[T](
  table:          raw.mod.Table[T],
  containerClass: Css = Css.Empty,
  tableClass:     Css = Css.Empty,
  rowClassFn:     (Int, T) => Css = (_, _: T) => Css.Empty
) extends ReactFnProps(HTMLVirtualizedTable.component)
    with HTMLVirtualizedTableProps[T]

private val baseHTMLRenderer: HTMLTableRenderer[Any] =
  new HTMLTableRenderer[Any] {}

object HTMLTable:
  private val component = HTMLTableRenderer.componentBuilder[Any, HTMLTable](baseHTMLRenderer)

object HTMLVirtualizedTable:
  private val component =
    HTMLTableRenderer.componentBuilderVirtualized[Any, HTMLVirtualizedTable](baseHTMLRenderer)
