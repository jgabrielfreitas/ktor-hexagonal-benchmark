package com.quick.tor.kafka

import com.github.avrokotlin.avro4k.AvroName
import com.quick.tor.common.toUUID
import com.quick.tor.usecases.user.model.User
import kotlinx.serialization.Serializable

@Serializable
@AvroName("UserDto")
data class UserMessageDto(
    @AvroName("id")
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
) {

    fun toModel() = User(
        name = name,
        email = email,
        idempotencyId = idempotencyId.toUUID()
    )
}