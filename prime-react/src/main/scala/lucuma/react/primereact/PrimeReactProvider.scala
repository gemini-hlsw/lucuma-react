// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.anon.PartialAPIOptions
import lucuma.typed.primereact.apiApiMod.FilterMatchModeOptions
import lucuma.typed.primereact.apiApiMod.PrimeReactProviderProps
import lucuma.typed.primereact.components.PrimeReactProvider as CPrimeReactProvider
import org.scalajs.dom.*

import scalajs.js

case class PrimeReactProvider(
  /**
   * This option allows components with overlays like dropdowns or popups to be mounted into either
   * the component or any DOM element, such as document body and self.
   */
  appendTo:                        js.UndefOr[SelfPosition | HTMLElement] = js.undefined,
  /**
   * ZIndexes are managed automatically to make sure layering of overlay components work seamlessly
   * when combining multiple components. When autoZIndex is false, each group increments its zIndex
   * within itself.
   */
  autoZIndex:                      js.UndefOr[Boolean] = js.undefined,
  /**
   * This method is used to change the theme dynamically.
   * @param {string} currentTheme - The name of the current theme. Example 'lara-light-blue'
   * @param {string} newTheme - The name of the new theme to be applied. Example 'md-dark-deeppurple'
   * @param {string} linkElementId - The id of the link element to be updated.
   * @param {() => void} [callback] - Callback to invoke when the theme change is completed.
   */
  changeTheme:                     js.UndefOr[
    (Option[String], Option[String], Option[String], Option[Callback]) => Callback
  ] = js.undefined,
  /**
   * PrimeReact components utilize "react-transition-group" internally to implement animations.
   * Setting "cssTransition" to "false" disables all animations.
   * @defaultValue
   *   true
   */
  cssTransition:                   js.UndefOr[Boolean] = js.undefined,
  /** Default filter modes to display on DataTable filter menus. */
  filterMatchModeOptions:          js.UndefOr[FilterMatchModeOptions] = js.undefined,
  /**
   * Define behavior if the browser window is scrolled while displaying an overlay panel like a
   * Dropdown or Calendar. Depending on your organization's accessibility needs some prefer panels
   * to be closed on scrolling and some prefer the overlay follow the scroll.
   * @defaultValue
   *   false
   */
  hideOverlaysOnDocumentScrolling: js.UndefOr[Boolean] = js.undefined,
  /**
   * Input fields have two styles: default (outlined with borders) and filled (background-colored).
   * Applying 'p-input-filled' to an input's ancestor enables the filled style.
   */
  inputStyle:                      js.UndefOr[InputStyle] = js.undefined,
  /**
   * The locale configuration sets up the language and region specific preferences.
   * @defaultValue
   *   'en'
   */
  locale:                          js.UndefOr[String] = js.undefined,
  /** The nonce value to use on dynamically generated style elements. */
  nonce:                           js.UndefOr[String] = js.undefined,
  /**
   * Determines how null values are sorted.
   * @defaultValue
   *   1
   */
  nullSortOrder:                   js.UndefOr[Double] = js.undefined,
  /**
   * Ripple is an optional animation for the supported components such as buttons.
   * @defaultValue
   *   false
   */
  ripple:                          js.UndefOr[Boolean] = js.undefined
) extends ReactFnPropsWithChildren(PrimeReactProvider)

object PrimeReactProvider
    extends ReactFnComponentWithChildren[PrimeReactProvider]((props, children) =>
      extension [B](b:       B)
        def applyOrNot[A](a: js.UndefOr[A], f: (B, A) => B): B = a.fold(b)(a => f(b, a))

      val options: PartialAPIOptions =
        PartialAPIOptions()
          .applyOrNot(props.appendTo, _.setAppendTo(_))
          .applyOrNot(props.autoZIndex, _.setAutoZIndex(_))
          .applyOrNot(props.cssTransition, _.setCssTransition(_))
          .applyOrNot(props.filterMatchModeOptions, _.setFilterMatchModeOptions(_))
          .applyOrNot(
            props.changeTheme,
            (c, p) =>
              c.setChangeTheme((currentTheme, newTheme, linkElementId, callback) =>
                p(
                  currentTheme.toOption,
                  newTheme.toOption,
                  linkElementId.toOption,
                  callback.toOption.map(Callback.fromJsFn(_))
                )
              )
          )
          .applyOrNot(
            props.hideOverlaysOnDocumentScrolling,
            _.setHideOverlaysOnDocumentScrolling(_)
          )
          .applyOrNot(props.inputStyle, (c, p) => c.setInputStyle(p.toJs))
          .applyOrNot(props.locale, _.setLocale(_))
          .applyOrNot(props.nonce, _.setNonce(_))
          .applyOrNot(props.nullSortOrder, _.setNullSortOrder(_))
          .applyOrNot(props.ripple, _.setRipple(_))

      CPrimeReactProvider.withProps(
        PrimeReactProviderProps().setValue(options).setChildren(children)
      )
    )
