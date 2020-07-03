package com.quick.tor.producer.dto

import com.quick.tor.usecases.user.model.UserEvent
import kotlinx.serialization.Serializable

@Serializable
data class UserMessageDTO(
    val id: String,
    val name: String,
    val idempotencyId: String,
    val correlationId: String?,
    val email: String
)

fun UserEvent.toMessageDTO(correlationId: String? = null): UserMessageDTO =
    UserMessageDTO(
        id = id.toString(),
        name = user.name,
        correlationId = correlationId,
        idempotencyId = user.idempotencyId.toString(),
        email = user.email
    )