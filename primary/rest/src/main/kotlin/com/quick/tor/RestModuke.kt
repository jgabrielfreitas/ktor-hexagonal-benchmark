package com.quick.tor

import com.quick.tor.hello.controller.HelloWorldController
import io.ktor.application.Application
import org.koin.core.scope.Scope
import org.koin.dsl.module

val restModule = module(createdAtStart = true) {

    // applicationconfig
    single { RestConfig(application = getApplication()) }

    // controllers
    single { HelloWorldController(application = getApplication()) }
}

private fun Scope.getApplication() = getProperty("application") as Application