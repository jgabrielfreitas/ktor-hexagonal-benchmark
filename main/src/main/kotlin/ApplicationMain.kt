import com.quick.tor.restModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.Koin

fun Application.module() {
    install(Koin) {
        properties(mapOf("application" to this@module))
        modules(restModule)
    }
}

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        module()
    }
    server.start(wait = true)
}