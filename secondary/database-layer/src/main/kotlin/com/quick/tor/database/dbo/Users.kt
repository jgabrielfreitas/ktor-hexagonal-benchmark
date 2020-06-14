package com.quick.tor.database.dbo

import com.quick.tor.common.toUUID
import com.quick.tor.database.dbo.Users.email
import com.quick.tor.database.dbo.Users.id
import com.quick.tor.database.dbo.Users.idempotencyId
import com.quick.tor.database.dbo.Users.name
import com.quick.tor.database.dbo.Users.sentNotification
import com.quick.tor.usecases.user.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import java.util.*


object Users: Table("users") {
    val id = uuid("id").autoIncrement()
    var name = text("name")
    var email = text("email")
    var sentNotification = bool("sent_notification")
    val idempotencyId = text("idempotency_id")
}

data class UserDBO(
    val id: UUID?,
    val name: String,
    val email: String,
    var sentNotification: Boolean = false,
    val idempotencyId: UUID
) {
    fun toModel() =
        User(
            id = id,
            name = name,
            email = email,
            sentNotification = sentNotification,
            idempotencyId = idempotencyId
        )
}

fun ResultRow.toUserDbo() = UserDBO(
    id = getOrNull(id),
    name = get(name),
    email = get(email),
    sentNotification = get(sentNotification),
    idempotencyId = get(idempotencyId).toUUID()
)

fun ResultRow.toUserModel() = User(
    id = getOrNull(id),
    name = get(name),
    email = get(email),
    sentNotification = get(sentNotification),
    idempotencyId = get(idempotencyId).toUUID()
)

//
//fun User.toDbo() = UserDBO
////    UserDBO(
////        id = id.toString(),
////        name = name,
////        email = email,
////        sentNotification = sentNotification,
////        idempotencyId = idempotencyId.toString()
////    )