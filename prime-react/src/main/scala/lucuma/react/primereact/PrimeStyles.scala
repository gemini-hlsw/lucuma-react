// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import lucuma.react.common.*

trait PrimeStyles {

  // wrappers for primereact classes
  val Invalid: Css   = Css("p-invalid")
  val Component: Css = Css("p-component")

  // Input groups
  val InputGroup: Css      = Css("p-inputgroup")
  val InputGroupAddon: Css = Css("p-inputgroup-addon")

  // Button
  val Button: Css           = Css("p-button")
  val ButtonSmall: Css      = Css("p-button-sm")
  val ButtonNormal: Css     = Css.Empty
  val ButtonLarge: Css      = Css("p-button-lg")
  val ButtonIcon: Css       = Css("p-button-icon")
  val ButtonIconLeft: Css   = Css("p-button-icon-left")
  val ButtonIconRight: Css  = Css("p-button-icon-right")
  val ButtonIconTop: Css    = Css("p-button-icon-top")
  val ButtonIconBottom: Css = Css("p-button-icon-bottom")
  val ButtonVertical: Css   = Css("p-button-vertical")

  val ButtonPrimary: Css   = Css.Empty
  val ButtonSecondary: Css = Css("p-button-secondary")
  val ButtonSuccess: Css   = Css("p-button-success")
  val ButtonInfo: Css      = Css("p-button-info")
  val ButtonWarning: Css   = Css("p-button-warning")
  val ButtonHelp: Css      = Css("p-button-help")
  val ButtonDanger: Css    = Css("p-button-danger")

  val ButtonOutlined: Css = Css("p-button-outlined")
  val ButtonRaised: Css   = Css("p-button-raised")
  val ButtonRounded: Css  = Css("p-button-rounded")
  val ButtonText: Css     = Css("p-button-text")
  val ButtonIconOnly: Css = Css("p-button-icon-only")

  val ButtonSet: Css = Css("p-buttonset")

  // Tag
  val TagIcon: Css = Css("p-tag-icon")

  // Menus
  val MenuItemIcon: Css = Css("p-menuitem-icon")
  val MenuItemLink: Css = Css("p-menuitem-link")

  // Messages
  val MessageIcon: Css       = Css("p-message-icon")
  val InlineMessageIcon: Css = Css("p-inline-message-icon")

  // Sidebar
  val SidebarSmall: Css  = Css("p-sidebar-sm")
  val SidebarMedium: Css = Css("p-sidebar-md")
  val SidebarLarge: Css  = Css("p-sidebar-lg")

  val SidebarIcon: Css      = Css("p-sidebar-icon")
  val DialogHeaderIcon: Css = Css("p-dialog-header-icon")

  // Tree
  val TreeIcon: Css = Css("p-tree-icon")

  // Dialog
  val Dialog: Css        = Css("p-dialog")
  val DialogHeader: Css  = Css("p-dialog-header")
  val DialogContent: Css = Css("p-dialog-content")
  val DialogFooter: Css  = Css("p-dialog-footer")
}

object PrimeStyles extends PrimeStyles
