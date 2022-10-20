// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import japgolly.scalajs.react.callback.*
import lucuma.react.SizePx
import lucuma.react.virtual.facade.VirtualOptionsJS
import lucuma.react.virtual.facade.Virtualizer
import org.scalajs.dom.Element
import reactST.{tanstackVirtualCore => rawVirtual}

import scalajs.js
import scalajs.js.JSConverters.*

case class VirtualOptions[TScrollElement <: Element, TItemElement <: Element](
  /** The total number of items to virtualize. */
  count:                Int,
  /**
   * A function that returns the scrollable element for the virtualizer. It may return undefined if
   * the element is not available yet.
   */
  getScrollElement:     CallbackTo[Option[TScrollElement]],
  /**
   * This function is passed the index of each item and should return the actual size (or estimated
   * size if you will be dynamically measuring items with virtualItem.measureElement) for each item.
   * This measurement should return either the width or height depending on the orientation of your
   * virtualizer.
   */
  estimateSize:         Int => SizePx,
  /** Set to true to enable debug logs */
  debug:                js.UndefOr[Boolean] = js.undefined,
  /**
   * The initial Rect of the scrollElement. This is mostly useful if you need to run the virtualizer
   * in an SSR environment, otherwise the initialRect will be calculated on mount by the
   * observeElementRect implementation.
   */
  initialRect:          js.UndefOr[Rect] = js.undefined,
  /**
   * A callback function that fires when the virtualizer's internal state changes. It's passed the
   * virtualizer instance.
   */
  onChange:             js.UndefOr[Virtualizer[TScrollElement, TItemElement] => Callback] = js.undefined,
  /**
   * The number of items to render above and below the visible area. Increasing this number will
   * increase the amount of time it takes to render the virtualizer, but might decrease the
   * likelihood of seeing slow-rendering blank items at the top and bottom of the virtualizer when
   * scrolling.
   */
  overscan:             js.UndefOr[Int] = js.undefined,
  /**
   * Set this to true if your virtualizer is oriented horizontally.
   */
  horizontal:           js.UndefOr[Boolean] = js.undefined,
  /**
   * The padding to apply to the start of the virtualizer in pixels.
   */
  paddingStart:         js.UndefOr[SizePx] = js.undefined,
  /**
   * The padding to apply to the end of the virtualizer in pixels.
   */
  paddingEnd:           js.UndefOr[SizePx] = js.undefined,
  /**
   * The padding to apply to the start of the virtualizer in pixels when scrolling to an element.
   */
  scrollPaddingStart:   js.UndefOr[SizePx] = js.undefined,
  /**
   * The padding to apply to the end of the virtualizer in pixels when scrolling to an element.
   */
  scrollPaddingEnd:     js.UndefOr[SizePx] = js.undefined,
  /**
   * The initial offset to apply to the virtualizer. This is usually only useful if you are
   * rendering the virtualizer in a SSR environment.
   */
  initialOffset:        js.UndefOr[SizePx] = js.undefined,
  /**
   * This function is passed the index of each item and should return a unique key for that item.
   * The default functionality of this function is to return the index of the item, but you should
   * override this when possible to return a unique identifier for each item across the entire set.
   */
  getItemKey:           js.UndefOr[Int => rawVirtual.mod.Key] = js.undefined,
  /**
   * This function receives visible range indexes and should return array of indexes to render. This
   * is useful if you need to add or remove items from the virtualizer manually regardless of the
   * visible range, eg. rendering sticky items, headers, footers, etc. The default range extractor
   * implementation will return the visible range indexes and is exported as defaultRangeExtractor.
   */
  rangeExtractor:       js.UndefOr[rawVirtual.mod.Range => List[Int]] = js.undefined,
  /**
   * Enables/disables smooth scrolling. Smooth scrolling is enabled by default, but may result in
   * inaccurate landing positions when dynamically measuring elements (a common use case and
   * configuration). If you plan to use smooth scrolling, it's suggested that you either estimate
   * the size of your elements as close to their maximums as possible, or simply turn off dynamic
   * measuring of elements.
   */
  enableSmoothScroll:   js.UndefOr[Boolean] = js.undefined,
  /**
   * An optional function that if provided should implement the scrolling behavior for your
   * scrollElement. It will be called with the offset to scroll to, a boolean indicating if the
   * scrolling is allowed to be smoothed, and the virtualizer instance. Built-in scroll
   * implementations are exported as elementScroll and windowScroll which are automatically
   * configured for you by your framework adapter's exported functions like useVirtualizer or
   * createWindowVirtualizer.
   *
   * Attempting to use smoothScroll with dynamically measured elements will not work.
   */
  scrollToFn:           js.UndefOr[(SizePx, Boolean, Virtualizer[TScrollElement, TItemElement]) => Callback] =
    js.undefined,
  /**
   * An optional function that if provided is called when the scrollElement changes and should
   * implement the initial measurement and continuous monitoring of the scrollElement's Rect (an
   * object with width and height). It's called with the instance (which also gives you access to
   * the scrollElement via instance.scrollElement. Built-in implementations are exported as
   * observeElementRect and observeWindowRect which are automatically configured for you by your
   * framework adapter's exported functions like useVirtualizer or createWindowVirtualizer.
   */
  observeElementRect:   js.UndefOr[
    ((Virtualizer[TScrollElement, TItemElement], Rect => Callback) => Callback)
  ] = js.undefined,
  /**
   * An optional function that if provided is called when the scrollElement changes and should
   * implement the initial measurement and continuous monitoring of the scrollElement's scroll
   * offset (a number). It's called with the instance (which also gives you access to the
   * scrollElement via instance.scrollElement. Built-in implementations are exported as
   * observeElementOffset and observeWindowOffset which are automatically configured for you by your
   * framework adapter's exported functions like useVirtualizer or createWindowVirtualizer.
   */
  observeElementOffset: js.UndefOr[
    ((Virtualizer[TScrollElement, TItemElement], Int => Callback) => Callback)
  ] = js.undefined,
  /**
   * This optional function is called when the virtualizer needs to dynamically measure the size
   * (width or height) of an item when virtualItem.measureElement is called. It's passed the element
   * given when you call virtualItem.measureElement(TItemElement) and the virtualizer instance. It
   * should return the size of the element as a number.
   *
   * You can use instance.options.horizontal to determine if the width or height of the item should
   * be measured.
   */
  measureElement:       js.UndefOr[
    (TItemElement, Virtualizer[TScrollElement, TItemElement]) => CallbackTo[Int]
  ] = js.undefined
):
  def toJs: VirtualOptionsJS[TScrollElement, TItemElement] = {
    val p: VirtualOptionsJS[TScrollElement, TItemElement] =
      new js.Object().asInstanceOf[VirtualOptionsJS[TScrollElement, TItemElement]]
    p.count = count
    p.getScrollElement = () => getScrollElement.map(_.orUndefined).runNow()
    p.estimateSize = estimateSize.andThen(_.value)
    debug.foreach(v => p.debug = v)
    initialRect.foreach(v => p.initialRect = v.toJs)
    onChange.foreach(v => p.onChange = v.andThen(_.runNow()))
    overscan.foreach(v => p.overscan = v)
    horizontal.foreach(v => p.horizontal = v)
    paddingStart.foreach(v => p.paddingStart = v.value)
    paddingEnd.foreach(v => p.paddingEnd = v.value)
    scrollPaddingStart.foreach(v => p.scrollPaddingStart = v.value)
    scrollPaddingEnd.foreach(v => p.scrollPaddingEnd = v.value)
    initialOffset.foreach(v => p.initialOffset = v.value)
    getItemKey.foreach(v => p.getItemKey = v)
    rangeExtractor.foreach(v => p.rangeExtractor = v.andThen(_.toJSArray))
    enableSmoothScroll.foreach(v => p.enableSmoothScroll = v)
    scrollToFn.foreach(v =>
      (offset: Int, canSmooth: Boolean, instance: Virtualizer[TScrollElement, TItemElement]) =>
        p.scrollToFn = v(SizePx(offset), canSmooth, instance).runNow()
    )
    observeElementRect.foreach(v =>
      (instance: Virtualizer[TScrollElement, TItemElement], cb: Rect => Unit) =>
        p.observeElementRect = v(instance, rect => Callback(cb(rect))).runNow()
    )
    observeElementOffset.foreach(v =>
      (instance: Virtualizer[TScrollElement, TItemElement], cb: Int => Unit) =>
        p.observeElementOffset = v(instance, rect => Callback(cb(rect))).runNow()
    )
    measureElement.foreach(v =>
      p.measureElement = (el: TItemElement, instance: Virtualizer[TScrollElement, TItemElement]) =>
        v(el, instance).runNow()
    )
    p
  }
