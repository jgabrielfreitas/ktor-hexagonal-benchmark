package com.quick.tor.usecases.user.port.secondary

import com.quick.tor.usecases.user.model.UserEvent

interface UserEventDataAccessPort {
    suspend fun save(userEvent: UserEvent): UserEvent
    suspend fun delete(userEvent: UserEvent)
}