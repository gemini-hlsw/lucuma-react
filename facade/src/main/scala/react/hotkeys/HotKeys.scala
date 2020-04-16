package react.hotkeys

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import japgolly.scalajs.react._
import react.common._
import japgolly.scalajs.react.vdom.VdomElement

final case class HotKeys(
    keyMap: js.UndefOr[js.Object] = js.undefined,
    handlers: js.UndefOr[js.Object] = js.undefined,
    component: js.UndefOr[String] = js.undefined,
    tabIndex: js.UndefOr[Int] = js.undefined,
    allowChanges: js.UndefOr[Boolean] = js.undefined,
    //innerRef: js.UndefOr[Ref] = js.undefined,
    root: js.UndefOr[Boolean] = js.undefined,
    override val children: CtorType.ChildrenArgs = Seq.empty
) extends GenericComponentPC[HotKeys.HotKeysProps, HotKeys] {
  override protected def cprops = HotKeys.props(this)
  def withChildren(children: CtorType.ChildrenArgs) =
    copy(children = children)
  @inline def renderWith = HotKeys.component(cprops)
}

object HotKeys {

  @js.native
  trait HotKeysProps extends js.Object {
    var keyMap: js.UndefOr[js.Object] = js.native
    var handlers: js.UndefOr[js.Object] = js.native
    var component: js.UndefOr[String] = js.native
    var tabIndex: js.UndefOr[Int] = js.native
    var allowChanges: js.UndefOr[Boolean] = js.native
    //var innerRef: js.UndefOr[Ref] = js.native
    var root: js.UndefOr[Boolean] = js.native
  }

  def props(
      q: HotKeys
  ): HotKeysProps = {
    val p = (new js.Object).asInstanceOf[HotKeysProps]
    p.keyMap = q.keyMap
    p.handlers = q.handlers
    p.component = q.component
    p.tabIndex = q.tabIndex
    p.allowChanges = q.allowChanges
    // p.innerRef = q.innerRef
    p.root = q.root
    p
  }

  @JSImport("react-hotkeys", "HotKeys")
  @js.native
  object RawComponent extends js.Object

  private val component =
    JsComponent[HotKeysProps, Children.Varargs, Null](RawComponent)
}
