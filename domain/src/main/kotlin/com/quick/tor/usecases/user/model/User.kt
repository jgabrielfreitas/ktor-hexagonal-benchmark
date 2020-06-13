package com.quick.tor.usecases.user.model

import java.util.UUID

data class User(
    val id: UUID? = null,
    val name: String,
    val email: String,
    var sentNotification: Boolean = false,
    val idempotencyId: UUID
) {
    fun withSendNotificationValidated(): User {
        sentNotification = true
        return this
    }
}