package com.quick.tor.usecases.port.primary

import com.quick.tor.usecases.user.model.User
import java.util.UUID

interface UserPort {
    fun save(user: User): User
    fun findById(id: UUID): User?
    fun update(user: User): User?
}