package com.quick.tor.controller.health

import com.quick.tor.controller.BaseController
import com.quick.tor.log.Logger
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing

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