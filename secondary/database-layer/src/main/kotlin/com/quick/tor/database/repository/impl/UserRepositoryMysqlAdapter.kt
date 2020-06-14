package com.quick.tor.database.repository.impl

import com.quick.tor.database.commons.DatabaseConnector
import com.quick.tor.database.dbo.UserDBO
import com.quick.tor.database.dbo.Users
import com.quick.tor.database.dbo.toUserDbo
import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.log.Logger
import org.jetbrains.exposed.sql.select

class UserRepositoryMysqlAdapter(
    private val dbConnector: DatabaseConnector,
    val log: Logger
) : UserRepositoryPort {

    override suspend fun findByIdempotencyId(idempotencyId: String): UserDBO? {
        log.info("finding by idempotencyId: $idempotencyId")
        return dbConnector.existingTransaction {
            Users.select { Users.idempotencyId eq idempotencyId }
        }.map { it.toUserDbo() }.singleOrNull()
    }

    override suspend fun findById(id: String): UserDBO? {
        return dbConnector.
    }

    override suspend fun save(userDbo: UserDBO): UserDBO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(userDbo: UserDBO): UserDBO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}