// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.ReactEvent
import lucuma.typed.tanstackTableCore as raw

case class Header[T, A, TM, CM] private[table] (
  private[table] val toJs: raw.buildLibTypesMod.Header[T, A]
):
  /**
   * The col-span for the header.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#colspan)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val colSpan: Int = toJs.colSpan.toInt

  /**
   * The header's associated column object.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#column)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val column: Column[T, A, TM, CM] = Column(toJs.column)

  /**
   * The depth of the header, zero-indexed based.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#depth)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val depth: Int = toJs.depth.toInt

  /**
   * Returns the rendering context (or props) for column-based components like headers, footers and
   * filters.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#getcontext)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  def getContext(): HeaderContext[T, A, TM, CM] = HeaderContext(toJs.getContext())

  /**
   * Returns the leaf headers hierarchically nested under this header.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#getleafheaders)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  def getLeafHeaders(): List[Header[T, Any, TM, Any]] = toJs.getLeafHeaders().toList.map(Header(_))

  /**
   * The header's associated header group object.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#headergroup)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val headerGroup: HeaderGroup[T, TM] = HeaderGroup(toJs.headerGroup)

  /**
   * The unique identifier for the header.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#id)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val id: HeaderId = HeaderId(toJs.id)

  /**
   * The index for the header within the header group.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#index)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val index: Int = toJs.index.toInt

  /**
   * A boolean denoting if the header is a placeholder header.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#isplaceholder)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val isPlaceholder: Boolean = toJs.isPlaceholder

  /**
   * If the header is a placeholder header, this will be a unique header ID that does not conflict
   * with any other headers across the table.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#placeholderid)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val placeholderId: Option[PlaceholderId] = toJs.placeholderId.toOption.map(PlaceholderId(_))

  /**
   * The row-span for the header.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#rowspan)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val rowSpan: Int = toJs.rowSpan.toInt

  /**
   * The header's hierarchical sub/child headers. Will be empty if the header's associated column is
   * a leaf-column.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/core/header#subheaders)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/headers)
   */
  lazy val subHeaders: List[Header[T, Any, TM, Any]] =
    toJs.subHeaders.toList.map(Header(_).asInstanceOf[Header[T, Any, TM, Any]])

  // ColumnSizingHeader

  /**
   * Returns an event handler function that can be used to resize the header. It can be used as an:
   *   - `onMouseDown` handler
   *   - `onTouchStart` handler
   *
   * The dragging and release events are automatically handled for you.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/features/column-sizing#getresizehandler)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/column-sizing)
   */
  def getResizeHandler(): ReactEvent => Callback = e => Callback(toJs.getResizeHandler()(e))

  /**
   * Returns the current size of the header.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/features/column-sizing#getsize)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/column-sizing)
   */
  def getSize(): Double = toJs.getSize()

  /**
   * Returns the offset measurement along the row-axis (usually the x-axis for standard tables) for
   * the header. This is effectively a sum of the offset measurements of all preceding headers.
   * @link
   *   [API Docs](https://tanstack.com/table/v8/docs/api/features/column-sizing#getstart)
   * @link
   *   [Guide](https://tanstack.com/table/v8/docs/guide/column-sizing)
   */
  def getStart(): Double                                = toJs.getStart()
  def getStart(position: ColumnPinningPosition): Double = toJs.getStart(position.toJs)
