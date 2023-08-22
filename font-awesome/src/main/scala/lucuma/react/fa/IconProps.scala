// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa

import cats.syntax.all.*
import lucuma.react.common.Css
import lucuma.react.common.EnumValue

import scalajs.js

trait IconProps:
  def beat: Boolean
  def beatFade: Boolean
  def border: Boolean
  def bounce: Boolean
  def clazz: Css
  def fade: Boolean
  def flash: Boolean
  def fixedWidth: Boolean
  def inverse: Boolean
  def flip: js.UndefOr[Flip]
  def listItem: Boolean
  def pull: js.UndefOr[Pull]
  def pulse: Boolean
  def rotation: js.UndefOr[Rotation]
  def shake: Boolean
  def size: js.UndefOr[IconSize]
  def spin: Boolean
  def spinPulse: Boolean
  def spinReverse: Boolean
  def swapOpacity: Boolean

  private def classIf(condition: Boolean, clazz: String): Css =
    Option.when(condition)(Css(clazz)).orEmpty

  def faClasses: Css =
    List(
      classIf(beat, "fa-beat"),
      classIf(beatFade, "fa-beat-fade"),
      classIf(border, "fa-border"),
      classIf(bounce, "fa-bounce"),
      clazz,
      classIf(fade, "fa-fade"),
      classIf(flash, "fa-flash"),
      classIf(fixedWidth, "fa-fw"),
      classIf(inverse, "fa-inverse"),
      classIf(flip.exists(List(Flip.Horizontal, Flip.Both).contains), "fa-flip-horizontal"),
      classIf(flip.exists(List(Flip.Vertical, Flip.Both).contains), "fa-flip-vertical"),
      classIf(listItem, "fa-li"),
      classIf(pull.contains(Pull.Left), "fa-pull-left"),
      classIf(pull.contains(Pull.Right), "fa-pull-right"),
      classIf(pulse, "fa-pulse"),
      classIf(rotation.contains(Rotation.Rotate90), "fa-rotate-90"),
      classIf(rotation.contains(Rotation.Rotate180), "fa-rotate-180"),
      classIf(rotation.contains(Rotation.Rotate270), "fa-rotate-270"),
      classIf(shake, "fa-shake"),
      size.map(s => Css(s"fa-${EnumValue[IconSize].value(s)}")).getOrElse(Css.Empty),
      classIf(spin, "fa-spin"),
      classIf(spinPulse, "fa-spin-pulse"),
      classIf(spinReverse, "fa-spin-reverse"),
      classIf(swapOpacity, "fa-swap-opacity")
    ).foldLeft(Css.Empty)(_ |+| _)
