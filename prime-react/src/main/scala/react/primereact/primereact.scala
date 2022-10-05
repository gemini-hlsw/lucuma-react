// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.JsNumber
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.VdomNode
import react.common.Css
import reactST.StBuildingComponent
import reactST.primereact.primereactStrings.horizontal
import reactST.primereact.primereactStrings.local
import reactST.primereact.primereactStrings.session
import reactST.primereact.primereactStrings.vertical

import scalajs.js

enum Layout(val value: horizontal | vertical):
  case Horizontal extends Layout(horizontal)
  case Vertical   extends Layout(vertical)

enum StateStorage(val value: local | session):
  case Local   extends StateStorage(local)
  case Session extends StateStorage(session)

extension [C <: js.Object, B <: StBuildingComponent[C]](b: B)
  def applyOrNot[A](a: js.UndefOr[A], f: (B, A) => B): B = a.fold(b)(a => f(b, a))
