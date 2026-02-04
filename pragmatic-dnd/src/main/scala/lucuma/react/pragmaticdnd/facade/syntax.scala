// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js
import scalajs.js.JSConverters.*

// Copied verbatim from react-table package. Maybe we should unify.
extension [B](b: B)
  private[facade] def applyOrElseWhen[A](
    cond:   Boolean,
    a:      js.UndefOr[A],
    f:      (B, A) => Unit,
    orElse: B => B
  ): B =
    if cond then { a.fold(orElse(b))(a => f(b, a)); b }
    else b

  inline private[facade] def applyOrElse[A](
    a:      js.UndefOr[A],
    f:      (B, A) => Unit,
    orElse: B => B
  ): B =
    applyOrElseWhen(true, a, f, orElse)

  inline private[facade] def applyOrNot[A](a: js.UndefOr[A], f: (B, A) => Unit): B =
    applyOrElse(a, f, identity)

  inline private[facade] def applyOrNull[A](
    a:     Option[A],
    f:     (B, A) => Unit,
    fNull: B => B
  ): B =
    applyOrElse(a.orUndefined, f, fNull)

  private[facade] def applyWhen(cond: Boolean, f: B => B): B =
    if cond then f(b) else b
