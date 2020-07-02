package com.quick.tor.kafka

import com.quick.tor.common.toUUID
import com.quick.tor.usecases.user.model.User
import com.sksamuel.avro4k.AvroName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
@AvroName("UserDto")
data class UserMessageDto(
    @SerialName(value = "Name")
    @AvroName("Name")
    val Name: String,
    @Serializable
    val Email: String,
    @Serializable
    val IdempotencyId: String,
    @Serializable
    val CorrelationId: String?
) {

    fun toModel() = User(
        name = Name,
        email = Email,
        idempotencyId = IdempotencyId.toUUID()
    )
}