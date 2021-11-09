package react.datepicker

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.ReactEventFrom
import lucuma.reactDatepicker.mod.ReactDatePickerProps
import lucuma.reactDatepicker.mod.default
import org.scalajs.dom.Element
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation.{ JSBracketAccess, JSGlobal, JSGlobalScope, JSImport, JSName }
import lucuma.reactDatepicker.components.SharedBuilder_ReactDatePickerProps_1216478921

// Copy+pasted from ST generated facade.
// We don't use ST generated one since it includes uppercase and lowercase definitions, which messes up the compiler
// in case-insensitive filesystems.
object Datepicker {

  @scala.inline
  def apply[CustomModifierNames /* <: String */ ](
    onChange: (
      js.UndefOr[DateOrRange],
      js.UndefOr[ReactEventFrom[js.Any with Element]]
    ) => Callback
  ): SharedBuilder_ReactDatePickerProps_1216478921[
    lucuma.reactDatepicker.mod.ReactDatePicker[CustomModifierNames],
    CustomModifierNames
  ] = {
    val __props = js.Dynamic.literal(onChange =
      js.Any.fromFunction2(
        (
          t0: js.UndefOr[
            js.Date | /* for selectsRange */ Null | (js.Tuple2[js.Date | Null, js.Date | Null])
          ],
          t1: js.UndefOr[ReactEventFrom[js.Any with Element]]
        ) => (onChange(t0, t1)).runNow()
      )
    )
    new SharedBuilder_ReactDatePickerProps_1216478921[
      lucuma.reactDatepicker.mod.ReactDatePicker[CustomModifierNames],
      CustomModifierNames
    ](js.Array(this.component, __props.asInstanceOf[ReactDatePickerProps[CustomModifierNames]]))
  }

  @JSImport("react-datepicker", JSImport.Default)
  @js.native
  val component: js.Object = js.native

  def withProps[CustomModifierNames /* <: String */ ](
    p: ReactDatePickerProps[CustomModifierNames]
  ): SharedBuilder_ReactDatePickerProps_1216478921[
    lucuma.reactDatepicker.mod.ReactDatePicker[CustomModifierNames],
    CustomModifierNames
  ] = new SharedBuilder_ReactDatePickerProps_1216478921[
    lucuma.reactDatepicker.mod.ReactDatePicker[CustomModifierNames],
    CustomModifierNames
  ](js.Array(this.component, p.asInstanceOf[js.Any]))
}
