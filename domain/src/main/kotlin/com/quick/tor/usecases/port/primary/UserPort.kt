package com.quick.tor.usecases.port.primary

import com.quick.tor.usecases.user.model.User
import java.util.UUID

interface UserPort {
    suspend fun save(user: User): User
    suspend fun findById(id: UUID): User?
    suspend fun update(user: User): User?
}