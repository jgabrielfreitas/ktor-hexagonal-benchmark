package com.quick.tor.kafka

import com.quick.tor.common.toUUID
import com.quick.tor.usecases.user.model.User


data class UserMessageDTO(
    val name: String,
    val email: String,
    val idempotencyId: String
) {

    fun toModel() = User(
        name = name,
        email = email,
        idempotencyId = idempotencyId.toUUID()
    )
}