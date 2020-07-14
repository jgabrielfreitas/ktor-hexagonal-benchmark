package com.quick.tor.database.adapters

import com.quick.tor.RequiresTransactionContext
import com.quick.tor.database.dbo.toDbo
import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.port.secondary.UserDataAccessPort
import com.quick.tor.usecases.user.model.User
import java.util.UUID

class UserDataAccessAdapter(
    val userRepositoryPort: UserRepositoryPort,
    val log: Logger
): UserDataAccessPort {

    @RequiresTransactionContext
    override suspend fun findById(id: UUID): User? {
        log.info("finding by id $id")
        return userRepositoryPort.findById(id.toString())?.toModel()
    }

    @RequiresTransactionContext
    override suspend fun update(user: User): User {
        log.info("trying to update $user")
        return userRepositoryPort.update(user.toDbo()).toModel()
    }

    @RequiresTransactionContext
    override suspend fun save(user: User): User {
        log.info("trying to save $user")
        return userRepositoryPort.save(user.toDbo()).toModel()
    }

    @RequiresTransactionContext
    override suspend fun findByIdempotency(idempotencyId: UUID): User? {
        return userRepositoryPort.findByIdempotencyId(idempotencyId.toString())?.toModel()
    }
}