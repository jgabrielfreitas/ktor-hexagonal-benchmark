package com.quick.tor.controller.user

import com.quick.tor.common.uuidParameter
import com.quick.tor.controller.BaseController
import com.quick.tor.controller.user.dto.UserDTO
import com.quick.tor.controller.user.dto.toDto
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.port.primary.UserPort
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.koin.ktor.ext.inject

class UserController(
    application: Application,
    log: Logger
) : BaseController(application, log) {

    private val userPort by application.inject<UserPort>()

    init {

        application.routing {

            route("users") {

                get("{id}") {
                    val id = call.uuidParameter("id")
                    val user = userPort.findById(id)
                    if (user == null) call.respond(NotFound, "")
                    else call.respond(HttpStatusCode.OK, user.toDto())
                }

                post("/") {
                    val userDto = call.receive<UserDTO>()
                    val userSaveResult = userPort.save(userDto.toModel())
                    call.respond(HttpStatusCode.OK, userSaveResult!!.toDto())
                }

                put("{id}") {
                    val id = call.uuidParameter("id")
                    val userDto = call.receive<UserDTO>().withId(id)
                    val userUpdateResult = userPort.update(userDto.toModel())
                    if (userUpdateResult == null) call.respond(NotFound, "")
                    else call.respond(HttpStatusCode.OK, userUpdateResult.toDto())
                }
            }
        }
    }
}