package react.hotkeys

import scalajs.js
import js.|
import js.JSConverters._

class KeyMap {
  private val jsMap: js.Dictionary[KeySpec] = js.Dictionary[KeySpec]()

  def add(name: String, key: KeySpec): KeyMap = {
    jsMap.addOne(name -> key)
    this
  }

  def toJs: js.Object =
    jsMap.asInstanceOf[js.Object]
}

object KeyMap {
  def apply(maps: (String, KeySpec)*): KeyMap =
    maps.foldLeft(new KeyMap) { case (keyMap, (name, key)) => keyMap.add(name, key) }
}
