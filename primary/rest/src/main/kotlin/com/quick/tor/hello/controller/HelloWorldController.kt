package com.quick.tor.hello.controller

import com.quick.tor.common.stringParameter
import com.quick.tor.controller.BaseController
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing

class HelloWorldController(
    application: Application
) : BaseController(application) {

    init {

        application.routing {
            get("/{name}") {
                val name = call.stringParameter("name")
                call.respond(HttpStatusCode.OK, mapOf("hello" to name))
            }
        }
    }
}

