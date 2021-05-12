package reactST.reactTable.mod

import japgolly.scalajs.react.raw.React.ComponentClassP
import japgolly.scalajs.react.vdom.VdomElement
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation.{ JSBracketAccess, JSGlobal, JSGlobalScope, JSImport, JSName }

@js.native
trait UseTableColumnFooter[D /* <: js.Object */ ] extends StObject {
  var Footer: js.UndefOr[Renderer[HeaderProps[D]]] = js.native
}

object UseTableColumnFooter {

  @scala.inline
  implicit class UseTableColumnFooterMutableBuilder[Self <: UseTableColumnFooter[
    _
  ], D /* <: js.Object */ ](val x: Self with UseTableColumnFooter[D])
      extends AnyVal {

    @scala.inline
    def setFooter(value: Renderer[HeaderProps[D]]): Self =
      StObject.set(x, "Footer", value.asInstanceOf[js.Any])

    @scala.inline
    def setFooterComponentClass(value: ComponentClassP[HeaderProps[D] with js.Object]): Self =
      StObject.set(x, "Footer", value.asInstanceOf[js.Any])

    @scala.inline
    def setFooterUndefined: Self = StObject.set(x, "Footer", js.undefined)

    @scala.inline
    def setFooterVdomElement(value: VdomElement): Self =
      StObject.set(x, "Footer", value.rawElement.asInstanceOf[js.Any])
  }
}
