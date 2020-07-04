package com.quick.tor

import com.quick.tor.usecases.user.port.primary.UserPort
import com.quick.tor.usecases.user.UserUseCase
import org.koin.dsl.module

val domainModule = module(createdAtStart = true) {
    single<UserPort> {
        UserUseCase(
            userDataAccessPort = get(),
            log = get(),
            userNotificationPort = get(),
            userEventDataAccessPort = get()
        )
    }
}
