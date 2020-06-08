package com.quick.tor.common

import com.quick.tor.common.exceptions.RestMissingRequestParameterException
import io.ktor.application.ApplicationCall

internal fun ApplicationCall.stringParameter(name: String) =
    this.parameters[name] ?: throw RestMissingRequestParameterException(paramName = name)