// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.*

import scalajs.js

private val attrConversions: Map[String, String] =
  List(
    "acceptCharset",
    "accessKey",
    "allowFullScreen",
    "autoComplete",
    "autoFocus",
    "autoPlay",
    "cellPadding",
    "cellSpacing",
    "charSet",
    "classID",
    "className",
    "colSpan",
    "contentEditable",
    "contextMenu",
    "controlsList",
    "crossOrigin",
    "dateTime",
    "encType",
    "formAction",
    "formEncType",
    "formMethod",
    "formNoValidate",
    "formTarget",
    "frameBorder",
    "hrefLang",
    "httpEquiv",
    "inputMode",
    "keyParams",
    "keyType",
    "marginHeight",
    "marginWidth",
    "maxLength",
    "mediaGroup",
    "minLength",
    "noValidate",
    "radioGroup",
    "readOnly",
    "rowSpan",
    "spellCheck",
    "srcDoc",
    "srcLang",
    "srcSet",
    "tabIndex",
    "useMap"
  ).map(k => k.toLowerCase -> k).toMap

extension (abstractElement: AbstractElement)
  def renderVdom: VdomElement =
    def go(ae: AbstractElement): facade.React.Element =
      def splitStyleString(style: String): List[(String, String)] =
        style
          .split(";")
          .toList
          .map(_.trim)
          .filter(_.nonEmpty)
          .map(_.split(":").toList)
          .collect:
            case List(k, v) =>
              val keyParts = k.trim.split("-")
              val fixedKey = keyParts.head + keyParts.tail.map(_.capitalize).mkString
              (fixedKey, v.trim)

      val vdomBuilder = new VdomBuilder.ToRawReactElement
      ae.attributes
        .foreach: (k, v) =>
          // Reference: https://reactjs.org/docs/dom-elements.html
          k.toLowerCase match
            case "class" =>
              vdomBuilder.addClassName(v)
            case "style" =>
              splitStyleString(v.asInstanceOf[String]).foreach: (k, v) =>
                vdomBuilder.addStyle(k, v)
            case "for"   =>
              vdomBuilder.addAttr("htmlFor", v)
            case _       =>
              attrConversions
                .get(k.replaceAll("-", ""))
                .orElse(k.some)
                .foreach(vdomBuilder.addAttr(_, v))
      ae.children.foreach:
        _.foreach: ch =>
          vdomBuilder.appendChild(js.typeOf(ch) match
            case "string" => ch.asInstanceOf[String]
            case _        => go(ch.asInstanceOf[AbstractElement])
          )
      vdomBuilder.render(ae.tag)

    VdomElement(go(abstractElement))
