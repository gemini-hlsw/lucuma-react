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
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.semanticui.addons.select.Select
import react.semanticui.modules.dropdown._
import react.semanticui.modules.dropdown.Dropdown._
import react.semanticui.collections.form.FormDropdown
import react.semanticui.collections.form.FormDropdown.FormDropdownProps

final case class FormSelect(
  additionLabel:          js.UndefOr[AdditionLabel] = js.undefined,
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
  defaultValue:           js.UndefOr[Value] = js.undefined,
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
  onAddItem:              js.UndefOr[OnAddItem] = js.undefined,
  onBlur:                 js.UndefOr[Callback] = js.undefined,
  onBlurE:                js.UndefOr[OnBlur] = js.undefined,
  onChange:               js.UndefOr[FormDropdown.OnChange] = js.undefined,
  onChangeE:              js.UndefOr[FormDropdown.OnChangeE] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onClickE:               js.UndefOr[OnClick] = js.undefined,
  onClose:                js.UndefOr[Callback] = js.undefined,
  onCloseE:               js.UndefOr[OnClose] = js.undefined,
  onFocus:                js.UndefOr[Callback] = js.undefined,
  onFocusE:               js.UndefOr[OnFocus] = js.undefined,
  onLabelClick:           js.UndefOr[Callback] = js.undefined,
  onLabelClickE:          js.UndefOr[OnLabelClick] = js.undefined,
  onMouseDown:            js.UndefOr[Callback] = js.undefined,
  onMouseDownE:           js.UndefOr[OnMouseDown] = js.undefined,
  onOpen:                 js.UndefOr[Callback] = js.undefined,
  onOpenE:                js.UndefOr[OnOpen] = js.undefined,
  onSearchChange:         js.UndefOr[OnSearchChange] = js.undefined,
  onSearchChangeE:        js.UndefOr[OnSearchChangeE] = js.undefined,
  open:                   js.UndefOr[Boolean] = js.undefined,
  openOnFocus:            js.UndefOr[Boolean] = js.undefined,
  options:                Seq[Select.SelectItem],
  placeholder:            js.UndefOr[String] = js.undefined,
  pointing:               js.UndefOr[Pointing] = js.undefined,
  renderLabel:            js.UndefOr[RenderLabel] = js.undefined,
  required:               js.UndefOr[Boolean] = js.undefined,
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
  tpe:                    js.UndefOr[String] = js.undefined,
  trigger:                js.UndefOr[VdomNode] = js.undefined,
  upward:                 js.UndefOr[Boolean] = js.undefined,
  value:                  js.UndefOr[Value] = js.undefined,
  width:                  js.UndefOr[SemanticWidth] = js.undefined,
  wrapSelection:          js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[FormSelect.FormSelectProps, FormSelect] {
  override protected def cprops                     = FormSelect.props(this)
  override protected val component                  = FormSelect.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object FormSelect {
  @js.native
  @JSImport("semantic-ui-react", "FormSelect")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait FormSelectProps extends FormDropdownProps

  def props(q: FormSelect): FormSelectProps =
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
    additionLabel:        js.UndefOr[AdditionLabel] = js.undefined,
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
    defaultValue:         js.UndefOr[Value] = js.undefined,
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
    onAddItem:            js.UndefOr[OnAddItem] = js.undefined,
    onBlur:               js.UndefOr[Callback] = js.undefined,
    onBlurE:              js.UndefOr[OnBlur] = js.undefined,
    onChange:             js.UndefOr[FormDropdown.OnChange] = js.undefined,
    onChangeE:            js.UndefOr[FormDropdown.OnChangeE] = js.undefined,
    onClick:              js.UndefOr[Callback] = js.undefined,
    onClickE:             js.UndefOr[OnClick] = js.undefined,
    onClose:              js.UndefOr[Callback] = js.undefined,
    onCloseE:             js.UndefOr[OnClose] = js.undefined,
    onFocus:              js.UndefOr[Callback] = js.undefined,
    onFocusE:             js.UndefOr[OnFocus] = js.undefined,
    onLabelClick:         js.UndefOr[Callback] = js.undefined,
    onLabelClickE:        js.UndefOr[OnLabelClick] = js.undefined,
    onMouseDown:          js.UndefOr[Callback] = js.undefined,
    onMouseDownE:         js.UndefOr[OnMouseDown] = js.undefined,
    onOpen:               js.UndefOr[Callback] = js.undefined,
    onOpenE:              js.UndefOr[OnOpen] = js.undefined,
    onSearchChange:       js.UndefOr[OnSearchChange] = js.undefined,
    onSearchChangeE:      js.UndefOr[OnSearchChangeE] = js.undefined,
    open:                 js.UndefOr[Boolean] = js.undefined,
    openOnFocus:          js.UndefOr[Boolean] = js.undefined,
    options:              js.UndefOr[Seq[DropdownItem]] = js.undefined,
    placeholder:          js.UndefOr[String] = js.undefined,
    pointing:             js.UndefOr[Pointing] = js.undefined,
    renderLabel:          js.UndefOr[RenderLabel] = js.undefined,
    required:             js.UndefOr[Boolean] = js.undefined,
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
    tpe:                  js.UndefOr[String] = js.undefined,
    trigger:              js.UndefOr[VdomNode] = js.undefined,
    upward:               js.UndefOr[Boolean] = js.undefined,
    value:                js.UndefOr[Value] = js.undefined,
    width:                js.UndefOr[SemanticWidth] = js.undefined,
    wrapSelection:        js.UndefOr[Boolean] = js.undefined
  ): FormSelectProps = {
    val p = as.toJsObject[FormSelectProps]
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
      .orElse[FormDropdown.RawOnChange](
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
      .orElse[RawOnSearchChange](
        onSearchChange
          .map(t => (_: ReactEvent, b: DropdownOnSearchChangeData) => t(b).runNow())
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
            val rsf: RawSearchFunction =
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
    JsComponent[FormSelectProps, Children.None, Null](RawComponent)
}
