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
    @AvroName("Name")
    val name: String,
    @AvroName("IdempotencyId")
    val idempotencyId: String,
    @AvroName("CorrelationId")
    val correlationId: String?,
    @AvroName("Email")
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