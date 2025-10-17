// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.Eq
import cats.derived.*
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.VdomNode
import lucuma.react.common.*
import lucuma.react.fa.FontAwesomeIcon
import lucuma.react.fa.LayeredIcon
import lucuma.typed.StBuildingComponent
import lucuma.typed.primereact.apiApiMod.InputStyleType
import lucuma.typed.primereact.primereactStrings.*

import scalajs.js

type SelfPosition = self

enum Layout(val value: horizontal | vertical) derives Eq:
  case Horizontal extends Layout(horizontal)
  case Vertical   extends Layout(vertical)

enum StateStorage(val value: local | session) derives Eq:
  case Local   extends StateStorage(local)
  case Session extends StateStorage(session)

enum DialogPosition(
  val value: bottom | `bottom-left` | `bottom-right` | center | left | right | top | `top-left` |
    `top-right`
) derives Eq:
  case Bottom      extends DialogPosition(bottom)
  case BottomLeft  extends DialogPosition(`bottom-left`)
  case BottomRight extends DialogPosition(`bottom-right`)
  case Center      extends DialogPosition(center)
  case Left        extends DialogPosition(left)
  case Right       extends DialogPosition(right)
  case Top         extends DialogPosition(top)
  case TopLeft     extends DialogPosition(`top-left`)
  case TopRight    extends DialogPosition(`top-right`)

case class ConfirmDialogReturn(show: Callback, hide: Callback)

enum ConfirmDialogHideParm:
  case Accept, Reject, Cancel

enum InputStyle(val toJs: InputStyleType):
  case Outlined extends InputStyle(InputStyleType.outlined)
  case Filled   extends InputStyle(InputStyleType.filled)

object ConfirmDialogHideParm {
  def fromString(s: String): ConfirmDialogHideParm =
    Option(s).fold(Cancel)(_ match {
      case "accept" => Accept
      case "reject" => Reject
      case "cancel" => Cancel
      case _        => Cancel
    })
}

type Icon = FontAwesomeIcon | LayeredIcon | Image | String

extension (icon: Icon)
  def toPrime: Node                      = icon match {
    case fa: FontAwesomeIcon => (fa: VdomNode).rawNode
    case fal: LayeredIcon    => (fal: VdomNode).rawNode
    case img: Image          => (img: VdomNode).rawNode
    case s: String           => s
  }
  def toPrimeWithClass(clazz: Css): Node = icon match {
    case fa: FontAwesomeIcon => (fa.copy(clazz = fa.clazz |+| clazz): VdomNode).rawNode
    case fal: LayeredIcon    => (fal.copy(clazz = fal.clazz |+| clazz): VdomNode).rawNode
    case img: Image          =>
      (img.copy(clazz = img.clazz.toOption.orEmpty |+| clazz): VdomNode).rawNode
    case s: String           => s
  }

extension [C <: js.Object, B <: StBuildingComponent[C]](b: B)
  def applyOrNot[A](a:                                     js.UndefOr[A], f: (B, A) => B): B = a.fold(b)(a => f(b, a))

extension [A](list: List[SelectItem[A]] | SelectItemGroups[A])
  def toOptionsWithIndex: List[(SelectItem[A], Int)] = list match
    case options: List[SelectItem[A]] => options.zipWithIndex
    case groups: SelectItemGroups[A]  => groups.optionsWithIndex
