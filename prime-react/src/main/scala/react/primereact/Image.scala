// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Image => CImage}

import scalajs.js

case class Image(
  src:          String,
  preview:      js.UndefOr[Boolean] = js.undefined,                        // default: false
  zoomSrc:      js.UndefOr[String] = js.undefined,
  downloadable: js.UndefOr[Boolean] = js.undefined,                        // default: false
  clazz:        js.UndefOr[Css] = js.undefined,
  onShow:       js.UndefOr[Callback] = js.undefined,                       // when preview is shown
  onHide:       js.UndefOr[Callback] = js.undefined,                       // when preview is hidden
  onError:      js.UndefOr[ReactEventFromHtml => Callback] = js.undefined, // when image fails to load
  modifiers:    Seq[TagMod] = Seq.empty
) extends ReactFnProps[Image](Image.component):
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)
  def apply(mods:             TagMod*)     = addModifiers(mods)

object Image:
  private val component = ScalaFnComponent[Image] { props =>
    CImage
      .src(props.src)
      .applyOrNot(props.preview, _.preview(_))
      .applyOrNot(props.zoomSrc, _.zoomSrc(_))
      .applyOrNot(props.downloadable, _.downloadable(_))
      .applyOrNot(props.clazz, (c, p) => c.imageClassName(p.htmlClass))
      .applyOrNot(props.onShow, _.onShow(_))
      .applyOrNot(props.onHide, _.onHide(_))
      .applyOrNot(props.onError, _.onError(_))
  }
