package inject

import akka.actor.ActorSystem
import com.google
import com.google.inject.{AbstractModule, Guice, Provider}
import play.api.db.slick.DatabaseConfigProvider
import play.api.{Application, Configuration}
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext

trait Injector {
  protected val injector: google.inject.Injector = Guice.createInjector(new MyAppModule)
}

trait ConfigurationInjector extends Injector {
  protected implicit val conf: Configuration = injector.getInstance(classOf[Configuration])
}
trait ExecutionContextInjector extends Injector {
  protected implicit val ec: ExecutionContext = injector.getInstance(classOf[ExecutionContext])
}
trait WsClientInjector extends Injector {
  protected implicit val wsClient: WSClient = injector.getInstance(classOf[WSClient])
}
trait AppProviderInjector extends Injector {
  protected implicit val appProvider: Provider[Application] = injector.getInstance(classOf[Provider[Application]])
}
trait ActorSystemInjector extends Injector {
  protected implicit val system: ActorSystem = injector.getInstance(classOf[ActorSystem])
}
trait DefaultDatabaseConfigInjector extends Injector {
  protected implicit val dbConfig: DatabaseConfigProvider = injector.getInstance(classOf[DatabaseConfigProvider])
}

class MyAppModule extends AbstractModule {

  override def configure(): Unit = {
  }

}