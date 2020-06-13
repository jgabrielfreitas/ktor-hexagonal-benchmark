package com.quick.tor.usecases.port.secondary

import com.quick.tor.usecases.user.model.User
import java.util.UUID

interface UserDataAccessPort {
    fun findById(id: UUID): User?
    fun update(user: User): User
    fun save(user: User): User
    fun findByIdempotency(idempotencyId: UUID): User?
}