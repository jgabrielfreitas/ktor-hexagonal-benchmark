package com.quick.tor.hello.controller

import com.quick.tor.common.stringParameter
import com.quick.tor.controller.BaseController
import com.quick.tor.log.Logger
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

class HelloWorldController(
    application: Application,
    log: Logger
) : BaseController(application, log) {

    init {

        application.routing {
            get("/{name}") {
                val name = call.stringParameter("name")
                call.respond(HttpStatusCode.OK, mapOf("hello" to name))
            }
        }
    }
}

