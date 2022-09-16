// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import japgolly.scalajs.react.callback.CallbackTo
import lucuma.react.virtual.facade.VirtualOptionsJS
import org.scalajs.dom.Element
import reactST.tanstackVirtualCore.mod.Key
import reactST.tanstackVirtualCore.mod.Range
import reactST.tanstackVirtualCore.mod.Rect
import reactST.tanstackVirtualCore.mod.Virtualizer

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
  estimateSize:         Int => Int,
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
  onChange:             js.UndefOr[Virtualizer[TScrollElement, TItemElement] => Unit] = js.undefined,
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
  paddingStart:         js.UndefOr[Int] = js.undefined,
  /**
   * The padding to apply to the end of the virtualizer in pixels.
   */
  paddingEnd:           js.UndefOr[Int] = js.undefined,
  /**
   * The padding to apply to the start of the virtualizer in pixels when scrolling to an element.
   */
  scrollPaddingStart:   js.UndefOr[Int] = js.undefined,
  /**
   * The padding to apply to the end of the virtualizer in pixels when scrolling to an element.
   */
  scrollPaddingEnd:     js.UndefOr[Int] = js.undefined,
  /**
   * The initial offset to apply to the virtualizer. This is usually only useful if you are
   * rendering the virtualizer in a SSR environment.
   */
  initialOffset:        js.UndefOr[Int] = js.undefined,
  /**
   * This function is passed the index of each item and should return a unique key for that item.
   * The default functionality of this function is to return the index of the item, but you should
   * override this when possible to return a unique identifier for each item across the entire set.
   */
  getItemKey:           js.UndefOr[Int => Key] = js.undefined,
  /**
   * This function receives visible range indexes and should return array of indexes to render. This
   * is useful if you need to add or remove items from the virtualizer manually regardless of the
   * visible range, eg. rendering sticky items, headers, footers, etc. The default range extractor
   * implementation will return the visible range indexes and is exported as defaultRangeExtractor.
   */
  rangeExtractor:       js.UndefOr[Range => List[Int]] = js.undefined,
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
  scrollToFn:           js.UndefOr[(Int, Boolean, Virtualizer[TScrollElement, TItemElement]) => Unit] =
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
    ((Virtualizer[TScrollElement, TItemElement], Rect) => Unit) | (() => Unit)
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
    ((Virtualizer[TScrollElement, TItemElement], Int) => Unit) | (() => Unit)
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
    (TItemElement, Virtualizer[TScrollElement, TItemElement]) => Int
  ] = js.undefined
):
  def toJS: VirtualOptionsJS[TScrollElement, TItemElement] = {
    val p: VirtualOptionsJS[TScrollElement, TItemElement] =
      new js.Object().asInstanceOf[VirtualOptionsJS[TScrollElement, TItemElement]]
    p.count = count
    p.getScrollElement = () => getScrollElement.map(_.orUndefined).runNow()
    p.estimateSize = estimateSize
    p.debug.foreach(v => p.debug = v)
    p.initialRect.foreach(v => p.initialRect = v)
    p.onChange.foreach(v => p.onChange = v)
    p.overscan.foreach(v => p.overscan = v)
    p.horizontal.foreach(v => p.horizontal = v)
    p.paddingStart.foreach(v => p.paddingStart = v)
    p.paddingEnd.foreach(v => p.paddingEnd = v)
    p.scrollPaddingStart.foreach(v => p.scrollPaddingStart = v)
    p.scrollPaddingEnd.foreach(v => p.scrollPaddingEnd = v)
    p.initialOffset.foreach(v => p.initialOffset = v)
    p.getItemKey.foreach(v => p.getItemKey = v)
    p.rangeExtractor.foreach(v => p.rangeExtractor = v)
    p.enableSmoothScroll.foreach(v => p.enableSmoothScroll = v)
    p.scrollToFn.foreach(v => p.scrollToFn = v)
    p.observeElementRect.foreach(v => p.observeElementRect = v)
    p.observeElementOffset.foreach(v => p.observeElementOffset = v)
    p.measureElement.foreach(v => p.measureElement = v)
    p
  }
