package com.quick.tor.usecases.user.port.secondary

import com.quick.tor.usecases.user.model.UserEvent

interface UserEventDataAccessPort {
    fun save(userEvent: UserEvent): UserEvent
    fun delete(userEvent: UserEvent)
}