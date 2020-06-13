package com.quick.tor.usecases.port.secondary

import com.quick.tor.commons.exceptions.UserNotificationException
import com.quick.tor.usecases.user.model.UserEvent

interface UserNotificationPort {

    @Throws(UserNotificationException::class)
    fun notify(userEvent: UserEvent)
}