package com.quick.tor

import io.ktor.serialization.gson.gson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

class RestConfig(
    application: Application
) {

    init {

        application.apply {

            install(ContentNegotiation) {
                gson { }
            }
        }
    }
}
