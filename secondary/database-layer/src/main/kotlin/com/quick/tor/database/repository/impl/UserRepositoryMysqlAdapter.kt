package com.quick.tor.database.repository.impl

import com.quick.tor.common.toUUID
import com.quick.tor.database.commons.insertOrUpdate
import com.quick.tor.database.commons.TransactionService
import com.quick.tor.database.dbo.UserDBO
import com.quick.tor.database.dbo.Users
import com.quick.tor.database.dbo.toUserDbo
import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.log.Logger
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class UserRepositoryMysqlAdapter(
    private val transactionService: TransactionService,
    val log: Logger
) : UserRepositoryPort {

    override suspend fun findByIdempotencyId(idempotencyId: String): UserDBO? {
        log.info("finding by idempotencyId: $idempotencyId")
        return transactionService.transaction {
            Users.select { Users.idempotencyId eq idempotencyId }.firstOrNull()
        }?.toUserDbo()
    }

    override suspend fun findById(id: String): UserDBO? = transactionService.transaction {
        log.info("finding by id: $id")
        return@transaction Users.select { Users.id eq id }.firstOrNull()?.toUserDbo()
    }

    override suspend fun save(userDbo: UserDBO): UserDBO = transactionService.transaction{
        log.info("saving user: $userDbo")
        val id = Users.insertAndGetId {
            fromDbo(it, userDbo)
        }.value.toUUID()
        return@transaction userDbo.copy(id = id)
    }

    override suspend fun update(userDbo: UserDBO): UserDBO = transactionService.transaction {
        log.info("updating user: $userDbo")
            Users.update(where = { Users.id eq userDbo.id.toString() }) {
                fromDbo(it, userDbo)
            }
        return@transaction userDbo
    }
}