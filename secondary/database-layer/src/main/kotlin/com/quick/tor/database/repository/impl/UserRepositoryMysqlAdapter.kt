package com.quick.tor.database.repository.impl

import com.quick.tor.common.toUUID
import com.quick.tor.database.commons.DatabaseConnector
import com.quick.tor.database.dbo.UserDBO
import com.quick.tor.database.dbo.Users
import com.quick.tor.database.dbo.toUserDbo
import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.log.Logger
import io.ktor.util.getDigestFunction
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class UserRepositoryMysqlAdapter(
    private val dbConnector: DatabaseConnector,
    val log: Logger
) : UserRepositoryPort {

    override suspend fun findByIdempotencyId(idempotencyId: String): UserDBO? {
        log.info("finding by idempotencyId: $idempotencyId")
        return dbConnector.transaction {
            Users.select { Users.idempotencyId eq idempotencyId }.firstOrNull()
        }?.toUserDbo()
    }

    override suspend fun findById(id: String): UserDBO? {
        log.info("finding by id: $id")
        return dbConnector.transaction {
            Users.select { Users.id eq id }.firstOrNull()
        }?.toUserDbo()
    }

    override suspend fun save(userDbo: UserDBO): UserDBO {
        log.info("saving user: $userDbo")
        val userSavedId = dbConnector.transaction { transaction ->

            val id = Users.insertAndGetId {
                fromDbo(it, userDbo)
            }

            transaction.commit()
            id
        }.value.toUUID()
        return userDbo.copy(id = userSavedId)
    }

    override suspend fun update(userDbo: UserDBO): UserDBO {
        log.info("updating user: $userDbo")
        dbConnector.transaction { transaction ->
            Users.update(where = { Users.id eq userDbo.id.toString() }) {
                fromDbo(it, userDbo)
            }

            transaction.commit()
        }
        return userDbo
    }
}