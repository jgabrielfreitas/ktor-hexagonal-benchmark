package com.quick.tor.usecases.user.port.secondary

import com.quick.tor.usecases.user.model.UserEvent

interface UserNotificationPort {
    suspend fun notify(userEvent: UserEvent)
}