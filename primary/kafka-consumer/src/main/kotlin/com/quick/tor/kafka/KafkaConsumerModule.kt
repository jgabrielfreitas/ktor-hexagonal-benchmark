package com.quick.tor.kafka

import com.quick.tor.kafka.consumer.consumerInsertUser
import io.ktor.application.Application
import kotlinx.coroutines.launch
import org.koin.ktor.ext.get

fun Application.installKafkaConsumers() {
    launch { consumerInsertUser(usePort = get()) }
}