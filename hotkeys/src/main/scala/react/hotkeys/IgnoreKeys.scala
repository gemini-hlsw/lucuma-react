// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.hotkeys

import japgolly.scalajs.react.*
import react.common.*

import scalajs.js
import js.annotation.JSImport

// This is not working if "only" or "except" are specified, resulting in a runtime exception.
// Haven't figured out the internals in order to pass properties another way.
final case class IgnoreKeys(
  only:                  js.UndefOr[String | js.Array[String]] = js.undefined,
  except:                js.UndefOr[String | js.Array[String]] = js.undefined,
  override val children: CtorType.ChildrenArgs = Seq.empty
) extends GenericComponentPC[IgnoreKeys.IgnoreKeysProps, IgnoreKeys] {
  override protected def cprops                     = IgnoreKeys.props(this)
  def withChildren(children: CtorType.ChildrenArgs) =
    copy(children = children)
  val component                                     = IgnoreKeys.component
}

object IgnoreKeys {

  @js.native
  trait IgnoreKeysProps extends js.Object {
    var only: js.UndefOr[String | js.Array[String]]   = js.native
    var except: js.UndefOr[String | js.Array[String]] = js.native
  }

  def props(
    q: IgnoreKeys
  ): IgnoreKeysProps = {
    val p = (new js.Object).asInstanceOf[IgnoreKeysProps]
    q.only.foreach((v: String | js.Array[String]) => p.only = v)
    q.except.foreach((v: String | js.Array[String]) => p.except = v)
    p
  }

  @JSImport("react-hotkeys", "IgnoreKeys")
  @js.native
  object RawComponent extends js.Object

  private val component =
    JsComponent[IgnoreKeysProps, Children.Varargs, Null](RawComponent)

}
