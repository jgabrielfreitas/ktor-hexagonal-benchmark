package com.quick.tor.usecases.user.port.secondary

import com.quick.tor.usecases.user.model.User
import java.util.UUID

interface UserDataAccessPort {
    suspend fun findById(id: UUID): User?
    suspend fun update(user: User): User
    suspend fun save(user: User): User
    suspend fun findByIdempotency(idempotencyId: UUID): User?
}