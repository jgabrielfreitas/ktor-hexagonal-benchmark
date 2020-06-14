package com.quick.tor.database

import com.quick.tor.usecases.user.model.User
import org.jetbrains.exposed.sql.Table
import java.util.UUID


data class UserDBO(
    var id: String?,
    var name: String,
    var email: String,
    var sentNotification: Boolean,
    val idempotencyId: String
) : Table() {
    fun toModel() =
        User(
            id = UUID.fromString(id),
            name = name,
            email = email,
            sentNotification = sentNotification,
            idempotencyId = UUID.fromString(idempotencyId)
        )
}

fun User.toDbo() =
    UserDBO(
        id = id.toString(),
        name = name,
        email = email,
        sentNotification = sentNotification,
        idempotencyId = idempotencyId.toString()
    )