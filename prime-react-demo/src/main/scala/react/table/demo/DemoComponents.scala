// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import cats.syntax.all._
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.HTMLElement
import react.common.*
import react.primereact.*
import react.primereact.hooks.all.*

object DemoComponents {
  case class SidebarOptions(
    visible:       Boolean,
    position:      Sidebar.Position,
    size:          Sidebar.Size,
    dismissable:   Boolean,
    closeOnEscape: Boolean,
    modal:         Boolean,
    fullScreen:    Boolean,
    blockScroll:   Boolean,
    showCloseIcon: Boolean
  )

  object SidebarOptions:
    def default = SidebarOptions(false,
                                 Sidebar.Position.Right,
                                 Sidebar.Size.Small,
                                 true,
                                 true,
                                 true,
                                 false,
                                 false,
                                 true
    )

  def mouseEntered(msg: String) = ^.onMouseEnter --> Callback.log(s"Mouse entered: $msg")

  val component =
    ScalaFnComponent
      .withHooks[Unit]
      .useState(Toast.Position.TopRight) // toast position
      .useState(false)                   // show dialog
      .useState(false)                   // panel collapsed
      .useState(SidebarOptions.default)  // sidebar options
      .useState(0)                       // tabview activeIndex
      .useState(0)                       // tabMenu activeIndex
      .usePopupMenuRef
      .useToastRef
      .render {
        (
          _,
          toastPosition,
          showDialog,
          panelCollapsed,
          sidebarOptions,
          tabView,
          tabMenu,
          popupMenuRef,
          toastRef
        ) =>
          val menuItems = List(
            MenuItem.SubMenu("SubMenu", icon = "pi pi-bolt")(
              MenuItem.Item("Item 1", icon = "pi pi-bolt")
            ),
            MenuItem.Separator,
            MenuItem.Item("Duck Duck Go", url = "http://duckduckgo.com") // , icon = Icons.DiceOne)
          )

          def showConfirmPopup(target: HTMLElement, message: String): Callback =
            ConfirmPopup
              .confirmPopup(
                target = target,
                message = message,
                acceptIcon = "pi pi-thumbs-up",
                rejectIcon = "pi pi-thumbs-down",
                onHide = s => Callback.log(s"Hiding ConfirmPopup with: $s"),
                dismissable = false
              )
              .show

          def showConfirmDialog: Callback =
            ConfirmDialog
              .confirmDialog(
                message = "Pops up where you tell it. See console for result of the dialog",
                acceptLabel = "You bet",
                rejectLabel = "NO way",
                acceptIcon = "pi pi-thumbs-up",
                rejectIcon = "pi pi-thumbs-down",
                onHide = s => Callback.log(s"Hiding ConfirmDialog with: $s"),
                header = <.h1("Big Header"),
                position = DialogPosition.Bottom
              )

          <.div(
            Toolbar(
              left = Button(label = "Toolbar Left Button",
                            size = Button.Size.Small,
                            onClick = toastRef.show(
                              MessageItem(summary = "Clicked", detail = "Top Left Button")
                            )
              ).withMods(mouseEntered("Toolbar")),
              right = <.div(
                DemoStyles.HorizontalStack,
                Button(
                  label = "Toolbar Right Button",
                  size = Button.Size.Small,
                  onClick = toastRef.show(
                    MessageItem(summary = "Clicked", detail = "Top Right Button")
                  )
                ),
                Button(
                  icon = "pi pi-bars",
                  size = Button.Size.Small,
                  onClickE = popupMenuRef.toggle
                )
              )
            ),
            <.h1("Demo for lucuma-prime-react"),
            <.div(
              DemoStyles.VerticalStack,
              Card(
                title = "I am a Card title",
                subTitle = "And subtitle",
                header = "The card header could be an image?",
                footer = <.div(
                  DemoStyles.HorizontalStack,
                  Button(
                    onClick = showDialog.setState(true),
                    size = Button.Size.Small,
                    label = "Show Dialog",
                    severity = Button.Severity.Warning
                  ),
                  Button(
                    onClickE = e =>
                      showConfirmPopup(
                        e.currentTarget,
                        "Pops up relative to the target. See console for result of the dialog"
                      ),
                    label = "Show ConfirmPopup"
                  ),
                  Button(onClick = showConfirmDialog,
                         label = "Show ConfirmDialog",
                         severity = Button.Severity.Help,
                         outlined = true
                  )
                )
              )(
                <.div(
                  DemoStyles.VerticalStack,
                  <.h2("Some Toast"),
                  <.div(
                    DemoStyles.FormColumn,
                    <.label("Toast Position",
                            ^.htmlFor := "toast-position",
                            DemoStyles.FormFieldLabel
                    ),
                    SelectButton(
                      id = "toast-position",
                      value = toastPosition.value,
                      options = List(
                        SelectItem(Toast.Position.TopRight, "Top Right"),
                        SelectItem(Toast.Position.TopLeft, "Top Left"),
                        SelectItem(Toast.Position.BottomRight, "Bottom Right"),
                        SelectItem(Toast.Position.BottomLeft, "Bottom Left")
                      ),
                      onChange = toastPosition.setState
                    )
                  ),
                  <.div(
                    DemoStyles.HorizontalStack,
                    Button(
                      label = "Info Toast",
                      severity = Button.Severity.Info,
                      onClick = toastRef
                        .show(MessageItem(summary = "Information", detail = "Informative Toast"))
                    ),
                    Button(
                      label = "Success Toast",
                      severity = Button.Severity.Success,
                      onClick = toastRef
                        .show(
                          MessageItem(summary = "Success!",
                                      detail = "Congratulations",
                                      severity = Message.Severity.Success
                          )
                        )
                    ),
                    Button(
                      label = "Warning Toast",
                      severity = Button.Severity.Warning,
                      onClick = toastRef
                        .show(
                          MessageItem(summary = "Warning!",
                                      detail = "I told you so!",
                                      severity = Message.Severity.Warning
                          )
                        )
                    ),
                    Button(
                      label = "Error Toast",
                      severity = Button.Severity.Danger,
                      onClick = toastRef
                        .show(
                          MessageItem(summary = "Error!",
                                      detail = "You shouldn't have done that!",
                                      severity = Message.Severity.Error
                          )
                        )
                    )
                  ),
                  <.div(
                    DemoStyles.HorizontalStack,
                    Button(
                      label = "Long Lived Toast",
                      icon = "pi pi-hourglass",
                      onClick = toastRef.show(
                        MessageItem(summary = "Long Live Toast!",
                                    detail = "10 seconds",
                                    life = 10000
                        )
                      )
                    ),
                    Button(
                      label = "Short Lived Toast",
                      icon = "pi pi-stopwatch",
                      iconPos = Button.IconPosition.Right,
                      onClick = toastRef.show(
                        MessageItem(summary = "It Dies Young", detail = "0.5 seconds", life = 500)
                      )
                    ),
                    Button(
                      label = "Sticky Toast",
                      onClick = toastRef.show(
                        MessageItem(summary = "Sticky!", detail = "Honey, perhaps?", sticky = true)
                      )
                    )
                  ),
                  <.div(
                    DemoStyles.HorizontalStack,
                    Button(
                      label = "Multiple Toasts",
                      onClick = toastRef.show(
                        MessageItem(summary = "Toast 1", detail = "The first"),
                        MessageItem(summary = "Toast 2", detail = "The second"),
                        MessageItem(summary = "Toast 3", detail = "The third")
                      )
                    ),
                    Button(label = "Non-closable Toast",
                           onClick = toastRef.show(
                             MessageItem(summary = "Can't close it.",
                                         detail = "You have to wait",
                                         closable = false
                             )
                           )
                    ),
                    Button(label = "Toast With Content",
                           onClick = toastRef.show(
                             MessageItem(content = <.h1("Big Content"))
                           )
                    )
                  ),
                  <.div(
                    DemoStyles.HorizontalStack,
                    Button(
                      label = "Replace All Toasts",
                      icon = "pi pi-replay",
                      iconPos = Button.IconPosition.Top,
                      onClick = toastRef.replace(
                        MessageItem(summary = "Replacement 1", detail = "The first"),
                        MessageItem(summary = "Replacement 2", detail = "The second"),
                        MessageItem(summary = "Replacement 3", detail = "The third")
                      )
                    ),
                    Button(label = "Clear all toasts",
                           icon = "pi pi-ban",
                           iconPos = Button.IconPosition.Bottom,
                           onClick = toastRef.clear()
                    )
                  )
                )
              ),
              Panel(
                header = React.Fragment(<.div("A collapsable Panel containing a Splitter"),
                                        <.small("* See the console for event information")
                ),
                toggleable = true,
                collapsed = panelCollapsed.value,
                onCollapse = Callback.log("Panel onCollapse"),
                onExpand = Callback.log("Panel onExpand"),
                onToggle = b => Callback.log(s"Panel onToggle($b)") >> panelCollapsed.setState(b)
              ).withMods(mouseEntered("Panel"))(
                Splitter(onResizeEnd =
                  (left, right) => Callback.log(s"Splitter onResizeEnd($left, $right)")
                ).withMods(mouseEntered("Splitter"))(
                  SplitterPanel(size = 30)(
                    mouseEntered("SplitterPanel"),
                    Button(label = "Open Sidebar",
                           onClick = sidebarOptions.modState(_.copy(visible = true))
                    )
                  ),
                  SplitterPanel(size = 70)(
                    <.h2("Sidebar Options"),
                    <.div(
                      DemoStyles.FormColumn,
                      <.label("Position",
                              ^.htmlFor          := "sidebar-position",
                              DemoStyles.FormFieldLabel
                      ),
                      SelectButton(
                        id = "sidebar-position",
                        value = sidebarOptions.value.position,
                        options = SelectItem
                          .fromTupleList[Sidebar.Position](
                            List(
                              (Sidebar.Position.Top, "Top"),
                              (Sidebar.Position.Bottom, "Bottom"),
                              (Sidebar.Position.Left, "Left"),
                              (Sidebar.Position.Right, "Right")
                            )
                          ),
                        onChange = v => sidebarOptions.modState(_.copy(position = v))
                      ),
                      <.label("Size", ^.htmlFor  := "sidebar-size", DemoStyles.FormFieldLabel),
                      SelectButton(
                        id = "sidebar-size",
                        value = sidebarOptions.value.size,
                        options = SelectItem
                          .fromTupleList[Sidebar.Size](
                            List(
                              (Sidebar.Size.Small, "Small"),
                              (Sidebar.Size.Medium, "Medium"),
                              (Sidebar.Size.Large, "Large")
                            )
                          ),
                        onChange = v => sidebarOptions.modState(_.copy(size = v))
                      ),
                      <.label("Close On Escape",
                              ^.htmlFor          := "sidebar-closeonescape",
                              DemoStyles.FormFieldLabel
                      ),
                      InputSwitch(
                        inputId = "sidebar-closeonescape",
                        checked = sidebarOptions.value.closeOnEscape,
                        onChange = v => sidebarOptions.modState(_.copy(closeOnEscape = v))
                      ),
                      <.label("Dismissable",
                              ^.htmlFor          := "sidebar-dismissable",
                              DemoStyles.FormFieldLabel
                      ),
                      InputSwitch(
                        inputId = "sidebar-dismissable",
                        checked = sidebarOptions.value.dismissable,
                        onChange = v => sidebarOptions.modState(_.copy(dismissable = v))
                      ),
                      <.label("Modal", ^.htmlFor := "sidebar-modal", DemoStyles.FormFieldLabel),
                      InputSwitch(
                        inputId = "sidebar-modal",
                        checked = sidebarOptions.value.modal,
                        onChange = v => sidebarOptions.modState(_.copy(modal = v))
                      ),
                      <.label("Full Screen",
                              ^.htmlFor          := "sidebar-fullscreen",
                              DemoStyles.FormFieldLabel
                      ),
                      InputSwitch(
                        inputId = "sidebar-fullscreen",
                        checked = sidebarOptions.value.fullScreen,
                        onChange = v => sidebarOptions.modState(_.copy(fullScreen = v))
                      ),
                      <.label("Block Scroll",
                              ^.htmlFor          := "sidebar-blockscroll",
                              DemoStyles.FormFieldLabel
                      ),
                      InputSwitch(
                        inputId = "sidebar-blockscroll",
                        checked = sidebarOptions.value.blockScroll,
                        onChange = v => sidebarOptions.modState(_.copy(blockScroll = v))
                      ),
                      <.label("Show Close Icon",
                              ^.htmlFor          := "sidebar-closeicon",
                              DemoStyles.FormFieldLabel
                      ),
                      InputSwitch(
                        inputId = "sidebar-closeicon",
                        checked = sidebarOptions.value.showCloseIcon,
                        onChange = v => sidebarOptions.modState(_.copy(showCloseIcon = v))
                      )
                    )
                  )
                )
              ),
              Panel(header = "Panel containing an Accordion. There is also AccordionMultiple.")
                .withMods(mouseEntered("Accordion"))(
                  Accordion(activeIndex = 0)(
                    AccordionTab(header = "Polka")(
                      "The polka is originally a Czech dance and genre of dance music familiar throughout all of Europe and the Americas. It originated in the middle of the nineteenth century in German and Austrian influenced Bohemia, now part of the Czech Republic. The polka remains a popular folk music genre in many western countries. [From Wikipedia]"
                    ),
                    AccordionTab(header = "Zydeco")(
                      "Zydeco (/ˈzaɪdɪˌkoʊ/ ZY-dih-koh or /ˈzaɪdiˌkoʊ/ ZY-dee-koh, French: Zarico) is a music genre that evolved in southwest Louisiana by French Creole speakers which blends blues, rhythm and blues, and music indigenous to the Louisiana Creoles and the Native American people of Louisiana. Although it is distinct in origin from the Cajun music of Louisiana, the two forms influenced each other, forming a complex of genres native to the region. [From Wikipedia]"
                    ),
                    AccordionTab(header = "Weird Al")(mouseEntered("Weird Al"),
                                                      "Well, Weird Al. What else can we say?"
                    )
                  )
                ),
              TabView(
                // I don't think you need to set `activeIndex` if you don't specify `onTabChange`
                activeIndex = tabView.value,
                onTabChange =
                  idx => Callback.log(s"TabView onTabChange: $idx") >> tabView.setState(idx),
                onTabClose = idx => Callback.log(s"TabView onTabClose: $idx")
              ).withMods(mouseEntered("TabView"))(
                TabPanel(header = "Simple Header")(mouseEntered("TabPanel"), "Simple Tab Contents"),
                TabPanel(header = <.div(DemoStyles.HorizontalStack, "TagMod", <.small("Header")))(
                  "Special Header Contents"
                ),
                TabPanel(header = "Closable Tab", leftIcon = "pi pi-bolt", closable = true)(
                  "I'm not sure how useful Closable Tabs will be..."
                ),
                TabPanel(header = "Disabled Tab", disabled = true)(
                  "You shouldn't be able to see this!"
                )
              ),
              Panel(header = "Some Menus - see PopupMenu in the top ToolBar")(
                <.div(
                  DemoStyles.VerticalStack,
                  <.label("Inline Menu", DemoStyles.FormFieldLabel),
                  InlineMenu(model = menuItems)(mouseEntered("InlineMenu")),
                  <.label("Tab Menu", DemoStyles.FormFieldLabel),
                  TabMenu(
                    model = List(MenuItem.Item("Tab 1", icon = "pi pi-hourglass"),
                                 MenuItem.Item("Tab 2", "pi pi-send"),
                                 MenuItem.Item("Tab 3", command = Callback.log("Tab 3 clicked"))
                    ),
                    activeIndex = tabMenu.value,
                    onTabChange = (i, mi) =>
                      Callback.log(s"Changed tab to $i: ${mi.label}") >> tabMenu.setState(i)
                  )(mouseEntered("TabMenu"))
                )
              ),
              DemoControlsPanel(),
              Dialog(
                onHide = showDialog.setState(false),
                visible = showDialog.value,
                header = "A Dialog",
                footer = Button(onClick = showDialog.setState(false),
                                label = "Close Me",
                                size = Button.Size.Small,
                                rounded = true,
                                outlined = true
                ),
                position = DialogPosition.Top,
                dismissableMask = true,
                draggable = true
              )("You can drag me around!"),
              ConfirmPopup(),
              ConfirmDialog(),
              PopupMenu(
                menuItems,
                onShow = Callback.log("Showing PopupMenu"),
                onHide = Callback.log("Hiding PopupMenu")
              ).withMods(
                ^.onMouseEnter --> Callback.log("Mouse entered!"),
                ^.onMouseLeave --> Callback.log("Mouse left!")
              ).withRef(popupMenuRef.ref),
              Sidebar(
                visible = sidebarOptions.value.visible,
                onHide = sidebarOptions.modState(_.copy(visible = false)),
                position = sidebarOptions.value.position,
                size = sidebarOptions.value.size,
                dismissable = sidebarOptions.value.dismissable,
                closeOnEscape = sidebarOptions.value.closeOnEscape,
                modal = sidebarOptions.value.modal,
                fullScreen = sidebarOptions.value.fullScreen,
                blockScroll = sidebarOptions.value.blockScroll,
                showCloseIcon = sidebarOptions.value.showCloseIcon
              ).withMods(mouseEntered("Sidebar"))(<.div(<.div("Some stuff"), <.div("More stuff"))),
              Toast(position = toastPosition.value).withRef(toastRef.ref)
            )
          )
      }
}
