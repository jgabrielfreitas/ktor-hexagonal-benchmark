package com.quick.tor.kafka

import com.quick.tor.config.ApplicationConfig
import com.quick.tor.kafka.consumer.consumerInsertUser
import io.ktor.application.Application
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.koin.ktor.ext.get

fun Application.installKafkaConsumers() {

    val kafkaProducerConfig = get<ApplicationConfig>().config.getConfig("kafka-consumer")
    val bootstrapServers = kafkaProducerConfig.getString("bootstrapServers")
    val schemaUrl = kafkaProducerConfig.getString("schemaUrl")

    val consumerContext = newSingleThreadContext("consumerContext")

    launch(consumerContext) {
        consumerInsertUser(bootstrapServers = bootstrapServers, schemaUrl = schemaUrl, usePort = get(), log = get())
    }
}