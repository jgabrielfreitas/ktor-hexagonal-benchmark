package com.quick.tor.database.adapters

import com.quick.tor.RequiresTransactionContext
import com.quick.tor.database.dbo.toDbo
import com.quick.tor.database.repository.EventRepositoryPort
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.model.UserEvent
import com.quick.tor.usecases.user.port.secondary.UserEventDataAccessPort

class UserEventDataAccessAdapter(
    private val eventRepositoryPort: EventRepositoryPort,
    private val logger: Logger
): UserEventDataAccessPort {
    @RequiresTransactionContext
    override suspend fun save(userEvent: UserEvent): UserEvent {
        logger.info("trying to save user event: $userEvent")
        return eventRepositoryPort.save(userEvent.toDbo()).toModel()
    }

    @RequiresTransactionContext
    override suspend fun delete(userEvent: UserEvent) {
        logger.info("trying to delete user event: $userEvent")
        eventRepositoryPort.delete(userEvent.toDbo())
    }
}