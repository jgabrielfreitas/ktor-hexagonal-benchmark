package com.quick.tor.database.repository.impl

import com.quick.tor.common.toUUID
import com.quick.tor.database.commons.DatabaseConnector
import com.quick.tor.database.dbo.EventDBO
import com.quick.tor.database.dbo.Events
import com.quick.tor.database.repository.EventRepositoryPort
import com.quick.tor.log.Logger
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId

class EventRepositoryMysqlAdapter(
    private val dbConnector: DatabaseConnector,
    private val log: Logger
) : EventRepositoryPort {
    override suspend fun save(eventDBO: EventDBO): EventDBO {
        log.info("saving event: $eventDBO")
        val eventSavedId = dbConnector.transaction { transaction ->

            val id = Events.insertAndGetId {
                fromDbo(it, eventDBO)
            }

            transaction.commit()
            id
        }.value.toUUID()
        return eventDBO.copy(id = eventSavedId)
    }

    override suspend fun delete(eventDBO: EventDBO) {
        log.info("deleting event: $eventDBO")
        dbConnector.transaction { transaction ->
            Events.deleteWhere { Events.id eq eventDBO.id.toString() }
            transaction.commit()
            log.info("event deleted $eventDBO")
        }
    }
}