package com.quick.tor.controller

import com.quick.tor.log.Logger
import io.ktor.server.application.Application

open class BaseController(
    protected val application: Application,
    protected val log: Logger
) {
}