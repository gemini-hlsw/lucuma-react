package react.hotkeys

import scalajs.js
import js.annotation.JSImport
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.HtmlTagOf

final case class HotKeysConfiguration(
  logLevel:                               js.UndefOr[LogLevel]                      = js.undefined,
  defaultKeyEvent:                        js.UndefOr[KeyInstance]                   = js.undefined,
  defaultComponent:                       js.UndefOr[HtmlTagOf[_]]                  = js.undefined,
  defaultTabIndex:                        js.UndefOr[Int]                           = js.undefined,
  ignoreTags:                             js.UndefOr[Seq[HtmlTagOf[_]]]             = js.undefined,
  ignoreEventsCondition:                  js.UndefOr[ReactKeyboardEvent => Boolean] = js.undefined,
  ignoreKeymapAndHandlerChangesByDefault: js.UndefOr[Boolean]                       = js.undefined,
  ignoreRepeatedEventsWhenKeyHeldDown:    js.UndefOr[Boolean]                       = js.undefined,
  simulateMissingKeyPressEvents:          js.UndefOr[Boolean]                       = js.undefined,
  stopEventPropagationAfterHandling:      js.UndefOr[Boolean]                       = js.undefined,
  stopEventPropagationAfterIgnoring:      js.UndefOr[Boolean]                       = js.undefined,
  allowCombinationSubmatches:             js.UndefOr[Boolean]                       = js.undefined,
  customKeyCodes:                         js.UndefOr[Map[String, String]]           = js.undefined
) {
  def apply(): Unit = HotKeysConfigure(this)
}

object HotKeysConfigure {

  @js.native
  trait HotKeysConfigureParams extends js.Object {
    var logLevel: js.UndefOr[String]                                                 = js.native
    var defaultKeyEvent: js.UndefOr[String]                                          = js.native
    var defaultComponent: js.UndefOr[String]                                         = js.native
    var defaultTabIndex: js.UndefOr[Int]                                             = js.native
    var ignoreTags: js.UndefOr[js.Array[String]]                                     = js.native
    var ignoreEventsCondition: js.UndefOr[js.Function1[ReactKeyboardEvent, Boolean]] = js.native
    var ignoreKeymapAndHandlerChangesByDefault: js.UndefOr[Boolean]                  = js.native
    var ignoreRepeatedEventsWhenKeyHeldDown: js.UndefOr[Boolean]                     = js.native
    var simulateMissingKeyPressEvents: js.UndefOr[Boolean]                           = js.native
    var stopEventPropagationAfterHandling: js.UndefOr[Boolean]                       = js.native
    var stopEventPropagationAfterIgnoring: js.UndefOr[Boolean]                       = js.native
    var allowCombinationSubmatches: js.UndefOr[Boolean]                              = js.native
    var customKeyCodes: js.UndefOr[js.Object]                                        = js.native
  }

  def props(
    q: HotKeysConfiguration
  ): HotKeysConfigureParams = {
    val p = (new js.Object).asInstanceOf[HotKeysConfigureParams]
    q.logLevel.foreach(v => p.logLevel                 = v.name)
    q.defaultKeyEvent.foreach(v => p.defaultKeyEvent   = v.action)
    q.defaultComponent.foreach(v => p.defaultComponent = v.name)
    q.defaultTabIndex.foreach(v => p.defaultTabIndex   = v)
    q.ignoreTags.foreach(v => p.ignoreTags             = v.map(_.name).toJSArray)
    q.ignoreEventsCondition.foreach(v =>
      p.ignoreEventsCondition = v: js.Function1[ReactKeyboardEvent, Boolean]
    )
    q.ignoreKeymapAndHandlerChangesByDefault.foreach(v =>
      p.ignoreKeymapAndHandlerChangesByDefault = v
    )
    q.ignoreRepeatedEventsWhenKeyHeldDown.foreach(v => p.ignoreRepeatedEventsWhenKeyHeldDown = v)
    q.simulateMissingKeyPressEvents.foreach(v => p.simulateMissingKeyPressEvents             = v)
    q.stopEventPropagationAfterHandling.foreach(v => p.stopEventPropagationAfterHandling     = v)
    q.stopEventPropagationAfterIgnoring.foreach(v => p.stopEventPropagationAfterIgnoring     = v)
    q.allowCombinationSubmatches.foreach(v => p.allowCombinationSubmatches                   = v)
    q.customKeyCodes.foreach(v => p.customKeyCodes                                           = v.toJSDictionary.asInstanceOf[js.Object])
    p
  }

  @JSImport("react-hotkeys", "configure")
  @js.native
  object configure extends js.Object {
    def apply(conf: js.Object): Unit = js.native
  }

  def apply(conf: HotKeysConfiguration): Unit = configure(props(conf))
}
