package com.quick.tor.database.repository

import com.quick.tor.database.commons.RequiresTransactionContext
import com.quick.tor.database.dbo.UserDBO

interface UserRepositoryPort {

    @RequiresTransactionContext
    suspend fun findByIdempotencyId(idempotencyId: String): UserDBO?

    @RequiresTransactionContext
    suspend fun findById(id: String): UserDBO?

    @RequiresTransactionContext
    suspend fun save(userDbo: UserDBO): UserDBO

    @RequiresTransactionContext
    suspend fun update(userDbo: UserDBO): UserDBO
}