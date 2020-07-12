package com.quick.tor.usecases.user

import com.quick.tor.usecases.user.model.User
import com.quick.tor.usecases.user.model.UserEvent
import kotlinx.coroutines.CoroutineScope
import java.util.UUID
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

private val uuid: UUID = UUID.fromString("003b9143-d101-4209-8243-c6ef65ccca78")
val idempotencyId = uuid

val savedUser = User(
    id = uuid,
    name = "João",
    email = "joao@cora.com.br",
    idempotencyId = idempotencyId
)

val user: User = User(
    id = null,
    name = "João",
    email = "joao@cora.com.br",
    idempotencyId = idempotencyId
)

val event = UserEvent(user = savedUser)

fun <T> runWithCoroutineTest(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T): Unit {
    block
    return
}

