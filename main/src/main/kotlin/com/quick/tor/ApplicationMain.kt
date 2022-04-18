package com.quick.tor

import com.quick.tor.database.databaseModule
import com.quick.tor.database.installFlyway
import com.quick.tor.kafka.installKafkaConsumers
import com.quick.tor.producer.kafkaProducerModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.Koin

fun Application.inject() {
    install(Koin) {
        properties(mapOf("application" to this@inject))
        modules(restModule, sharedModule, domainModule, databaseModule, kafkaProducerModule)
    }
}

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8090) {
        inject()
        installFlyway()
        installKafkaConsumers()
    }
    server.start(wait = true)
}