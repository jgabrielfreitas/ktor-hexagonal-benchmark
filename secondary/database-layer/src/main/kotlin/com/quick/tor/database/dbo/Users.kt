package com.quick.tor.database.dbo

import com.quick.tor.common.toUUID
import com.quick.tor.database.commons.extentions.StringIdTable
import com.quick.tor.database.dbo.Users.email
import com.quick.tor.database.dbo.Users.id
import com.quick.tor.database.dbo.Users.idempotencyId
import com.quick.tor.database.dbo.Users.name
import com.quick.tor.database.dbo.Users.sentNotification
import com.quick.tor.usecases.user.model.User
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import java.util.*


object Users : StringIdTable("users") {
    var name = text("name")
    var email = text("email")
    var sentNotification = bool("sent_notification")
    var idempotencyId = text("idempotency_id")

    fun fromDbo(
        statement: InsertStatement<EntityID<String>>,
        userDbo: UserDBO
    ) {
        statement[name] = userDbo.name
        statement[email] = userDbo.email
        statement[sentNotification] = userDbo.sentNotification
        statement[idempotencyId] = userDbo.idempotencyId.toString()
    }

    fun fromDbo(
        statement: UpdateStatement,
        userDbo: UserDBO
    ) {
        statement[name] = userDbo.name
        statement[email] = userDbo.email
        statement[sentNotification] = userDbo.sentNotification
        statement[idempotencyId] = userDbo.idempotencyId.toString()
    }
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

fun User.toDbo() =
    UserDBO(
        id = id,
        name = name,
        email = email,
        sentNotification = sentNotification,
        idempotencyId = idempotencyId
    )

fun ResultRow.toUserDbo() = UserDBO(
    id = getOrNull(id)?.value?.toUUID(),
    name = get(name),
    email = get(email),
    sentNotification = get(sentNotification),
    idempotencyId = get(idempotencyId).toUUID()
)
