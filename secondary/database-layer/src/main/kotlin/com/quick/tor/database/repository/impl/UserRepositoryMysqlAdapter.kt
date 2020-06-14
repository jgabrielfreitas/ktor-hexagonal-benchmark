package com.quick.tor.database.repository.impl

import com.quick.tor.common.toUUID
import com.quick.tor.database.commons.DatabaseConnector
import com.quick.tor.database.dbo.UserDBO
import com.quick.tor.database.dbo.Users
import com.quick.tor.database.dbo.toUserDbo
import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.model.User
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import java.util.*

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
        log.info("finding by id: $id")
        return dbConnector.existingTransaction {
            Users.select { Users.id eq id.toUUID() }
        }.map { it.toUserDbo() }.singleOrNull()
    }

    override suspend fun save(userDbo: UserDBO): UserDBO {
        log.info("saving user: $userDbo")
        val userSavedId = dbConnector.existingTransaction { transaction ->
            val id = Users.insertAndGetId {
                fromDbo(it, userDbo)
            }

            transaction.commit()
            id
        }.value
        return userDbo.copy(id = userSavedId)
    }

    override suspend fun update(userDbo: UserDBO): UserDBO {
        log.info("updating user: $userDbo")
        dbConnector.existingTransaction { transaction ->
            Users.update(where = {Users.id eq userDbo.id}) {
                fromDbo(it, userDbo)
            }

            transaction.commit()
        }
        return userDbo
    }
}