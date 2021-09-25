package react.virtuoso

import japgolly.scalajs.react.callback.Callback
import scalajs.js

import scalajs.js.|

trait VirtuosoComponent extends js.Object {
  def scrollBy(location:       ScrollToOptions): Unit
  def scrollIntoView(location: ScrollIntoViewLocation): Unit
  def scrollTo(location:       ScrollToOptions): Unit
  def scrollToIndex(location:  Int | IndexLocationWithAlign): Unit
}

// https://developer.mozilla.org/en-US/docs/Web/API/Window/scroll
// Doesn't seem to be defined in org.scalajs.dom yet.
trait ScrollToOptions extends js.Object {
  var top: js.UndefOr[Int]
  var left: js.UndefOr[Int]
  var behavior: js.UndefOr[ScrollBehavior.Raw]
}

object ScrollToOptions {
  def apply(
    top:      js.UndefOr[Int] = js.undefined,
    left:     js.UndefOr[Int] = js.undefined,
    behavior: js.UndefOr[ScrollBehavior] = js.undefined
  ): ScrollToOptions = {
    val p = (new js.Object).asInstanceOf[ScrollToOptions]
    top.foreach(v => p.top = v)
    left.foreach(v => p.left = v)
    behavior.foreach(v => p.behavior = v.raw)
    p
  }
}

trait ScrollIntoViewLocation extends js.Object {
  var index: Int
  var behavior: js.UndefOr[ScrollBehavior.Raw]
  var done: js.UndefOr[() => Unit]
}

object ScrollIntoViewLocation {
  def apply(
    index:    Int,
    behavior: js.UndefOr[ScrollBehavior] = js.undefined,
    done:     js.UndefOr[Callback] = js.undefined
  ): ScrollIntoViewLocation = {
    val p = (new js.Object).asInstanceOf[ScrollIntoViewLocation]
    p.index = index
    behavior.foreach(v => p.behavior = v.raw)
    done.foreach(v => p.done = () => v.runNow())
    p
  }
}

trait IndexLocationWithAlign extends js.Object {
  var index: Int
  var align: js.UndefOr[Align.Raw]
  var behavior: js.UndefOr[ScrollBehavior.Raw]
  var offset: js.UndefOr[Int]
}

object IndexLocationWithAlign {
  def apply(
    index:    Int,
    align:    js.UndefOr[Align] = js.undefined,
    behavior: js.UndefOr[ScrollBehavior] = js.undefined,
    offset:   js.UndefOr[Int] = js.undefined
  ): IndexLocationWithAlign = {
    val p = (new js.Object).asInstanceOf[IndexLocationWithAlign]
    p.index = index
    align.foreach(v => p.align = v.raw)
    behavior.foreach(v => p.behavior = v.raw)
    offset.foreach(v => p.offset = v)
    p
  }
}
