package com.quick.tor.controller.user

import com.quick.tor.controller.BaseController
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.port.primary.UserPort
import io.ktor.application.Application

class UserController(
    application: Application,
    log: Logger,
    private val userPort: UserPort
) : BaseController(application, log) {
}