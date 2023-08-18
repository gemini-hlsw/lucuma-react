// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.*

import scalajs.js

extension (ae: AbstractElement)
  // WARNING: Mutates a.attributes
  def renderVdom: VdomElement =
    def splitStyleString(style: String): List[(String, String)] =
      style
        .split(";")
        .toList
        .map(_.trim)
        .filter(_.nonEmpty)
        .map(_.split(":").toList)
        .collect:
          case List(k, v) => (k.trim, v.trim)

    val vdomBuilder = new VdomBuilder.ToRawReactElement
    ae.attributes.get("class").foreach(vdomBuilder.addClassName(_))
    ae.attributes
      .get("style")
      .map: s =>
        splitStyleString(s.asInstanceOf[String])
      .orEmpty
      .foreach: (k, v) =>
        vdomBuilder.addStyle(k, v)
    ae.attributes -= "class"
    ae.attributes -= "style"
    vdomBuilder.addAttrsObject(ae.attributes.asInstanceOf[js.Object])
    ae.children.foreach:
      _.foreach: ch =>
        vdomBuilder.appendChild(ch.asInstanceOf[facade.React.Node])

    VdomElement(vdomBuilder.render(ae.tag))
