package com.quick.tor

import com.quick.tor.usecases.port.primary.UserPort
import com.quick.tor.usecases.user.UserUseCase
import org.koin.dsl.module

val domainModule = module(createdAtStart = true) {
    single<UserPort> {
        UserUseCase(
            userDataAccessPort = get(),
            userEventDataAccessPort = get(),
            userNotificationPort = get(),
            log = get()
        )
    }
}
