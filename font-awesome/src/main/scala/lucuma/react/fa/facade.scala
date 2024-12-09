// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.fa

import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.Css
import org.scalajs.dom.HTMLCollection
import org.scalajs.dom.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.*

import scalajs.js.JSConverters.*

type Attributes = js.Dictionary[Double | String]

type Styles = js.Dictionary[String]

type FaSymbol = String | Boolean

type IconPrefix = String

type IconName = String

@js.native
trait FAIcon extends js.Object:
  val iconName: String = js.native
  val prefix: String   = js.native

@js.native
trait Library extends js.Object:
  def add(arg: FAIcon*): js.Any = js.native

@js.native
trait Transform extends js.Object:
  var size: js.UndefOr[Double]
  var x: js.UndefOr[Double]
  var y: js.UndefOr[Double]
  var rotate: js.UndefOr[Double]
  var flipX: js.UndefOr[Boolean]
  var flipY: js.UndefOr[Boolean]

object Transform:
  def apply(
    size:   js.UndefOr[Double] = js.undefined,
    x:      js.UndefOr[Double] = js.undefined,
    y:      js.UndefOr[Double] = js.undefined,
    rotate: js.UndefOr[Double] = js.undefined,
    flipX:  js.UndefOr[Boolean] = js.undefined,
    flipY:  js.UndefOr[Boolean] = js.undefined
  ): Transform =
    val p = (new js.Object).asInstanceOf[Transform]
    size.foreach(v => p.size = v)
    x.foreach(v => p.x = v)
    y.foreach(v => p.y = v)
    rotate.foreach(v => p.rotate = v)
    flipX.foreach(v => p.flipX = v)
    flipY.foreach(v => p.flipY = v)
    p

@js.native
trait IconLookup extends js.Object:
  var prefix: IconPrefix
  var iconName: IconName

object IconLookup:
  def apply(prefix: IconPrefix, iconName: IconName): IconLookup =
    val p = (new js.Object).asInstanceOf[IconLookup]
    p.prefix = prefix
    p.iconName = iconName
    p

@js.native
trait Params extends js.Object:
  var title: js.UndefOr[String]
  var titleId: js.UndefOr[String]
  var classes: js.UndefOr[String | js.Array[String]]
  var attributes: js.UndefOr[Attributes]
  var styles: js.UndefOr[Styles]

@js.native
trait IconParams extends Params:
  var mask: js.UndefOr[IconLookup]
  var maskId: js.UndefOr[String]
  var symbol: js.UndefOr[FaSymbol]
  var transform: js.UndefOr[Transform]

object IconParams:
  def apply(
    classes:   Css = Css.Empty,
    mask:      js.UndefOr[IconLookup] = js.undefined,
    maskId:    js.UndefOr[String] = js.undefined,
    symbol:    js.UndefOr[FaSymbol] = js.undefined,
    title:     js.UndefOr[String] = js.undefined,
    titleId:   js.UndefOr[String] = js.undefined,
    transform: js.UndefOr[Transform] = js.undefined,
    modifiers: List[TagMod] = List.empty
  ): IconParams =
    val p           = (new js.Object).asInstanceOf[IconParams]
    p.classes = classes.value.toJSArray
    mask.foreach(v => p.mask = v)
    maskId.foreach(v => p.maskId = v)
    symbol.foreach(v => p.symbol = v)
    title.foreach(v => p.title = v)
    titleId.foreach(v => p.titleId = v)
    transform.foreach(v => p.transform = v)
    val modsBuilder = modifiers.toTagMod.toJs
    p.attributes = modsBuilder.props.asInstanceOf[Attributes]
    p.styles = modsBuilder.styles.asInstanceOf[Styles]
    p

@js.native
trait CounterParams extends Params

object CounterParams:
  def apply(
    classes:   Css = Css.Empty,
    title:     js.UndefOr[String] = js.undefined,
    titleId:   js.UndefOr[String] = js.undefined,
    modifiers: List[TagMod] = List.empty
  ): CounterParams =
    val p           = (new js.Object).asInstanceOf[CounterParams]
    p.classes = classes.value.toJSArray
    title.foreach(v => p.title = v)
    titleId.foreach(v => p.titleId = v)
    val modsBuilder = modifiers.toTagMod.toJs
    p.attributes = modsBuilder.props.asInstanceOf[Attributes]
    p.styles = modsBuilder.styles.asInstanceOf[Styles]
    p

@js.native
trait TextParams extends Params:
  var transform: js.UndefOr[Transform]

object TextParams:
  def apply(
    classes:   Css = Css.Empty,
    title:     js.UndefOr[String] = js.undefined,
    titleId:   js.UndefOr[String] = js.undefined,
    transform: js.UndefOr[Transform] = js.undefined,
    modifiers: List[TagMod] = List.empty
  ): TextParams =
    val p           = (new js.Object).asInstanceOf[TextParams]
    p.classes = classes.value.toJSArray
    title.foreach(v => p.title = v)
    titleId.foreach(v => p.titleId = v)
    transform.foreach(v => p.transform = v)
    val modsBuilder = modifiers.toTagMod.toJs
    p.attributes = modsBuilder.props.asInstanceOf[Attributes]
    p.styles = modsBuilder.styles.asInstanceOf[Styles]
    p

@js.native
trait AbstractElement extends js.Object:
  val tag: String
  val attributes: js.Dictionary[js.Any]
  val children: js.UndefOr[js.Array[String | AbstractElement]]

@js.native
trait FontawesomeObject extends js.Object:
  @JSName("abstract")
  val abstractDef: js.Array[AbstractElement]
  val html: js.Array[String]
  val node: HTMLCollection[HTMLElement]

@js.native
trait IconRaw extends FontawesomeObject

@js.native
trait Text extends FontawesomeObject

@js.native
trait Counter extends FontawesomeObject

@js.native
@JSImport("@fortawesome/fontawesome-svg-core", JSImport.Namespace)
object FontAwesome extends js.Object {
  def library: Library                                                                        = js.native
  def text(content: String, params: js.UndefOr[TextParams] = js.undefined): Text              = js.native
  def counter(
    content: String | Float | Double | Int | Short | Byte,
    params:  js.UndefOr[CounterParams]
  ): Counter = js.native
  def icon(icon: FAIcon | IconLookup, params: js.UndefOr[IconParams] = js.undefined): IconRaw =
    js.native

}
