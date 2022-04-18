package com.quick.tor.controller.health

import com.quick.tor.controller.BaseController
import com.quick.tor.log.Logger
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

class HealthCheckerController(
    application: Application,
    log: Logger
) : BaseController(application, log) {

    init {

        application.routing {

            get("health") {
                log.info("calling health controller")
                call.respond(mapOf("status" to "UP"))
            }
        }
    }
}