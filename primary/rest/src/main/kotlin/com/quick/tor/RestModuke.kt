package com.quick.tor

import com.quick.tor.controller.health.HealthCheckerController
import com.quick.tor.controller.user.UserController
import com.quick.tor.hello.controller.HelloWorldController
import io.ktor.server.application.Application
import org.koin.core.scope.Scope
import org.koin.dsl.module

val restModule = module(createdAtStart = true) {

    // applicationconfig
    single { RestConfig(application = getApplication()) }

    // controllers
    single { HelloWorldController(application = getApplication(), log = get()) }
    single { HealthCheckerController(application = getApplication(), log = get()) }
    single { UserController(application = getApplication(), log = get(), userPort = get()) }
}

private fun Scope.getApplication() = getProperty("application") as Application