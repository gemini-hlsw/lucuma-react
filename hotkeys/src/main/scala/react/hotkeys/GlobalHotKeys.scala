// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.hotkeys

import japgolly.scalajs.react._
import react.common._

import scala.scalajs.js

import js.annotation.JSImport

final case class GlobalHotKeys(
  keyMap:                js.UndefOr[KeyMap] = js.undefined,
  handlers:              js.UndefOr[Handlers] = js.undefined,
  allowChanges:          js.UndefOr[Boolean] = js.undefined,
  override val children: CtorType.ChildrenArgs = Seq.empty
) extends GenericComponentPC[GlobalHotKeys.GlobalHotKeysProps, GlobalHotKeys] {
  override protected def cprops                     = GlobalHotKeys.props(this)
  def withChildren(children: CtorType.ChildrenArgs) =
    copy(children = children)
  val component                                     = GlobalHotKeys.component
}

object GlobalHotKeys {

  @js.native
  trait GlobalHotKeysProps extends js.Object {
    var keyMap: js.UndefOr[js.Object]     = js.native
    var handlers: js.UndefOr[js.Object]   = js.native
    var allowChanges: js.UndefOr[Boolean] = js.native
  }

  def props(
    q: GlobalHotKeys
  ): GlobalHotKeysProps = {
    val p = (new js.Object).asInstanceOf[GlobalHotKeysProps]
    q.keyMap.foreach(v => p.keyMap = v.toJs)
    q.handlers.foreach(v => p.handlers = v.toJs)
    q.allowChanges.foreach(v => p.allowChanges = v)
    p
  }

  @JSImport("react-hotkeys", "GlobalHotKeys")
  @js.native
  object RawComponent extends js.Object

  private val component =
    JsComponent[GlobalHotKeysProps, Children.Varargs, Null](RawComponent)
}
