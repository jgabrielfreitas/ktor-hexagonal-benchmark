package com.quick.tor.producer

import com.quick.tor.config.ApplicationConfig
import com.quick.tor.infrastructure.producer.producer
import com.quick.tor.producer.adapter.UserNotificationAdapter
import com.quick.tor.usecases.user.port.secondary.UserNotificationPort
import org.koin.dsl.module

val kafkaProducerModule = module(createdAtStart = true) {

    single{

        val kafkaProducerConfig = get<ApplicationConfig>().config.getConfig("kafka-producer")
        val bootstrapServers = kafkaProducerConfig.getString("bootstrapServers")
        val schemaUrl = kafkaProducerConfig.getString("schemaUrl")

        val producer = producer(bootstrapServers = bootstrapServers, schemaUrl = schemaUrl)

        KafkaProducerAdapter(
            producer = producer
        )
    }
    single<UserNotificationPort> { UserNotificationAdapter(producer = get(), logger = get()) }
}