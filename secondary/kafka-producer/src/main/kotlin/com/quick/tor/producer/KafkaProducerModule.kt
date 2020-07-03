package com.quick.tor.producer

import com.quick.tor.producer.adapter.UserNotificationAdapter
import com.quick.tor.usecases.user.port.secondary.UserNotificationPort
import org.koin.dsl.module

val kafkaProducerModule = module(createdAtStart = true) {

    single<UserNotificationPort> { UserNotificationAdapter(logger = get()) }
}