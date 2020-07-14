package com.quick.tor.database.repository.impl

import com.quick.tor.RequiresTransactionContext
import com.quick.tor.common.toUUID
import com.quick.tor.database.commons.DatabaseConnector
import com.quick.tor.database.commons.TransactionService
import com.quick.tor.database.dbo.EventDBO
import com.quick.tor.database.dbo.Events
import com.quick.tor.database.repository.EventRepositoryPort
import com.quick.tor.log.Logger
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId

class EventRepositoryMysqlAdapter(
    private val transactionService: TransactionService,
    private val log: Logger
) : EventRepositoryPort {

    @RequiresTransactionContext
    override suspend fun save(eventDBO: EventDBO): EventDBO {
        log.info("saving event: $eventDBO")
        val eventSavedId = transactionService.transaction {

            val id = Events.insertAndGetId {
                fromDbo(it, eventDBO)
            }
            id
        }.value.toUUID()
        return eventDBO.copy(id = eventSavedId)
    }

    @RequiresTransactionContext
    override suspend fun delete(eventDBO: EventDBO) {
        log.info("deleting event: $eventDBO")
        transactionService.transaction {
            Events.deleteWhere { Events.id eq eventDBO.id.toString() }
            log.info("event deleted $eventDBO")
        }
    }
}