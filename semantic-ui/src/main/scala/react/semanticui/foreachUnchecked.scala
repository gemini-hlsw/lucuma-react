// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui

import scala.quoted.*
import scala.scalajs.js

extension [A](inline undefOr: js.UndefOr[A])
  inline def foreachUnchecked(inline f: Nothing => Unit): Unit =
    if undefOr.isDefined then foreachUncheckedIfDefined(f) else ()

  private inline def foreachUncheckedIfDefined(inline f: Nothing => Unit): Unit =
    ${ foreachUncheckedMacro('undefOr, 'f) }

private def foreachUncheckedMacro[A](undefOr: Expr[js.UndefOr[A]], f: Expr[Nothing => Unit])(using
  Quotes
): Expr[Unit] =
  Expr.betaReduce('{ ($f)(${ undefOr.asInstanceOf[Expr[Nothing]] }) })
