// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import japgolly.scalajs.react.vdom.TagMod
import reactST.{tanstackTableCore => raw}

import scalajs.js

package object table extends HooksApiExt:
  extension [T, A](cellCtx: raw.mod.CellContext[T, A])
    def value: A = cellCtx.getValue().asInstanceOf[A]

  given renderJSArray[A](using ev: A => TagMod): Conversion[js.Array[A], TagMod] =
    new Conversion {
      def apply(x: js.Array[A]): TagMod = TagMod.fromTraversableOnce(x.map(ev))
    }
