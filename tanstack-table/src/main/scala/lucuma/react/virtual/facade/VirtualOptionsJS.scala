// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual.facade

import lucuma.typed.tanstackVirtualCore.mod.Key
import lucuma.typed.tanstackVirtualCore.mod.Range
import lucuma.typed.tanstackVirtualCore.mod.Rect
import org.scalajs.dom.Element

import scalajs.js

trait VirtualOptionsJS[TScrollElement <: Element, TItemElement <: Element] extends js.Object:
  /** The total number of items to virtualize. */
  var count: Int

  /**
   * A function that returns the scrollable element for the virtualizer. It may return undefined if
   * the element is not available yet.
   */
  var getScrollElement: js.Function0[js.UndefOr[TScrollElement]]

  /**
   * This function is passed the index of each item and should return the actual size (or estimated
   * size if you will be dynamically measuring items with virtualItem.measureElement) for each item.
   * This measurement should return either the width or height depending on the orientation of your
   * virtualizer.
   */
  var estimateSize: js.Function1[Int, Int]

  /** Set to true to enable debug logs */
  var debug: js.UndefOr[Boolean] = js.undefined

  /**
   * The initial Rect of the scrollElement. This is mostly useful if you need to run the virtualizer
   * in an SSR environment, otherwise the initialRect will be calculated on mount by the
   * observeElementRect implementation.
   */
  var initialRect: js.UndefOr[Rect] = js.undefined

  /**
   * A callback function that fires when the virtualizer's internal state changes. It's passed the
   * virtualizer instance.
   */
  var onChange: js.UndefOr[js.Function1[Virtualizer[TScrollElement, TItemElement], Unit]] =
    js.undefined

  /**
   * The number of items to render above and below the visible area. Increasing this number will
   * increase the amount of time it takes to render the virtualizer, but might decrease the
   * likelihood of seeing slow-rendering blank items at the top and bottom of the virtualizer when
   * scrolling.
   */
  var overscan: js.UndefOr[Int] = js.undefined

  /**
   * Set this to true if your virtualizer is oriented horizontally.
   */
  var horizontal: js.UndefOr[Boolean] = js.undefined

  /**
   * The padding to apply to the start of the virtualizer in pixels.
   */
  var paddingStart: js.UndefOr[Int] = js.undefined

  /**
   * The padding to apply to the end of the virtualizer in pixels.
   */
  var paddingEnd: js.UndefOr[Int] = js.undefined

  /**
   * The padding to apply to the start of the virtualizer in pixels when scrolling to an element.
   */
  var scrollPaddingStart: js.UndefOr[Int] = js.undefined

  /**
   * The padding to apply to the end of the virtualizer in pixels when scrolling to an element.
   */
  var scrollPaddingEnd: js.UndefOr[Int] = js.undefined

  /**
   * The initial offset to apply to the virtualizer. This is usually only useful if you are
   * rendering the virtualizer in a SSR environment.
   */
  var initialOffset: js.UndefOr[Int] = js.undefined

  /**
   * This function is passed the index of each item and should return a unique key for that item.
   * The default functionality of this function is to return the index of the item, but you should
   * override this when possible to return a unique identifier for each item across the entire set.
   */
  var getItemKey: js.UndefOr[js.Function1[Int, Key]] = js.undefined

  /**
   * This function receives visible range indexes and should return array of indexes to render. This
   * is useful if you need to add or remove items from the virtualizer manually regardless of the
   * visible range, eg. rendering sticky items, headers, footers, etc. The default range extractor
   * implementation will return the visible range indexes and is exported as defaultRangeExtractor.
   */
  var rangeExtractor: js.UndefOr[js.Function1[Range, js.Array[Int]]] = js.undefined

  /**
   * Enables/disables smooth scrolling. Smooth scrolling is enabled by default, but may result in
   * inaccurate landing positions when dynamically measuring elements (a common use case and
   * configuration). If you plan to use smooth scrolling, it's suggested that you either estimate
   * the size of your elements as close to their maximums as possible, or simply turn off dynamic
   * measuring of elements.
   */
  var enableSmoothScroll: js.UndefOr[Boolean] = js.undefined

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
  var scrollToFn
    : js.UndefOr[js.Function3[Int, Boolean, Virtualizer[TScrollElement, TItemElement], Unit]] =
    js.undefined

  /**
   * An optional function that if provided is called when the scrollElement changes and should
   * implement the initial measurement and continuous monitoring of the scrollElement's Rect (an
   * object with width and height). It's called with the instance (which also gives you access to
   * the scrollElement via instance.scrollElement. Built-in implementations are exported as
   * observeElementRect and observeWindowRect which are automatically configured for you by your
   * framework adapter's exported functions like useVirtualizer or createWindowVirtualizer.
   */
  var observeElementRect: js.UndefOr[
    js.Function2[Virtualizer[TScrollElement, TItemElement], js.Function1[Rect, Unit], Unit] |
      js.Function0[Unit]
  ] =
    js.undefined

  /**
   * An optional function that if provided is called when the scrollElement changes and should
   * implement the initial measurement and continuous monitoring of the scrollElement's scroll
   * offset (a number). It's called with the instance (which also gives you access to the
   * scrollElement via instance.scrollElement. Built-in implementations are exported as
   * observeElementOffset and observeWindowOffset which are automatically configured for you by your
   * framework adapter's exported functions like useVirtualizer or createWindowVirtualizer.
   */
  var observeElementOffset: js.UndefOr[
    js.Function2[Virtualizer[TScrollElement, TItemElement], js.Function1[Int, Unit], Unit] |
      js.Function0[Unit]
  ] =
    js.undefined

  /**
   * This optional function is called when the virtualizer needs to dynamically measure the size
   * (width or height) of an item when virtualItem.measureElement is called. It's passed the element
   * given when you call virtualItem.measureElement(TItemElement) and the virtualizer instance. It
   * should return the size of the element as a number.
   *
   * You can use instance.options.horizontal to determine if the width or height of the item should
   * be measured.
   */
  var measureElement
    : js.UndefOr[js.Function2[TItemElement, Virtualizer[TScrollElement, TItemElement], Int]] =
    js.undefined

  /**
   * With this option, you can specify where the scroll offset should originate. Typically, this
   * value represents the space between the beginning of the scrolling element and the start of the
   * list. This is especially useful in common scenarios such as when you have a header preceding a
   * window virtualizer or when multiple virtualizers are utilized within a single scrolling
   * element. If you are using absolute positioning of elements, you should take into account the
   * scrollMargin in your CSS transform:
   *
   * transform: `translateY(${ virtualRow.start - rowVirtualizer.options.scrollMargin }px)`
   *
   * To dynamically measure value for scrollMargin you can use getBoundingClientRect() or
   * ResizeObserver. This is helpful in scenarios when items above your virtual list might change
   * their height.
   */
  var scrollMargin: js.UndefOr[Double] = js.undefined

  /**
   * This option allows you to set the spacing between items in the virtualized list. It's
   * particularly useful for maintaining a consistent visual separation between items without having
   * to manually adjust each item's margin or padding. The value is specified in pixels.
   */
  var gap: js.UndefOr[Double] = js.undefined

  /**
   * The number of lanes the list is divided into (aka columns for vertical lists and rows for
   * horizontal lists).
   */
  var lanes: js.UndefOr[Int] = js.undefined

  /**
   * This option allows you to specify the duration to wait after the last scroll event before
   * resetting the isScrolling instance property. The default value is 150 milliseconds.
   *
   * The implementation of this option is driven by the need for a reliable mechanism to handle
   * scrolling behavior across different browsers. Until all browsers uniformly support the
   * scrollEnd event.
   */
  var isScrollingResetDelay: js.UndefOr[Int] = js.undefined

  /**
   * This option allows you to switch to use debounced fallback to reset the isScrolling instance
   * property after isScrollingResetDelay milliseconds. The default value is true.
   *
   * The implementation of this option is driven by the need for a reliable mechanism to handle
   * scrolling behavior across different browsers. Until all browsers uniformly support the
   * scrollEnd event.
   */
  var useScrollendEvent: js.UndefOr[Boolean] = js.undefined

  /** Whether to invert horizontal scrolling to support right-to-left language locales. */
  var isRtl: js.UndefOr[Boolean] = js.undefined
