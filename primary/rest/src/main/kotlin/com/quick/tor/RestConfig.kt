package com.quick.tor

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson

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
