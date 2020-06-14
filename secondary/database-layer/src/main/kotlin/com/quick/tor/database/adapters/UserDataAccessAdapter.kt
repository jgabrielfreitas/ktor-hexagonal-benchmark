package com.quick.tor.database.adapters

import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.port.secondary.UserDataAccessPort
import com.quick.tor.usecases.user.model.User
import java.util.UUID

class UserDataAccessAdapter(
    val userRepositoryPort: UserRepositoryPort,
    val log: Logger
): UserDataAccessPort {

    override suspend fun findById(id: UUID): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(user: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun save(user: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun findByIdempotency(idempotencyId: UUID): User? {
        return userRepositoryPort.findByIdempotencyId(idempotencyId.toString())?.toModel()
    }
}