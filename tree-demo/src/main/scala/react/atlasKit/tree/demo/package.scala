// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.atlasKit.tree

import react.atlasKit.tree.Tree

package demo {
  case class DemoData(title: String)
}

package object demo {
  // https://bitbucket.org/atlassian/atlaskit-mk-2/src/master/packages/core/tree/mockdata/treeWithTwoBranches.ts
  val treeWithTwoBranches: Tree.Data[DemoData] = Tree.Data(
    rootId = "1",
    items = Map(
      "1"     -> Tree.Item("1",
                       List("1-1", "1-2"),
                       hasChildren = true,
                       isExpanded = true,
                       isChildrenLoading = false,
                       DemoData("root")
      ),
      "1-1"   -> Tree.Item("1-1",
                         List("1-1-1", "1-1-2"),
                         hasChildren = true,
                         isExpanded = true,
                         isChildrenLoading = false,
                         DemoData("First parent")
      ),
      "1-2"   -> Tree.Item("1-2",
                         List("1-2-1", "1-2-2"),
                         hasChildren = true,
                         isExpanded = true,
                         isChildrenLoading = false,
                         DemoData("Second parent")
      ),
      "1-1-1" -> Tree.Item("1-1-1",
                           List.empty,
                           hasChildren = false,
                           isExpanded = false,
                           isChildrenLoading = false,
                           DemoData("Child one")
      ),
      "1-1-2" -> Tree.Item("1-1-2",
                           List.empty,
                           hasChildren = false,
                           isExpanded = false,
                           isChildrenLoading = false,
                           DemoData("Child two")
      ),
      "1-2-1" -> Tree.Item("1-2-1",
                           List.empty,
                           hasChildren = false,
                           isExpanded = false,
                           isChildrenLoading = false,
                           DemoData("Child three")
      ),
      "1-2-2" -> Tree.Item("1-2-2",
                           List.empty,
                           hasChildren = false,
                           isExpanded = false,
                           isChildrenLoading = false,
                           DemoData("Child four")
      )
    )
  )
}
