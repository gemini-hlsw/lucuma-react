package react.hotkeys

import scalajs.js

class Handlers {
  private val jsMap: js.Dictionary[Handler] = js.Dictionary[Handler]()

  def add(name: String, h: Handler): Handlers = {
    jsMap.addOne(name -> h)
    this
  }

  def toJs: js.Object =
    jsMap.asInstanceOf[js.Object]
}

object Handlers {
  def apply(handlers: (String, Handler)*): Handlers =
    handlers.foldLeft(new Handlers) { case (handlers, (name, h)) => handlers.add(name, h) }
}
