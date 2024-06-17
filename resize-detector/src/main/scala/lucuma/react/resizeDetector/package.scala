// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.resizeDetector

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.HTMLElement

import scalajs.js

case class UseResizeDetectorReturn(
  height: Option[Int],
  width:  Option[Int],
  ref:    Ref.ToVdom[HTMLElement]
):
  def isReady: Boolean = width.isDefined && height.isDefined

  override def toString(): String = s"Resize($width, $height)"

object UseResizeDetectorReturn:
  given Reusability[UseResizeDetectorReturn] = Reusability.by(x => (x.height, x.width, x.ref))

case class UseResizeDetectorProps(onResize: js.UndefOr[(Int, Int) => Callback] = js.undefined)
