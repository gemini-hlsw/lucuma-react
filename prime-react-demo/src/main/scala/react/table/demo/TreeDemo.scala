// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.primereact.Button
import react.primereact.Panel
import react.primereact.Tree

import scalajs.js

final case class TreeDemo() extends ReactFnProps[TreeDemo](TreeDemo.component)
object TreeDemo:
  private val component = ScalaFnComponent
    .withHooks[TreeDemo]
    .useState(
      Seq(
        Tree.Node(
          Tree.Id("1"),
          "Label1",
          icon = Css("pi pi-thumbs-up").htmlClass,
          children = Seq(
            Tree.Node(Tree.Id("1.1"), "Label1.1 (no icon)"),
            Tree.Node(Tree.Id("1.2"), "Label1.2", icon = Css("pi pi-send").htmlClass)
          )
        ),
        Tree.Node(Tree.Id("2"), "Label2", icon = Css("pi pi-hourglass").htmlClass),
        Tree.Node(
          Tree.Id("3"),
          "Label3",
          icon = Css("pi pi-thumbs-down").htmlClass,
          children = Seq(
            Tree.Node(Tree.Id("3.1"), "Label3.1", icon = Css("pi pi-send").htmlClass),
            Tree.Node(Tree.Id("3.2"), "Label3.2", icon = Css("pi pi-send").htmlClass)
          )
        )
      )
    )
    .render { (props, nodes) =>
      Panel(header = "Tree")(
        Button(
          label = "Add root node",
          onClick = nodes.modState(nodes =>
            val highestId = nodes.map(_.id.value.toInt).max
            val newId     = Tree.Id((highestId + 1).toString)
            nodes.appended(
              Tree.Node(newId, s"Label${newId.value}", icon = Css("pi pi-star").htmlClass)
            )
          )
        ),
        Tree(
          nodes.value,
          selectionMode = Tree.SelectionMode.Single,
          nodeTemplate = (i, _) => <.span(<.b("Hello there, "), i).rawNode,
          onSelect = (d, e) =>
            Callback {
              println(s"selected $d")
              js.Dynamic.global.console.log(e)
            }
        )
      )
    }