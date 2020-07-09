package com.quick.tor.kafka

import com.quick.tor.kafka.consumer.consumerInsertUser
import io.ktor.application.Application
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.koin.ktor.ext.get

fun Application.installKafkaConsumers() {

    val consumerContext = newSingleThreadContext("consumerContext")

    launch(consumerContext) { consumerInsertUser(usePort = get()) }
}