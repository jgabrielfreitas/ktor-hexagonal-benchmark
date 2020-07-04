package com.quick.tor.database.dbo

import com.quick.tor.common.fromJson
import com.quick.tor.common.toJson
import com.quick.tor.common.toUUID
import com.quick.tor.database.commons.extentions.StringIdTable
import com.quick.tor.usecases.user.model.User
import com.quick.tor.usecases.user.model.UserEvent
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.UUID

object Events: StringIdTable("events") {
    var jsonPayload = text("json_payload")
    var userId = text("user_id").references(Users.id)

    fun fromDbo(
        statement: InsertStatement<EntityID<String>>,
        eventDbo: EventDBO
    ) {
        statement[jsonPayload] = eventDbo.jsonPayload
        statement[userId] = eventDbo.userId
    }
}

data class EventDBO(
    val id: UUID?,
    val jsonPayload: String,
    val userId: String
) {
    fun toModel() = UserEvent(
        id = id,
        user = jsonPayload.fromJson()
    )
}
fun UserEvent.toDbo() = EventDBO(
    id = id,
    jsonPayload = user.toJson(),
    userId = user.id.toString()
)
