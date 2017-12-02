import play.sbt.PlayRunHook
import scala.sys.process.Process

object RunSubProcess {
  def apply(command: String): PlayRunHook = {

    object RunSubProcessHook extends PlayRunHook {

      var process: Option[Process] = None

      override def beforeStarted(): Unit = {
        process = Some(Process(command).run)
      }

      override def afterStopped(): Unit = {
        process.foreach(p => p.destroy())
        process = None
      }
    }

    RunSubProcessHook
  }
}
