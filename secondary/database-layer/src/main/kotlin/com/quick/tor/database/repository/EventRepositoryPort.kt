package com.quick.tor.database.repository

import com.quick.tor.database.commons.RequiresTransactionContext
import com.quick.tor.database.dbo.EventDBO

interface EventRepositoryPort {

    @RequiresTransactionContext
    suspend fun save(eventDBO: EventDBO): EventDBO

    @RequiresTransactionContext
    suspend fun delete(eventDBO: EventDBO)
}