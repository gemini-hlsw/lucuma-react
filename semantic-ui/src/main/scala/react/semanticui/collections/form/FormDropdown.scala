package react.semanticui.collections.form

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import js.JSConverters._
import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.elements.label.Label
import react.semanticui.elements.icon.Icon
import react.semanticui.modules.dropdown._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormDropdown(
  additionLabel:          js.UndefOr[Dropdown.AdditionLabel] = js.undefined,
  additionPosition:       js.UndefOr[AdditionPosition] = js.undefined,
  allowAdditions:         js.UndefOr[Boolean] = js.undefined,
  as:                     js.UndefOr[AsC] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  button:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  clearable:              js.UndefOr[Boolean] = js.undefined,
  closeOnBlur:            js.UndefOr[Boolean] = js.undefined,
  closeOnChange:          js.UndefOr[Boolean] = js.undefined,
  closeOnEscape:          js.UndefOr[Boolean] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  control:                js.UndefOr[String] = js.undefined,
  deburr:                 js.UndefOr[Boolean] = js.undefined,
  defaultOpen:            js.UndefOr[Boolean] = js.undefined,
  defaultSearchQuery:     js.UndefOr[String] = js.undefined,
  defaultSelectedLabel:   js.UndefOr[Double | String] = js.undefined,
  defaultUpward:          js.UndefOr[Boolean] = js.undefined,
  defaultValue:           js.UndefOr[Dropdown.Value] = js.undefined,
  direction:              js.UndefOr[Direction] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[ShorthandB[Label]] = js.undefined,
  floating:               js.UndefOr[Boolean] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  header:                 js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[Icon]] = js.undefined,
  inline:                 js.UndefOr[Boolean] = js.undefined,
  item:                   js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  labeled:                js.UndefOr[Boolean] = js.undefined,
  lazyLoad:               js.UndefOr[Boolean] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  minCharacters:          js.UndefOr[Double] = js.undefined,
  multiple:               js.UndefOr[Boolean] = js.undefined,
  noResultsMessage:       js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  onAddItem:              js.UndefOr[Dropdown.OnAddItem] = js.undefined,
  onBlur:                 js.UndefOr[Callback] = js.undefined,
  onBlurE:                js.UndefOr[Dropdown.OnBlur] = js.undefined,
  onChange:               js.UndefOr[FormDropdown.OnChange] = js.undefined,
  onChangeE:              js.UndefOr[FormDropdown.OnChangeE] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onClickE:               js.UndefOr[Dropdown.OnClick] = js.undefined,
  onClose:                js.UndefOr[Callback] = js.undefined,
  onCloseE:               js.UndefOr[Dropdown.OnClose] = js.undefined,
  onFocus:                js.UndefOr[Callback] = js.undefined,
  onFocusE:               js.UndefOr[Dropdown.OnFocus] = js.undefined,
  onLabelClick:           js.UndefOr[Callback] = js.undefined,
  onLabelClickE:          js.UndefOr[Dropdown.OnLabelClick] = js.undefined,
  onMouseDown:            js.UndefOr[Callback] = js.undefined,
  onMouseDownE:           js.UndefOr[Dropdown.OnMouseDown] = js.undefined,
  onOpen:                 js.UndefOr[Callback] = js.undefined,
  onOpenE:                js.UndefOr[Dropdown.OnOpen] = js.undefined,
  onSearchChange:         js.UndefOr[Dropdown.OnSearchChange] = js.undefined,
  onSearchChangeE:        js.UndefOr[Dropdown.OnSearchChangeE] = js.undefined,
  open:                   js.UndefOr[Boolean] = js.undefined,
  openOnFocus:            js.UndefOr[Boolean] = js.undefined,
  options:                js.UndefOr[Seq[DropdownItem]] = js.undefined,
  placeholder:            js.UndefOr[String] = js.undefined,
  pointing:               js.UndefOr[Pointing] = js.undefined,
  renderLabel:            js.UndefOr[Dropdown.RenderLabel] = js.undefined,
  required:               js.UndefOr[Boolean] = js.undefined,
  scrolling:              js.UndefOr[Boolean] = js.undefined,
  search:                 js.UndefOr[Boolean | Dropdown.SearchFunction] = js.undefined,
  searchInput:            js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  searchQuery:            js.UndefOr[String] = js.undefined,
  selectOnBlur:           js.UndefOr[Boolean] = js.undefined,
  selectOnNavigation:     js.UndefOr[Boolean] = js.undefined,
  selectedLabel:          js.UndefOr[Double | String] = js.undefined,
  selection:              js.UndefOr[Boolean] = js.undefined,
  simple:                 js.UndefOr[Boolean] = js.undefined,
  tabIndex:               js.UndefOr[String | Double] = js.undefined,
  text:                   js.UndefOr[String] = js.undefined,
  tpe:                    js.UndefOr[String] = js.undefined,
  trigger:                js.UndefOr[VdomNode] = js.undefined,
  upward:                 js.UndefOr[Boolean] = js.undefined,
  value:                  js.UndefOr[Dropdown.Value] = js.undefined,
  width:                  js.UndefOr[SemanticWidth] = js.undefined,
  wrapSelection:          js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[FormDropdown.FormDropdownProps, FormDropdown] {
  override protected def cprops                     = FormDropdown.props(this)
  override protected val component                  = FormDropdown.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object FormDropdown {
  type RawOnChange = js.Function2[ReactEvent, FormDropdownProps, Unit]
  type OnChangeE   = (ReactEvent, FormDropdownProps) => Callback
  type OnChange    = FormDropdownProps => Callback

  @js.native
  @JSImport("semantic-ui-react", "FormDropdown")
  object RawComponent extends js.Object

  @js.native
  trait FormDropdownProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] =
      js.undefined

    /**
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormDropdown
     * props are passed to the control component. Mutually exclusive with children.
     */
    // control?: any
    var control: js.UndefOr[String]

    /** Mutually exclusive with children. */
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** A field can show that input is mandatory.  Requires a label. */
    var required: js.UndefOr[Boolean] = js.native

    /** Passed to the control component (i.e. <input type='password' />) */
    var `type`: js.UndefOr[String] = js.native

    /** A field can specify its width in grid columns */
    var width: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** Label prefixed to an option added by a user. */
    var additionLabel: js.UndefOr[Double | String | suiraw.SemanticShorthandContent] = js.native

    /** Position of the `Add: ...` option in the dropdown list ('top' or 'bottom'). */
    var additionPosition: js.UndefOr[String]

    /**
     * Allow user additions to the list of options (boolean). Requires the use of `selection`,
     * `options` and `search`.
     */
    var allowAdditions: js.UndefOr[Boolean] = js.native

    /** A Dropdown can reduce its complexity. */
    var basic: js.UndefOr[Boolean] = js.native

    /** Format the Dropdown to appear as a button. */
    var button: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Using the clearable setting will let users remove their selection from a dropdown. */
    var clearable: js.UndefOr[Boolean] = js.native

    /** Whether or not the menu should close when the dropdown is blurred. */
    var closeOnBlur: js.UndefOr[Boolean] = js.native

    /** Whether or not the dropdown should close when the escape key is pressed. */
    var closeOnEscape: js.UndefOr[Boolean] = js.native

    /**
     * Whether or not the menu should close when a value is selected from the dropdown. By default,
     * multiple selection dropdowns will remain open on change, while single selection dropdowns
     * will close on change.
     */
    var closeOnChange: js.UndefOr[Boolean] = js.native

    /** A compact dropdown has no minimum width. */
    var compact: js.UndefOr[Boolean] = js.native

    /** Whether or not the dropdown should strip diacritics in options and input search */
    var deburr: js.UndefOr[Boolean] = js.native

    /** Initial value of open. */
    var defaultOpen: js.UndefOr[Boolean] = js.native

    /** Initial value of searchQuery. */
    var defaultSearchQuery: js.UndefOr[String] = js.native

    /** Currently selected label in multi-select. */
    var defaultSelectedLabel: js.UndefOr[Double | String] = js.native

    /** Initial value of upward. */
    var defaultUpward: js.UndefOr[Boolean] = js.native

    /** Initial value or value array if multiple. */
    var defaultValue: js.UndefOr[Dropdown.Value] = js.native

    /** A dropdown menu can open to the left or to the right. */
    var direction: js.UndefOr[String] = js.native

    /** A disabled dropdown menu or item does not allow user interaction. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** Individual fields may display an error state along with a message. */
    var error: js.UndefOr[suiraw.SemanticShorthandItemB[Label.LabelProps]] = js.native

    /** A dropdown menu can contain floated content. */
    var floating: js.UndefOr[Boolean] = js.native

    /** A dropdown can take the full width of its parent */
    var fluid: js.UndefOr[Boolean] = js.native

    /** A dropdown menu can contain a header. */
    var header: js.UndefOr[React.Node] = js.native

    /** Shorthand for Icon. */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemS[Icon.IconProps]] = js.native

    /** A dropdown can be formatted to appear inline in other content. */
    var inline: js.UndefOr[Boolean] = js.native

    /** A dropdown can be formatted as a Menu item. */
    var item: js.UndefOr[Boolean] = js.native

    /** A dropdown can be labeled. */
    var labeled: js.UndefOr[Boolean] = js.native

    /** A dropdown can defer rendering its options until it is open. */
    var lazyLoad: js.UndefOr[Boolean] = js.native

    /** A dropdown can show that it is currently loading data. */
    var loading: js.UndefOr[Boolean] = js.native

    /** The minimum characters for a search to begin showing results. */
    var minCharacters: js.UndefOr[Double] = js.native

    /** A selection dropdown can allow multiple selections. */
    var multiple: js.UndefOr[Boolean] = js.native

    /** Message to display when there are no results. */
    var noResultsMessage: js.UndefOr[React.Node] = js.native

    /**
     * Called when a user adds a new item. Use this to update the options list.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and the new item's value.
     */
    var onAddItem: js.UndefOr[Dropdown.RawOnAddItem] = js.native

    /**
     * Called on blur.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onBlur: js.UndefOr[Dropdown.RawOnBlur] = js.native

    /**
     * Called when the user attempts to change the value.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and proposed value.
     */
    var onChange: js.UndefOr[FormDropdown.RawOnChange] = js.undefined

    /**
     * Called on click.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: js.UndefOr[Dropdown.RawOnClick]

    /**
     * Called when a close event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClose: js.UndefOr[Dropdown.RawOnClose] = js.undefined

    /**
     * Called on focus.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onFocus: js.UndefOr[Dropdown.RawOnFocus] = js.undefined

    /**
     * Called when a multi-select label is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All label props.
     */
    var onLabelClick: js.UndefOr[Dropdown.RawOnLabelClick] = js.undefined

    /**
     * Called on mousedown.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onMouseDown: js.UndefOr[Dropdown.RawOnMouseDown] = js.undefined

    /**
     * Called when an open event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onOpen: js.UndefOr[Dropdown.RawOnOpen] = js.undefined

    /**
     * Called on search input change.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props, includes current value of searchQuery.
     */
    var onSearchChange: js.UndefOr[Dropdown.RawOnSearchChange] = js.undefined

    /** Controls whether or not the dropdown menu is displayed. */
    var open: js.UndefOr[Boolean] = js.native

    /** Whether or not the menu should open when the dropdown is focused. */
    var openOnFocus: js.UndefOr[Boolean] = js.native

    /** Array of Dropdown.Item props e.g. `{ text: '', value: '' }` */
    var options: js.UndefOr[js.Array[DropdownItem.DropdownItemProps]] = js.native

    /** Placeholder text. */
    var placeholder: js.UndefOr[String] = js.native

    /** A dropdown can be formatted so that its menu is pointing. */
    var pointing: js.UndefOr[Boolean | String] = js.native

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
    var renderLabel: js.UndefOr[Dropdown.RawRenderLabel] = js.native

    /** A dropdown can have its menu scroll. */
    var scrolling: js.UndefOr[Boolean] = js.native

    /**
     * A selection dropdown can allow a user to search through a large list of choices. Pass a
     * function here to replace the default search.
     */
    var search: js.UndefOr[Boolean | Dropdown.RawSearchFunction] = js.native

    /** A shorthand for a search input. */
    var searchInput: js.UndefOr[React.Node] = js.native

    /** Current value of searchQuery. Creates a controlled component. */
    var searchQuery: js.UndefOr[String] = js.native

    /** Define whether the highlighted item should be selected on blur. */
    var selectOnBlur: js.UndefOr[Boolean] = js.native

    /**
     * Whether dropdown should select new option when using keyboard shortcuts. Setting to false
     * will require enter or left click to confirm a choice.
     */
    var selectOnNavigation: js.UndefOr[Boolean] = js.native

    /** Currently selected label in multi-select. */
    var selectedLabel: js.UndefOr[Double | String]

    /** A dropdown can be used to select between choices in a form. */
    var selection: js.UndefOr[Boolean]

    /** A simple dropdown can open without Javascript. */
    var simple: js.UndefOr[Boolean] = js.native

    /** A dropdown can receive focus. */
    var tabIndex: js.UndefOr[String | Double] = js.native

    /** The text displayed in the dropdown, usually for the active item. */
    var text: js.UndefOr[String] = js.native

    /** Custom element to trigger the menu to become visible. Takes place of 'text'. */
    var trigger: js.UndefOr[React.Node]

    /** Current value or value array if multiple. Creates a controlled component. */
    var value: js.UndefOr[Dropdown.Value] = js.native

    /** Controls whether the dropdown will open upward. */
    var upward: js.UndefOr[Boolean] = js.native

    /**
     * A dropdown will go to the last element when ArrowUp is pressed on the first, or go to the
     * first when ArrowDown is pressed on the last( aka infinite selection )
     */
    var wrapSelection: js.UndefOr[Boolean] = js.native
  }

  def props(q: FormDropdown): FormDropdownProps =
    rawprops(
      q.additionLabel,
      q.additionPosition,
      q.allowAdditions,
      q.as,
      q.basic,
      q.button,
      q.className,
      q.clazz,
      q.clearable,
      q.closeOnBlur,
      q.closeOnChange,
      q.closeOnEscape,
      q.compact,
      q.content,
      q.control,
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
      q.label,
      q.labeled,
      q.lazyLoad,
      q.loading,
      q.minCharacters,
      q.multiple,
      q.noResultsMessage,
      q.onAddItem,
      q.onBlur,
      q.onBlurE,
      q.onChange,
      q.onChangeE,
      q.onClick,
      q.onClickE,
      q.onClose,
      q.onCloseE,
      q.onFocus,
      q.onFocusE,
      q.onLabelClick,
      q.onLabelClickE,
      q.onMouseDown,
      q.onMouseDownE,
      q.onOpen,
      q.onOpenE,
      q.onSearchChange,
      q.onSearchChangeE,
      q.open,
      q.openOnFocus,
      q.options,
      q.placeholder,
      q.pointing,
      q.renderLabel,
      q.required,
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
      q.tpe,
      q.trigger,
      q.upward,
      q.value,
      q.width,
      q.wrapSelection
    )

  def rawprops(
    additionLabel:        js.UndefOr[Dropdown.AdditionLabel] = js.undefined,
    additionPosition:     js.UndefOr[AdditionPosition] = js.undefined,
    allowAdditions:       js.UndefOr[Boolean] = js.undefined,
    as:                   js.UndefOr[AsC] = js.undefined,
    basic:                js.UndefOr[Boolean] = js.undefined,
    button:               js.UndefOr[Boolean] = js.undefined,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    clearable:            js.UndefOr[Boolean] = js.undefined,
    closeOnBlur:          js.UndefOr[Boolean] = js.undefined,
    closeOnChange:        js.UndefOr[Boolean] = js.undefined,
    closeOnEscape:        js.UndefOr[Boolean] = js.undefined,
    compact:              js.UndefOr[Boolean] = js.undefined,
    content:              js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    control:              js.UndefOr[String] = js.undefined,
    deburr:               js.UndefOr[Boolean] = js.undefined,
    defaultOpen:          js.UndefOr[Boolean] = js.undefined,
    defaultSearchQuery:   js.UndefOr[String] = js.undefined,
    defaultSelectedLabel: js.UndefOr[Double | String] = js.undefined,
    defaultUpward:        js.UndefOr[Boolean] = js.undefined,
    defaultValue:         js.UndefOr[Dropdown.Value] = js.undefined,
    direction:            js.UndefOr[Direction] = js.undefined,
    disabled:             js.UndefOr[Boolean] = js.undefined,
    error:                js.UndefOr[ShorthandB[Label]] = js.undefined,
    floating:             js.UndefOr[Boolean] = js.undefined,
    fluid:                js.UndefOr[Boolean] = js.undefined,
    header:               js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    icon:                 js.UndefOr[ShorthandS[Icon]] = js.undefined,
    inline:               js.UndefOr[Boolean] = js.undefined,
    item:                 js.UndefOr[Boolean] = js.undefined,
    label:                js.UndefOr[ShorthandS[Label]] = js.undefined,
    labeled:              js.UndefOr[Boolean] = js.undefined,
    lazyLoad:             js.UndefOr[Boolean] = js.undefined,
    loading:              js.UndefOr[Boolean] = js.undefined,
    minCharacters:        js.UndefOr[Double] = js.undefined,
    multiple:             js.UndefOr[Boolean] = js.undefined,
    noResultsMessage:     js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    onAddItem:            js.UndefOr[Dropdown.OnAddItem] = js.undefined,
    onBlur:               js.UndefOr[Callback] = js.undefined,
    onBlurE:              js.UndefOr[Dropdown.OnBlur] = js.undefined,
    onChange:             js.UndefOr[FormDropdown.OnChange] = js.undefined,
    onChangeE:            js.UndefOr[FormDropdown.OnChangeE] = js.undefined,
    onClick:              js.UndefOr[Callback] = js.undefined,
    onClickE:             js.UndefOr[Dropdown.OnClick] = js.undefined,
    onClose:              js.UndefOr[Callback] = js.undefined,
    onCloseE:             js.UndefOr[Dropdown.OnClose] = js.undefined,
    onFocus:              js.UndefOr[Callback] = js.undefined,
    onFocusE:             js.UndefOr[Dropdown.OnFocus] = js.undefined,
    onLabelClick:         js.UndefOr[Callback] = js.undefined,
    onLabelClickE:        js.UndefOr[Dropdown.OnLabelClick] = js.undefined,
    onMouseDown:          js.UndefOr[Callback] = js.undefined,
    onMouseDownE:         js.UndefOr[Dropdown.OnMouseDown] = js.undefined,
    onOpen:               js.UndefOr[Callback] = js.undefined,
    onOpenE:              js.UndefOr[Dropdown.OnOpen] = js.undefined,
    onSearchChange:       js.UndefOr[Dropdown.OnSearchChange] = js.undefined,
    onSearchChangeE:      js.UndefOr[Dropdown.OnSearchChangeE] = js.undefined,
    open:                 js.UndefOr[Boolean] = js.undefined,
    openOnFocus:          js.UndefOr[Boolean] = js.undefined,
    options:              js.UndefOr[Seq[DropdownItem]] = js.undefined,
    placeholder:          js.UndefOr[String] = js.undefined,
    pointing:             js.UndefOr[Pointing] = js.undefined,
    renderLabel:          js.UndefOr[Dropdown.RenderLabel] = js.undefined,
    required:             js.UndefOr[Boolean] = js.undefined,
    scrolling:            js.UndefOr[Boolean] = js.undefined,
    search:               js.UndefOr[Boolean | Dropdown.SearchFunction] = js.undefined,
    searchInput:          js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    searchQuery:          js.UndefOr[String] = js.undefined,
    selectOnBlur:         js.UndefOr[Boolean] = js.undefined,
    selectOnNavigation:   js.UndefOr[Boolean] = js.undefined,
    selectedLabel:        js.UndefOr[Double | String] = js.undefined,
    selection:            js.UndefOr[Boolean] = js.undefined,
    simple:               js.UndefOr[Boolean] = js.undefined,
    tabIndex:             js.UndefOr[String | Double] = js.undefined,
    text:                 js.UndefOr[String] = js.undefined,
    tpe:                  js.UndefOr[String] = js.undefined,
    trigger:              js.UndefOr[VdomNode] = js.undefined,
    upward:               js.UndefOr[Boolean] = js.undefined,
    value:                js.UndefOr[Dropdown.Value] = js.undefined,
    width:                js.UndefOr[SemanticWidth] = js.undefined,
    wrapSelection:        js.UndefOr[Boolean] = js.undefined
  ): FormDropdownProps = {
    val p = as.toJsObject[FormDropdownProps]
    as.toJs.foreach(v => p.as = v)
    additionLabel
      .map[Double | String | suiraw.SemanticShorthandContent] {
        (_: Any) match {
          case b: String   => b
          case b: Byte     => b
          case b: Short    => b
          case b: Int      => b
          case b: Float    => b
          case b: Double   => b
          case b: VdomNode =>
            b.rawNode.asInstanceOf[Dropdown.RawAdditionLabel]
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
    floating.foreach(v => p.floating = v)
    fluid.foreach(v => p.fluid = v)
    header.toJs.foreach(v => p.header = v)
    icon.toJs.foreach(v => p.icon = v)
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
        onChange.map(t => (_: ReactEvent, b: FormDropdownProps) => t(b).runNow())
      )
      .foreach(v => p.onChange = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    (onCloseE, onClose).toJs.foreach(v => p.onClose = v)
    (onFocusE, onFocus).toJs.foreach(v => p.onFocus = v)
    (onLabelClickE, onLabelClick).toJs.foreach(v => p.onLabelClick = v)
    (onMouseDownE, onMouseDown).toJs.foreach(v => p.onMouseDown = v)
    (onOpenE, onOpen).toJs.foreach(v => p.onOpen = v)
    onSearchChangeE.toJs
      .orElse[Dropdown.RawOnSearchChange](
        onSearchChange
          .map(t => (_: ReactEvent, b: Dropdown.DropdownOnSearchChangeData) => t(b).runNow())
      )
      .foreach(v => p.onSearchChange = v)
    onAddItem.toJs.foreach(v => p.onAddItem = v)
    open.foreach(v => p.open = v)
    openOnFocus.foreach(v => p.openOnFocus = v)
    options.map(_.map(_.props).toJSArray).foreach(v => p.options = v)
    placeholder.foreach(v => p.placeholder = v)
    pointing.toJs.foreach(v => p.pointing = v)
    renderLabel
      .map[Dropdown.RawRenderLabel] {
        b => (item: DropdownItem.DropdownItemProps, index: Int, defaultProps: Label.LabelProps) =>
          b(item, index, defaultProps).runNow()
      }
      .foreach(v => p.renderLabel = v)
    scrolling.foreach(v => p.scrolling = v)
    search
      .map[Boolean | Dropdown.RawSearchFunction] {
        (_: Any) match {
          case b: Boolean => b
          case b          =>
            val sf                              = b.asInstanceOf[Dropdown.SearchFunction]
            val rsf: Dropdown.RawSearchFunction =
              (l: js.Array[DropdownItem.DropdownItemProps], s: String) =>
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
    content.toJs.foreach(v => p.content = v)
    control.foreach(v => p.control = v)
    disabled.foreach(v => p.disabled = v)
    error.toJs.foreach(v => p.error = v)
    inline.foreach(v => p.inline = v)
    label.toJs.foreach(v => p.label = v)
    required.foreach(v => p.required = v)
    p.`type` = tpe
    width.toJs.foreach(v => p.width = v)
    p
  }

  private val component =
    JsComponent[FormDropdownProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): FormDropdown =
    FormDropdown(modifiers = modifiers)
}
