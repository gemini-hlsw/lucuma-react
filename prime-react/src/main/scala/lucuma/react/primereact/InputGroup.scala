// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import lucuma.react.common.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*

case class InputGroup(mods: TagMod*) extends ReactFnProps(InputGroup.component):
  def apply(moreMods: TagMod*): InputGroup = InputGroup((mods ++ moreMods): _*)

object InputGroup:
  private type Props = InputGroup

  private val component = ScalaFnComponent[Props]: props =>
    <.div(Css("p-inputgroup"), props.mods.toTagMod)

  case class Addon(mods: TagMod*) extends ReactFnProps(Addon.component):
    def apply(moreMods: TagMod*): Addon = Addon((mods ++ moreMods): _*)

  object Addon:
    private type Props = Addon

    private val component = ScalaFnComponent[Props]: props =>
      <.span(Css("p-inputgroup-addon"), props.mods.toTagMod)
