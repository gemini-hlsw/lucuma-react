import sbt._
import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.jsenv._

import HackedJSDOMNodeJSEnv._

class HackedJSDOMNodeJSEnv extends JSDOMNodeJSEnv {
  override def startWithCom(
    input:     Seq[Input],
    runConfig: RunConfig,
    onMessage: String => Unit
  ): JSComRun = super.startWithCom(globalRequire +: hackInput(input), runConfig, onMessage)

  def hackInput(input: Seq[Input]) =
    input.map {
      case cjs @ Input.CommonJSModule(module) =>
        Input.Script(module)
      case other                              => other
    }
}

object HackedJSDOMNodeJSEnv {
  private val globalRequire = {
    val tmp = IO.createTemporaryDirectory / "require.js"
    IO.write(
      tmp,
      """|const outerRealmFunctionConstructor = Node.constructor;
         |window.require = new outerRealmFunctionConstructor("return require")();
         |""".stripMargin
    )
    Input.Script(tmp.toPath)
  }
}
