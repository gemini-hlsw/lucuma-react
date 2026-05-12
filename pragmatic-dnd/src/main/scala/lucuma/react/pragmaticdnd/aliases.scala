// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.callback.CallbackTo

import scala.annotation.targetName
import scala.scalajs.js

type OptionalCallbackTo[A] = A | CallbackTo[A]

extension [A](x: OptionalCallbackTo[A])
  private[pragmaticdnd] def resolve: A =
    x match
      case cb: CallbackTo[?] => cb.asInstanceOf[CallbackTo[A]].runNow()
      case a                 => a.asInstanceOf[A]

extension [A, B](f: B => OptionalCallbackTo[A])
  private[pragmaticdnd] def resolve: B => A =
    b => f(b).resolve

extension [A, B](f: js.UndefOr[B => OptionalCallbackTo[A]])
  @targetName("resolveFnInUndef")
  private[pragmaticdnd] def resolve: js.UndefOr[B => A] =
    f.map(_.resolve)
