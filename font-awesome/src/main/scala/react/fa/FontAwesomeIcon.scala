// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.fa

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*

import scala.annotation.nowarn
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.*

/**
 * Facade for the react component for FontAwesomeIcons See:
 * https://fontawesome.com/v5.15/how-to-use/on-the-web/using-with/react
 */
final case class FontAwesomeIcon(
  family:                 Family,
  icon:                   js.UndefOr[react.fa.FontAwesomeIcon.Icon],
  clazz:                  js.UndefOr[Css],
  color:                  js.UndefOr[String],
  pulse:                  js.UndefOr[Boolean],
  beat:                   js.UndefOr[Boolean],
  beatFade:               js.UndefOr[Boolean],
  border:                 js.UndefOr[Boolean],
  fade:                   js.UndefOr[Boolean],
  flash:                  js.UndefOr[Boolean],
  fixedWidth:             js.UndefOr[Boolean],
  inverse:                js.UndefOr[Boolean],
  listItem:               js.UndefOr[Boolean],
  flip:                   js.UndefOr[Flip],
  size:                   js.UndefOr[IconSize],
  pull:                   js.UndefOr[Pull],
  rotation:               js.UndefOr[Rotation],
  transform:              js.UndefOr[String | Transform],
  tabIndex:               js.UndefOr[Int],
  spin:                   js.UndefOr[Boolean],
  spinPulse:              js.UndefOr[Boolean],
  spinReverse:            js.UndefOr[Boolean],
  style:                  js.UndefOr[Style],
  title:                  js.UndefOr[String],
  swapOpacity:            js.UndefOr[Boolean],
  override val modifiers: Seq[TagMod]
) extends GenericFnComponentPA[FontAwesomeIcon.Props, FontAwesomeIcon] {
  override protected def cprops    = FontAwesomeIcon.props(this)
  override protected val component = FontAwesomeIcon.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  def addClass(clazz: Css): FontAwesomeIcon = copy(clazz = this.clazz.toOption.orEmpty |+| clazz)

  def withClass(clazz: Css): FontAwesomeIcon = copy(clazz = clazz)

  def withColor(color: String): FontAwesomeIcon = copy(color = color)

  def withPulse(pulse: Boolean = true): FontAwesomeIcon = copy(pulse = pulse)

  def withBeat(beat: Boolean = true): FontAwesomeIcon = copy(beat = beat)

  def withBeatFade(beat: Boolean = true): FontAwesomeIcon = copy(beatFade = beatFade)

  def withBorder(border: Boolean = true): FontAwesomeIcon = copy(border = border)

  def withFade(fade: Boolean = true): FontAwesomeIcon = copy(fade = fade)

  def withFlash(flash: Boolean = true): FontAwesomeIcon = copy(flash = flash)

  def withFixedWidth(fixedWidth: Boolean = true): FontAwesomeIcon = copy(fixedWidth = fixedWidth)

  def withInverse(inverse: Boolean = true): FontAwesomeIcon = copy(inverse = inverse)

  def withListItem(listItem: Boolean = true): FontAwesomeIcon = copy(listItem = listItem)

  def withFlip(flip: Flip): FontAwesomeIcon = copy(flip = flip)

  def withSize(size: IconSize): FontAwesomeIcon = copy(size = size)

  def withPull(pull: Pull): FontAwesomeIcon = copy(pull = pull)

  def withRotation(rotation: Rotation): FontAwesomeIcon = copy(rotation = rotation)

  def withTransform(transform: Transform): FontAwesomeIcon = copy(transform = transform)

  def withTabIndex(tabIndex: Int): FontAwesomeIcon = copy(tabIndex = tabIndex)

  def withSpin(spin: Boolean = true): FontAwesomeIcon = copy(spin = spin)

  def withSpinPulse(spinPulse: Boolean = true): FontAwesomeIcon = copy(spinPulse = spinPulse)

  def withSpinReverse(spinReverse: Boolean = true): FontAwesomeIcon =
    copy(spinReverse = spinReverse)

  def withStyle(style: Style): FontAwesomeIcon = copy(style = style)

  def withTitle(title: String): FontAwesomeIcon = copy(title = title)

  def withSwapOpacity(swapOpacity: Boolean = true): FontAwesomeIcon =
    copy(swapOpacity = swapOpacity)
}

@js.native
@nowarn
trait FAIcon extends js.Object {
  val iconName: String = js.native
  val prefix: String   = js.native
}

@js.native
trait Transform extends js.Object {
  var size: js.UndefOr[Double]
  var x: js.UndefOr[Double]
  var y: js.UndefOr[Double]
  var rotate: js.UndefOr[Double]
  var flipX: js.UndefOr[Boolean]
  var flipY: js.UndefOr[Boolean]
}

object Transform {
  def apply(
    size:   js.UndefOr[Double] = js.undefined,
    x:      js.UndefOr[Double] = js.undefined,
    y:      js.UndefOr[Double] = js.undefined,
    rotate: js.UndefOr[Double] = js.undefined,
    flipX:  js.UndefOr[Boolean] = js.undefined,
    flipY:  js.UndefOr[Boolean] = js.undefined
  ): Transform = {
    val p = (new js.Object).asInstanceOf[Transform]
    size.foreach(v => p.size = v)
    x.foreach(v => p.x = v)
    y.foreach(v => p.y = v)
    rotate.foreach(v => p.rotate = v)
    flipX.foreach(v => p.flipX = v)
    flipY.foreach(v => p.flipY = v)
    p
  }
}

@js.native
@JSImport("@fortawesome/fontawesome-svg-core", "library")
object IconLibrary extends js.Object {
  @nowarn
  def add(arg: FAIcon*): js.Any = js.native
}

object FontAwesomeIcon {
  type Icon            = String | List[String]
  private type RawIcon = String | js.Array[String]

  def apply(
    faIcon:      FAIcon,
    clazz:       js.UndefOr[Css] = js.undefined,
    color:       js.UndefOr[String] = js.undefined,
    pulse:       js.UndefOr[Boolean] = js.undefined,
    beat:        js.UndefOr[Boolean] = js.undefined,
    beatFade:    js.UndefOr[Boolean] = js.undefined,
    border:      js.UndefOr[Boolean] = js.undefined,
    fade:        js.UndefOr[Boolean] = js.undefined,
    flash:       js.UndefOr[Boolean] = js.undefined,
    fixedWidth:  js.UndefOr[Boolean] = js.undefined,
    inverse:     js.UndefOr[Boolean] = js.undefined,
    listItem:    js.UndefOr[Boolean] = js.undefined,
    flip:        js.UndefOr[Flip] = js.undefined,
    size:        js.UndefOr[IconSize] = js.undefined,
    pull:        js.UndefOr[Pull] = js.undefined,
    rotation:    js.UndefOr[Rotation] = js.undefined,
    transform:   js.UndefOr[String | Transform] = js.undefined,
    tabIndex:    js.UndefOr[Int] = js.undefined,
    spin:        js.UndefOr[Boolean] = js.undefined,
    spinPulse:   js.UndefOr[Boolean] = js.undefined,
    spinReverse: js.UndefOr[Boolean] = js.undefined,
    style:       js.UndefOr[Style] = js.undefined,
    title:       js.UndefOr[String] = js.undefined,
    swapOpacity: js.UndefOr[Boolean] = js.undefined,
    modifiers:   Seq[TagMod] = Seq.empty
  ): FontAwesomeIcon = new FontAwesomeIcon(
    Family.fromString(faIcon.prefix),
    faIcon.iconName,
    clazz,
    color,
    pulse,
    beat,
    beatFade,
    border,
    fade,
    flash,
    fixedWidth,
    inverse,
    listItem,
    flip,
    size,
    pull,
    rotation,
    transform,
    tabIndex,
    spin,
    spinPulse,
    spinReverse,
    style,
    title,
    swapOpacity,
    modifiers
  )

  inline def layered(icons: FontAwesomeIcon*): TagMod = {
    val attrs: Seq[TagMod] = (^.cls := "fa-layers fa-fw") +: icons.map(x => x: TagMod)
    <.span(attrs: _*)
  }

  @js.native
  @JSImport("@fortawesome/react-fontawesome", "FontAwesomeIcon")
  private object FontAwesomeIcon extends js.Function1[js.Any, js.Any] {
    def apply(arg: js.Any): js.Any = js.native
  }

  @js.native
  trait Props extends js.Object {
    var icon: js.UndefOr[RawIcon]
    // mask?: IconProp
    var className: js.UndefOr[String]
    var color: js.UndefOr[String]
    var pulse: js.UndefOr[Boolean]
    var beat: js.UndefOr[Boolean]
    var beatFade: js.UndefOr[Boolean]
    var border: js.UndefOr[Boolean]
    var fade: js.UndefOr[Boolean]
    var flash: js.UndefOr[Boolean]
    var fixedWidth: js.UndefOr[Boolean]
    var inverse: js.UndefOr[Boolean]
    var listItem: js.UndefOr[Boolean]
    var flip: js.UndefOr[String]
    var size: js.UndefOr[String]
    var pull: js.UndefOr[String]
    var rotation: js.UndefOr[Int]
    var transform: js.UndefOr[String | Transform]
    // symbol?: FaSymbol
    var spin: js.UndefOr[Boolean];
    var spinPulse: js.UndefOr[Boolean];
    var spinReverse: js.UndefOr[Boolean];
    var style: js.UndefOr[js.Object]
    var tabIndex: js.UndefOr[Int]
    var title: js.UndefOr[String]
    var swapOpacity: js.UndefOr[Boolean];
  }

  protected def props(p: FontAwesomeIcon): Props =
    rawprops(
      p.family,
      p.icon,
      p.clazz,
      p.color,
      p.pulse,
      p.beat,
      p.beatFade,
      p.border,
      p.fade,
      p.flash,
      p.fixedWidth,
      p.inverse,
      p.listItem,
      p.flip,
      p.size,
      p.pull,
      p.rotation,
      p.transform,
      p.tabIndex,
      p.spin,
      p.spinPulse,
      p.spinReverse,
      p.style,
      p.title,
      p.swapOpacity
    )

  protected def rawprops(
    family:      Family,
    icon:        js.UndefOr[Icon],
    clazz:       js.UndefOr[Css],
    color:       js.UndefOr[String],
    pulse:       js.UndefOr[Boolean],
    beat:        js.UndefOr[Boolean],
    beatFade:    js.UndefOr[Boolean],
    border:      js.UndefOr[Boolean],
    fade:        js.UndefOr[Boolean],
    flash:       js.UndefOr[Boolean],
    fixedWidth:  js.UndefOr[Boolean],
    inverse:     js.UndefOr[Boolean],
    listItem:    js.UndefOr[Boolean],
    flip:        js.UndefOr[Flip],
    size:        js.UndefOr[IconSize],
    pull:        js.UndefOr[Pull],
    rotation:    js.UndefOr[Rotation],
    transform:   js.UndefOr[String | Transform],
    tabIndex:    js.UndefOr[Int],
    spin:        js.UndefOr[Boolean],
    spinPulse:   js.UndefOr[Boolean],
    spinReverse: js.UndefOr[Boolean],
    style:       js.UndefOr[Style],
    title:       js.UndefOr[String],
    swapOpacity: js.UndefOr[Boolean]
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    icon.foreach { d =>
      p.icon = (d: Any) match {
        case s: String => List(family.prefix, s).toJSArray
        case s         => s.asInstanceOf[List[String]].toJSArray
      }
    }
    clazz.foreach(v => p.className = v.htmlClass)
    color.foreach(v => p.color = v)
    pulse.foreach(v => p.pulse = v)
    beat.foreach(v => p.beat = v)
    beatFade.foreach(v => p.beatFade = v)
    border.foreach(v => p.border = v)
    fade.foreach(v => p.fade = v)
    flash.foreach(v => p.flash = v)
    fixedWidth.foreach(v => p.fixedWidth = v)
    inverse.foreach(v => p.inverse = v)
    listItem.foreach(v => p.listItem = v)
    flip.foreach(v => p.flip = v.toJs)
    size.foreach(v => p.size = v.toJs)
    pull.foreach(v => p.pull = v.toJs)
    rotation.foreach(v =>
      p.rotation = v match {
        case Rotation.Rotate90  => 90
        case Rotation.Rotate180 => 180
        case Rotation.Rotate270 => 270
      }
    )
    transform.foreach(v => p.transform = v.asInstanceOf[String | Transform])
    tabIndex.foreach(v => p.tabIndex = v)
    spin.foreach(v => p.spin = v)
    spinPulse.foreach(v => p.spinPulse = v)
    spinReverse.foreach(v => p.spinReverse = v)
    style.foreach(v => p.style = v.toJsObject)
    title.foreach(v => p.title = v)
    swapOpacity.foreach(v => p.swapOpacity = v)
    p
  }

  private val component = JsFnComponent[Props, Children.None](FontAwesomeIcon)
}
