package com.quick.tor.controller.user.dto

import com.quick.tor.usecases.user.model.User
import java.util.UUID

class UserDTO(

    var id: UUID? = null,

    val name: String = "",

    val email: String = "",

    var sentNotification: Boolean = false,

    val idempotencyId: UUID

) {
    fun toModel() = User(
        id = id,
        name = name,
        email = email,
        sentNotification = sentNotification,
        idempotencyId = idempotencyId
    )

    fun withId(id: UUID): UserDTO {
        this.id = id
        return this
    }
}

fun User.toDto(): UserDTO =
    UserDTO(
        id = id,
        name = name,
        email = email,
        sentNotification = sentNotification,
        idempotencyId = idempotencyId
    )