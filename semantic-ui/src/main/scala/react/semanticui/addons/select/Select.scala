package react.semanticui.addons.select

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import react.common._
import react.semanticui._
import react.semanticui.raw._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.label.Label
import react.semanticui.modules.dropdown._
import react.semanticui.modules.dropdown.Dropdown._

final case class Select(
  as:                     js.UndefOr[AsC] = js.undefined,
  additionLabel:          js.UndefOr[AdditionLabel] = js.undefined,
  additionPosition:       js.UndefOr[AdditionPosition] = js.undefined,
  allowAdditions:         js.UndefOr[Boolean] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  button:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  clearable:              js.UndefOr[Boolean] = js.undefined,
  closeOnBlur:            js.UndefOr[Boolean] = js.undefined,
  closeOnEscape:          js.UndefOr[Boolean] = js.undefined,
  closeOnChange:          js.UndefOr[Boolean] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  deburr:                 js.UndefOr[Boolean] = js.undefined,
  defaultOpen:            js.UndefOr[Boolean] = js.undefined,
  defaultSearchQuery:     js.UndefOr[String] = js.undefined,
  defaultSelectedLabel:   js.UndefOr[Double | String] = js.undefined,
  defaultUpward:          js.UndefOr[Boolean] = js.undefined,
  defaultValue:           js.UndefOr[Value] = js.undefined,
  direction:              js.UndefOr[Direction] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[Boolean] = js.undefined,
  floating:               js.UndefOr[Boolean] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  header:                 js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[Icon]] = js.undefined,
  inline:                 js.UndefOr[Boolean] = js.undefined,
  item:                   js.UndefOr[Boolean] = js.undefined,
  labeled:                js.UndefOr[Boolean] = js.undefined,
  lazyLoad:               js.UndefOr[Boolean] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  minCharacters:          js.UndefOr[Double] = js.undefined,
  multiple:               js.UndefOr[Boolean] = js.undefined,
  noResultsMessage:       js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  onAddItem:              js.UndefOr[OnAddItem] = js.undefined,
  onBlurE:                js.UndefOr[OnBlur] = js.undefined,
  onBlur:                 js.UndefOr[Callback] = js.undefined,
  onClickE:               js.UndefOr[OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onChangeE:              js.UndefOr[OnChangeE] = js.undefined,
  onChange:               js.UndefOr[OnChange] = js.undefined,
  onCloseE:               js.UndefOr[OnClose] = js.undefined,
  onClose:                js.UndefOr[Callback] = js.undefined,
  onFocusE:               js.UndefOr[OnFocus] = js.undefined,
  onFocus:                js.UndefOr[Callback] = js.undefined,
  onLabelClickE:          js.UndefOr[OnLabelClick] = js.undefined,
  onLabelClick:           js.UndefOr[Callback] = js.undefined,
  onMouseDownE:           js.UndefOr[OnMouseDown] = js.undefined,
  onMouseDown:            js.UndefOr[Callback] = js.undefined,
  onOpenE:                js.UndefOr[OnOpen] = js.undefined,
  onOpen:                 js.UndefOr[Callback] = js.undefined,
  onSearchChangeE:        js.UndefOr[OnSearchChangeE] = js.undefined,
  onSearchChange:         js.UndefOr[OnSearchChange] = js.undefined,
  open:                   js.UndefOr[Boolean] = js.undefined,
  openOnFocus:            js.UndefOr[Boolean] = js.undefined,
  options:                Seq[Select.SelectItem],
  placeholder:            js.UndefOr[String] = js.undefined,
  pointing:               js.UndefOr[Pointing] = js.undefined,
  renderLabel:            js.UndefOr[RenderLabel] = js.undefined,
  scrolling:              js.UndefOr[Boolean] = js.undefined,
  search:                 js.UndefOr[Boolean | SearchFunction] = js.undefined,
  searchInput:            js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  searchQuery:            js.UndefOr[String] = js.undefined,
  selectOnBlur:           js.UndefOr[Boolean] = js.undefined,
  selectOnNavigation:     js.UndefOr[Boolean] = js.undefined,
  selectedLabel:          js.UndefOr[Double | String] = js.undefined,
  simple:                 js.UndefOr[Boolean] = js.undefined,
  tabIndex:               js.UndefOr[String | Double] = js.undefined,
  text:                   js.UndefOr[String] = js.undefined,
  trigger:                js.UndefOr[VdomNode] = js.undefined,
  value:                  js.UndefOr[Value] = js.undefined,
  upward:                 js.UndefOr[Boolean] = js.undefined,
  wrapSelection:          js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Select.SelectProps, Select] {
  override protected def cprops                     = Select.props(this)
  override protected val component                  = Select.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Select {
  type SelectItem = DropdownItem

  @js.native
  @JSImport("semantic-ui-react", "Select")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait SelectProps extends DropdownProps

  def props(q: Select): SelectProps =
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
      q.simple,
      q.tabIndex,
      q.text,
      q.trigger,
      q.value,
      q.upward,
      q.wrapSelection
    )

  def rawprops(
    as:                   js.UndefOr[AsC] = js.undefined,
    additionLabel:        js.UndefOr[AdditionLabel] = js.undefined,
    additionPosition:     js.UndefOr[AdditionPosition] = js.undefined,
    allowAdditions:       js.UndefOr[Boolean] = js.undefined,
    basic:                js.UndefOr[Boolean] = js.undefined,
    button:               js.UndefOr[Boolean] = js.undefined,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    clearable:            js.UndefOr[Boolean] = js.undefined,
    closeOnBlur:          js.UndefOr[Boolean] = js.undefined,
    closeOnEscape:        js.UndefOr[Boolean] = js.undefined,
    closeOnChange:        js.UndefOr[Boolean] = js.undefined,
    compact:              js.UndefOr[Boolean] = js.undefined,
    deburr:               js.UndefOr[Boolean] = js.undefined,
    defaultOpen:          js.UndefOr[Boolean] = js.undefined,
    defaultSearchQuery:   js.UndefOr[String] = js.undefined,
    defaultSelectedLabel: js.UndefOr[Double | String] = js.undefined,
    defaultUpward:        js.UndefOr[Boolean] = js.undefined,
    defaultValue:         js.UndefOr[Value] = js.undefined,
    direction:            js.UndefOr[Direction] = js.undefined,
    disabled:             js.UndefOr[Boolean] = js.undefined,
    error:                js.UndefOr[Boolean] = js.undefined,
    floating:             js.UndefOr[Boolean] = js.undefined,
    fluid:                js.UndefOr[Boolean] = js.undefined,
    header:               js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    icon:                 js.UndefOr[ShorthandS[Icon]] = js.undefined,
    inline:               js.UndefOr[Boolean] = js.undefined,
    item:                 js.UndefOr[Boolean] = js.undefined,
    labeled:              js.UndefOr[Boolean] = js.undefined,
    lazyLoad:             js.UndefOr[Boolean] = js.undefined,
    loading:              js.UndefOr[Boolean] = js.undefined,
    minCharacters:        js.UndefOr[Double] = js.undefined,
    multiple:             js.UndefOr[Boolean] = js.undefined,
    noResultsMessage:     js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    onAddItem:            js.UndefOr[OnAddItem] = js.undefined,
    onBlurE:              js.UndefOr[OnBlur] = js.undefined,
    onBlur:               js.UndefOr[Callback] = js.undefined,
    onClickE:             js.UndefOr[OnClick] = js.undefined,
    onClick:              js.UndefOr[Callback] = js.undefined,
    onChangeE:            js.UndefOr[OnChangeE] = js.undefined,
    onChange:             js.UndefOr[OnChange] = js.undefined,
    onCloseE:             js.UndefOr[OnClose] = js.undefined,
    onClose:              js.UndefOr[Callback] = js.undefined,
    onFocusE:             js.UndefOr[OnFocus] = js.undefined,
    onFocus:              js.UndefOr[Callback] = js.undefined,
    onLabelClickE:        js.UndefOr[OnLabelClick] = js.undefined,
    onLabelClick:         js.UndefOr[Callback] = js.undefined,
    onMouseDownE:         js.UndefOr[OnMouseDown] = js.undefined,
    onMouseDown:          js.UndefOr[Callback] = js.undefined,
    onOpenE:              js.UndefOr[OnOpen] = js.undefined,
    onOpen:               js.UndefOr[Callback] = js.undefined,
    onSearchChangeE:      js.UndefOr[OnSearchChangeE] = js.undefined,
    onSearchChange:       js.UndefOr[OnSearchChange] = js.undefined,
    open:                 js.UndefOr[Boolean] = js.undefined,
    openOnFocus:          js.UndefOr[Boolean] = js.undefined,
    options:              js.UndefOr[Seq[SelectItem]] = js.undefined,
    placeholder:          js.UndefOr[String] = js.undefined,
    pointing:             js.UndefOr[Pointing] = js.undefined,
    renderLabel:          js.UndefOr[RenderLabel] = js.undefined,
    scrolling:            js.UndefOr[Boolean] = js.undefined,
    search:               js.UndefOr[Boolean | SearchFunction] = js.undefined,
    searchInput:          js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    searchQuery:          js.UndefOr[String] = js.undefined,
    selectOnBlur:         js.UndefOr[Boolean] = js.undefined,
    selectOnNavigation:   js.UndefOr[Boolean] = js.undefined,
    selectedLabel:        js.UndefOr[Double | String] = js.undefined,
    simple:               js.UndefOr[Boolean] = js.undefined,
    tabIndex:             js.UndefOr[String | Double] = js.undefined,
    text:                 js.UndefOr[String] = js.undefined,
    trigger:              js.UndefOr[VdomNode] = js.undefined,
    value:                js.UndefOr[Value] = js.undefined,
    upward:               js.UndefOr[Boolean] = js.undefined,
    wrapSelection:        js.UndefOr[Boolean] = js.undefined
  ): SelectProps = {
    val p = as.toJsObject[SelectProps]
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
    JsComponent[SelectProps, Children.None, Null](RawComponent)
}
