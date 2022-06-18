// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui.raw._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps

import react.semanticui.elements.label.Label
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Dropdown(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  additionLabel:          MyUndefOr[Dropdown.AdditionLabel] = MyUndefOr.undefined,
  additionPosition:       MyUndefOr[AdditionPosition] = MyUndefOr.undefined,
  allowAdditions:         MyUndefOr[Boolean] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  button:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  clearable:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnBlur:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnEscape:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnChange:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  compact:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  deburr:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultOpen:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultSearchQuery:     MyUndefOr[String] = MyUndefOr.undefined,
  defaultSelectedLabel:   MyUndefOr[Double | String] = MyUndefOr.undefined,
  defaultUpward:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultValue:           MyUndefOr[Dropdown.Value] = MyUndefOr.undefined,
  direction:              MyUndefOr[Direction] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  floating:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  inline:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  item:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  labeled:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  lazyLoad:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  minCharacters:          MyUndefOr[Double] = MyUndefOr.undefined,
  multiple:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  noResultsMessage:       MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  onAddItem:              MyUndefOr[Dropdown.OnAddItem] = MyUndefOr.undefined,
  onBlurE:                MyUndefOr[Dropdown.OnBlur] = MyUndefOr.undefined,
  onBlur:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[Dropdown.OnClick] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[Dropdown.OnChangeE] = MyUndefOr.undefined,
  onChange:               MyUndefOr[Dropdown.OnChange] = MyUndefOr.undefined,
  onCloseE:               MyUndefOr[Dropdown.OnClose] = MyUndefOr.undefined,
  onClose:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onFocusE:               MyUndefOr[Dropdown.OnFocus] = MyUndefOr.undefined,
  onFocus:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onLabelClickE:          MyUndefOr[Dropdown.OnLabelClick] = MyUndefOr.undefined,
  onLabelClick:           MyUndefOr[Callback] = MyUndefOr.undefined,
  onMouseDownE:           MyUndefOr[Dropdown.OnMouseDown] = MyUndefOr.undefined,
  onMouseDown:            MyUndefOr[Callback] = MyUndefOr.undefined,
  onOpenE:                MyUndefOr[Dropdown.OnOpen] = MyUndefOr.undefined,
  onOpen:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onSearchChangeE:        MyUndefOr[Dropdown.OnSearchChangeE] = MyUndefOr.undefined,
  onSearchChange:         MyUndefOr[Dropdown.OnSearchChange] = MyUndefOr.undefined,
  open:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  openOnFocus:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  options:                MyUndefOr[Seq[DropdownItem]] = MyUndefOr.undefined,
  placeholder:            MyUndefOr[String] = MyUndefOr.undefined,
  pointing:               MyUndefOr[Pointing] = MyUndefOr.undefined,
  renderLabel:            MyUndefOr[Dropdown.RenderLabel] = MyUndefOr.undefined,
  scrolling:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  search:                 MyUndefOr[Boolean | Dropdown.SearchFunction] = MyUndefOr.undefined,
  searchInput:            MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  searchQuery:            MyUndefOr[String] = MyUndefOr.undefined,
  selectOnBlur:           MyUndefOr[Boolean] = MyUndefOr.undefined,
  selectOnNavigation:     MyUndefOr[Boolean] = MyUndefOr.undefined,
  selectedLabel:          MyUndefOr[Double | String] = MyUndefOr.undefined,
  selection:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  simple:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[String | Double] = MyUndefOr.undefined,
  text:                   MyUndefOr[String] = MyUndefOr.undefined,
  trigger:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  value:                  MyUndefOr[Dropdown.Value] = MyUndefOr.undefined,
  upward:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  wrapSelection:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Dropdown.DropdownProps, Dropdown] {
  override protected def cprops                     = Dropdown.props(this)
  override protected val component                  = Dropdown.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Dropdown {
  type Value             =
    String | Double | Boolean | js.Array[Double] | js.Array[String] | js.Array[Boolean]
  type RawAdditionLabel  = Double | String | SemanticShorthandContent
  type AdditionLabel     = Double | String | VdomNode
  type RawSearchFunction =
    js.Function2[js.Array[DropdownItem.DropdownItemProps], String, js.Array[
      DropdownItem.DropdownItemProps
    ]]
  type SearchFunction    = (
    List[DropdownItem.DropdownItemProps],
    String
  ) => CallbackTo[List[DropdownItem.DropdownItemProps]]
  type RawRenderLabel    =
    js.Function3[DropdownItem.DropdownItemProps, Int, Label.LabelProps, Label.LabelProps]
  type RenderLabel       =
    (DropdownItem.DropdownItemProps, Int, Label.LabelProps) => CallbackTo[Label.LabelProps]
  type RawOnAddItem      = js.Function2[ReactKeyboardEvent, DropdownProps, Unit]
  type OnAddItem         = (ReactKeyboardEvent, DropdownProps) => Callback
  type RawOnBlur         = RawOnAddItem
  type OnBlur            = OnAddItem
  type RawOnChange       = js.Function2[ReactEvent, DropdownProps, Unit]
  type OnChangeE         = (ReactEvent, DropdownProps) => Callback
  type OnChange          = DropdownProps => Callback
  type RawOnClick        = js.Function2[ReactMouseEvent, DropdownProps, Unit]
  type OnClick           = (ReactMouseEvent, DropdownProps) => Callback
  type RawOnClose        = RawOnChange
  type OnClose           = OnChangeE
  type RawOnFocus        = RawOnChange
  type OnFocus           = OnChangeE
  type RawOnOpen         = RawOnChange
  type OnOpen            = OnChangeE
  type RawOnLabelClick   = js.Function2[ReactMouseEvent, Label.LabelProps, Unit]
  type OnLabelClick      = (ReactMouseEvent, Label.LabelProps) => Callback
  type RawOnMouseDown    = RawOnLabelClick
  type OnMouseDown       = OnLabelClick
  type RawOnSearchChange = js.Function2[ReactEvent, DropdownOnSearchChangeData, Unit]
  type OnSearchChangeE   = (ReactEvent, DropdownOnSearchChangeData) => Callback
  type OnSearchChange    = DropdownOnSearchChangeData => Callback

  @js.native
  @JSImport("semantic-ui-react", "Dropdown")
  object RawComponent extends js.Object

  @js.native
  @JSImport("semantic-ui-react", "DropdownOnSearchChangeData")
  class DropdownOnSearchChangeData extends js.Object {
    val searchQuery: String = js.native
  }

  @js.native
  trait DropdownProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Label prefixed to an option added by a user. */
    var additionLabel: MyUndefOr[Double | String | SemanticShorthandContent] = js.native

    /** Position of the `Add: ...` option in the dropdown list ('top' or 'bottom'). */
    var additionPosition: MyUndefOr[String]

    /**
     * Allow user additions to the list of options (boolean). Requires the use of `selection`,
     * `options` and `search`.
     */
    var allowAdditions: MyUndefOr[Boolean] = js.native

    /** A Dropdown can reduce its complexity. */
    var basic: MyUndefOr[Boolean] = js.native

    /** Format the Dropdown to appear as a button. */
    var button: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Using the clearable setting will let users remove their selection from a dropdown. */
    var clearable: MyUndefOr[Boolean] = js.native

    /** Whether or not the menu should close when the dropdown is blurred. */
    var closeOnBlur: MyUndefOr[Boolean] = js.native

    /** Whether or not the dropdown should close when the escape key is pressed. */
    var closeOnEscape: MyUndefOr[Boolean] = js.native

    /**
     * Whether or not the menu should close when a value is selected from the dropdown. By default,
     * multiple selection dropdowns will remain open on change, while single selection dropdowns
     * will close on change.
     */
    var closeOnChange: MyUndefOr[Boolean] = js.native

    /** A compact dropdown has no minimum width. */
    var compact: MyUndefOr[Boolean] = js.native

    /** Whether or not the dropdown should strip diacritics in options and input search */
    var deburr: MyUndefOr[Boolean] = js.native

    /** Initial value of open. */
    var defaultOpen: MyUndefOr[Boolean] = js.native

    /** Initial value of searchQuery. */
    var defaultSearchQuery: MyUndefOr[String] = js.native

    /** Currently selected label in multi-select. */
    var defaultSelectedLabel: MyUndefOr[Double | String] = js.native

    /** Initial value of upward. */
    var defaultUpward: MyUndefOr[Boolean] = js.native

    /** Initial value or value array if multiple. */
    var defaultValue: MyUndefOr[Value] = js.native

    /** A dropdown menu can open to the left or to the right. */
    var direction: MyUndefOr[String] = js.native

    /** A disabled dropdown menu or item does not allow user interaction. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** An errored dropdown can alert a user to a problem. */
    var error: MyUndefOr[Boolean] = js.native

    /** A dropdown menu can contain floated content. */
    var floating: MyUndefOr[Boolean] = js.native

    /** A dropdown can take the full width of its parent */
    var fluid: MyUndefOr[Boolean] = js.native

    /** A dropdown menu can contain a header. */
    var header: MyUndefOr[React.Node] = js.native

    /** Shorthand for Icon. */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native

    /** A dropdown can be formatted to appear inline in other content. */
    var inline: MyUndefOr[Boolean] = js.native

    /** A dropdown can be formatted as a Menu item. */
    var item: MyUndefOr[Boolean] = js.native

    /** A dropdown can be labeled. */
    var labeled: MyUndefOr[Boolean] = js.native

    /** A dropdown can defer rendering its options until it is open. */
    var lazyLoad: MyUndefOr[Boolean] = js.native

    /** A dropdown can show that it is currently loading data. */
    var loading: MyUndefOr[Boolean] = js.native

    /** The minimum characters for a search to begin showing results. */
    var minCharacters: MyUndefOr[Double] = js.native

    /** A selection dropdown can allow multiple selections. */
    var multiple: MyUndefOr[Boolean] = js.native

    /** Message to display when there are no results. */
    var noResultsMessage: MyUndefOr[React.Node] = js.native

    /**
     * Called when a user adds a new item. Use this to update the options list.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and the new item's value.
     */
    var onAddItem: MyUndefOr[RawOnAddItem] = js.native

    /**
     * Called on blur.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onBlur: MyUndefOr[RawOnBlur] = js.native

    /**
     * Called when the user attempts to change the value.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and proposed value.
     */
    var onChange: MyUndefOr[RawOnChange] = MyUndefOr.undefined

    /**
     * Called on click.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: MyUndefOr[RawOnClick]

    /**
     * Called when a close event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClose: MyUndefOr[RawOnClose] = MyUndefOr.undefined

    /**
     * Called on focus.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onFocus: MyUndefOr[RawOnFocus] = MyUndefOr.undefined

    /**
     * Called when a multi-select label is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All label props.
     */
    var onLabelClick: MyUndefOr[RawOnLabelClick] = MyUndefOr.undefined

    /**
     * Called on mousedown.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onMouseDown: MyUndefOr[RawOnMouseDown] = MyUndefOr.undefined

    /**
     * Called when an open event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onOpen: MyUndefOr[RawOnOpen] = MyUndefOr.undefined

    /**
     * Called on search input change.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props, includes current value of searchQuery.
     */
    var onSearchChange: MyUndefOr[RawOnSearchChange] = MyUndefOr.undefined

    /** Controls whether or not the dropdown menu is displayed. */
    var open: MyUndefOr[Boolean] = js.native

    /** Whether or not the menu should open when the dropdown is focused. */
    var openOnFocus: MyUndefOr[Boolean] = js.native

    /** Array of Dropdown.Item props e.g. `{ text: '', value: '' }` */
    var options: MyUndefOr[js.Array[DropdownItem.DropdownItemProps]] = js.native

    /** Placeholder text. */
    var placeholder: MyUndefOr[String] = js.native

    /** A dropdown can be formatted so that its menu is pointing. */
    var pointing: MyUndefOr[Boolean | String] = js.native

    /**
     * Mapped over the active items and returns shorthand for the active item Labels. Only applies
     * to `multiple` Dropdowns.
     *
     * @param {object}
     *   item - A currently active dropdown item.
     * @param {number}
     *   index - The current index.
     * @param {props}
     *   defaultLabelProps - The default props for an active item Label.
     * @return
     *   {*} Shorthand for a Label.
     */
    var renderLabel: MyUndefOr[RawRenderLabel] = js.native

    /** A dropdown can have its menu scroll. */
    var scrolling: MyUndefOr[Boolean] = js.native

    /**
     * A selection dropdown can allow a user to search through a large list of choices. Pass a
     * function here to replace the default search.
     */
    var search: MyUndefOr[Boolean | RawSearchFunction] = js.native

    /** A shorthand for a search input. */
    var searchInput: MyUndefOr[React.Node] = js.native

    /** Current value of searchQuery. Creates a controlled component. */
    var searchQuery: MyUndefOr[String] = js.native

    /** Define whether the highlighted item should be selected on blur. */
    var selectOnBlur: MyUndefOr[Boolean] = js.native

    /**
     * Whether dropdown should select new option when using keyboard shortcuts. Setting to false
     * will require enter or left click to confirm a choice.
     */
    var selectOnNavigation: MyUndefOr[Boolean] = js.native

    /** Currently selected label in multi-select. */
    var selectedLabel: MyUndefOr[Double | String]

    /** A dropdown can be used to select between choices in a form. */
    var selection: MyUndefOr[Boolean]

    /** A simple dropdown can open without Javascript. */
    var simple: MyUndefOr[Boolean] = js.native

    /** A dropdown can receive focus. */
    var tabIndex: MyUndefOr[String | Double] = js.native

    /** The text displayed in the dropdown, usually for the active item. */
    var text: MyUndefOr[String] = js.native

    /** Custom element to trigger the menu to become visible. Takes place of 'text'. */
    var trigger: MyUndefOr[React.Node]

    /** Current value or value array if multiple. Creates a controlled component. */
    var value: MyUndefOr[Value] = js.native

    /** Controls whether the dropdown will open upward. */
    var upward: MyUndefOr[Boolean] = js.native

    /**
     * A dropdown will go to the last element when ArrowUp is pressed on the first, or go to the
     * first when ArrowDown is pressed on the last( aka infinite selection )
     */
    var wrapSelection: MyUndefOr[Boolean] = js.native
  }

  def props(q: Dropdown): DropdownProps =
    rawprops(
      q.as,
      q.additionLabel,
      q.additionPosition,
      q.allowAdditions,
      q.basic,
      q.button,
      q.className,
      q.clazz,
      q.clearable,
      q.closeOnBlur,
      q.closeOnEscape,
      q.closeOnChange,
      q.compact,
      q.deburr,
      q.defaultOpen,
      q.defaultSearchQuery,
      q.defaultSelectedLabel,
      q.defaultUpward,
      q.defaultValue,
      q.direction,
      q.disabled,
      q.error,
      q.floating,
      q.fluid,
      q.header,
      q.icon,
      q.inline,
      q.item,
      q.labeled,
      q.lazyLoad,
      q.loading,
      q.minCharacters,
      q.multiple,
      q.noResultsMessage,
      q.onAddItem,
      q.onBlurE,
      q.onBlur,
      q.onClickE,
      q.onClick,
      q.onChangeE,
      q.onChange,
      q.onCloseE,
      q.onClose,
      q.onFocusE,
      q.onFocus,
      q.onLabelClickE,
      q.onLabelClick,
      q.onMouseDownE,
      q.onMouseDown,
      q.onOpenE,
      q.onOpen,
      q.onSearchChangeE,
      q.onSearchChange,
      q.open,
      q.openOnFocus,
      q.options,
      q.placeholder,
      q.pointing,
      q.renderLabel,
      q.scrolling,
      q.search,
      q.searchInput,
      q.searchQuery,
      q.selectOnBlur,
      q.selectOnNavigation,
      q.selectedLabel,
      q.selection,
      q.simple,
      q.tabIndex,
      q.text,
      q.trigger,
      q.value,
      q.upward,
      q.wrapSelection
    )

  def rawprops(
    as:                   MyUndefOr[AsC] = MyUndefOr.undefined,
    additionLabel:        MyUndefOr[AdditionLabel] = MyUndefOr.undefined,
    additionPosition:     MyUndefOr[AdditionPosition] = MyUndefOr.undefined,
    allowAdditions:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    basic:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    button:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:            MyUndefOr[String] = MyUndefOr.undefined,
    clazz:                MyUndefOr[Css] = MyUndefOr.undefined,
    clearable:            MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnBlur:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnEscape:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnChange:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    compact:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    deburr:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultOpen:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultSearchQuery:   MyUndefOr[String] = MyUndefOr.undefined,
    defaultSelectedLabel: MyUndefOr[Double | String] = MyUndefOr.undefined,
    defaultUpward:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultValue:         MyUndefOr[Value] = MyUndefOr.undefined,
    direction:            MyUndefOr[Direction] = MyUndefOr.undefined,
    disabled:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    error:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    floating:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    fluid:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    header:               MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    icon:                 MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
    inline:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    item:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
    labeled:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    lazyLoad:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    loading:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    minCharacters:        MyUndefOr[Double] = MyUndefOr.undefined,
    multiple:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    noResultsMessage:     MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    onAddItem:            MyUndefOr[OnAddItem] = MyUndefOr.undefined,
    onBlurE:              MyUndefOr[OnBlur] = MyUndefOr.undefined,
    onBlur:               MyUndefOr[Callback] = MyUndefOr.undefined,
    onClickE:             MyUndefOr[OnClick] = MyUndefOr.undefined,
    onClick:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onChangeE:            MyUndefOr[OnChangeE] = MyUndefOr.undefined,
    onChange:             MyUndefOr[OnChange] = MyUndefOr.undefined,
    onCloseE:             MyUndefOr[OnClose] = MyUndefOr.undefined,
    onClose:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onFocusE:             MyUndefOr[OnFocus] = MyUndefOr.undefined,
    onFocus:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onLabelClickE:        MyUndefOr[OnLabelClick] = MyUndefOr.undefined,
    onLabelClick:         MyUndefOr[Callback] = MyUndefOr.undefined,
    onMouseDownE:         MyUndefOr[OnMouseDown] = MyUndefOr.undefined,
    onMouseDown:          MyUndefOr[Callback] = MyUndefOr.undefined,
    onOpenE:              MyUndefOr[OnOpen] = MyUndefOr.undefined,
    onOpen:               MyUndefOr[Callback] = MyUndefOr.undefined,
    onSearchChangeE:      MyUndefOr[OnSearchChangeE] = MyUndefOr.undefined,
    onSearchChange:       MyUndefOr[OnSearchChange] = MyUndefOr.undefined,
    open:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
    openOnFocus:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    options:              MyUndefOr[Seq[DropdownItem]] = MyUndefOr.undefined,
    placeholder:          MyUndefOr[String] = MyUndefOr.undefined,
    pointing:             MyUndefOr[Pointing] = MyUndefOr.undefined,
    renderLabel:          MyUndefOr[RenderLabel] = MyUndefOr.undefined,
    scrolling:            MyUndefOr[Boolean] = MyUndefOr.undefined,
    search:               MyUndefOr[Boolean | SearchFunction] = MyUndefOr.undefined,
    searchInput:          MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    searchQuery:          MyUndefOr[String] = MyUndefOr.undefined,
    selectOnBlur:         MyUndefOr[Boolean] = MyUndefOr.undefined,
    selectOnNavigation:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    selectedLabel:        MyUndefOr[Double | String] = MyUndefOr.undefined,
    selection:            MyUndefOr[Boolean] = MyUndefOr.undefined,
    simple:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    tabIndex:             MyUndefOr[String | Double] = MyUndefOr.undefined,
    text:                 MyUndefOr[String] = MyUndefOr.undefined,
    trigger:              MyUndefOr[VdomNode] = MyUndefOr.undefined,
    value:                MyUndefOr[Value] = MyUndefOr.undefined,
    upward:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    wrapSelection:        MyUndefOr[Boolean] = MyUndefOr.undefined
  ): DropdownProps = {
    val p = as.toJsObject[DropdownProps]
    as.toJs.foreach(v => p.as = v)
    additionLabel
      .map[Double | String | SemanticShorthandContent] {
        (_: Any) match {
          case b: String   => b
          case b: Byte     => b
          case b: Short    => b
          case b: Int      => b
          case b: Float    => b
          case b: Double   => b
          case b: VdomNode =>
            b.rawNode.asInstanceOf[RawAdditionLabel]
          case _           => sys.error("Shouldn't happen")
        }
      }
      .foreach(v => p.additionLabel = v)
    additionPosition.toJs.foreach(v => p.additionPosition = v)
    allowAdditions.foreach(v => p.allowAdditions = v)
    basic.foreach(v => p.basic = v)
    button.foreach(v => p.button = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    clearable.foreach(v => p.clearable = v)
    closeOnBlur.foreach(v => p.closeOnBlur = v)
    closeOnEscape.foreach(v => p.closeOnEscape = v)
    closeOnChange.foreach(v => p.closeOnChange = v)
    compact.foreach(v => p.compact = v)
    deburr.foreach(v => p.deburr = v)
    defaultOpen.foreach(v => p.defaultOpen = v)
    defaultSearchQuery.foreach(v => p.defaultSearchQuery = v)
    defaultSelectedLabel.foreach(v => p.defaultSelectedLabel = v)
    defaultUpward.foreach(v => p.defaultUpward = v)
    defaultValue.foreach(v => p.defaultValue = v)
    direction.toJs.foreach(v => p.direction = v)
    disabled.foreach(v => p.disabled = v)
    error.foreach(v => p.error = v)
    floating.foreach(v => p.floating = v)
    fluid.foreach(v => p.fluid = v)
    header.toJs.foreach(v => p.header = v)
    icon.toJs.foreach(v => p.icon = v)
    inline.foreach(v => p.inline = v)
    item.foreach(v => p.item = v)
    labeled.foreach(v => p.labeled = v)
    lazyLoad.foreach(v => p.lazyLoad = v)
    loading.foreach(v => p.loading = v)
    minCharacters.foreach(v => p.minCharacters = v)
    multiple.foreach(v => p.multiple = v)
    noResultsMessage.toJs.foreach(v => p.noResultsMessage = v)
    onAddItem.toJs.foreach(v => p.onAddItem = v)
    (onBlurE, onBlur).toJs.foreach(v => p.onBlur = v)
    onChangeE.toJs
      .orElse[RawOnChange](
        onChange.map(t => (_: ReactEvent, b: DropdownProps) => t(b).runNow())
      )
      .foreach(v => p.onChange = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    (onCloseE, onClose).toJs.foreach(v => p.onClose = v)
    (onFocusE, onFocus).toJs.foreach(v => p.onFocus = v)
    (onLabelClickE, onLabelClick).toJs.foreach(v => p.onLabelClick = v)
    (onMouseDownE, onMouseDown).toJs.foreach(v => p.onMouseDown = v)
    (onOpenE, onOpen).toJs.foreach(v => p.onOpen = v)
    onSearchChangeE.toJs
      .orElse[RawOnSearchChange](
        onSearchChange.map(t => (_: ReactEvent, b: DropdownOnSearchChangeData) => t(b).runNow())
      )
      .foreach(v => p.onSearchChange = v)
    onAddItem.toJs.foreach(v => p.onAddItem = v)
    open.foreach(v => p.open = v)
    openOnFocus.foreach(v => p.openOnFocus = v)
    options.map(_.map(_.props).toJSArray).foreach(v => p.options = v)
    placeholder.foreach(v => p.placeholder = v)
    pointing.toJs.foreach(v => p.pointing = v)
    renderLabel
      .map[RawRenderLabel] {
        b => (item: DropdownItem.DropdownItemProps, index: Int, defaultProps: Label.LabelProps) =>
          b(item, index, defaultProps).runNow()
      }
      .foreach(v => p.renderLabel = v)
    scrolling.foreach(v => p.scrolling = v)
    search
      .map[Boolean | RawSearchFunction] {
        (_: Any) match {
          case b: Boolean => b
          case b          =>
            val sf                     = b.asInstanceOf[SearchFunction]
            val rsf: RawSearchFunction = (l: js.Array[DropdownItem.DropdownItemProps], s: String) =>
              sf(l.toList, s).runNow().toJSArray
            rsf
        }
      }
      .foreach(v => p.search = v)
    searchInput.toJs.foreach(v => p.searchInput = v)
    searchQuery.foreach(v => p.searchQuery = v)
    selectOnBlur.foreach(v => p.selectOnBlur = v)
    selectOnNavigation.foreach(v => p.selectOnNavigation = v)
    selectedLabel.foreach(v => p.selectedLabel = v)
    selection.foreach(v => p.selection = v)
    simple.foreach(v => p.simple = v)
    tabIndex.foreach(v => p.tabIndex = v)
    text.foreach(v => p.text = v)
    trigger.toJs.foreach(v => p.trigger = v)
    value.foreach(v => p.value = v)
    upward.foreach(v => p.upward = v)
    wrapSelection.foreach(v => p.wrapSelection = v)
    p
  }

  private val component =
    JsComponent[DropdownProps, Children.Varargs, Null](RawComponent)

}
