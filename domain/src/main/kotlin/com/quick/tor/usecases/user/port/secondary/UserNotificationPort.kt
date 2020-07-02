package com.quick.tor.usecases.user.port.secondary

import com.quick.tor.usecases.user.model.UserEvent

interface UserNotificationPort {
    fun notify(userEvent: UserEvent)
}