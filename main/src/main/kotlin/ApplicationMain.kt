import com.quick.tor.database.databaseModule
import com.quick.tor.database.installFlyway
import com.quick.tor.domainModule
import com.quick.tor.restModule
import com.quick.tor.sharedModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.Koin

fun Application.inject() {
    install(Koin) {
        properties(mapOf("application" to this@inject))
        modules(restModule, sharedModule, domainModule, databaseModule)
    }
}

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8090) {
        inject()
        installFlyway()
    }
    server.start(wait = true)
}