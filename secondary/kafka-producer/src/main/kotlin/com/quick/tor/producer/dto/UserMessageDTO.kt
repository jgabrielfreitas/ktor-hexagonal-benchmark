package com.quick.tor.producer.dto

import com.quick.tor.usecases.user.model.UserEvent
import com.sksamuel.avro4k.AvroName
import com.sksamuel.avro4k.AvroNamespace
import kotlinx.serialization.Serializable

@Serializable
@AvroName("UserDto")
@AvroNamespace("dto")
data class UserMessageDTO(
    val id: String,
    @AvroName("name")
    val name: String,
    @Serializable
    @AvroName("email")
    val email: String,
    @Serializable
    @AvroName("idempotencyId")
    val idempotencyId: String,
    @Serializable
    @AvroName("correlationId")
    val correlationId: String?
)

fun UserEvent.toMessageDTO(correlationId: String? = null): UserMessageDTO =
    UserMessageDTO(
        id = id.toString(),
        name = user.name,
        correlationId = correlationId,
        idempotencyId = user.idempotencyId.toString(),
        email = user.email
    )