// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui

import scala.scalajs.js

extension [A](undefOr: js.UndefOr[A])
  def foreachUnchecked[U](f: Nothing => U): Unit =
    undefOr.foreach(a => f.asInstanceOf[(A) => U](a))
