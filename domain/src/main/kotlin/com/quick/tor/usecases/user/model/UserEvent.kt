package com.quick.tor.usecases.user.model

import java.util.UUID

data class UserEvent(
    val id: UUID? = null,
    val user: User
)