// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.semanticui.addons.select.Select
import react.semanticui.modules.dropdown._
import react.semanticui.modules.dropdown.Dropdown._
import react.semanticui.collections.form.FormDropdown
import react.semanticui.collections.form.FormDropdown.FormDropdownProps

final case class FormSelect(
  additionLabel:          MyUndefOr[AdditionLabel] = MyUndefOr.undefined,
  additionPosition:       MyUndefOr[AdditionPosition] = MyUndefOr.undefined,
  allowAdditions:         MyUndefOr[Boolean] = MyUndefOr.undefined,
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  button:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  clearable:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnBlur:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnChange:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnEscape:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  compact:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  control:                MyUndefOr[String] = MyUndefOr.undefined,
  deburr:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultOpen:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultSearchQuery:     MyUndefOr[String] = MyUndefOr.undefined,
  defaultSelectedLabel:   MyUndefOr[Double | String] = MyUndefOr.undefined,
  defaultUpward:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultValue:           MyUndefOr[Value] = MyUndefOr.undefined,
  direction:              MyUndefOr[Direction] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
  floating:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  inline:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  item:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  labeled:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  lazyLoad:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  minCharacters:          MyUndefOr[Double] = MyUndefOr.undefined,
  multiple:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  noResultsMessage:       MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  onAddItem:              MyUndefOr[OnAddItem] = MyUndefOr.undefined,
  onBlur:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onBlurE:                MyUndefOr[OnBlur] = MyUndefOr.undefined,
  onChange:               MyUndefOr[FormDropdown.OnChange] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[FormDropdown.OnChangeE] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[OnClick] = MyUndefOr.undefined,
  onClose:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onCloseE:               MyUndefOr[OnClose] = MyUndefOr.undefined,
  onFocus:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onFocusE:               MyUndefOr[OnFocus] = MyUndefOr.undefined,
  onLabelClick:           MyUndefOr[Callback] = MyUndefOr.undefined,
  onLabelClickE:          MyUndefOr[OnLabelClick] = MyUndefOr.undefined,
  onMouseDown:            MyUndefOr[Callback] = MyUndefOr.undefined,
  onMouseDownE:           MyUndefOr[OnMouseDown] = MyUndefOr.undefined,
  onOpen:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onOpenE:                MyUndefOr[OnOpen] = MyUndefOr.undefined,
  onSearchChange:         MyUndefOr[OnSearchChange] = MyUndefOr.undefined,
  onSearchChangeE:        MyUndefOr[OnSearchChangeE] = MyUndefOr.undefined,
  open:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  openOnFocus:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  options:                Seq[Select.SelectItem],
  placeholder:            MyUndefOr[String] = MyUndefOr.undefined,
  pointing:               MyUndefOr[Pointing] = MyUndefOr.undefined,
  renderLabel:            MyUndefOr[RenderLabel] = MyUndefOr.undefined,
  required:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  scrolling:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  search:                 MyUndefOr[Boolean | SearchFunction] = MyUndefOr.undefined,
  searchInput:            MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  searchQuery:            MyUndefOr[String] = MyUndefOr.undefined,
  selectOnBlur:           MyUndefOr[Boolean] = MyUndefOr.undefined,
  selectOnNavigation:     MyUndefOr[Boolean] = MyUndefOr.undefined,
  selectedLabel:          MyUndefOr[Double | String] = MyUndefOr.undefined,
  simple:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[String | Double] = MyUndefOr.undefined,
  text:                   MyUndefOr[String] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[String] = MyUndefOr.undefined,
  trigger:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  upward:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  value:                  MyUndefOr[Value] = MyUndefOr.undefined,
  width:                  MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  wrapSelection:          MyUndefOr[Boolean] = MyUndefOr.undefined,
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
    additionLabel:        MyUndefOr[AdditionLabel] = MyUndefOr.undefined,
    additionPosition:     MyUndefOr[AdditionPosition] = MyUndefOr.undefined,
    allowAdditions:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    as:                   MyUndefOr[AsC] = MyUndefOr.undefined,
    basic:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    button:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:            MyUndefOr[String] = MyUndefOr.undefined,
    clazz:                MyUndefOr[Css] = MyUndefOr.undefined,
    clearable:            MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnBlur:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnChange:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnEscape:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    compact:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    content:              MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    control:              MyUndefOr[String] = MyUndefOr.undefined,
    deburr:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultOpen:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultSearchQuery:   MyUndefOr[String] = MyUndefOr.undefined,
    defaultSelectedLabel: MyUndefOr[Double | String] = MyUndefOr.undefined,
    defaultUpward:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultValue:         MyUndefOr[Value] = MyUndefOr.undefined,
    direction:            MyUndefOr[Direction] = MyUndefOr.undefined,
    disabled:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    error:                MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
    floating:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    fluid:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    header:               MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    icon:                 MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
    inline:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    item:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:                MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
    labeled:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    lazyLoad:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    loading:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    minCharacters:        MyUndefOr[Double] = MyUndefOr.undefined,
    multiple:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    noResultsMessage:     MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    onAddItem:            MyUndefOr[OnAddItem] = MyUndefOr.undefined,
    onBlur:               MyUndefOr[Callback] = MyUndefOr.undefined,
    onBlurE:              MyUndefOr[OnBlur] = MyUndefOr.undefined,
    onChange:             MyUndefOr[FormDropdown.OnChange] = MyUndefOr.undefined,
    onChangeE:            MyUndefOr[FormDropdown.OnChangeE] = MyUndefOr.undefined,
    onClick:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onClickE:             MyUndefOr[OnClick] = MyUndefOr.undefined,
    onClose:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onCloseE:             MyUndefOr[OnClose] = MyUndefOr.undefined,
    onFocus:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onFocusE:             MyUndefOr[OnFocus] = MyUndefOr.undefined,
    onLabelClick:         MyUndefOr[Callback] = MyUndefOr.undefined,
    onLabelClickE:        MyUndefOr[OnLabelClick] = MyUndefOr.undefined,
    onMouseDown:          MyUndefOr[Callback] = MyUndefOr.undefined,
    onMouseDownE:         MyUndefOr[OnMouseDown] = MyUndefOr.undefined,
    onOpen:               MyUndefOr[Callback] = MyUndefOr.undefined,
    onOpenE:              MyUndefOr[OnOpen] = MyUndefOr.undefined,
    onSearchChange:       MyUndefOr[OnSearchChange] = MyUndefOr.undefined,
    onSearchChangeE:      MyUndefOr[OnSearchChangeE] = MyUndefOr.undefined,
    open:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
    openOnFocus:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    options:              MyUndefOr[Seq[DropdownItem]] = MyUndefOr.undefined,
    placeholder:          MyUndefOr[String] = MyUndefOr.undefined,
    pointing:             MyUndefOr[Pointing] = MyUndefOr.undefined,
    renderLabel:          MyUndefOr[RenderLabel] = MyUndefOr.undefined,
    required:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    scrolling:            MyUndefOr[Boolean] = MyUndefOr.undefined,
    search:               MyUndefOr[Boolean | SearchFunction] = MyUndefOr.undefined,
    searchInput:          MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    searchQuery:          MyUndefOr[String] = MyUndefOr.undefined,
    selectOnBlur:         MyUndefOr[Boolean] = MyUndefOr.undefined,
    selectOnNavigation:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    selectedLabel:        MyUndefOr[Double | String] = MyUndefOr.undefined,
    simple:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    tabIndex:             MyUndefOr[String | Double] = MyUndefOr.undefined,
    text:                 MyUndefOr[String] = MyUndefOr.undefined,
    tpe:                  MyUndefOr[String] = MyUndefOr.undefined,
    trigger:              MyUndefOr[VdomNode] = MyUndefOr.undefined,
    upward:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    value:                MyUndefOr[Value] = MyUndefOr.undefined,
    width:                MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    wrapSelection:        MyUndefOr[Boolean] = MyUndefOr.undefined
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
