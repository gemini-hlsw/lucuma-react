// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.derived.*
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.JsNumber
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.VdomNode
import react.common.*
import react.fa.FontAwesomeIcon
import reactST.StBuildingComponent
import reactST.primereact.dialogDialogMod.DialogPositionType
import reactST.primereact.primereactStrings.horizontal
import reactST.primereact.primereactStrings.local
import reactST.primereact.primereactStrings.self
import reactST.primereact.primereactStrings.session
import reactST.primereact.primereactStrings.vertical

import scalajs.js

type SelfPosition = self

enum Layout(val value: horizontal | vertical) derives Eq:
  case Horizontal extends Layout(horizontal)
  case Vertical   extends Layout(vertical)

enum StateStorage(val value: local | session) derives Eq:
  case Local   extends StateStorage(local)
  case Session extends StateStorage(session)

enum DialogPosition(val value: DialogPositionType) derives Eq:
  case Bottom      extends DialogPosition(DialogPositionType.bottom)
  case BottomLeft  extends DialogPosition(DialogPositionType.`bottom-left`)
  case BottomRight extends DialogPosition(DialogPositionType.`bottom-right`)
  case Center      extends DialogPosition(DialogPositionType.center)
  case Left        extends DialogPosition(DialogPositionType.left)
  case Right       extends DialogPosition(DialogPositionType.right)
  case Top         extends DialogPosition(DialogPositionType.top)
  case TopLeft     extends DialogPosition(DialogPositionType.`top-left`)
  case TopRight    extends DialogPosition(DialogPositionType.`top-right`)

case class ConfirmDialogReturn(
  show: Callback,
  hide: Callback
)

enum ConfirmDialogHideParm:
  case Accept, Reject, Cancel

object ConfirmDialogHideParm {
  def fromString(s: String): ConfirmDialogHideParm =
    Option(s).fold(Cancel)(_ match {
      case "accept" => Accept
      case "reject" => Reject
      case "cancel" => Cancel
      case _        => Cancel
    })
}

extension (icon: FontAwesomeIcon | Image | String)
  def toPrime: Node                      = icon match {
    case fa: FontAwesomeIcon => fa.raw
    case img: Image          =>
      val v: VdomNode = img
      v.rawNode
    case s: String           => s
  }
  def toPrimeWithClass(clazz: Css): Node = icon match {
    case fa: FontAwesomeIcon => fa.addClass(clazz).raw
    case img: Image          =>
      val v: VdomNode = img.copy(clazz = img.clazz.toOption.orEmpty |+| clazz)
      v.rawNode
    case s: String           => s
  }

extension [C <: js.Object, B <: StBuildingComponent[C]](b: B)
  def applyOrNot[A](a:                                     js.UndefOr[A], f: (B, A) => B): B = a.fold(b)(a => f(b, a))

extension [A: Eq](list: List[SelectItem[A]] | SelectItemGroups[A])
  def toOptionsWithIndex: List[(SelectItem[A], Int)] = list match
    case options: List[SelectItem[A]] => options.zipWithIndex
    case groups: SelectItemGroups[A]  => groups.optionsWithIndex
